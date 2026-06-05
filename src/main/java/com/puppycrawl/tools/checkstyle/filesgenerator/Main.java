package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.util.concurrent.Callable;
import java.nio.file.Path;

import com.puppycrawl.tools.checkstyle.filesgenerator.meta.MetadataGeneratorUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "checkstyle-files-generator",
    description = "Generates metadata and XDoc files in the specified checkstyle repository.",
    mixinStandardHelpOptions = true
)
public class Main implements Callable<Integer> {

    @Parameters(description = "Path to the checkstyle repository directory.")
    private Path checkstylePath;

    @Option(names = "--generateMetadata", description = "Generate metadata files.")
    private boolean generateMetadata;

    @Option(names = "--generateXdoc", description = "Generate XDoc files.")
    private boolean generateXdoc;

    @Override
    public Integer call() throws Exception {
        if (generateMetadata) {
            final String checkstyleModulesDir = checkstylePath.resolve(
                    Path.of(
                            "src",
                            "main",
                            "java",
                            "com",
                            "puppycrawl",
                            "tools",
                            "checkstyle"))
                    .toAbsolutePath()
                    .toString();
            MetadataGeneratorUtil.generate(
                    checkstyleModulesDir, "checks", "filters", "filefilters");
        }
        if (generateXdoc) {
            // TODO: https://github.com/checkstyle/checkstyle/issues/13426
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
