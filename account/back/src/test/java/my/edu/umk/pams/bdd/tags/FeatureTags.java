package my.edu.umk.pams.bdd.tags;


import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag(  name = "Issue", value = "Special",
        style = "background-color: white; " +
        "color: green;" +
        "border: 1px solid purple",
        description = "This is a combined Features Tag")
@Retention( RetentionPolicy.RUNTIME )
public @interface FeatureTags {
}
