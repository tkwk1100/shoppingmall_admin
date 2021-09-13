<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/recommand.js"></script>
    <link rel="stylesheet" href="/assets/css/table_style.css">
    <link rel="stylesheet" href="/assets/css/recommand.css">
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <div class="container list">
        <h1>추천상품 관리</h1>
        <table>
            <thead>
                <tr>
                    <td>번호</td>
                    <td>제품명</td>
                    <td>제품이미지</td>
                    <td>카테고리</td>
                    <td>업체명</td>
                    <td></td>
                </tr>
            </thead>
            <tbody id="recommand_tbody">
                
            </tbody>
        </table>
        <button class="add">추천상품 추가</button>
    </div>
    <div class="add_recommand">
        <div class="add_recommand_wrap">
            <h1>추천상품 추가</h1>
            <button class="close">닫기</button>
            <div class="search_area">
                <select id="cate">

                </select>
                <select id="seller">

                </select>
                <input type="text" id="keyword" placeholder="제품명 검색">
                <button id="search">검색</button>
            </div>

            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>제품명</td>
                        <td>제품이미지</td>
                        <td>카테고리</td>
                        <td>업체명</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody class="product_list">
    
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>