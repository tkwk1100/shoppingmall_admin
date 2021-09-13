<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/header.css">
</head>
<body>
    <header>
        <a href="/" id="logo">
            <img src="http://placekitten.com/40/40">
            <span>Shopping Mall<br>Admin</span>
        </a>
        <nav id="gnb">
            <ul>
                <li>
                    <a href="/category/add" id="category">카테고리 관리</a>
                </li>
                <li>
                    <a href="/seller/regist" id="shop">업체 관리</a>
                </li>
                <li>
                    <a href="/delivery/add" id="delivery">배송업체 관리</a>
                </li>
                <li>
                    <a href="/product" id="product">상품 관리</a>
                </li>
                <li>
                    <a href="/member/list" id="member">회원 관리</a>
                </li>
                <li>
                    <a href="/recommand" id="recommand">추천상품 관리</a>
                </li>
            </ul>
        </nav>
    </header>
</body>
</html>
