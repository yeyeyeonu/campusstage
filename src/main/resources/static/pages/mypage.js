const API_BASE_URL = "http://localhost:9090";
const userId = localStorage.getItem("userId");


document.addEventListener("DOMContentLoaded", () => {
    loadProfile();
    loadFavorites();
    loadWatchHistory();
    loadReviews();
});

async function loadProfile() {
    const response = await fetch(`${API_BASE_URL}/mypage/${userId}`);
    const user = await response.json();

    document.getElementById("name").textContent = user.name;
    document.getElementById("userid").textContent = user.userid;
    document.getElementById("major").textContent = user.major;
    document.getElementById("nickname").textContent = user.nickname;
}

async function loadFavorites() {
    const response = await fetch(`${API_BASE_URL}/mypage/${userId}/favorites`);
    const favorites = await response.json();

    const container = document.getElementById("favorites");
    container.innerHTML = favorites.map(item => `
        <div class="item-card">
            <img src="${item.posterUrl || ''}" alt="공연 포스터">
            <h3>${item.performanceName}</h3>
            <p>찜한 공연</p>
        </div>
    `).join("");
}

async function loadWatchHistory() {
    const response = await fetch(`${API_BASE_URL}/mypage/${userId}/watch-history`);
    const histories = await response.json();

    const container = document.getElementById("watchHistory");
    container.innerHTML = histories.map(item => `
        <div class="item-card">
            <img src="${item.posterUrl || ''}" alt="공연 포스터">
            <h3>${item.performanceName}</h3>
            <p>관람 완료</p>
        </div>
    `).join("");
}

async function loadReviews() {
    const response = await fetch(`${API_BASE_URL}/mypage/${userId}/reviews`);
    const reviews = await response.json();

    const container = document.getElementById("reviews");
    container.innerHTML = reviews.map(item => `
        <div class="item-card">
            <h3>${item.performanceName}</h3>
            <p>${item.content}</p>
        </div>
    `).join("");
}