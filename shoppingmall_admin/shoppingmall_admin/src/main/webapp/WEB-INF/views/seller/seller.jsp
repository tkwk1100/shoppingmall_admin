<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>판매자 관리</title>
    <link rel="stylesheet" href="/assets/css/table_style.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        // HTML의 DOM이 로드가 완료되었을 때
        let isDupId = true; // id가 중복값이냐?

        document.addEventListener("DOMContentLoaded", function(){
            $("#shop").addClass("current");
            $.ajax({
                type:"get",
                url:"/seller/api/list",
                success:function(r) {
                    // console.log(r);
                    for(let i=0; i<r.data.length; i++) {
                        let tag = 
                        '<tr>'+
                            '<td>'+r.data[i].si_seq+'</td>'+
                            '<td>'+r.data[i].si_id+'</td>'+
                            '<td>'+r.data[i].si_name+'</td>'+
                            '<td>'+r.data[i].si_address+'</td>'+
                            '<td>'+r.data[i].si_email+'</td>'+
                            '<td>'+r.data[i].si_phone+'</td>'+
                            '<td>'+r.data[i].si_grade+'</td>'+
                            '<td>'+r.data[i].seller_prod_cnt+'</td>'+
                            '<td>'+
                                '<button class="seller_delete" data-seq="'+r.data[i].si_seq+'" '+(r.data[i].seller_prod_cnt!=0?"disabled":"")+'>삭제</button>'+
                            '</td>'+
                        '</tr>'
                        $(".seller_tbody").append(tag);
                    }
                    $(".seller_delete").click(function(){
                        let seq = $(this).attr("data-seq");
                        // alert(seq);
                        if(confirm("삭제하시겠습니까?")) {
                            $.ajax({
                                type:"delete",
                                url:"/seller/api/delete?seq="+seq,
                                success:function(r) {
                                    alert(r.message);
                                    location.reload();
                                }
                            })
                        }
                    })
                }
            });

            document.getElementById("dup_check").addEventListener("click", function(){
                let id = document.getElementById("seller_id").value;
                if(id == '') {
                    alert("아이디를 입력하세요");
                    return;
                }
                if(id.length < 4) {
                    alert("아이디는 4자 이상으로 입력해주세요");
                    return;
                }
                $.ajax({
                    type:"get",
                    url:"/seller/isDuplicateId?id="+id,
                    success:function(data) {
                        alert(data.message);
                        isDupId = data.status;
                    }
                });
            });
            document.getElementById("seller_id").addEventListener("input", function(){
                // 입력이 발생했을 때
                isDupId = true; // 아이디 중복 변수를 초기 값으로 되돌린다.
            });
            document.getElementById("regist").addEventListener("click", function(){
                if(isDupId == true) {
                    alert("아이디 중복 여부를 체크해주세요");
                    return;
                }
                let id = document.getElementById("seller_id").value;
                let pwd = document.getElementById("seller_pwd").value;
                let pwd_con = document.getElementById("seller_pwd_con").value;
                let name = document.getElementById("seller_name").value;
                let addr = document.getElementById("seller_addr").value;
                let email = document.getElementById("seller_email").value;
                let phone = document.getElementById("seller_phone").value;

                if(id == '') {
                    alert("아이디를 입력하세요");
                    return;
                }
                if(id.length < 4) {
                    alert("아이디는 4자 이상으로 입력해주세요");
                    return;
                }

                if(pwd == '') {
                    alert("비밀번호를 입력하세요.");
                    return;
                }
                if(pwd.length < 6) {
                    alert("비밀번호는 6글자 이상 입력하세요.");
                    return;
                }
                if(pwd != pwd_con) {
                    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                    return;
                }
                if(name == '') {
                    alert("이름을 입력해주세요");
                    return;
                }
                if(addr == '') {
                    alert("주소를 입력해주세요");
                    return;
                }
                if(email == '') {
                    alert("이메일을 입력해주세요");
                    return;
                }
                if(phone == '') {
                    alert("전화번호를 입력해주세요");
                    return;
                }

                let sellerInfo = {
                    si_id:id,
                    si_pwd:pwd,
                    si_name:name,
                    si_address:addr,
                    si_email:email,
                    si_phone:phone
                };
                
                $.ajax({
                    type:"post", // @PostMapping
                    url:"/seller/regist", // ("/seller/regist")
                    data:JSON.stringify(sellerInfo), // @RequestBody
                    contentType:"application/json",
                    success:function(result) {
                        // ARC - 200 OK / 202 
                        // 코드실행
                        alert(result.message);
                        if(result.result == 'success') {
                            // 성공 시 메시지
                        }
                        else {
                            // 실패시 메시지
                        }
                    },
                    error:function(error) {
                        // exception, 발생시킨 에러정보
                        // 404 415 500 발생 시 실행
                    }
                })
            })
        })
    </script>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div>
        <table>
            <tbody>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" id="seller_id"></td>
                    <td><button type="button" id="dup_check">중복체크</button></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" id="seller_pwd"></td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td><input type="password" id="seller_pwd_con"></td>
                </tr>
                <tr>
                    <td>판매자 명</td>
                    <td><input type="text" id="seller_name"></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" id="seller_addr"></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="text" id="seller_email"></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" id="seller_phone"></td>
                </tr>
                <tr>
                    <td>
                        <button id="regist">등록하기</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="seller_list list">
        <table class="seller_table">
            <thead>
                <tr>
                    <td>번호</td>
                    <td>아이디</td>
                    <td>업체명</td>
                    <td>주소</td>
                    <td>이메일</td>
                    <td>전화번호</td>
                    <td>등급</td>
                    <td>제품수</td>
                    <td></td>
                </tr>
            </thead>
            <tbody class="seller_tbody">
                
            </tbody>
        </table>
    </div>
</body>
</html>