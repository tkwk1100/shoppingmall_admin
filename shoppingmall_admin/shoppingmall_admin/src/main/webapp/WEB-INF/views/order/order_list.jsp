<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/order_list.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/order_list.js"></script>
</head>
<body>
    <h1>주문 리스트</h1>
    <p>총 ${cnt}건</p>
    <%-- ${list} --%>
    <table>
        <thead>
            <tr>
                <td>번호</td>
                <td>이미지</td>
                <td>제품명</td>
                <td>업체명</td>
                <td>고객명</td>
                <td>이메일</td>
                <td>주소</td>
                <td>전화번호</td>
                <td>주문금액</td>
                <td>적립금</td>
                <td>주문일</td>
                <td>배송상태</td>
                <td>배송상태 설정</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="data" varStatus="status">
                <tr data-oi-seq="${data.oi_seq}" data-pi-seq="${data.pi_seq}" data-mi-seq="${data.mi_seq}">
                    <td>${cnt - status.index}</td>
                    <td>
                        <img src="/image/${data.pi_img_uri}" style="height:50px">
                    </td>
                    <td>${data.pi_name}</td>
                    <td>${data.si_name}</td>
                    <td><span>${data.mi_name}</span><br><span>(${data.mi_id})</span></td>
                    <td>${data.mi_email}</td>
                    <td>${data.mi_address}</td>
                    <td>${data.mi_phone}</td>
                    <td>${data.final_price}원</td>
                    <td>${data.final_point}</td>
                    <td>
                        <fmt:formatDate value="${data.oi_reg_dt}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>
                        <c:if test="${data.oi_delivery_status == 0}">배송준비중</c:if>
                        <c:if test="${data.oi_delivery_status == 1}">배송중</c:if>
                        <c:if test="${data.oi_delivery_status == 2}">배송완료</c:if>
                    </td>
                    <td id="status_change">
                        <button data-status="0" id="delivery_ready">배송준비</button>
                        <button data-status="1" id="delivery_ing">배송중</button>
                        <button data-status="2" id="delivery_fin">배송완료</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>