(function ($) { 
    'use strict';

    // Ẩn preloader khi trang tải xong
    $(window).on('load', function () {
        $('.preloader').fadeOut(100);
    });

    // Thêm hoặc xóa hiệu ứng shadow cho thanh navbar khi cuộn trang
    $(window).scroll(function () {
        if ($(window).scrollTop() > 50) {
            $('#main-navbar').addClass('shadow');
        } else {
            $('#main-navbar').removeClass('shadow');
        }
    });

    $(document).ready(function() {
        // Xử lý sự kiện click dropdown user
        $('#userDropdown').click(function(e) {
            e.stopPropagation();
            $('#user-info').toggleClass('active');
        });
      
        // Ẩn dropdown khi click ra ngoài
        $(document).click(function() {
            $('#user-info').removeClass('active');
        });
    });

    $(document).ready(function() {
        // Nếu tồn tại carousel câu chuyện
        if ($('#story-carousel').length) {
            const carousel = $('#story-carousel');
            const images = $('#story-carousel img');
            const dotsContainer = $('#carousel-dots');
            const prevBtn = $('.prev-btn');
            const nextBtn = $('.next-btn');

            let currentIndex = 0;
            const visibleItems = 6; // Số lượng ảnh hiển thị cùng lúc
            const imageWidth = images.eq(0).width() + 30; // Chiều rộng ảnh + khoảng cách
            const totalItems = images.length;
            const numDots = Math.max(totalItems - visibleItems + 1, 0); // Số dots

            // Xóa dots cũ trước khi tạo mới
            dotsContainer.empty();

            // Tạo các dots điều hướng
            for (let i = 0; i < numDots; i++) {
                const dot = $('<span class="dot"></span>');
                if (i === 0) dot.addClass('active');
                dot.on('click', () => goToSlide(i));
                dotsContainer.append(dot);
            }

            const dots = $('.dot');

            // Tự động chuyển ảnh mỗi 3 giây
            let interval = setInterval(nextSlide, 3000);

            function updateCarousel() {
                carousel.css('transform', `translateX(-${currentIndex * imageWidth}px)`);
                
                // Cập nhật dot active
                dots.removeClass('active').eq(currentIndex).addClass('active');
            }

            function nextSlide() {
                if (currentIndex < totalItems - visibleItems) { 
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                updateCarousel();
            }

            function prevSlide() {
                if (currentIndex > 0) { 
                    currentIndex--;
                } else {
                    currentIndex = totalItems - visibleItems;
                }
                updateCarousel();
            }

            function goToSlide(index) {
                currentIndex = index;
                updateCarousel();
                resetInterval();
            }

            function resetInterval() {
                clearInterval(interval);
                interval = setInterval(nextSlide, 3000);
            }

            // Xử lý sự kiện click nút next và prev
            nextBtn.on('click', function() {
                nextSlide();
                resetInterval();
            });

            prevBtn.on('click', function() {
                prevSlide();
                resetInterval();
            });

            // Dừng tự động chuyển ảnh khi hover vào carousel
            carousel.on('mouseenter', () => clearInterval(interval));
            carousel.on('mouseleave', () => resetInterval());
        }
    });    

    $(document).ready(function() {
        function adjustContentHeight() {
            var windowHeight = $(window).height();
            var headerHeight = $('#admin-header').outerHeight();
            var contentHeight = windowHeight - headerHeight;
            
            $('#admin-main').css('height', contentHeight);
        }
    
        adjustContentHeight();
        $(window).resize(adjustContentHeight);
    });  
    
    
 

})(jQuery);


// Đang kí Tài Khoản 
document.addEventListener("DOMContentLoaded", () => {
  const registerForm = document.getElementById("auth-form");
  
  registerForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const avatarInput = document.getElementById("avatar");

    // Kiểm tra mật khẩu khớp
    if (password !== confirmPassword) {
      alert("Mật khẩu không khớp!");
      return;
    }

    // Chuẩn bị dữ liệu
    const formData = new FormData();
    formData.append("name", name);
    formData.append("email", email);
    formData.append("password", password);
    if (avatarInput.files.length > 0) {
      formData.append("avatar", avatarInput.files[0]);
    }

    try {
      const res = await fetch("https:  ", {  // Thay APi zô đây
        method: "POST",
        body: formData
      });

      if (!res.ok) throw new Error("Đăng ký thất bại");

      const data = await res.json();
      alert("Đăng ký thành công! Mời bạn đăng nhập.");
      window.location.href = "login.html";
    } catch (err) {
      console.error("Lỗi đăng ký:", err);
      alert("⚠️ Đăng ký không thành công. Vui lòng thử lại!");
    }
  });
});




 
// đang nhap 

