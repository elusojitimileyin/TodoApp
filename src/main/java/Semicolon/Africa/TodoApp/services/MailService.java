package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.dtos.request.JavaMailRequest;

public interface MailService {


    void send(JavaMailRequest request);
}
