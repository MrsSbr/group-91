package org.lab7.tests;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import org.lab7.component.ComponentProcessor;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

public class ComponentAnnotatingTests {

    @Test
    public void failToCompileComponentOnAbstractClass() {
        // given
        String className = "data.ComponentOnAbstractClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public abstract class ComponentOnAbstractClass {}";

        // when
        Compilation compilation = javac()
                .withProcessors(new ComponentProcessor())
                .compile(JavaFileObjects.forSourceLines(className, classContent));

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a class can be annotated with @Component"
        );
    }

    @Test
    public void failToCompileComponentOnInterface() {
        // given
        String className = "data.ComponentOnInterface";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public interface ComponentOnInterface {}";

        // when
        Compilation compilation = javac()
                .withProcessors(new ComponentProcessor())
                .compile(JavaFileObjects.forSourceLines(className, classContent));

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a class can be annotated with @Component"
        );
    }

    @Test
    public void failToCompileComponentOnAnnotation() {
        // given
        String className = "data.ComponentOnAnnotation";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public @interface ComponentOnAnnotation {}";

        // when
        Compilation compilation = javac()
                .withProcessors(new ComponentProcessor())
                .compile(JavaFileObjects.forSourceLines(className, classContent));

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a class can be annotated with @Component"
        );
    }
}