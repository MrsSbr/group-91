package org.lab7.autowired;

import com.google.auto.service.AutoService;
import org.lab7.component.Component;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.lab7.autowired.Autowired")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class AutowiredProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(Autowired.class);

        for (Element element : annotatedElements) {
            if (!element.getClass().isAnnotationPresent(Component.class)) {
                processingEnv
                        .getMessager()
                        .printMessage(
                                Diagnostic.Kind.ERROR,
                                "Only field, whose class is annotated with @Component, can be annotated with @Autowired",
                                element
                        );
                return true;
            }

            if (!element.getEnclosingElement().getClass().isAnnotationPresent(Component.class)) {
                processingEnv
                        .getMessager()
                        .printMessage(
                                Diagnostic.Kind.ERROR,
                                "Only field, that belongs to a class annotated with @Component, can be annotated with @Autowired",
                                element
                        );
                return true;
            }
        }

        return true;
    }
}