package my.edu.umk.pams.bdd.tags;


import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag( style = "background-color: white; " +
        "color: blue;" +
        "border: 1px solid blue",
        description = "This is a Submodule")
@Retention( RetentionPolicy.RUNTIME )
public @interface Submodule {
    String value();
}
