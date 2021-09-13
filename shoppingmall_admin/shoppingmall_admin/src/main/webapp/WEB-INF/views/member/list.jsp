<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/table_style.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        $(function(){
            $("#member").addClass("current");
            $(".member_del").click(function(){
                // alert("삭제");
                let seq = $(this).attr("data-seq");
                // alert(seq);
                if(confirm("삭제하시겠습니까?")) {
                    $.ajax({
                        type:"delete",
                        url:"/member/delete?seq="+seq,
                        success:function(r) {
                            alert(r.message);
                            location.reload();
                        }
                    })
                }
            });
        })
    </script>
</head>
<body class="list">
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <h1>회원관리</h1>
    <table class="member_table">
        <thead>
            <td>번호</td>
            <td>아이디</td>
            <td>이름</td>
            <td>이메일</td>
            <td>주소</td>
            <td>생일</td>
            <td>성별</td>
            <td>전화번호</td>
            <td>카드번호</td>
            <td>계좌번호</td>
            <td>회원등급</td>
            <td>포인트</td>
            <td>계정상태</td>
            <td></td>
        </thead>
        <tbody>
            <!-- mi_seq,mi_id,mi_name,mi_email,mi_address,mi_birth,mi_pwd,mi_gen,mi_phone,mi_pay_card,mi_pay_account,mi_grade,mi_point,mi_status -->
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.mi_seq}</td>
                    <td>${user.mi_id}</td>
                    <td>${user.mi_name}</td>
                    <td>${user.mi_email}</td>
                    <td>${user.mi_address}</td>
                    <td>${user.mi_birth}</td>
                    <td>
                        <c:if test="${user.mi_gen == 0}">
                            남자
                        </c:if>
                        <c:if test="${user.mi_gen == 1}">
                            여자
                        </c:if>
                        <c:if test="${user.mi_gen == 2}">
                            선택안함
                        </c:if>
                    </td>
                    <td>${user.mi_phone}</td>
                    <td>${user.mi_pay_card}</td>
                    <td>${user.mi_pay_account}</td>
                    <td>${user.mi_grade}</td>
                    <td>${user.mi_point}</td>
                    <td>
                        <c:if test="${user.mi_status == 0}">
                            가입대기
                        </c:if>
                        <c:if test="${user.mi_status == 1}">
                            정상사용
                        </c:if>
                        <c:if test="${user.mi_status == 2}">
                            사용정지
                        </c:if>
                        <c:if test="${user.mi_status == 3}">
                            탈퇴대기
                        </c:if>
                    </td>
                    <td>
                        <button class="member_del" data-seq="${user.mi_seq}">삭제</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/member/join">사용자 추가</a>
</body>
</html>