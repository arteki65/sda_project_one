package pl.aptewicz.sda.projectone.service.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import pl.aptewicz.sda.projectone.config.AppConfig;
import pl.aptewicz.sda.projectone.config.CliUsageConfig;

import java.util.Optional;

import static pl.aptewicz.sda.projectone.config.CliUsageConfig.*;

public class CliArgsParser {
    private final CommandLineParser cmdParser = new DefaultParser();

    public AppConfig parseAppConfig(String... args) throws ParseException {
        final var cmd = cmdParser.parse(CliUsageConfig.CLI_OPTIONS, args);
        return new AppConfig(
                cmd.hasOption(DEBUG),
                Optional.ofNullable(cmd.getOptionValue(DB_USER)).orElseThrow(() -> cmdArgMissing(DB_USER)),
                Optional.ofNullable(cmd.getOptionValue(DB_PASS)).orElseThrow(() -> cmdArgMissing(DB_PASS)),
                Optional.ofNullable(cmd.getOptionValue(DB_NAME)).orElseThrow(() -> cmdArgMissing(DB_NAME)),
                Optional.ofNullable(cmd.getOptionValue(DB_HOST)).orElseThrow(() -> cmdArgMissing(DB_HOST))
        );
    }

    private ParseException cmdArgMissing(String cmdArg) {
        return new ParseException("Cmd argument '" + cmdArg + "' is missing.\n");
    }
}
