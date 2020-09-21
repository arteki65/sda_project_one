package pl.aptewicz.sda.projectone.config;

import org.apache.commons.cli.Options;

public class CliUsageConfig {
    public static final String DEBUG = "debug";

    public static final Options CLI_OPTIONS;

    public static final String DB_USER = "dbUser";

    public static final String DB_PASS = "dbPass";

    public static final String DB_HOST = "dbHost";

    public static final String DB_NAME = "dbName";

    static {
        CLI_OPTIONS = new Options();
        CLI_OPTIONS.addOption(DEBUG, DEBUG, false, "set debug mode which prints all stack traces");
        CLI_OPTIONS.addOption(DB_USER, DB_USER, true, "database user");
        CLI_OPTIONS.addOption(DB_PASS, DB_PASS, true, "database password");
        CLI_OPTIONS.addOption(DB_HOST, DB_HOST, true, "database host");
        CLI_OPTIONS.addOption(DB_NAME, DB_NAME, true, "database name");
    }
}
