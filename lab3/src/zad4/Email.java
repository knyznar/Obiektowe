package zad4;

import java.util.LinkedList;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class Email {
    public static void main(String[] args) {

        EmailMessage wiadomosc = EmailMessage.builder()
                .addFrom("katarzyna.nyznar@gmail.com")
                .addTo("katarzyna.nyznar@gmail.com")
                .addSubject("Mail tekstowy")
                .addContent("Brak tresci")
                .build();

        wiadomosc.send();
    }
}