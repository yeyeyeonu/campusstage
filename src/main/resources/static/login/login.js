const API_BASE_URL = "http://localhost:8081";

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("loginBtn")
        .addEventListener("click", login);
});

async function login() {

    const userid =
        document.getElementById("userid").value.trim();

    const password =
        document.getElementById("password").value.trim();

    if (!userid || !password) {

        alert("아이디와 비밀번호를 입력해주세요.");

        return;
    }

    try {

        const response = await fetch(`${API_BASE_URL}/auth/login`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                userid,
                password
            })
        });

        const result = await response.text();

        if (result === "로그인 성공") {

            localStorage.setItem("loginUser", userid);

            alert("로그인 성공!");

            location.href = "../index.html";

        } else {

            alert(result);
        }

    } catch (error) {

        console.error(error);

        alert("서버 연결 실패");
    }
}