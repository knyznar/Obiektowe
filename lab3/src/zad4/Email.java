package zad4;

import java.util.LinkedList;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class Email {
    public static void main(String[] args) {
        String password;
        String username;

        EmailMessage wiadomosc = EmailMessage.builder()
                .addFrom("katarzyna.nyznar@gmail.com")
                .addTo("katarzyna.nyznar@gmail.com")
                .addSubject("Mail tekstowy")
                .addContent("Brak tresci")
                .build();

        System.out.print("Podaj haslo: ");
        username = wiadomosc.getFrom();
        Scanner odczyt = new Scanner(System.in);
        password = odczyt.nextLine();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(wiadomosc.getFrom()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(wiadomosc.getTo().get(0)));
            message.setSubject(wiadomosc.getSubject());
            message.setText(wiadomosc.getContent());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}