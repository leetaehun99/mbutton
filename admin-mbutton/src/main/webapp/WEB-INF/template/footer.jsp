<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script>
$(document).ready(function() {
	var msg = "${alertMsg}";
	if(msg!="") alert(msg);
	
});
</script>	
<div class="spaceBox40"></div>
<div class="foot">
	<div class="footInner">
	<img src="/resources/img/foot.png">
	</div>
</div>
<div id="loadingDiv" style="display:none;width:295px;" >
	<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
		<div class="xboxcontent">
		<img src='/resources/img/loading_001.gif'/>
		</div>
	<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>

<div id="updateMemberContener" style="display:none;cursor:default;">
	<form id="updateUserForm" name="updateUserForm" action="/user/updateUser.json"method="post">
		<input type="hidden" id="userPwd" name="userPwd" value=""/>
		<input type="hidden" id="zip" 	  name="zip"     value=""/>	
		<input type="hidden" id="phone"   name="phone" value=""/>	
		<input type="hidden" id="email"   name="email" value=""/>	
		<div style="border: 0px; height: 37px;">
			<img src="/resources/img/modify_bar_2.png" /><img src="/resources/img/mid_bar.png" /><img src="/resources/img/close_bar.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
		</div>
		<div style="padding: 20px; background: #f8f8f8; height: 480px;">
			<table class="table7 table7_main" style="border-left: 1px solid #b5b5b5;border-bottom: 1px solid #b5b5b5;">
				<tbody>
					<tr>
						<th style="text-align:left; padding-left:30px;">사용자ID</th>
						<td style="text-align:left; padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="userId" name="userId" value="" size="20" height="28px" maxlength="14" readonly/>
						</td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">패스워드</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="password" id="userPwd1" name="userPwd1" value="" size="14"  maxlength="20" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">패스워드 확인</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="password" id="userPwd2" name="userPwd2" value="" size="14"  maxlength="20" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">요양기관기호</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="hspId" name="hspId" value="" size="20"  maxlength="8" height="28px" readonly />
						</td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">요양기관명칭</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="hspNm" name="hspNm" value="" size="20"  maxlength="20" height="28px" readonly/>
						</td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">병원장명</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="hspDtNm" name="hspDtNm" value="" size="20"  maxlength="8" height="28px" />
						</td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">의사면허번호</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="licenNum" name="licenNum" value="" size="20"  maxlength="5" height="28px" readonly/>
						</td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">병원주소</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
						<input type="text" id="zip1" value="" size="3"  maxlength="3" height="28px" />-<input type="text" id="zip2" value="" size="3"  maxlength="3"/>&nbsp;&nbsp;<input type="text" id="addr" name="addr" value="" size="20"  maxlength="50" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">병원연락처</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
							<ct:code name="phone1" type="select" groupCode="00049" selectCode="" />-
							<input type="text" id="phone2" name="phone2" value="" size="4"  height="28px"  maxlength="4"/>-<input type="text" id="phone3" name="phone3" value="" size="4" height="28px" maxlength="4"/></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">이메일</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
							<input type="text" id="email1" name="email1" value="" size="20" height="28px" maxlength="20"/>@
							<ct:code name="email2" type="select" groupCode="00050" selectCode="" />
						</td>
					</tr>
					<tr>
			        <th style="text-align:left; padding-left:30px;">메시지 레벨</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2">
				  			 <ct:code name="msgLev" type="select" groupCode="00068"  selectCode="${user.msgLev}"/>
				   		</td>
					</tr>
				</tbody>
			</table>
			<div style="padding-top:30px;padding-left: 98px;"><a id="updateUser" class="trUpdaterFocus" onclick="javascript:updateUser();"></a></div>
		</div>
	</form>	
</div>