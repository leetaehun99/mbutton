package com.doo.mbutton.common.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doo.mbutton.management.model.FileVo;


public class FileUtil {

	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");

	// 파일 확장자 검사(청구서 검사) 
	@Value("#{applicationProp['edi.file.extension']}")
	private String approveFile;
	
	// 파일 확장자 검사(파일 검사) 
	@Value("#{applicationProp['file.extension']}")
	private String approveFile2;

	// 업로드 경로
	@Value("#{applicationProp['system.upload.path']}")
	private String uploadRrootPath;

	// 다운로드 경로
	@Value("#{applicationProp['system.download.path']}")
	private String downloadRrootPath;
	/**
	 * 파일 업로드
	 * 
	 * @param request
	 * @param fileVo
	 * @return Map<String, Object>
	 */
	public Map<String, Object> fileUpload(HttpServletRequest request, FileVo fileVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("RESULT", "N");

		List<Map<String, String>> uploadFileList = null; // 업로드 파일 정보

		try {
			// Request가 Multipart인지 검사
			if (!(ServletFileUpload.isMultipartContent(request))) {
				result.put("ERROR_MSG", "NOT MULTIPART");
				return result;
			}
			// MultipartHttpServletRequest로 캐스팅 가능한지 검사
			if (!(request instanceof MultipartHttpServletRequest)) {
				result.put("ERROR_MSG", "NOT CASTING");
				return result;
			}
			// 업로드할 폴더 생성
			String uploadPath = makeUploadDir(fileVo.getUploadPath());
			String rootPath = this.uploadRrootPath ;
			if (uploadPath == null) {
				result.put("ERROR_MSG", "디렉터리 생성 오류");
				return result;
			}
			// 업로드할 파일 추출
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			if (files != null) {
				uploadFileList = new ArrayList<Map<String, String>>();
				Map<String, String> fileInfo = null;
				Entry<String, MultipartFile> entry;
				MultipartFile file;
				String fileFullPath = ""; // 파일 전체 경로(파일 경로 + 파일명)
				String fileName = ""; // 파일명
				// 파일정보 추출
				Iterator<Entry<String, MultipartFile>> iterator = files.entrySet().iterator();

				boolean isSuccess = true;
				while (iterator.hasNext()) {
					entry = iterator.next();
					file = entry.getValue();
					fileName = file.getOriginalFilename(); // 업로드 한 파일의 실제 이름
					String pathType = fileVo.getPathType(); 
					if (fileName != null && !fileName.equals("")) {
						
						if (!checkFileExt(fileName, pathType)) {
							isSuccess = false;
							break;
						}
						isSuccess = true;
					}
				}
				if (!isSuccess) {
					if(fileVo.getPathType().equals("R")) {
						result.put("ERROR_MSG", "허용되지 않는 파일 확장자 (SAM파일 확장자 : *.GHP,*.B00,*.B01,*.B60,*.B61,*.C00,*.C01,*.C60,*.C61)");
					}else {
						result.put("ERROR_MSG", "허용되지 않는 파일 확장자 (파일 확장자 : *.HWP,*.XLS,*.XLSX,*.PDF)");
					}
					return result;
				}
				
				
				File tempFile = null;
				
				// 파일 업로드
				iterator = files.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = iterator.next();
					file = entry.getValue();
					fileName = file.getOriginalFilename(); // 업로드 한 파일의 실제 이름
					if (fileName != null && !fileName.equals("")) {
						// 파일정보 담기
						fileInfo = new HashMap<String, String>();
						
						// 파일명 변경 (공백 => "_", 파일명 끝에 millisecond)
						String newFileName = fileName+"_"+ System.currentTimeMillis();

						// 파일 업로드
						fileFullPath = uploadPath + File.separator + newFileName;
						tempFile = new File(rootPath+File.separator+fileFullPath);
						tempFile.setLastModified(System.currentTimeMillis());
						file.transferTo(tempFile);
						
						
						fileInfo.put("paramNm", file.getName()); // 파라미터 이름
						fileInfo.put("orgFileName", fileName); // 기존 파일명
						fileInfo.put("fileName", newFileName); // 업로드 한 파일명
						fileInfo.put("uploadPath", uploadPath); // 업로드 경로
						//fileInfo.put("uploadPathByUrl",uploadUrl + fileVo.getUploadDir() + "/"); // 업로드 경로 (URL )
						fileInfo.put("filePath", fileFullPath); // 파일 경로
						fileInfo.put("fileSize", Long.toString(file.getSize())); // 파일 사이즈
						fileInfo.put("crtDtLong", System.currentTimeMillis() + "");
						uploadFileList.add(fileInfo);
					}
				}
			}

			result.put("RESULT", "Y");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("ERROR_MSG", "오류 발생");
		}
		result.put("uploadFileList", uploadFileList);
		return result;
	}

	/**
	 * File Download
	 * 
	 * @param response
	 * @param path
	 * @param sysFileName
	 * @param orgFileName
	 * @throws IOException
	 * @throws Exception
	 */
	public void download(HttpServletRequest request,HttpServletResponse response,FileVo fileVo) throws IOException, Exception {

		String userAgent = request.getHeader("User-Agent");
		boolean agent = false;
		if(userAgent.indexOf("rv:11") > -1 ){
			agent=true;
		}else if(userAgent.indexOf("Firefox") > -1 ){
			agent=true;
		}else if(userAgent.indexOf("Chrome") > -1 ){
			agent=true;
		}else{
			agent=false;
		}
		String orgFileName = null;
		String path = fileVo.getFilePath();
		
		logger.info("userAgent = " + userAgent);
		logger.info("1orgFileName = " + fileVo.getOrgFileName());
		logger.info("AGENT = " + agent);
		if(agent){
			orgFileName =  URLEncoder.encode(fileVo.getOrgFileName(),"UTF-8").replaceAll("\\+", "%20"); 
        } else {
        	orgFileName = new String(fileVo.getOrgFileName().getBytes("UTF-8"));
             
        }
		logger.info("2orgFileName = " + orgFileName);
		
		File file = new File(uploadRrootPath+File.separator+path );
		//response.setContentType("aapplication/x-msdownload;charset=utf-8");
		response.setContentType("application/octet-stream;charset=utf-8");
		
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary");

		FileCopyUtils.copy(getFileToByte(file), response.getOutputStream());
	}
	
	/**
	 * File Download
	 * 
	 * @param response
	 * @param path
	 * @param sysFileName
	 * @param orgFileName
	 * @throws IOException
	 * @throws Exception
	 */
	public void download2(HttpServletRequest request,HttpServletResponse response,FileVo fileVo) throws IOException, Exception {
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie =userAgent.indexOf("MISE") > -1 ;
		String orgFileName = null;
		String path = fileVo.getFilePath();
		if(ie){
			orgFileName = URLEncoder.encode(fileVo.getOrgFileName(), "utf-8");
        } else {
        	orgFileName = new String(fileVo.getOrgFileName().getBytes("utf-8"));
             
        }
		
		File file = new File(path );

		response.setContentType("aapplication/x-msdownload");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary");

		FileCopyUtils.copy(getFileToByte(file), response.getOutputStream());
	}

	/**
	 * File -> byt[]
	 * 
	 * @param file
	 * @return byte[]
	 * @throws Exception
	 */
	private byte[] getFileToByte(File file) throws Exception {
		InputStream is = new FileInputStream(file);
		long length = file.length();

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely rea file "
					+ file.getName());
		}

		is.close();
		return bytes;
	}
	/**
	 * 파일업로드 디렉터리 생성
	 * 
	 * @param rootPath
	 * @param uploadDir
	 * @return String
	 */
	private String makeUploadDir(String uploadDir)
			throws Exception {
		String path = null;
		if (uploadRrootPath != null && !uploadRrootPath.equals("") && uploadDir != null && !uploadDir.equals("")) {
			File saveDir = new File(uploadRrootPath + File.separator + uploadDir);
			if (!saveDir.exists() || !saveDir.isDirectory()) {
				saveDir.mkdirs();
			}
			path = uploadDir;
		}
		return path;
	}
	
	/**
	 * 파일다운로드 디렉터리 생성
	 * 
	 * @param rootPath
	 * @param uploadDir
	 * @return String
	 */
	public String makeDownloadDir(String uploadDir)
			throws Exception {
		String path = null;
		if (downloadRrootPath != null && !downloadRrootPath.equals("") && uploadDir != null && !uploadDir.equals("")) {
			File saveDir = new File(downloadRrootPath + File.separator + uploadDir);
			if (!saveDir.exists() || !saveDir.isDirectory()) {
				saveDir.mkdirs();
			}
			path = saveDir.getPath();
		}
		return path;
	}

	private boolean checkFileExt( String fileName, String pathType)
			throws Exception {

		// 2. 확장자 검사
		String fileExt = "";
		boolean result = false;
		String arrExt[];
		if(pathType.equals("R")) {
			arrExt = approveFile.split(",");
			
		}else {
			arrExt = approveFile2.split(",");
		}
		
		int i = -1;
		if ((i = fileName.lastIndexOf(".")) != -1) {
			fileExt = fileName.substring(i).toUpperCase().replace(".",""); // 파일 확장자 추출
			for (int j = 0; j < arrExt.length; j++) {
				if (arrExt[j].equals(fileExt)) {
					// 허용된 확장자
					result = true;
					break;
				} 
			}
			if(!result) result=false;
		} else {
			// 확장자 없음
			result = false;
		}
		return result;
	}
	
}
