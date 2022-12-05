package com.lab.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.annotation.StringCommand")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CommandProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            List<Element> otherMethods = annotatedElements.stream()
                    .filter(element ->
                            ((ExecutableType) element.asType()).getParameterTypes().size() != 1
                            || !((ExecutableType) element.asType()).getReturnType().toString().equals("String")
                            || !element.getSimpleName().toString().toLowerCase().contains("convert"))
                    .collect(Collectors.toList());

            otherMethods.forEach(element ->
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "Method" + element.getSimpleName().toString()+ "is annotated as @StringCommand, " +
                                    "but declaration is incorrect"));
        }

        return true;
    }
}



























