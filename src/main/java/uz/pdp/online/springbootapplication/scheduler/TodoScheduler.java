package uz.pdp.online.springbootapplication.scheduler;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TodoScheduler {

    private final TodoService todoService;
    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Scheduled(cron = "0 15 8,20 * * *")
    public void sendUncompletedTodos() {
        List<Todo> uncompletedTodos = todoService.getAllTodos().stream()
                .filter(todo -> !todo.isCompleted())
                .collect(Collectors.toList());

        sendEmailWithTodos(uncompletedTodos);
    }

    public void sendEmailWithTodos(List<Todo> todos) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("company@gmail.com");
            helper.setTo("user@gmail.com");
            helper.setSubject("Uncompleted Todos");

            Map<String, Object> model = new HashMap<>();
            model.put("todos", todos);

            String htmlContent = processFreemarkerTemplate(model);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private String processFreemarkerTemplate(Map<String, Object> model) throws IOException, TemplateException {
        Template template = configuration.getTemplate("uncompleted-todos.ftlh");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

}