document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("auth-form");
  const emailInput = document.getElementById("email");
  const passwordInput = document.getElementById("password");

  loginForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    fetch("https://your-api.com/api/login", {                 // thay URL API zo đây nha
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password })
    })
      .then(res => {
        if (!res.ok) throw new Error("Tài khoản không đúng");
        return res.json();
      })
      .then(data => {
        // Có thể lưu token nếu backend trả về
        // localStorage.setItem("token", data.token);
        window.location.href = "index.html"; 
      })
      .catch(err => {
        console.error("Lỗi đăng nhập:", err);
        alert("Sai email hoặc mật khẩu!");
      });
  });
});




// Nhat ki theo doi 

const API_URL = 'https:              ';              // (thay bằng URL API thật của backend zô đây)

window.onload = async () => {
  try {
    const response = await fetch(API_URL);
    const data = await response.json();
    // Hiển thị số thuốc hút
    document.getElementById('cigaretteCount').textContent = `${data.cigarettes} điếu`;
    // Hiển thị thông tin sức khỏe
    const healthList = document.getElementById('healthInfo');
    healthList.innerHTML = '';
    data.health.forEach(item => {
      const li = document.createElement('li');
      li.classList.add('list-group-item');
      li.textContent = item;
      healthList.appendChild(li);
    });

  } catch (error) {
    document.getElementById('cigaretteCount').textContent = 'Lỗi tải dữ liệu!';
    console.error('API error:', error);
  }
};



// Thong bao

notificationButton.addEventListener("click", () => {
    const popup = document.getElementById("notificationPopup");
    const content = document.getElementById("notificationContent");

    popup.style.display = "block";
    content.innerHTML = "<p>Đang tải thông báo...</p>";

    fetch("https:                ")                        // Thay API vào đây
        .then(res => {
            if (!res.ok) throw new Error("Lỗi khi gọi API");
            return res.json();
        })
        .then(data => {
            if (!Array.isArray(data) || data.length === 0) {
                content.innerHTML = "<p>Không có thông báo nào.</p>";
                return;
            }

            const html = data.map(item => `
                <div class="alert alert-success mb-2">
                    <strong>${item.title}</strong><br>
                    ${item.message}
                </div>
            `).join('');

            content.innerHTML = html;
        })
        .catch(err => {
            console.error("Lỗi lấy thông báo:", err);
            content.innerHTML = "<p>Không thể tải thông báo.</p>";
        });
});


// Tin nhan

document.addEventListener("DOMContentLoaded", () => {
  const chatBox = document.getElementById("chatBox");
  const chatForm = document.getElementById("chatForm");
  const messageInput = document.getElementById("messageInput");

  function addMessage(text, sender = "bot") {
    const message = document.createElement("div");
    message.classList.add("chat-message", sender === "user" ? "user-message" : "bot-message");
    message.textContent = text;
    chatBox.appendChild(message);
    chatBox.scrollTop = chatBox.scrollHeight;
  }

  // Gửi tin nhắn lên server
  chatForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const userMessage = messageInput.value.trim();
    if (userMessage !== "") {
      addMessage(userMessage, "user");
      messageInput.value = "";

      fetch("https:      ", {                       // Thay API vào đây
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: userMessage })
      })
        .then(res => res.json())
        .then(data => {
          if (data.reply) {
            addMessage(data.reply, "bot");
          }
        })
        .catch(err => {
          console.error("Lỗi gửi tin nhắn:", err);
          addMessage("⚠️ Lỗi khi gửi tin nhắn.", "bot");
        });
    }
  });

  // Polling lấy tin nhắn mới từ server (mỗi 3s)
  setInterval(() => {
    fetch("https:     ")                                   // Thay API zô đây 
      .then(res => res.json())
      .then(messages => {
        messages.forEach(msg => {
          addMessage(msg.content, msg.sender);
        });
      });
  }, 3000);
});







