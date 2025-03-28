package ru.glindaquint.everwell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Почтовый сервис
 *
 * @see JavaMailSender
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    // получение имени ящика из контекста (application.properties)
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Отправка письма
     *
     * @param emailTo Почта получателя
     * @param subject Заголовок письма
     * @param message Тело письма
     */
    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}