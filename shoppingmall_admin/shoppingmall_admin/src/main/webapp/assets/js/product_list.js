$(function(){
    $("#add_product").click(function(){
        $(".product_form").css("display", "block");
        $("#img_preview").html("");
        $("#save").css("display", "block");
        $("#modify").css("display", "none");
        $(".product_form > h1").html("상품 추가");
    });
    $("#close").click(function(){
        if(confirm("입력을 취소하시겠습니까?\n(저장하지 않은 정보는 모두 사라집니다.)")){
            $(".product_form").css("display", "");
            $(".product_form input").val("");
            $(".product_form select option:first-child").prop("selected", true);
            $(".product_form textarea").val("");
        }
        
    });

    $(".product_form").draggable({
        handle:"h1"
    });
    $("#product").addClass("current");
    getProductData();
    function getProductData(keyword, cate_seq, offset){
        // 안쪽 내용을 괄호안의 내용으로 바꾼다.
        $("#product_tbody").html("");

        let url = "/product/api/list";
        if(keyword == undefined || keyword == null) {
            keyword = "";
        }
        url += "?keyword="+keyword
        if(cate_seq != undefined && cate_seq != null) {
            url += "&category="+cate_seq;
        }
        if(offset != undefined && offset != null) {
            url += "&offset="+offset;
        }
        $.ajax({
            type:"get",
            url:url,
            success:function(r) {
                for(let i=0; i<r.data.length; i++) {
                    let tag = 
                    '<tr>'+
                        '<td>'+r.data[i].pi_seq+'</td>'+
                        '<td>'+r.data[i].pi_name+'</td>'+
                        '<td class="preview"><img src="/image/'+r.data[i].pi_img_uri+'"></td>'+
                        '<td>'+r.data[i].category_name+'</td>'+
                        '<td>'+r.data[i].pi_stock+'</td>'+
                        '<td>'+r.data[i].seller_name+'</td>'+
                        '<td>'+r.data[i].pi_create_dt+'</td>'+
                        '<td>'+r.data[i].pi_discount_rate+'</td>'+
                        '<td>'+r.data[i].pi_point_rate+'</td>'+
                        '<td>'+r.data[i].pi_caution+'</td>'+
                        '<td>'+r.data[i].pi_weight+'</td>'+
                        '<td>'+r.data[i].delivery_name+'</td>'+
                        '<td>'+r.data[i].pi_price+'</td>'+
                        '<td>'+
                            '<button class="product_modify" data-seq="'+r.data[i].pi_seq+'">수정</button>'+
                        '</td>'+
                        '<td>'+
                            '<button class="product_delete" data-seq="'+r.data[i].pi_seq+'">삭제</button>'+
                        '</td>'+
                    '</tr>'
                    $("#product_tbody").append(tag);
                }
                $(".product_modify").click(function(){
                    $("#save").css("display", "none");
                    $("#modify").css("display", "block");
                    $(".product_form > h1").html("상품 수정");
                    $(".product_form").css("display", "block");
                    let seq = $(this).attr("data-seq");
                    $("#modify").attr("mod-seq", seq);
                    $.ajax({
                        type:"get",
                        url:"/product/api/get?seq="+seq,
                        success:function(r) {
                            console.log(r);
                            $("#pi_name").val(r.data.pi_name);
                            $("#pi_price").val(r.data.pi_price);
                            $("#pi_cate_seq").val(r.data.pi_cate_seq);
                            $("#pi_stock").val(r.data.pi_stock);
                            $("#pi_si_seq").val(r.data.pi_si_seq);
                            $("#pi_discount_rate").val(r.data.pi_discount_rate);
                            $("#pi_point_rate").val(r.data.pi_point_rate);
                            $("#pi_caution").val(r.data.pi_caution);
                            $("#pi_weight").val(r.data.pi_weight);
                            $("#pi_di_seq").val(r.data.pi_di_seq);
                            
                            $("#img_preview").html("");
                            if(r.data.pi_img_uri != null) {
                                $("#img_preview").html(
                                    '<img src="/image/'+r.data.pi_img_uri+'" img-url="'+r.data.pi_img_uri+'">'
                                )
                                $("#img_preview").attr("img-uri", r.data.pi_img_uri);
                            }
                        }
                    })
                });
                $(".product_delete").click(function(){
                    //alert("삭제클릭");
                    // confirm 창에서 취소가 눌리면 이 함수를 종료한다.
                    if(!confirm("삭제하시겠습니까?")) return;

                    let seq = $(this).attr("data-seq");
                    $.ajax({
                        type:"delete",
                        url:"/product/api/delete?seq="+seq,
                        success:function(r){
                            alert(r.message);
                            location.reload();
                        }
                    })
                })
            }
        })
    }

    // $("CSS선택자표현").이벤트종류(function(){ 실행내용; })
    $("#save").click(function(){
        let pi_name = $("#pi_name").val();
        let pi_price = $("#pi_price").val();
        let pi_cate_seq = $("#pi_cate_seq option:selected").val();
        let pi_stock = $("#pi_stock").val();
        let pi_si_seq = $("#pi_si_seq option:selected").val();
        let pi_discount_rate = $("#pi_discount_rate").val();
        let pi_point_rate = $("#pi_point_rate").val();
        let pi_caution = $("#pi_caution").val();
        let pi_weight = $("#pi_weight").val();
        let pi_di_seq = $("#pi_di_seq option:selected").val();

        if(pi_name == null || pi_name == "" || pi_name == undefined) {
            alert("상품명을 입력하세요"); return; 
        }
        if(pi_price == null || pi_price == "" || pi_price == undefined) {
            alert("가격을 입력하세요"); return; 
        }
        if(pi_cate_seq == null || pi_cate_seq == "" || pi_cate_seq == undefined) {
            alert("카테고리를 입력하세요"); return; 
        }
        if(pi_stock == null || pi_stock == "" || pi_stock == undefined) {
            alert("재고를 입력하세요"); return; 
        }
        if(pi_si_seq == null || pi_si_seq == "" || pi_si_seq == undefined) {
            alert("판매자를 입력하세요"); return; 
        }
        if(pi_discount_rate == null || pi_discount_rate == "" || pi_discount_rate == undefined) {
            alert("할인율을 입력하세요"); return; 
        }
        if(pi_point_rate == null || pi_point_rate == "" || pi_point_rate == undefined) {
            alert("적립율을 입력하세요"); return; 
        }
        if(pi_weight == null || pi_weight == "" || pi_weight == undefined) {
            alert("무게를 입력하세요"); return; 
        }
        if(pi_di_seq == null || pi_di_seq == "" || pi_di_seq == undefined) {
            alert("배송사를 입력하세요"); return; 
        }

        let data = {
            pi_name:pi_name,
            pi_price:pi_price,
            pi_cate_seq:pi_cate_seq,
            pi_stock:pi_stock,
            pi_si_seq:pi_si_seq,
            pi_discount_rate:pi_discount_rate,
            pi_point_rate:pi_point_rate,
            pi_caution:pi_caution,
            pi_weight:pi_weight,
            pi_di_seq:pi_di_seq,
            pi_img_uri:$("#img_preview").attr("img-uri")
        };

        $.ajax({
            type:"post",
            url:"/product/api/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })

    $("#modify").click(function(){
        let pi_name = $("#pi_name").val();
        let pi_price = $("#pi_price").val();
        let pi_cate_seq = $("#pi_cate_seq option:selected").val();
        let pi_stock = $("#pi_stock").val();
        let pi_si_seq = $("#pi_si_seq option:selected").val();
        let pi_discount_rate = $("#pi_discount_rate").val();
        let pi_point_rate = $("#pi_point_rate").val();
        let pi_caution = $("#pi_caution").val();
        let pi_weight = $("#pi_weight").val();
        let pi_di_seq = $("#pi_di_seq option:selected").val();

        if(pi_name == null || pi_name == "" || pi_name == undefined) {
            alert("상품명을 입력하세요"); return; 
        }
        if(pi_price == null || pi_price == "" || pi_price == undefined) {
            alert("가격을 입력하세요"); return; 
        }
        if(pi_cate_seq == null || pi_cate_seq == "" || pi_cate_seq == undefined) {
            alert("카테고리를 입력하세요"); return; 
        }
        if(pi_stock == null || pi_stock == "" || pi_stock == undefined) {
            alert("재고를 입력하세요"); return; 
        }
        if(pi_si_seq == null || pi_si_seq == "" || pi_si_seq == undefined) {
            alert("판매자를 입력하세요"); return; 
        }
        if(pi_discount_rate == null || pi_discount_rate == "" || pi_discount_rate == undefined) {
            alert("할인율을 입력하세요"); return; 
        }
        if(pi_point_rate == null || pi_point_rate == "" || pi_point_rate == undefined) {
            alert("적립율을 입력하세요"); return; 
        }
        if(pi_weight == null || pi_weight == "" || pi_weight == undefined) {
            alert("무게를 입력하세요"); return; 
        }
        if(pi_di_seq == null || pi_di_seq == "" || pi_di_seq == undefined) {
            alert("배송사를 입력하세요"); return; 
        }

        let data = {
            pi_seq:$(this).attr("mod-seq"),
            pi_name:pi_name,
            pi_price:pi_price,
            pi_cate_seq:pi_cate_seq,
            pi_stock:pi_stock,
            pi_si_seq:pi_si_seq,
            pi_discount_rate:pi_discount_rate,
            pi_point_rate:pi_point_rate,
            pi_caution:pi_caution,
            pi_weight:pi_weight,
            pi_di_seq:pi_di_seq,
            pi_img_uri:$("#img_preview").attr("img-uri")
        };

        $.ajax({
            type:"patch",
            url:"/product/api/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })

    $("#search").click(function(){
        let seq = $("#cate_search option:selected").val();
        let keyword = $("#search_keyword").val();
        if(seq == "전체") seq = null;
        getProductData(keyword, seq, 0);
    })

    $("#cate_search").change(function(){
        // alert("값 변경됨");
        let seq = $("#cate_search option:selected").val();
        let keyword = $("#search_keyword").val();
        if(seq == "전체") seq = null;
        getProductData(keyword, seq, 0);
    })
//             img_form_td
// image_form
// img_save
// img_delete
    $("#img_save").click(function(){
        let form = $("#image_form");
        let formData = new FormData(form[0]);
        $.ajax({
            url:"/upload",
            type:"post",
            data:formData,
            contentType:false,
            processData:false,
            success:function(r) {
                // alert(r.message);
                console.log(r);
                if(r.status) {
                    $("#img_save").prop("disabled", true);
                    $("#img_delete").prop("disabled", false);
                    $("#image_form > input").prop("disabled", true);
                    $("#img_preview").append('<img src="/image/'+r.image_uri+'">');
                    $("#img_preview").attr("img-uri", r.image_uri);
                }
                alert(r.message);
            }
        })
    });

    $("#img_delete").click(function(){
        let uri = $("#img_preview").attr("img-uri");
        $("#img_preview").html("");

        $("#image_form > input").val("");
        $(this).prop("disabled", true);
        $("#image_form > input").prop("disabled", false);
        $("#img_save").prop("disabled", false);

        alert("클릭");
    })
});