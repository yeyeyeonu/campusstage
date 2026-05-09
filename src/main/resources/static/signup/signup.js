const API_BASE_URL = "http://localhost:8081";

let currentStep = 1;
const totalStep = 7;

const collegeData = {

    "의과대학": [
        "의예과",
        "의학과",
        "간호학과"
    ],

    "자연과학대학": [
        "화학과",
        "식품영양학과",
        "환경보건학과",
        "생명과학과",
        "스포츠과학과",
        "스포츠응용산업학과",
        "스포츠의학과"
    ],

    "인문사회과학대학": [
        "유아교육과",
        "특수교육과",
        "청소년교육상담학과",
        "법학과",
        "행정학과",
        "경찰행정학과",
        "사회복지학과"
    ],

    "글로벌경영대학": [
        "경영학과",
        "국제통상학과",
        "관광경영학과",
        "경제금융학과",
        "IT금융경영학과",
        "글로벌문화산업학과",
        "회계학과",
        "GBS"
    ],

    "SW융합대학": [
        "컴퓨터소프트웨어공학과",
        "정보보호학과",
        "의료IT공학과",
        "AI・빅데이터학과",
        "사물인터넷학과",
        "메타버스&게임학과"
    ],

    "공과대학":[
        "컴퓨터공학과",
        "정보통신공학과",
        "전자공학과",
        "전기공학과",
        "전자정보공학과",
        "나노화학공학과",
        "에너지환경공학과",
        "디스플레이신소재공학과",
        "기계공학과"
    ],

    "의료과학대학":[
        "보건행정경영학과",
        "의료생명공학과",
        "임상병리학과",
        "작업치료학과",
        "의약공학과",
        "의공학과"
    ],

    "SCH미디어랩스":[
        "한국문화콘텐츠학과",
        "영미학과",
        "중국학과",
        "미디어커뮤니케이션학과",
        "건축학과",
        "디지털애니메이션학과",
        "스마트자동차학과",
        "에너지공학과",
        "공연영상학과",
        "탄소중립학과",
        "의생명융합학부 헬스케어융합전공",
        "의생명융합학부 바이오의약전공",
    ],

    "기타": [
        "기타"
    ]
};

let signupData = {
    userid: "",
    password: "",
    name: "",
    college: "",
    major: "",
    email: "",
    gender: "",
    nickname: ""
};

function showStep(step) {

    document.querySelectorAll(".step").forEach(stepEl => {
        stepEl.classList.remove("active");
    });

    document.getElementById(`step${step}`)
        .classList.add("active");

    document.getElementById("stepText")
        .innerText = `${step} / ${totalStep}`;

    restoreInputValues();
}

function restoreInputValues() {

    if (document.getElementById("userid")) {
        document.getElementById("userid").value =
            signupData.userid;
    }

    if (document.getElementById("password")) {
        document.getElementById("password").value =
            signupData.password;
    }

    if (document.getElementById("name")) {
        document.getElementById("name").value =
            signupData.name;
    }

    if (document.getElementById("email")) {
        document.getElementById("email").value =
            signupData.email;
    }
}

function nextStep() {
    if (currentStep < totalStep) {
        currentStep++;
        showStep(currentStep);
    }
}

function prevStep() {
    if (currentStep > 1) {
        currentStep--;
        showStep(currentStep);
    } else {
        location.href = "../index/index.html";
    }
}

function saveUserid() {
    const userid = document.getElementById("userid").value.trim();

    if (!userid) {
        alert("아이디를 입력해주세요.");
        return;
    }

    signupData.userid = userid;
    nextStep();
}

function savePassword() {
    const password = document.getElementById("password").value.trim();

    if (!password) {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    if (password.length < 4) {
        alert("비밀번호는 4자 이상 입력해주세요.");
        return;
    }

    signupData.password = password;
    nextStep();
}

function checkPassword() {
    const passwordConfirm = document.getElementById("passwordConfirm").value.trim();

    if (signupData.password !== passwordConfirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    nextStep();
}

function saveName() {
    const name = document.getElementById("name").value.trim();

    if (!name) {
        alert("이름을 입력해주세요.");
        return;
    }

    signupData.name = name;
    signupData.nickname = name;

    nextStep();
}

function saveCollegeMajor() {

    const college =
        document.getElementById("collegeSelect").value;

    const major =
        document.getElementById("majorSelect").value;

    if (!college || !major) {

        alert("단과대와 학과를 선택해주세요.");

        return;
    }

    signupData.college = college;
    signupData.major = major;

    nextStep();
}

function saveEmail() {
    const email = document.getElementById("email").value.trim();

    if (!email) {
        alert("이메일을 입력해주세요.");
        return;
    }

    signupData.email = email;

    nextStep();
}

function selectGender(gender) {
    signupData.gender = gender;

    document.querySelectorAll(".gender-box button").forEach(btn => {
        btn.classList.remove("selected");
    });

    event.target.classList.add("selected");
}

async function signup() {
    if (!signupData.gender) {
        alert("성별을 선택해주세요.");
        return;
    }

    const response = await fetch(`${API_BASE_URL}/auth/signup`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(signupData)
    });

    const result = await response.text();

    if (result === "회원가입 성공") {
        alert("회원가입 성공! 로그인해주세요.");
        location.href = "../login/login.html";
    } else {
        alert(result);
    }
}

function loadCollegeOptions() {

    const collegeSelect =
        document.getElementById("collegeSelect");

    collegeSelect.innerHTML =
        `<option value="">단과대 선택</option>`;

    for (const college in collegeData) {

        const option = document.createElement("option");

        option.value = college;
        option.textContent = college;

        collegeSelect.appendChild(option);
    }
}

function updateMajorOptions() {

    const college =
        document.getElementById("collegeSelect").value;

    const majorSelect =
        document.getElementById("majorSelect");

    majorSelect.innerHTML =
        `<option value="">학과 선택</option>`;

    if (!college || !collegeData[college]) {
        return;
    }

    collegeData[college].forEach(major => {

        const option = document.createElement("option");

        option.value = major;
        option.textContent = major;

        majorSelect.appendChild(option);
    });
}

document.addEventListener("DOMContentLoaded", () => {

    showStep(currentStep);

    loadCollegeOptions();

});

document.addEventListener("DOMContentLoaded", () => {

    showStep(currentStep);

    loadCollegeOptions();

});