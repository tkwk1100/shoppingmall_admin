$(function(){
    let idCheck = false;
    let emailCheck = false;

    $("#join").click(function(){
        if(idCheck == false) {
            alert("아이디 중복여부를 확인해주세요.");
            return;
        }
        if(emailCheck == false) {
            alert("이메일 중복여부를 확인해주세요.");
            return;
        }

        // alert("클릭되었습니다.");
        const pattern = /\s/g; // 공백 체크 정규표현식

        let user_id = $("#user_id").val();
        if(user_id == "" || user_id == null || user_id == undefined) {
            alert("아이디를 입력해주세요");
            return;
        }
        if(user_id.match(pattern)) {
            alert("아이디에는 공백문자가 들어갈 수 없습니다.");
            return;
        }

        let user_pwd = $("#user_pwd").val();
        if(user_pwd == "" || user_pwd == null || user_pwd == undefined) {
            alert("비밀번호를 입력해주세요");
            return;
        }
        if(user_pwd.match(pattern)) {
            alert("비밀번호는 공백문자가 들어갈 수 없습니다.");
            return;
        }
        let user_pwd_confirm = $("#user_pwd_confirm").val();
        if(user_pwd != user_pwd_confirm) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        let user_name = $("#user_name").val();
        if(user_name == "" || user_name == null || user_name == undefined) {
            alert("이름을 입력해주세요");
            return;
        }
        if(user_name.match(pattern)) {
            alert("이름은 공백문자가 들어갈 수 없습니다.");
            return;
        }

        const patternEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        let user_email = $("#user_email").val();
        if(user_email == "" || user_email == null || user_email == undefined) {
            alert("이메일을 입력해주세요");
            return;
        }
        if(user_email.match(pattern)) {
            alert("이메일은 공백문자가 들어갈 수 없습니다.");
            return;
        }
        if(!user_email.match(patternEmail)) {
            alert("올바른 이메일 형식을 입력하세요\n예시)aaa@service.com");
            return;
        }

        let user_birth_year = $("#user_birth_year").val();
        let user_birth_month = $("#user_birth_month").val();
        let user_birth_date = $("#user_birth_date").val();
        let user_address = $("#user_address").val();
        let user_phone = $("#user_phone").val();
        let user_card = $("#user_card").val();
        let user_account = $("#user_account").val();

        if(!inputValidation(user_birth_year, "생년월일")) { return; }
        if(!inputValidation(user_birth_month, "생년월일")) { return; }
        if(!inputValidation(user_birth_date, "생년월일")) { return; }
        if(!inputValidation(user_address, "주소")) { return; }
        if(!inputValidation(user_phone, "전화번호")) { return; }
        // if(!inputValidation(user_card, "카드번호")) { return; }
        // if(!inputValidation(user_account, "계좌번호")) { return; }
        let user_gen = $("#user_gen option:selected").val();
        // 20210614
        // 2021614
        // 202161
        // 2021  006
        let birth = user_birth_year+leadingZero(user_birth_month)+leadingZero(user_birth_date);
            // user_birth_month가 10보다 작으면 앞에 0을 붙인다
            // (user_birth_month<10?"0"+user_birth_month:""+user_birth_month)+
            // user_birth_date가 10보다 작으면 앞에 0을 붙인다
            // (user_birth_date<10?"0"+user_birth_date:""+user_birth_date);

        let data = {
            mi_id:user_id,
            mi_name:user_name,
            mi_email:user_email,
            mi_address:user_address,
            mi_birth:birth,
            mi_pwd:user_pwd,
            mi_gen:user_gen,
            mi_phone:user_phone,
            mi_pay_card:user_card,
            mi_pay_account:user_account
        };
        $.ajax({
            type:"post",
            url:"/member/join",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status) {
                    location.href="/";
                }
            },
            error:function(e) {
                console.log(e);
            }
        })
    });

    $("#chk_id").click(function(){
        const pattern = /\s/g; // 공백 체크 정규표현식

        let user_id = $("#user_id").val();
        if(user_id == "" || user_id == null || user_id == undefined) {
            alert("아이디를 입력해주세요");
            return;
        }
        if(user_id.match(pattern)) {
            alert("아이디에는 공백문자가 들어갈 수 없습니다.");
            return;
        }
        $.ajax({
            type:"get",
            url:"/member/id_check?id="+user_id,
            success:function(r) {
                // 요청결과로 상태값이 200이나 202가 나왔을 때 실행.
                alert(r.message);
                idCheck = r.status;
                // if(r.status == "duplicated") {
                //     idCheck = false;
                // }
                // else {
                //     idCheck = true;
                // }
            }
        });
    });
    $("#chk_email").click(function(){
        const pattern = /\s/g;
        const patternEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        let user_email = $("#user_email").val();
        if(user_email == "" || user_email == null || user_email == undefined) {
            alert("이메일을 입력해주세요");
            return;
        }
        if(user_email.match(pattern)) {
            alert("이메일은 공백문자가 들어갈 수 없습니다.");
            return;
        }
        if(!user_email.match(patternEmail)) {
            alert("올바른 이메일 형식을 입력하세요\n예시)aaa@service.com");
            return;
        }

        // 중복체크
        $.ajax({
            type:"get",
            url:"/member/email_check?email="+user_email,
            success:function(r) {
                // 요청결과로 상태값이 200이나 202가 나왔을 때 실행.
                alert(r.message);
                emailCheck = r.status;
                // if(r.status == "duplicated") {
                //     idCheck = false;
                // }
                // else {
                //     idCheck = true;
                // }
            }
        });
    });

    $("#user_id").change(function(){
        idCheck = false;
    });
    $("#user_email").change(function(){
        emailCheck = false;
    });
});

function leadingZero(n) {
    // 입력된 데이터를 숫자 형태로 형변환
    let num = Number(n);
    // 10 미만의 숫자이면, 앞쪽에 0을 붙인 문자열로 내보내고,
    // 10 이상의 숫자이면 문자열 형태로 변환해서 내보낸다.
    return num<10?"0"+num:""+num;
}

function inputValidation(input, type) {
    if(input == "" || input == null || input == undefined) {
        alert(type+"을/를 입력해주세요");
        return false;
    }
    return true;
}