package pl.coderslab.charity.app;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import pl.coderslab.charity.models.entities.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;


@Component
public class SendPasswordEmail {

    private final JavaMailSender mailSender;

    public SendPasswordEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordMail(String password, User user) throws IOException, TemplateException, MessagingException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/mail/templates");

        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        Template mailTemplate = configuration.getTemplate("passwordEmail.ftlh");
        Map<String, Object> model = new HashMap<>();
        model.put("password", password);
        model.put("admin", user);
        String mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom("emailsenderportal@gmail.com");
        messageHelper.setSubject("Hasło do portalu");
        messageHelper.setBcc(new String[]{"emailsenderportal@gmail.com", user.getEmail()});
        messageHelper.setText(mailBody, true);

        mailSender.send(mimeMessage);
    }
}