package com.lab.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.lab.annotation.StringCommand")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CommandProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(StringCommand.class);

        Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
                Collectors.partitioningBy(element ->
                        ((ExecutableType) element.asType()).getParameterTypes().size() == 1
                                && ((ExecutableType) element.asType()).getReturnType()
                                .toString()
                                .equals("java.lang.String")
                                && !element.getModifiers().contains(Modifier.STATIC)));

        List<Element> converters = annotatedMethods.get(true);
        List<Element> otherMethods = annotatedMethods.get(false);

        otherMethods.forEach(element ->
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Method " + element.getSimpleName().toString() + " is annotated as @StringCommand, " +
                                "but declaration is incorrect"
                ));

        if (converters.isEmpty()) {
            return false;
        }
//
//        Map<Element, String> casesMap = converters.stream().collect(
//                Collectors.toMap(c -> c, c -> c.getAnnotation(StringCommand.class).value()));


        Map<TypeElement, List<Element>> classesMap = converters.stream()
                .collect(Collectors.groupingBy((Element element) -> (TypeElement) element.getEnclosingElement()));

        writeFiles(classesMap);
        return true;
    }

    private void writeFiles(Map<TypeElement, List<Element>> classesMap) {
        classesMap.forEach((cl, elems) -> {
            try {
                writeFile(cl, elems);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void writeFile(TypeElement classConverter, List<Element> methods) throws IOException {
        String implClassName = classConverter.getSimpleName() + "Command";
        String className = classConverter.getQualifiedName().toString();

        JavaFileObject commandFile = processingEnv.getFiler()
                .createSourceFile(implClassName);
        try (PrintWriter out = new PrintWriter(commandFile.openWriter())) {
            String packageName = classConverter.getEnclosingElement().getSimpleName().toString();

            out.print("package ");
            out.print(packageName);
            out.println(";");
            out.println();

            out.print("public class ");
            out.print(implClassName + " extends " + className);
            out.println(" {");
            out.println();

            for (var method : methods) {
                out.println("    @Override");
                out.print("    public String " + method.getSimpleName() + "(String str) {");
                out.println("\n        return super." + method.getSimpleName() + "(str);");
//                switch (casesMap.get(method)){
//                    case "camelCase"->
//                    case "snake_case"-> out.println("        return str + 'S';");
//                    case "kebab-case"-> out.println("        return str + 'K';");
//                }
                out.println("    }");
                out.println();
            }

            out.println("}");

        }
    }
}



























