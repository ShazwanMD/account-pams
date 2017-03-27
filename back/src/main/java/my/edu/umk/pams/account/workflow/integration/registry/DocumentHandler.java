package my.edu.umk.pams.account.workflow.integration.registry;


import java.util.Map;

/**
 * @author canang technologies
 * @since 2/21/14
 */
public interface DocumentHandler<T extends my.edu.umk.pams.account.core.model.AcDocument> {

    /**
     * handle document processing according to its type
     *
     * @param document
     * @param variables
     * @return
     */
    String process(T document, Map<String, Object> variables);
}
