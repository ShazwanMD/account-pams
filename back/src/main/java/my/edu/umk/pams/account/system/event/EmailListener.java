package my.edu.umk.pams.account.system.event;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.InternetAddress;
import java.util.HashMap;

/**
 * @author canang technologies
 * @since 4/18/14
 */
@Component("emailListener")
public class EmailListener implements ApplicationListener<EmailEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(EmailListener.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false) // TDO
    private VelocityEngine velocityEngine;

    @Override
    @Async
    public void onApplicationEvent(EmailEvent event) {
        try {
            send(
                    event.getRecipient(),
                    event.getFrom(),
                    event.getSubject(),
                    event.getTemplate(),
                    event.getVelocityModel());
        } catch (Exception e) {
            LOG.error("error occurred", e);
        }
    }


    private void send(final InternetAddress to, final InternetAddress from, final String subject, final String template, final HashMap<String, Object> model) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
            helper.setText(body, true);
        };
        doSend(preparator);
    }

    private void doSend(MimeMessagePreparator preparator) {
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            LOG.error("error occurred", e);
            throw e;
        }
    }
}
