package service;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class NotificationService {

    @KafkaListener(topics = "book-created", groupId = "notification-group")
    public void handleNotification(BookDTO bookDTO) {
    System.out.println("------------------------------------------------");
    System.out.println("📧 RECEIVED KAFKA MESSAGE: New Book Added!");
    System.out.println("📖 Title: " + bookDTO.getTitle());
    System.out.println("------------------------------------------------");
    }
}
