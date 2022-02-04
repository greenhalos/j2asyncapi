package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.core.fields.FieldType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static lu.greenhalos.j2asyncapi.core.ClassNameUtil.name;

import static org.assertj.core.api.Assertions.assertThat;


class ClassNameUtilTest {

    @ParameterizedTest
    @MethodSource
    void testName(Class<?> targetClass, String expectedName) {

        assertThat(name(targetClass)).isEqualTo(expectedName);
    }


    private static Stream<Arguments> testName() {

        return Stream.of(Arguments.of(ClassNameUtilTest.class, "l.g.j.c.ClassNameUtilTest"),
                Arguments.of(InnerClass.class, "l.g.j.c.ClassNameUtilTest$InnerClass"),
                Arguments.of(FieldType.class, "l.g.j.c.f.FieldType"),
                Arguments.of(InnerClass.NestedInnerClass.class,
                    "l.g.j.c.ClassNameUtilTest$InnerClass$NestedInnerClass"));
    }

    private static class InnerClass {

        private static class NestedInnerClass {
        }
    }
}
