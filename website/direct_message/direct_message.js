const chatList = document.getElementById("chatList");
const chatMessages = document.getElementById("chatMessages");
const chatWith = document.getElementById("chatWith");
const messageForm = document.getElementById("messageForm");
const messageInput = document.getElementById("messageInput");

// Mock data (tin nhắn theo user)
const conversations = {
  Alice: [
    { from: "other", text: "Xin chào 👋" },
    { from: "me", text: "Hello Alice!" },
  ],
  Bob: [{ from: "other", text: "Ê, mai đi chơi không?" }],
  Charlie: [],
};
let currentUser = "Alice";

// Hiển thị tin nhắn theo user
function renderMessages(user) {
  chatMessages.innerHTML = "";
  conversations[user].forEach((m) => {
    const div = document.createElement("div");
    div.className = "message " + m.from;
    div.textContent = m.text;
    chatMessages.appendChild(div);
  });
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Chọn bạn chat
chatList.addEventListener("click", (e) => {
  if (e.target.classList.contains("list-group-item")) {
    document
      .querySelectorAll("#chatList .list-group-item")
      .forEach((li) => li.classList.remove("active"));
    e.target.classList.add("active");
    currentUser = e.target.dataset.user;
    chatWith.textContent = currentUser;
    renderMessages(currentUser);
  }
});

// Gửi tin nhắn
messageForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const text = messageInput.value.trim();
  if (!text) return;
  conversations[currentUser].push({ from: "me", text });
  renderMessages(currentUser);
  messageInput.value = "";

  // Giả lập người kia trả lời sau 1s
  setTimeout(() => {
    conversations[currentUser].push({
      from: "other",
      text: "👍 Đã nhận: " + text,
    });
    renderMessages(currentUser);
  }, 1000);
});

renderMessages(currentUser);
