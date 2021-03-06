package zad4;

import java.util.LinkedList;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class EmailMessage {
    private String from;
    private LinkedList<String> to;
    private String subject;
    private String content;
    private String mimeType;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    public String getFrom() {
        return from;
    }

    public LinkedList<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public LinkedList<String> getCc() {
        return cc;
    }

    public LinkedList<String> getBcc() {
        return bcc;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(LinkedList<String> to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setCc(LinkedList<String> cc) {
        this.cc = cc;
    }

    public void setBcc(LinkedList<String> bcc) {
        this.bcc = bcc;
    }


    private EmailMessage(Builder builder) {
        if (builder == null)
            return;
        from = builder.from;
        to = builder.to;
        subject = builder.subject;
        content = builder.content;
        mimeType = builder.mimeType;
        cc = builder.cc;
        bcc = builder.bcc;
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    static public class Builder {
        private String from;
        private LinkedList<String> to = new LinkedList<>();
        private String subject;
        private String content;
        private String mimeType;
        private LinkedList<String> cc = new LinkedList<>();
        private LinkedList<String> bcc = new LinkedList<>();

        Builder addFrom(String _to) {
            this.from = _to;
            return this;
        }

        Builder addTo(String _to) {
            to.add(_to);
            return this;
        }

        Builder addSubject(String _subject) {
            this.subject = _subject;
            return this;
        }

        Builder addContent(String _content) {
            this.content = _content;
            return this;
        }

        Builder addMimeType(String _mimeType) {
            this.mimeType = _mimeType;
            return this;
        }

        Builder addCC(String _cc) {
            cc.add(_cc);
            return this;
        }

        Builder addBCC(String _bcc) {
            bcc.add(_bcc);
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(this);
        }

    }

    public void send() {
        String password;
        String username;
        System.out.print("Podaj haslo: ");
        username = this.getFrom();
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
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.getFrom()));
            for (int i = 0; i < this.getTo().size(); i++) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(this.getTo().get(0)));
            }
            message.setSubject(this.getSubject());
            message.setText(this.getContent());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}