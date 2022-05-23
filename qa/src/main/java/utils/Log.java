package utils;

public class Log {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Log.class.getName());

    public static void info(String message) {
        log.info(message);
    }
}
