package uz.pdp.online.springbootapplication.mailing.register;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import uz.pdp.online.springbootapplication.mailing.user.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Async
    public void sendActivationEmail(User user) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = getMimeMessageHelper(user, message);

            Map<String, String> model = getObjectMap(user);

            String htmlContent = processFreemarkerTemplate(model);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException | TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static MimeMessageHelper getMimeMessageHelper(User user, MimeMessage message) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("pdp@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Account Activation");
        return helper;
    }

    private static Map<String, String> getObjectMap(User user) {
        Map<String, String> model = new HashMap<>();
        model.put("username", user.getUsername());
        model.put("activationToken", UUID.randomUUID().toString());
        return model;
    }

    private String processFreemarkerTemplate(Map<String, String> model) throws IOException, TemplateException {
        Template template = configuration.getTemplate("activation-email.ftlh");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
