package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.nio.file.Path;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "checkstyle-files-generator",
    description = "Generates metadata and XDoc files in the specified checkstyle repository.",
    mixinStandardHelpOptions = true
)
public class Main implements Runnable {

    @Parameters(description = "Path to the checkstyle repository directory.")
    private Path repositoryPath;

    @Option(names = "--metadata", description = "Generate metadata files.")
    private boolean generateMetadata;

    @Option(names = "--xdoc", description = "Generate XDoc files.")
    private boolean generateXdoc;

    @Override
    public void run() {
        if (generateMetadata) {
            // TODO: execute metadata generation.
        }
        if (generateXdoc) {
            // TODO: execute xdoc generation.
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
