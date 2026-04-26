const API_BASE_URL = "http://localhost:8080";

const detailContainer = document.getElementById("detailContainer");

document.addEventListener("DOMContentLoaded", () => {
    const performanceId = getPerformanceIdFromUrl();

    if (!performanceId) {
        detailContainer.innerHTML = `<p class="empty">공연 정보가 없습니다.</p>`;
        return;
    }

    loadPerformanceDetail(performanceId);
});

function getPerformanceIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get("id");
}

async function loadPerformanceDetail(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/performances/detail/${id}`);

        if (!response.ok) {
            throw new Error("공연 상세 조회 실패");
        }

        const performance = await response.json();
        renderDetail(performance);

    } catch (error) {
        console.error(error);
        detailContainer.innerHTML = `<p class="empty">공연 상세 정보를 불러오지 못했습니다.</p>`;
    }
}

function renderDetail(performance) {
    detailContainer.innerHTML = `
        <img
            class="poster"
            src="${performance.posterUrl || 'https://images.unsplash.com/photo-1507874457470-272b3c8d8ee2?q=80&w=500'}"
            alt="${performance.name || '공연 포스터'}"
        />

        <span class="badge">${performance.status || '예매중'}</span>

        <h2 class="title">${performance.name || '공연명 없음'}</h2>

        <p class="meta">장소: ${performance.location || '장소 미정'}</p>
        <p class="meta">일시: ${formatDate(performance.performanceDate)}</p>

        <section class="description">
            <h2>공연 소개</h2>
            <p>${performance.description || '공연 설명이 없습니다.'}</p>
        </section>

        <section class="description">
            <h2>상세 정보</h2>
            <p>${performance.detailInfo || '상세 정보가 없습니다.'}</p>
        </section>

        <button class="reserve-btn">예매하기</button>
    `;
}

function formatDate(dateString) {
    if (!dateString) return "날짜 미정";

    const date = new Date(dateString);

    return date.toLocaleString("ko-KR", {
        year: "numeric",
        month: "long",
        day: "numeric",
        weekday: "short",
        hour: "2-digit",
        minute: "2-digit"
    });
}