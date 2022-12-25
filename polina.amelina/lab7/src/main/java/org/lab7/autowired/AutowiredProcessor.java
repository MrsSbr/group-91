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

        for (Element element : roundEnv.getElementsAnnotatedWith(Autowired.class)) {
            Set<? extends Element> componentElements = roundEnv.getElementsAnnotatedWith(Component.class);

            if (componentElements.stream().noneMatch(x -> x.asType().equals(element.asType()))) {

                processingEnv
                        .getMessager()
                        .printMessage(
                                Diagnostic.Kind.ERROR,
                                "Only a field of type annotated with @Component can be annotated with @Autowired",
                                element
                        );
                return true;
            }

            if (componentElements.stream().noneMatch(x -> x.asType().equals(element.getEnclosingElement().asType()))) {

                processingEnv
                        .getMessager()
                        .printMessage(
                                Diagnostic.Kind.ERROR,
                                "Only a field of class annotated with @Component can be annotated with @Autowired",
                                element
                        );
                return true;
            }
        }

        return true;
    }
}