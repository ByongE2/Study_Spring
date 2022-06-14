<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %> <!-- 세션을 "생성"하지 않겠다. -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
       * { box-sizing:border-box; }
       a { text-decoration: none; }
        form {
            width:400px;
            height:500px;
            display : flex;
            flex-direction: column;
            align-items:baseline;
            position : absolute;
            top:30%;
            left:50%;
            transform: translate( -50%, -50%) ;
        }
        input[type='text'], input[type='password'] {
            width: 250px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        button {
            background-color: black;
            color : white;
            width:250px;
            height:40px;
            font-size: 15px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        #title {
            font-size : 20px;
            margin: 40px 0 30px 0;
        }
        #msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <form action="<c:url value='/login2/login'/>" method="post" onsubmit="return formCheck(this);">
        <h3 id="title">Log in</h3>
        <div id="msg">
	    <c:if test="${not empty param.msg}">
		<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
	    </c:if>        
	</div>
        <input type="text" name="id" value="${cookie.id.value}" placeholder="id" autofocus="autofocus" borde>
        <input type="password" name="pwd" placeholder="Password">
        <input type="hidden" name="toURL" value="${param.toURL}">
        <button>Log in</button>
            <div class="naver "><a href="#none" onclick="MemberAction.snsLogin('naver', '%2Findex.html')">NAVER Login</a></div>
            <div class="kakao "><a href="#none" onclick="MemberAction.snsLogin('kakao', '%2Findex.html')">KAKAO Login</a></div><br>
        <div>
            <input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}> 아이디 기억<br>
            <a href="#none">Search id</a><br>
            <a href="#none">Search password</a>
        </div>    
            <button>Register</button>
        <script>
            function formCheck(frm) {
                 let msg ='';
     
                 if(frm.id.value.length==0) {
                     setMessage('id를 입력해주세요.', frm.id);
                     return false;
                 }
     
                 if(frm.pwd.value.length==0) {
                     setMessage('password를 입력해주세요.', frm.pwd);
                     return false;
                 }
                 return true;
            }
     
            function setMessage(msg, element){
                 document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
     
                 if(element) {
                     element.select();
                 }
            }
        </script>
    </form>
</body>
</html>