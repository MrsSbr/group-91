package methodNameGetter;

public final class MethodNameGetter {
    public static String getMethodName() {
        return StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .orElseThrow()
                .getMethodName();
    }
}