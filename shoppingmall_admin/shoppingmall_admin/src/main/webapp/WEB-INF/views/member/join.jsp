<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/member_join.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/member_join.js"></script>
</head>
<body>
    <h1 class="title">회원가입</h1>
    <table class="join_table">
        <tbody>
            <tr>
                <td>아이디</td>
                <td colspan="3">
                    <input type="text" id="user_id">
                </td>
                <td>
                    <button id="chk_id">중복확인</button>
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td colspan="3">
                    <input type="password" id="user_pwd">
                </td>
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td colspan="3">
                    <input type="password" id="user_pwd_confirm">
                </td>
            </tr>
            <tr>
                <td>이름</td>
                <td colspan="3">
                    <input type="text" id="user_name">
                </td>
            </tr>
            <tr>
                <td>이메일</td>
                <td colspan="3">
                    <input type="text" id="user_email">
                </td>
                <td>
                    <button id="chk_email">중복확인</button>
                </td>
            </tr>
            <tr>
                <td>생년월일</td>
                <td>
                    <input type="text" id="user_birth_year">
                    <span>년</span>
                </td>
                <td>
                    <input type="text" id="user_birth_month">
                    <span>월</span>
                </td>
                <td>
                    <input type="text" id="user_birth_date">
                    <span>일</span>
                </td>
            </tr>
            <tr>
                <td>성별</td>
                <td colspan="3">
                    <select id="user_gen">
                        <option value="0" selected>남</option>
                        <option value="1">여</option>
                        <option value="2">선택안함</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>주소</td>
                <td colspan="3">
                    <input type="text" id="user_address">
                </td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td colspan="3">
                    <input type="text" id="user_phone">
                </td>
            </tr>
            <tr>
                <td>카드번호</td>
                <td colspan="3">
                    <input type="text" id="user_card">
                </td>
            </tr>
            <tr>
                <td>계좌번호</td>
                <td colspan="3">
                    <input type="text" id="user_account">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <button id="join">회원가입</button>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>