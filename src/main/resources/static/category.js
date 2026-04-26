const API_BASE_URL = "http://localhost:8080";

const categoryTitle = document.getElementById("categoryTitle");
const categorySubTitle = document.getElementById("categorySubTitle");
const performanceGrid = document.getElementById("performanceGrid");

document.addEventListener("DOMContentLoaded", () => {
    const categoryId = getCategoryIdFromUrl();

    if (!categoryId) {
        performanceGrid.innerHTML = `<p class="empty">카테고리 정보가 없습니다.</p>`;
        return;
    }

    setCategoryText(categoryId);
    loadPerformancesByCategory(categoryId);
});

function getCategoryIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get("categoryId");
}

function setCategoryText(categoryId) {
    const categoryMap = {
        "1": "밴드동아리",
        "2": "공연영상학과",
        "3": "학생회",
        "4": "기타동아리"
    };

    const categoryName = categoryMap[categoryId] || "카테고리";

    categoryTitle.textContent = categoryName;
    categorySubTitle.textContent = `${categoryName}의 공연과 행사를 확인해보세요.`;
}

async function loadPerformancesByCategory(categoryId) {
    try {
        const response = await fetch(`${API_BASE_URL}/performances/category/${categoryId}`);

        if (!response.ok) {
            throw new Error("카테고리별 공연 조회 실패");
        }

        const performances = await response.json();
        renderPerformances(performances);

    } catch (error) {
        console.error(error);
        performanceGrid.innerHTML = `<p class="empty">공연 정보를 불러오지 못했습니다.</p>`;
    }
}

function renderPerformances(performances) {
    performanceGrid.innerHTML = "";

    if (!performances || performances.length === 0) {
        performanceGrid.innerHTML = `<p class="empty">등록된 공연이 없습니다.</p>`;
        return;
    }

    performances.forEach(performance => {
        const card = document.createElement("article");
        card.className = "performance-card";

        card.addEventListener("click", () => {
            location.href = `/detail.html?id=${performance.id}`;
        });

        card.innerHTML = `
            <div class="poster-wrap">
                <img 
                    src="${performance.posterUrl || 'https://images.unsplash.com/photo-1507874457470-272b3c8d8ee2?q=80&w=400'}" 
                    alt="${performance.name || '공연 포스터'}"
                />
            </div>

            <h3>${performance.name || '공연명 없음'}</h3>
            <p>${performance.description || '공연 설명이 없습니다.'}</p>
            <div class="performance-meta">
                ${performance.location || '장소 미정'} · ${formatDate(performance.performanceDate)}
            </div>
        `;

        performanceGrid.appendChild(card);
    });
}

function formatDate(dateString) {
    if (!dateString) return "날짜 미정";

    const date = new Date(dateString);

    return date.toLocaleDateString("ko-KR", {
        month: "long",
        day: "numeric",
        weekday: "short"
    });
}