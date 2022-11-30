package MethodNameGetter;

import java.util.stream.Stream;

public class MethodNameGetter {
    private static final StackWalker walker = StackWalker.getInstance();

    public static String getMethodName() {
        return walker.walk(Stream::findFirst)
                     .orElseThrow()
                     .getMethodName();
    }
}