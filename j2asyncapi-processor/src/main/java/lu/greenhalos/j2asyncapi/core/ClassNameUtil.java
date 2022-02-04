package lu.greenhalos.j2asyncapi.core;

/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ClassNameUtil {

    public static String name(Class<?> targetClass) {

        return shorten(targetClass.getName());
    }


    /**
     * Source https://codegolf.stackexchange.com/questions/119126/shorten-the-java-package/119133.
     */
    private static String shorten(String name) {

        return name.replaceAll("\\B\\w+(\\.[a-zA-Z])", "$1");
    }
}
