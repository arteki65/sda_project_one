package pl.aptewicz.sda.projectone.service.logger;

import java.time.LocalDateTime;

public class LoggerService {

    private boolean isDebugMode = false;

    public void logError(String msg, Throwable e) {
        if (isDebugMode) {
            System.err.println(LocalDateTime.now() + ": " + msg);
            e.printStackTrace();
        }
    }

    public void setIsDebugMode(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }
}
