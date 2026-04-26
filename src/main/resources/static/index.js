const API_BASE_URL = "http://localhost:8080";

const recommendList = document.getElementById("recommendList");

// 페이지 처음 열리면 밴드동아리 추천부터 출력
document.addEventListener("DOMContentLoaded", () => {
    loadRecommendByCategory(1);
    bindTopCategoryEvents();
    bindRecommendTabEvents();
});

// 상단 카테고리: 카테고리별 목록 페이지로 이동
function bindTopCategoryEvents() {
    const topButtons = document.querySelectorAll(".top-category button");

    topButtons.forEach(button => {
        button.addEventListener("click", () => {
            const categoryId = button.dataset.categoryId;

            // 나중에 category.html 만들면 여기로 이동
            location.href = `/category.html?categoryId=${categoryId}`;
        });
    });
}

// 하단 추천 탭: 메인 화면 안에서 추천 공연만 변경
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

// 카테고리별 공연 불러오기
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

// 추천 공연 카드 출력
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