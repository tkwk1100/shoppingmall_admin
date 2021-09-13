<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/table_style.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function(){
            $("#delivery").addClass("current");
            $.ajax({
                url:"/delivery/api/list",
                type:"get",
                success:function(r) {
                    for(let i=0; i<r.data.length; i++){
                        let tag = 
                        '<tr>'+
                            '<td>'+r.data[i].di_seq+'</td>'+
                            '<td>'+r.data[i].di_name+'</td>'+
                            '<td>'+r.data[i].di_phone+'</td>'+
                            '<td>'+r.data[i].di_price+'원</td>'+
                            '<td>'+r.data[i].delivery_prod_cnt+'</td>'+
                            '<td>'+
                                '<button class="delivery_delete" data-seq="'+r.data[i].di_seq+'" '+(r.data[i].delivery_prod_cnt!=0?"disabled":"")+'>삭제</button>'+
                            '</td>'+
                        '</tr>'
                        $(".delivery_tbody").append(tag);
                    }
                    $(".delivery_delete").click(function(){
                        // alert("삭제버튼");
                        let seq = $(this).attr("data-seq");
                        // console.log(seq);
                        if(confirm("삭제하시겠습니까?")) {
                            $.ajax({
                                url:"/delivery/api/delete?seq="+seq,
                                type:"delete",
                                success:function(r) {
                                    alert(r.message);
                                    location.reload();
                                }
                            })
                        }
                    })
                }
            })


            document.getElementById("add").addEventListener("click", function(){
                let di_name = document.getElementById("di_name").value;
                let di_phone = document.getElementById("di_phone").value;
                let di_price = document.getElementById("di_price").value;

                if(di_name == '' || di_name == null || di_name == undefined){
                    alert("택배사 명을 입력하세요");
                    return;
                }
                if(di_phone == '' || di_phone == null || di_phone == undefined){
                    alert("전화번호를 입력하세요");
                    return;
                }
                if(di_price == '' || di_price == null || di_price == undefined){
                    alert("배송비를 입력하세요");
                    return;
                }

                let data = {
                    "di_name":di_name,
                    "di_phone":di_phone,
                    "di_price":di_price
                };
                $.ajax({
                    type:"post",
                    url:"/delivery/api/add",
                    contentType:"application/json",
                    data:JSON.stringify(data),
                    success:function(r) {
                        alert(r.message);
                        location.reload();
                    },
                    error:function(e) {
                        console.log(e);
                    }
                });
            });

            document.getElementById("dup_chk").addEventListener("click", function(){
                let di_name = document.getElementById("di_name").value;
                if(di_name == '' || di_name == null || di_name == undefined){
                    alert("택배사 명을 입력하세요");
                    return;
                }
                $.ajax({
                    url:"/delivery/api/check?name="+di_name,
                    type:"get",
                    success:function(r) {
                        alert(r.message);
                    },
                    error:function(e){
                        console.log(e);
                    }
                })
            })
        });
    </script>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <h1>배송사 추가</h1>
    <table>
        <tr>
            <td>배송사 명</td>
            <td><input type="text" id="di_name"></td>
            <td><button id="dup_chk">중복확인</button></td>
        </tr>
        <tr>
            <td>전화번호</td>
            <td><input type="text" id="di_phone"></td>
        </tr>
        <tr>
            <td>배송비</td>
            <td><input type="number" id="di_price"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="add">업체등록</button>
            </td>
        </tr>
    </table>

    <div class="delivery_list list">
        <table class="delivery_table">
            <thead>
                <tr>
                    <td>번호</td>
                    <td>배송사</td>
                    <td>전화번호</td>
                    <td>배송비</td>
                    <td>제품수</td>
                    <td></td>
                </tr>
            </thead>
            <tbody class="delivery_tbody">
                
            </tbody>
        </table>
    </div>
</body>
</html>