const API_BASE_URL = "http://localhost:9090";

const recommendList = document.getElementById("recommendList");

document.addEventListener("DOMContentLoaded", () => {
    loadRecommendByCategory(1);
    bindTopCategoryEvents();
    bindRecommendTabEvents();
    renderUserMenu();
});

function bindTopCategoryEvents() {
    const topButtons = document.querySelectorAll(".top-category button");

    topButtons.forEach(button => {
        button.addEventListener("click", () => {
            const categoryId = button.dataset.categoryId;
            location.href = `/category.html?categoryId=${categoryId}`;
        });
    });
}

function bindRecommendTabEvents() {
    const tabButtons = document.querySelectorAll(".recommend-tabs button");

    tabButtons.forEach(button => {
        button.addEventListener("click", () => {
            tabButtons.forEach(btn => btn.classList.remove("active"));
            button.classList.add("active");

            const categoryId = button.dataset.categoryId;
            loadRecommendByCategory(categoryId);
        });
    });
}

async function loadRecommendByCategory(categoryId) {
    try {
        const response = await fetch(`${API_BASE_URL}/performances/${categoryId}`);

        if (!response.ok) {
            throw new Error("공연 목록 조회 실패");
        }

        const performances = await response.json();
        renderRecommendList(performances);

    } catch (error) {
        console.error(error);
        recommendList.innerHTML = `<p class="empty">공연 정보를 불러오지 못했습니다.</p>`;
    }
}

function renderRecommendList(performances) {
    recommendList.innerHTML = "";

    if (!performances || performances.length === 0) {
        recommendList.innerHTML = `<p class="empty">등록된 공연이 없습니다.</p>`;
        return;
    }

    performances.forEach(performance => {
        const card = document.createElement("div");
        card.className = "recommend-card";

        card.addEventListener("click", () => {
            location.href = `/detail.html?id=${performance.id}`;
        });

        card.innerHTML = `
            <img 
                src="${performance.posterUrl || 'https://images.unsplash.com/photo-1507874457470-272b3c8d8ee2?q=80&w=400'}" 
                alt="${performance.name || '공연 포스터'}"
            />
            <h4>${performance.name || '공연명 없음'}</h4>
            <p>${performance.location || '장소 미정'}</p>
        `;

        recommendList.appendChild(card);
    });
}

function renderUserMenu() {
    const userMenu = document.getElementById("userMenu");
    const loginUser = localStorage.getItem("loginUser");


    if (loginUser) {

        userMenu.innerHTML = `
        <div class="welcome-box">

            <p class="welcome-text">
                ${loginUser}님 반갑습니다!
            </p>

            <div class="menu-links">

                <span id="logoutBtn"
                      class="user-menu-btn">
                    로그아웃
                </span>

                <span>/</span>

                <button class="user-menu-btn"
                        onclick="location.href='/pages/mypage.html'">
                    마이페이지
                </button>

            </div>

        </div>
        `;

        document.getElementById("logoutBtn")
            .addEventListener("click", logout);

    } else {
        userMenu.innerHTML = `
            <button class="user-menu-btn" onclick="location.href='/signup/signup.html'">
                회원가입
            </button>
            <span>/</span>
            <button class="user-menu-btn" onclick="location.href='/login/login.html'">
                로그인
            </button>
<!--            <button class="user-menu-btn" onclick="location.href='/mypage.html'">-->
<!--                마이페이지-->
<!--            </button>-->
        `;
    }
}

function logout() {
    localStorage.removeItem("loginUser");

    alert("로그아웃 되었습니다.");

    location.reload();
}