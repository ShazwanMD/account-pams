package my.edu.umk.pams.bdd.tags;

import com.tngtech.jgiven.annotation.TagDescriptionGenerator;
import com.tngtech.jgiven.config.TagConfiguration;

import java.lang.annotation.Annotation;

/**
 * Created by PAMS on 3/11/2017.
 */
public class IssueDescriptionGenerator implements TagDescriptionGenerator {
    @Override
    public String generateDescription(TagConfiguration tagConfiguration,
                                      Annotation annotation, Object value ) {
        return String.format(
                "<a href='https://jira.umk.edu.my/PAMS/issues/%s'>Issue %s</a>",
                value, value );
    }
}
