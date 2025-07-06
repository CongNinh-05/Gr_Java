package co.smokefree.service;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.message.MessageRequest;
import co.smokefree.dto.message.MessageResponse;
import co.smokefree.entity.Message;
import co.smokefree.entity.User;
import co.smokefree.exception.AppException;
import co.smokefree.repository.MessageRepository;
import co.smokefree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
        private final MessageRepository messageRepository;
        private final UserRepository userRepository;

        @Transactional
        public ApiResponse sendMessage(MessageRequest request) {
                User sender = userRepository.findById(request.getSenderId())
                                .orElseThrow(() -> new AppException("Người gửi không tồn tại", HttpStatus.NOT_FOUND));

                User receiver = userRepository.findById(request.getReceiverId())
                                .orElseThrow(() -> new AppException("Người nhận không tồn tại", HttpStatus.NOT_FOUND));

                Message message = new Message();
                message.setSender(sender);
                message.setReceiver(receiver);
                message.setMessageText(request.getContent());
                message.setSentAt(LocalDateTime.now());

                messageRepository.save(message);
                return new ApiResponse(true, "Gửi tin nhắn thành công");
        }

        public List<MessageResponse> getMessageHistory(Long senderId, Long receiverId) {
                User sender = userRepository.findById(senderId)
                                .orElseThrow(() -> new AppException("Người gửi không tồn tại", HttpStatus.NOT_FOUND));

                User receiver = userRepository.findById(receiverId)
                                .orElseThrow(() -> new AppException("Người nhận không tồn tại", HttpStatus.NOT_FOUND));

                return messageRepository.findBySenderAndReceiver(sender, receiver)
                                .stream()
                                .map(this::convertToResponse)
                                .collect(Collectors.toList());
        }

        @Transactional
        public ApiResponse markAsRead(Long messageId) {
                Message message = messageRepository.findById(messageId)
                                .orElseThrow(() -> new AppException("Tin nhắn không tồn tại", HttpStatus.NOT_FOUND));

                message.setRead(true);
                messageRepository.save(message);
                return new ApiResponse(true, "Đã đánh dấu đã đọc");
        }

        private MessageResponse convertToResponse(Message message) {
                return MessageResponse.builder()
                                .id(message.getMessageId())
                                .senderName(message.getSender().getUserName())
                                .receiverName(message.getReceiver().getUserName())
                                .content(message.getMessageText())
                                .sentAt(message.getSentAt())
                                .isRead(message.isRead())
                                .build();
        }
}