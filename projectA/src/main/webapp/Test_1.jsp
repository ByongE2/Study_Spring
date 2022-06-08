<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="login">
  <fieldset>
   <legend>회원로그인</legend>
 	<!--<span class="title">ID</span>-->
    <label class="id ePlaceholder" title="id">
     <input id="member_id" name="member_id" fw-filter="isFill" fw-label="아이디" fw-msg="" class="inputTypeText" placeholder="id" value="" type="text">
    </label>
 	
 	<!--<span class="title">Password</span>-->
    <label class="password ePlaceholder" title="Password">
 	 <input id="member_passwd" name="member_passwd" fw-filter="isFill&amp;isMin[4]&amp;isMax[16]" fw-label="패스워드"	fw-msg="" autocomplete="off" value="" type="password" placeholder="Password">
    </label> 
    <a href="#none" class="btnLogin" onclick="MemberAction.login('member_form_3656914302'); return false;">Log In</a>
    <p class="security">
 			<!--<img src="//img.echosting.cafe24.com/design/skin/default/member/ico_access.gif" alt="보안접속" /> 보안접속 -->
    </p>
    <ul class="snsArea">
    <li class="naver "><a href="#none" onclick="MemberAction.snsLogin('naver', '%2Findex.html')">NAVER Login</a></li>
    <li class="facebook displaynone"><a href="#none" onclick="">FACEBOOK Login</a></li>
 	<li class="google displaynone"><a href="#none" onclick="">GOOGLE Login</a></li>
 	<li class="kakao "><a href="#none" onclick="MemberAction.snsLogin('kakao', '%2Findex.html')">KAKAO Login</a></li>
 	<li class="line displaynone"><a href="#none" onclick="">LINE Login</a></li>
 </ul>
 <ul>
 	<li><a href="/member/id/find_id.html">Search id</a></li>
 	<li><a href="/member/passwd/find_passwd_info.html">Search password</a></li>
 </ul>
 <a href="/member/join.html" class="btnJoin">Register</a>
 <p class="link displaynone" id="noMemberWrap">
 	<!--비회원님도 상품구매가 가능하나 다양한<br />회원혜택에서 제외됩니다.-->
 	<a href="" class="btnGuest" onclick="">Guest Checkout</a>
 </p>
 </fieldset>
 </div>

</body>
</html>

