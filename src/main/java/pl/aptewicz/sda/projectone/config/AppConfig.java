package pl.aptewicz.sda.projectone.config;

public class AppConfig {
    private final boolean isDebugMode;

    private final String dbUser;

    private final String dbPass;

    private final String dbName;

    private final String dbHost;

    public AppConfig(boolean isDebugMode, String dbUser, String dbPass, String dbName, String dbHost) {
        this.isDebugMode = isDebugMode;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbName = dbName;
        this.dbHost = dbHost;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbHost() {
        return dbHost;
    }
}
