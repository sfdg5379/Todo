<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script>
        function checkDuplicate() {
            const username = document.getElementById('username').value;
            if (!username) {
                alert("아이디를 입력해주세요.");
                return;
            }

            // 서버에 중복확인 요청 (GET 방식)
            fetch(`${location.origin}${location.pathname.replace('enroll.jsp', '')}checkId?username=${username}`)
                .then(response => response.text())
                .then(result => {
                    if (result === 'true') {
                        alert("이미 사용 중인 아이디입니다.");
                    } else {
                        alert("사용 가능한 아이디입니다.");
                    }
                })
                .catch(error => {
                    console.error("중복 확인 중 오류:", error);
                    alert("오류가 발생했습니다.");
                });
        }
    </script>
</head>
<body>
    <h2>회원가입</h2>
    <form action="enroll" method="post">
        <label>아이디: 
            <input type="text" name="username" id="username" required>
            <button type="button" onclick="checkDuplicate()">중복확인</button>
        </label><br><br>

        <label>비밀번호: 
            <input type="password" name="password" required>
        </label><br><br>

        <label>이메일: 
            <input type="email" name="email">
        </label><br><br>

        <button type="submit">가입하기</button>
    </form>
</body>
</html>
