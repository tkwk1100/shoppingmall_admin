<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/product.css">
    <link rel="stylesheet" href="/assets/css/table_style.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/assets/js/product_list.js"></script>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <h1>상품관리</h1>
    <div class="product_form">
        <h1>상품 추가</h1>
        <table class="prod_form_table">
            <tbody>
                <tr>
                    <td>상품명</td>
                    <td><input type="text" id="pi_name" placeholder="상품명"></td>
                    <td>가격</td>
                    <td><input type="number" id="pi_price" placeholder="가격"></td>
                </tr>
                <tr>
                    <td>카테고리</td>
                    <td>
                        <select id="pi_cate_seq">
                            <option value="null">카테고리 선택</option>
                            <c:forEach items="${clist}" var="cate">
                                <option value="${cate.cate_seq}">${cate.cate_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>판매자</td>
                    <td>
                        <select id="pi_si_seq">
                            <option value="null">판매자 선택</option>
                            <c:forEach items="${slist}" var="seller">
                                <option value="${seller.si_seq}">${seller.si_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>재고수량</td>
                    <td>
                        <input type="number" id="pi_stock" placeholder="재고">
                    </td>
                    <td>무게(g)</td>
                    <td>
                        <input type="number" min="0" id="pi_weight" placeholder="무게(g)">
                    </td>
                </tr>
                <tr>
                    <td>할인률(%)</td>
                    <td>
                        <input type="number" id="pi_discount_rate" min="0" max="100" placeholder="할인률">
                    </td>
                    <td>적립률(%)</td>
                    <td>
                        <input type="number" id="pi_point_rate" min="0" max="100" placeholder="적립률">
                    </td>
                </tr>
                <tr>
                    <td>배송업체</td>
                    <td colspan="3">
                        <select id="pi_di_seq">
                            <option value="null">배송업체 선택</option>
                            <c:forEach items="${dlist}" var="delivery">
                                <option value="${delivery.di_seq}">${delivery.di_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>주의사항</td>
                    <td colspan="3">
                        <textarea id="pi_caution" placeholder="주의사항"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>제품이미지</td>
                </tr>
                <tr>
                    <td class="img_form_td" colspan="4">
                        <span id="img_preview">
                            
                        </span>
                        <form id="image_form">
                            <input type="file" accept="image/gif, image/jpeg, image/png" name="file" value="제품이미지 선택">
                            <button type="button" id="img_save">등록</button>
                            <button type="button" id="img_delete" disabled>삭제</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" id="buttons">
                        <button id="save">등록하기</button>
                        <button id="modify">수정하기</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <button id="close">&times;</button>
    </div>
    <div class="product_list list">
        <div class="search_area">
            <span>카테고리</span>
            <select id="cate_search">
                <option>전체</option>
                <c:forEach items="${clist}" var="cate">
                    <option value="${cate.cate_seq}">${cate.cate_name}</option>
                </c:forEach>
            </select>
            <input type="text" id="search_keyword" placeholder="제품명검색">
            <button id="search">검색</button>
        </div>
        <table id="product_table">
            <thead>
                <tr>
                    <td>번호</td>
                    <td>제품명</td>
                    <td>제품이미지</td>
                    <td>카테고리</td>
                    <td>재고</td>
                    <td>업체명</td>
                    <td>등록일</td>
                    <td>할인율</td>
                    <td>적립율</td>
                    <td>주의사항</td>
                    <td>무게</td>
                    <td>배송사</td>
                    <td>제품가격</td>
                    <td></td>
                </tr>
            </thead>
            <tbody id="product_tbody">

            </tbody>
        </table>
        <div class="button_area">
            <button id="add_product">상품 추가</button>
        </div>
    </div>

</body>
</html>






