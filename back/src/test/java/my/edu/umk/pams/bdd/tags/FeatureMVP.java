package my.edu.umk.pams.bdd.tags;


import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CoreMVP
@IsTag( style = "background-color: white; " +
        "color: green;" +
        "border: 1px solid green",
        description = "This is a Feature")
@Retention( RetentionPolicy.RUNTIME )
public @interface FeatureMVP {
    String value();
}
