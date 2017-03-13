package my.edu.umk.pams.bdd.tags;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag(  name = "Scenario", value = "Special",
        style = "background-color: white; " +
                "color: brown;" +
                "border: 1px solid purple",
        description = "This is a combined Features Tag")
@Retention( RetentionPolicy.RUNTIME )
public @interface ScenarioTags {
    String value();
}
