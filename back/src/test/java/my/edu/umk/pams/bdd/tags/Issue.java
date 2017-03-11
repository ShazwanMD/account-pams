package my.edu.umk.pams.bdd.tags;


import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag(descriptionGenerator = IssueDescriptionGenerator.class )
@Retention( RetentionPolicy.RUNTIME )
public @interface Issue {
    String[] value();
}
