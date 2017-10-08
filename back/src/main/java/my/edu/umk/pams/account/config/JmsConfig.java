package my.edu.umk.pams.account.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.support.Function;
import org.springframework.integration.dsl.support.FunctionExpression;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import my.edu.umk.pams.connector.payload.AccountPayload;
import my.edu.umk.pams.connector.payload.AdmissionPayload;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.GuardianPayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;

@Configuration
public class JmsConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JmsConfig.class);

    @Bean
    public JmsSendingMessageHandler jmsMessageHandler(final JmsTemplate jmsTemplate, final ConnectionFactory connectionFactory) {
        System.out.println("*****************************************************");
        System.out.println("we have jms template: " + jmsTemplate);
        System.out.println("we have connection factory: " + ((ActiveMQConnectionFactory) connectionFactory).getBrokerURL());
        JmsSendingMessageHandler handler = new JmsSendingMessageHandler(jmsTemplate);
        handler.setDestinationExpression(new FunctionExpression<>(new DestinationExpressionFunction()));
        handler.setExtractPayload(true);
        return handler;
    }

    private class DestinationExpressionFunction implements Function<GenericMessage, Object> {
        private final Map<String, ActiveMQQueue> queueMap;

        // map to queue or topic
        public DestinationExpressionFunction() {
            queueMap = new HashMap<>();
            queueMap.put(ProgramCodePayload.class.getName(), new ActiveMQQueue("programCodeQueue"));
            queueMap.put(FacultyCodePayload.class.getName(), new ActiveMQQueue("facultyCodeQueue"));
            queueMap.put(CandidatePayload.class.getName(), new ActiveMQQueue("candidateQueue"));
            queueMap.put(AccountPayload.class.getName(), new ActiveMQQueue("accountQueue"));
            queueMap.put(AdmissionPayload.class.getName(), new ActiveMQQueue("AdmissionPayloadQueue"));
            queueMap.put(GuardianPayload.class.getName(), new ActiveMQQueue("GuardianPayloadQueue"));
        }

        @Override
        public Object apply(GenericMessage message) {
            Object objectType = message.getHeaders().get("json__TypeId__");
            return queueMap.get(((Class) objectType).getName());
        }
    }
}
