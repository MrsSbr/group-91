package org.lab7.component;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.lab7.component.Component")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class ComponentProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element element : roundEnv.getElementsAnnotatedWith(Component.class)) {
            if (element.getModifiers().contains(Modifier.ABSTRACT)
                    || element.getKind() == ElementKind.INTERFACE
                    || element.getKind() == ElementKind.ANNOTATION_TYPE) {

                processingEnv
                        .getMessager()
                        .printMessage(
                                Diagnostic.Kind.ERROR,
                                "Only a class can be annotated with @Component",
                                element
                        );
                return true;
            }
        }

        return true;
    }
}