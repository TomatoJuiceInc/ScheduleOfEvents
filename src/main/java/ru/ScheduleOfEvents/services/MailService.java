package ru.ScheduleOfEvents.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.ScheduleOfEvents.models.MailStructure;

@Service
public class MailService {

    private  final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;


    @Value("$(TomatoJuice)")
    private String fromMail;

    @Autowired
    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMail(String mail, MailStructure mailStructure) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("invoiceAmount", mailStructure.getPrice());
        context.setVariable("timeOfP", mailStructure.getTimeOfPurchase());
        context.setVariable("placeEvent", mailStructure.getPlace());
        context.setVariable("eventDisc", mailStructure.getEventTimeAndName());
        context.setVariable("tickets", mailStructure.getTickets());
        String htmlContent = templateEngine.process("payment/check", context);

        helper.setFrom(fromMail);
        helper.setTo(mail);
        helper.setSubject(mailStructure.getSubject());
        helper.setText(htmlContent, true);

        javaMailSender.send(message);

    }
}
