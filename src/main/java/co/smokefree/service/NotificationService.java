package co.smokefree.service;

import org.springframework.http.HttpStatus;
import co.smokefree.dto.notification.NotificationResponse;
import co.smokefree.entity.Notification;
import co.smokefree.entity.User;
import co.smokefree.exception.AppException;
import co.smokefree.repository.NotificationRepository;
import co.smokefree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    private final String[] motivationalMessages = {
            "Bạn đang làm rất tốt! Hãy tiếp tục phát huy trên hành trình bỏ thuốc lá.",
            "Mỗi điếu thuốc không hút là một chiến thắng. Hãy tự hào về những cố gắng của bạn!",
            "Hãy nhớ lý do bạn bắt đầu hành trình này. Bạn mạnh mẽ hơn cơn thèm thuốc!",
            "Sức khỏe của bạn đang cải thiện từng ngày vì không hút thuốc lá. Tiếp tục phát huy nhé!",
            "Hãy nghĩ về số tiền bạn đang tiết kiệm được khi không mua thuốc lá!",
            "Phần khó khăn nhất đã qua rồi. Đừng bỏ cuộc lúc này nhé!",
            "Cơ thể bạn đang hồi phục. Mỗi một ngày không thuốc lá đều tạo nên sự khác biệt!",
            "Bạn đang truyền cảm hứng cho những người muốn bỏ thuốc lá!",
            "Lợi ích của việc bỏ thuốc sẽ ngày càng tăng theo thời gian. Hãy kiên trì!",
            "Bạn không chỉ đang bỏ thuốc lá, bạn đang giành lại một cuộc sống khỏe mạnh hơn!",
            "Hôm nay bạn đã chiến thắng bản thân. Ngày mai sẽ dễ dàng hơn!",
            "Không khí trong lành, hơi thở thơm tho - đó là phần thưởng xứng đáng cho bạn!",
            "Mỗi giờ trôi qua không thuốc lá là một bước tiến gần hơn tới thành công!",
            "Bạn đang viết nên câu chuyện thành công của chính mình - câu chuyện về ý chí mạnh mẽ!",
            "Hãy tự hào vì bạn đang làm điều mà nhiều người không dám thực hiện!"
    };

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyMotivationalNotifications() {
        List<User> activeUsers = userRepository.findAll();
        Random random = new Random();

        for (User user : activeUsers) {
            String message = motivationalMessages[random.nextInt(motivationalMessages.length)];

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage(message);
            notification.setCreatedAt(LocalDateTime.now());
            notification.setRead(false);

            notificationRepository.save(notification);
        }
    }

    public List<NotificationResponse> getUserNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        return notificationRepository.findByUser(user).stream()
                .map(this::mapToNotificationResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponse markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new AppException("Không tìm thấy thông báo!", HttpStatus.NOT_FOUND));

        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return mapToNotificationResponse(updatedNotification);
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getNotificationId());
        response.setMessage(notification.getMessage());
        response.setSentAt(notification.getCreatedAt().toString());
        response.setRead(notification.isRead());
        return response;
    }
}