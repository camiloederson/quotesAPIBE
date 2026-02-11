package com.mikadev.quotesapi.notifications;

import com.mikadev.quotesapi.entities.QuoteEntity;
import com.mikadev.quotesapi.repositories.QuoteRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;

@Service
public class EmailNotificationService {

    private final JavaMailSender javaMailSender;
    private final QuoteRepository quoteRepository;

    public EmailNotificationService(JavaMailSender javaMailSender, QuoteRepository quoteRepository) {
        this.javaMailSender = javaMailSender;
        this.quoteRepository = quoteRepository;
    }

    public EmailResponseDTO sendEmail() {

        QuoteEntity quoteEntity = quoteRepository.findRandomQuote();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("camilo.ederson@gmail.com");
        message.setTo("camilo.ederson@gmail.com", "marieturcios1@gmail.com");
        message.setSubject("English Quote of the Day");
        String body = quoteEntity.getQuote() + "\n"
                + quoteEntity.getAuthor().getFirstName().toUpperCase()
                + " " + (quoteEntity.getAuthor().getSecondName() != null ? quoteEntity.getAuthor().getSecondName().toUpperCase()
                + " " : "") + (quoteEntity.getAuthor().getSurname() != null ? quoteEntity.getAuthor().getSurname().toUpperCase() : "");
        message.setText(body);

        try {
            javaMailSender.send(message);
            return new EmailResponseDTO(
                    "Success", "The email has been sent"
            );

        } catch (Exception e) {
            return new EmailResponseDTO(
                    "Failed", "The email has not been sent" + e.getMessage()
            );
        }
    }

}
