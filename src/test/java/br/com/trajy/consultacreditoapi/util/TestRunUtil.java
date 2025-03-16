package br.com.trajy.consultacreditoapi.util;


public final class TestRunUtil {

    private TestRunUtil() { }

    public static void ignoreThrows(Runnable action) {
        try {
            action.run();
        } catch (Exception e) { }
    }

}
