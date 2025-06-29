package co.smokefree.controller;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.message.MessageRequest;
import co.smokefree.dto.message.MessageResponse;
import co.smokefree.exception.AppException;
import co.smokefree.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Tag(name = "Message", description = "API quản lý tin nhắn giữa người dùng")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Gửi tin nhắn mới")
    public ResponseEntity<ApiResponse> sendMessage(
            @Valid @RequestBody MessageRequest request) {
        try {
            ApiResponse response = messageService.sendMessage(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AppException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(new ApiResponse(false, ex.getMessage()));
        }
    }

    @GetMapping("/history/{senderId}/{receiverId}")
    @Operation(summary = "Lấy lịch sử tin nhắn")
    public ResponseEntity<List<MessageResponse>> getMessageHistory(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {
        try {
            List<MessageResponse> messages = messageService.getMessageHistory(senderId, receiverId);
            return ResponseEntity.ok(messages);
        } catch (AppException ex) {
            throw ex;
        }
    }

    @PatchMapping("/{messageId}/read")
    @Operation(summary = "Đánh dấu tin nhắn đã đọc")
    public ResponseEntity<ApiResponse> markAsRead(
            @PathVariable Long messageId) {
        try {
            ApiResponse response = messageService.markAsRead(messageId);
            return ResponseEntity.ok(response);
        } catch (AppException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(new ApiResponse(false, ex.getMessage()));
        }
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ApiResponse(false, ex.getMessage()));
    }
}