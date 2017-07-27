package my.edu.umk.pams.account.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.account.system.model.AcEmailQueue;
import my.edu.umk.pams.account.system.model.AcEmailQueueStatus;

/**
 */
@Component("systemScheduler")
public class SystemSchedulerImpl implements SystemScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SystemSchedulerImpl.class);

    @Autowired
    private SystemService systemService;
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Scheduled(cron = "*/10 * * * * *")
    public void sendEmail() {
        loginAsSystem();

        try {
            List<AcEmailQueue> queues = systemService.findEmailQueues(AcEmailQueueStatus.QUEUED);
            LOG.debug("{} email to be sent", queues.size());
            for (AcEmailQueue queue : queues) {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(queue.getTo());
                helper.setTo(queue.getTo());
                helper.setSubject(queue.getSubject());
                helper.setText(queue.getBody());
                mailSender.send(mimeMessage);

                // update queue
                queue.setQueueStatus(AcEmailQueueStatus.SENT);
                systemService.updateEmailQueue(queue);
            }
            
        } catch (MessagingException e) {
            LOG.error("error " + e);
        }

        loginAsSystem();
    }

    private SecurityContext loginAsSystem() {
        SecurityContext savedCtx = SecurityContextHolder.getContext();
        AcAutoLoginToken authenticationToken = new AcAutoLoginToken("system");
        Authentication authed = authenticationManager.authenticate(authenticationToken);
        SecurityContext system = new NonSerializableSecurityContext();
        system.setAuthentication(authed);
        SecurityContextHolder.setContext(system);
        return savedCtx;
    }

    private void logoutAsSystem(SecurityContext context) {
        SecurityContextHolder.setContext(context);
    }
}

