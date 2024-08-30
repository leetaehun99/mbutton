package com.doo.mbutton.common.helper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.util.Base64;

public class EncUtil {
	private static Log logger = LogFactory.getLog("MBUTTON.INFO");

	public static String getSSEnc(String value,byte[] bKey){
		String rtnEncValue = "";
		
		if( value == null )	return "";
		if( "".equals(value) )	return "";
		if( "".equals(bKey) )	return "";
		try{
			rtnEncValue = Encipher(value, bKey );
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return rtnEncValue;
	}

	public static String getSSDec(String value,byte[] bKey){
		String rtnDecValue = "";
		
		if( value == null )	return "";
		if( "".equals(value) )	return "";
		if( "".equals(bKey) )	return "";

		try{
			rtnDecValue = Decipher(value, bKey );
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return rtnDecValue;
	}
	
	public static String Encipher(String value, byte pbUserKey[]) {
		String tResult = null;
		int pdwRoundKey[] = new int[32];

		byte pbData[]   = new byte[16];     
		byte pbCipher[]   = new byte[16];
		byte btValue[]   = value.getBytes();

		int tLoopCnt = btValue.length/16 + 1;
		byte pbCipherResult[] = new byte[16 * tLoopCnt];

		SEED_KISA.SeedRoundKey(pdwRoundKey, pbUserKey);

		int i = 0 ; 
		do{
			clearToByte(pbData);
			copyToByte(btValue, i*16, pbData, 0, 16);

			SEED_KISA.SeedEncrypt(pbData, pdwRoundKey, pbCipher);

			copyToByte(pbCipher, 0, pbCipherResult, i*16, 16);

			i++;
		}
		while(i < tLoopCnt);

		tResult = new String(Base64.encodeBase64(pbCipherResult));

		return tResult;		
	}
	public static String Decipher(String value,byte pbUserKey[]){
		String tResult = null;

		int pdwRoundKey[] = new int[32];

		byte pbPlain[]    = new byte[16];

		byte btValue[]   = Base64.decodeBase64(value);
		byte bt16Value[]   = new byte[16];

		int tLoopCnt = ((int)btValue.length/16);

		byte pbPlainResult[] = new byte[16 * tLoopCnt];

		clearToByte(pbPlainResult);

		SEED_KISA.SeedRoundKey(pdwRoundKey, pbUserKey);

		int i = 0 ; 
		do{
			clearToByte(bt16Value);
			copyToByte(btValue, i*16, bt16Value, 0, 16);

			SEED_KISA.SeedDecrypt(bt16Value, pdwRoundKey, pbPlain);

			copyToByte(pbPlain, 0, pbPlainResult, i*16, 16);

			i++;
		}
		while(i < tLoopCnt);


		tResult = new String(pbPlainResult).trim();

		return tResult;
	}	



	public static void copyToByte(byte[] src, byte[] target){
		int i;
		for(i = 0; i < src.length && i < target.length; i++){
			target[i] = src[i];
		}
		for(; i < target.length; i++){
			target[i] = (byte)0;
		}

	}


	private static void clearToByte(byte[] src){
		int i;
		for(i = 0; i < src.length; i++){
			src[i] = (byte)0;
		}

	}

	private static void copyToByte(byte[] src, int srtIndex, byte[] target, int tgt, int langth){
		int i;
		langth = (src.length - srtIndex < langth) ? src.length - srtIndex : langth;
		for(i = 0; i < langth; i++){
			target[tgt+i] = src[srtIndex+i];
		}

	}	
	
	public static void main(String[] args)
	{
		String Key = "kj32h498y49328h4324h329";
		byte [] bKey =Key.getBytes();

		String value = "6302181063426";

		System.out.print(value + "\n\n");

		String ret = Encipher(value, bKey);


		ret = Decipher(ret, bKey);

//		ntUizfe2ivhoB0vYAaSbtA==
//		Wv3AF0MtwRySFHe0P5pevA==
//		+ESdeY/1kBegimWmYMMA==
		
		System.out.print(ret + "\n\n");

	}
}