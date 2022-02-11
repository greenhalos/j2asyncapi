package lu.greenhalos.j2asyncapi.schemas;

import java.util.Objects;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Reference {

    private String $ref;

    public Reference() {
    }


    public Reference(String $ref) {

        this.$ref = $ref;
    }

    public String get$ref() {

        return $ref;
    }


    public void set$ref(String $ref) {

        this.$ref = $ref;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reference reference = (Reference) o;

        return Objects.equals($ref, reference.$ref);
    }


    @Override
    public int hashCode() {

        return Objects.hash($ref);
    }


    @Override
    public String toString() {

        return "Reference{"
            + "$ref='" + $ref + '\'' + '}';
    }
}
