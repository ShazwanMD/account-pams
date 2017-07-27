package my.edu.umk.pams.account.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Function;
import org.springframework.integration.dsl.support.FunctionExpression;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;


//@Configuration
//@EnableIntegration
public class IntegrationConfig {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(env.getProperty("broker.url"));
    }

    @Bean
    public ConnectionFactory cacheConnectionFactory() {
        CachingConnectionFactory connFactory = new CachingConnectionFactory();
        connFactory.setClientId("pams-account");
        connFactory.setTargetConnectionFactory(connFactory);
        connFactory.setSessionCacheSize(5);
        return connFactory;
    }

    // ==================================================================================================== //
    // OUTGOING : CANDIDATE
    // ==================================================================================================== //

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true);
        template.setDeliveryMode(2);
        template.setSessionAcknowledgeMode(2);
        template.setExplicitQosEnabled(true);
        return template;
    }

    @Bean
    public ApplicationEventListeningMessageProducer candidateEventAdapter() {
        ApplicationEventListeningMessageProducer producer = new ApplicationEventListeningMessageProducer();
        producer.setApplicationContext(applicationContext);
        // todo: producer.setEventTypes();
        producer.setPayloadExpressionString("payload");
        return producer;
    }

    @Bean
    public JmsSendingMessageHandler jmsAdapter() {
        JmsSendingMessageHandler handler = new JmsSendingMessageHandler(jmsTemplate());
        handler.setDestinationExpression(new FunctionExpression<>(new DestinationExpressionFunction()));
        return handler;
    }

    @Bean
    public IntegrationFlow candidateIntegrationFlow() {
        return IntegrationFlows.from(candidateEventAdapter())
                .transform(Transformers.toJson(
                        new Jackson2JsonObjectMapper(),
                        ObjectToJsonTransformer.ResultType.STRING,
                        MediaType.APPLICATION_JSON_VALUE))
                .handle(jmsAdapter())
                .get();
    }


    private class DestinationExpressionFunction implements Function<GenericMessage, Object> {

        private final Map<String, ActiveMQQueue> queueMap;

        public DestinationExpressionFunction() {
            queueMap = new HashMap<>();
            // queue mapping
            // queueMap.put(CohortPayload.class.getName(), new ActiveMQQueue("cohortQueue"));
        }

        @Override
        public Object apply(GenericMessage message) {
            Object objectType = message.getHeaders().get("json__TypeId__");
            return queueMap.get(((Class) objectType).getName());
        }
    }

}
