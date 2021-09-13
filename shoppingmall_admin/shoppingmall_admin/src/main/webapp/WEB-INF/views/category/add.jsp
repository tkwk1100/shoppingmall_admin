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
            $("#category").addClass("current");

            $.ajax({
                type:"get",
                url:"/category/api/list",
                success:function(r) {
                    for(let i=0; i<r.data.length; i++){
                        console.log(r.data[i].cate_seq, r.data[i].cate_name);
                        let tag = 
                            '<tr>'+
                                '<td>'+r.data[i].cate_seq+'</td>'+
                                '<td>'+r.data[i].cate_name+'</td>'+
                                '<td>'+r.data[i].cate_prod_cnt+'</td>'+
                                '<td>'+
                                    '<button data-seq="'+r.data[i].cate_seq+'" class="cate_del" '+(r.data[i].cate_prod_cnt!=0?"disabled":"")+'>삭제</button>'+
                                '</td>'+
                            '</tr>';
                        // document.getElementById('cate_table_body').append(tag);
                        $("#cate_table_body").append(tag);
                    }

                    // for(let i=0; i<document.getElementsByClassName("cate_del").length; i++) {
                    //     document.getElementsByClassName("cate_del")[i].addEventListener("click", function(){
                    //         alert("delete");
                    //     })
                    // }

                    $(".cate_del").click(function(){
                        // alert("delete"); // 메시지 출력
                        // $(this) - 클래스가 cate_del인 버튼 중 현재 클릭된 버튼 
                        // $(this) - 클래스가 cate_del인 것들 중 현재 클릭 이벤트를 발생시킨 태그
                        // attr("data-seq") : 태그에 지정된 속성 중 data-seq의 값을 가져온다.
                        console.log( $(this).attr("data-seq") );
                        if(confirm("삭제하시겠습니까?")) {
                            // 삭제 동작
                            console.log("삭제동작");
                            $.ajax({
                                url:"/category/api/delete?seq="+$(this).attr("data-seq"),
                                type:"delete",
                                success:function(r){
                                    alert(r.message);
                                    location.reload();
                                }
                            })
                        }
                    });
                },
                error:function(e) {

                }
            })

            document.getElementById("add").addEventListener("click", function(){
                let name = document.getElementById("cate_name").value;
                if(name == '' || name == null || name == undefined) {
                    alert("카테고리명을 입력하세요.");
                    return;
                }
                $.ajax({
                    type:"get",
                    url:"/category/api/add?name="+name,
                    success:function(data){
                        alert(data.message);
                        location.reload();
                    },
                    error:function(data) {
                        console.log(data);
                    }
                })
            });
        });
    </script>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <h1>카테고리 추가</h1>
    <div class="cate_wrap">
        <span>카테고리명</span>
        <input text="text" id="cate_name">
        <button id="add">추가</button>
    </div>

    <div id="cate_list" class="list">
        <table id="cate_table">
            <thead>
                <tr>
                    <td>번호</td>
                    <td>이름</td>
                    <td>제품수</td>
                    <td></td>
                </tr>
            </thead>
            <tbody id="cate_table_body">

            </tbody>
        </table>
    </div>
</body>
</html>