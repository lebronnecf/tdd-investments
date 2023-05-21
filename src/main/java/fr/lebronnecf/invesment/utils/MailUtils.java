package fr.lebronnecf.invesment.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailUtils {

    public static void send(SimpleMailMessage var1) throws MailException {
        JavaMailSender mailSender = new JavaMailSenderImpl();
    }
}
