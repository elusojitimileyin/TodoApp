package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.dtos.request.JavaMailRequest;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JavaMailer implements MailService{

    private final JavaMailSender mailSender;
    @Override
    public void send(JavaMailRequest request) {
        String message = request.getMessage();
        String subject = request.getSubject();
        String to = request.getTo();
        String from = request.getFrom();

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);

            mailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
