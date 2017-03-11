package my.edu.umk.pams.bdd.tags;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag( style = "background-color: orange; color: white;",
        description = "Charge type")
@Retention( RetentionPolicy.RUNTIME )
public @interface Unused {
    String[] value();
}
