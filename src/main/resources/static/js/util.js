function fetchUtil(url, action){
    fetch(url)
        .then(data => data.json())
    .then(action)
    .catch(err => console.error(
        "에러"
    ));
}

$(document).on("click", "#pagination .page-link", function () {
    const page = $(this).data("page");  // data-page 값 가져오기

    // 숫자일 경우
    if (!isNaN(page)) {
        loadPage(page);
    }

    // 이전/다음
    if (page === "prev") {
        const current = $("#pagination .active a").data("page");
        if (current > 1) loadPage(current - 1);
    }

    if (page === "next") {
        const current = $("#pagination .active a").data("page");
        loadPage(current + 1);
    }
});

function updatePaginationUI(page, totalPages) {
    const pag = $("#pagination");
    pag.find(".page-item").removeClass("active");

    // 현재 페이지 활성화
    pag.find(`a[data-page='${page}']`).closest(".page-item").addClass("active");

    // 이전 버튼 비활성화
    if (page == 1) {
        pag.find("a[data-page='prev']").closest(".page-item").addClass("disabled");
    } else {
        pag.find("a[data-page='prev']").closest(".page-item").removeClass("disabled");
    }

    // 다음 버튼 비활성화
    if (page == totalPages) {
        pag.find("a[data-page='next']").closest(".page-item").addClass("disabled");
    } else {
        pag.find("a[data-page='next']").closest(".page-item").removeClass("disabled");
    }
}