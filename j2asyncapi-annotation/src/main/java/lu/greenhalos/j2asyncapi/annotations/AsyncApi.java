package lu.greenhalos.j2asyncapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AsyncApi {

    enum Type {

        PUBLISHER,
        LISTENER
    }

    /**
     * Whether this method is a publisher or subscriber.
     */
    Type type();


    String exchange();


    String routingKey();


    Class<?> payload();


    String description() default "";

    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @interface Field {

        Class<?> type() default Void.class;


        String[] examples() default {};


        String format() default "";
    }
}
