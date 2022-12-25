package org.lab7.tests;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import org.lab7.autowired.AutowiredProcessor;
import org.lab7.component.ComponentProcessor;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

public class AnnotationCombinationTests {

    @Test
    public void failToCompileGoodAutowiredFieldInNotComponentClass() {
        // given
        String fieldClassName = "data.ComponentClass";
        String fieldClassContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public class ComponentClass {}";

        String className = "data.GoodAutowiredFieldInNotComponentClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.autowired.Autowired;" + System.lineSeparator() +
                System.lineSeparator() +
                "public class GoodAutowiredFieldInNotComponentClass {" + System.lineSeparator() +
                "   @Autowired" + System.lineSeparator() +
                "   private ComponentClass auto;" + System.lineSeparator() +
                "}";

        // when
        Compilation compilation = javac()
                .withProcessors(new AutowiredProcessor(), new ComponentProcessor())
                .compile(
                        JavaFileObjects.forSourceLines(fieldClassName, fieldClassContent),
                        JavaFileObjects.forSourceLines(className, classContent)
                );

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a field of class annotated with @Component can be annotated with @Autowired"
        );
    }

    @Test
    public void failToCompileBadAutowiredFieldInComponentClass() {
        // given
        String fieldClassName = "data.NotComponentClass";
        String fieldClassContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "public class NotComponentClass {}";

        String className = "data.BadAutowiredFieldInComponentClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.autowired.Autowired;" + System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public class BadAutowiredFieldInComponentClass {" + System.lineSeparator() +
                "   @Autowired" + System.lineSeparator() +
                "   private NotComponentClass auto;" + System.lineSeparator() +
                "}";

        // when
        Compilation compilation = javac()
                .withProcessors(new AutowiredProcessor(), new ComponentProcessor())
                .compile(
                        JavaFileObjects.forSourceLines(fieldClassName, fieldClassContent),
                        JavaFileObjects.forSourceLines(className, classContent)
                );

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a field of type annotated with @Component can be annotated with @Autowired"
        );
    }

    @Test
    public void failToCompileBadAutowiredFieldInNotComponentClass() {
        // given
        String fieldClassName = "data.NotComponentClass";
        String fieldClassContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "public class NotComponentClass {}";

        String className = "data.BadAutowiredFieldInNotComponentClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.autowired.Autowired;" + System.lineSeparator() +
                System.lineSeparator() +
                "public class BadAutowiredFieldInNotComponentClass {" + System.lineSeparator() +
                "   @Autowired" + System.lineSeparator() +
                "   private NotComponentClass auto;" + System.lineSeparator() +
                "}";

        // when
        Compilation compilation = javac()
                .withProcessors(new AutowiredProcessor())
                .compile(
                        JavaFileObjects.forSourceLines(fieldClassName, fieldClassContent),
                        JavaFileObjects.forSourceLines(className, classContent)
                );

        // then
        assertThat(compilation).hadErrorContaining(
                "Only a field of type annotated with @Component can be annotated with @Autowired"
        );
    }

    @Test
    public void compileGoodAutowiredFieldInComponentClass() {
        // given
        String fieldClassName = "data.ComponentClass";
        String fieldClassContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public class ComponentClass {}";

        String className = "data.GoodAutowiredFieldInComponentClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.autowired.Autowired;" + System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public class GoodAutowiredFieldInComponentClass {" + System.lineSeparator() +
                "   @Autowired" + System.lineSeparator() +
                "   private ComponentClass auto;" + System.lineSeparator() +
                "}";

        // when
        Compilation compilation = javac()
                .withProcessors(new AutowiredProcessor(), new ComponentProcessor())
                .compile(
                        JavaFileObjects.forSourceLines(fieldClassName, fieldClassContent),
                        JavaFileObjects.forSourceLines(className, classContent)
                );

        // then
        assertThat(compilation).succeededWithoutWarnings();
    }

    @Test
    public void compileNoAutowiredFieldInComponentClass() {
        // given
        String className = "data.NoAutowiredFieldInComponentClass";
        String classContent = "package org.lab7.tests.data;" + System.lineSeparator() +
                System.lineSeparator() +
                "import org.lab7.component.Component;" + System.lineSeparator() +
                System.lineSeparator() +
                "@Component" + System.lineSeparator() +
                "public class NoAutowiredFieldInComponentClass {}";

        // when
        Compilation compilation = javac()
                .withProcessors(new AutowiredProcessor(), new ComponentProcessor())
                .compile(JavaFileObjects.forSourceLines(className, classContent));

        // then
        assertThat(compilation).succeededWithoutWarnings();
    }
}