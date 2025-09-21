document.addEventListener("DOMContentLoaded", function () {
  console.log("Profile page loaded. Bootstrap is ready!");

  var tabEl = document.querySelectorAll(
    '#profileTabs button[data-bs-toggle="tab"]'
  );
  tabEl.forEach(function (tab) {
    tab.addEventListener("shown.bs.tab", function (event) {
      console.log("Tab mới hoạt động: " + event.target.id);
    });
  });
});
