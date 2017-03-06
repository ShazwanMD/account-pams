package my.edu.umk.pams.account.workflow.integration.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public class ProcessEvent<T extends my.edu.umk.pams.account.core.model.AcDocument> extends ApplicationEvent {

    private T document;
    private Map<String, Object> variables;

    public ProcessEvent(T source) {
        super(source);
        this.document = source;
    }

    public ProcessEvent(T document, Map<String, Object> variables) {
        super(document);
        this.document = document;
        this.variables = variables;
    }

    public T getDocument() {
        return document;
    }

    public void setDocument(T document) {
        this.document = document;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
