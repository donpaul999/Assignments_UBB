package ui.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String key();
    String description() default "";
    String usage() default "";
    String group() default "";
}
