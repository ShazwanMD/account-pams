package my.edu.umk.pams.bdd.tags;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IsTag(explodeArray = true)
@Retention( RetentionPolicy.RUNTIME )
public @interface Story {
    String[] value();
}
