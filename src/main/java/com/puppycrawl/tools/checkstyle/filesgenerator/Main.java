package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import com.puppycrawl.tools.checkstyle.meta.MetadataGeneratorUtil;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "checkstyle-files-generator",
    description = "Generates metadata files in the specified Checkstyle directory.",
    mixinStandardHelpOptions = true
)
public final class Main implements Callable<Integer> {

    @Parameters(description = "Path to the Checkstyle source code directory.")
    private Path checkstylePath;

    @Option(names = "--generateMetadata", description = "Generate metadata files.", required = true)
    private boolean generateMetadata;

    @Option(names="--generateXdoc", description = "Generate XDoc files.")
    private boolean generateXdoc;

    /** Entry point for standalone command-line use. */
    public static void main(String[] args) {
        final int exitCode = execute(args);
        System.exit(exitCode);
    }

    /**
     * Execute the command without terminating the current JVM.
     *
     * @param args command-line arguments.
     * @return process-compatible exit code.
     */
    public static int execute(String... args) {
        return new CommandLine(new Main()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        if (generateMetadata) {
            MetadataGeneratorUtil.generate(checkstylePath, "checks", "filters", "filefilters");
        }
        if (generateXdoc) {
            // TODO: until https://github.com/checkstyle/checkstyle/issues/13426
        }
        return 0;
    }
}
