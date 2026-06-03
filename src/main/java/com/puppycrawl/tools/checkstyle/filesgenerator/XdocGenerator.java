package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.io.File;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkFactory;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.util.ReaderFactory;

public final class XdocGenerator {
    private static final String XDOCS_TEMPLATE_HINT = "xdocs-template";

    private XdocGenerator() {
    }

    public static void generateXdocContent(Path repositoryPath) throws Exception {
        final PlexusContainer plexus = new DefaultPlexusContainer();
        final Set<Path> templatesFilePaths = XdocUtil.getXdocsTemplatesFilePaths();
        final File temporaryFolder = Files.createTempDirectory(null).toFile();


        for (Path path : templatesFilePaths) {
            final String pathToFile = path.toString();
            final File inputFile = new File(pathToFile);
            final File outputFile = new File(pathToFile.replace(".template", ""));
            final File tempFile = new File(temporaryFolder, outputFile.getName());
            tempFile.deleteOnExit();
            final XdocsTemplateSinkFactory sinkFactory = (XdocsTemplateSinkFactory)
                    plexus.lookup(SinkFactory.ROLE, XDOCS_TEMPLATE_HINT);
            final Sink sink = sinkFactory.createSink(tempFile.getParentFile(),
                    tempFile.getName(), String.valueOf(StandardCharsets.UTF_8));
            final XdocsTemplateParser parser = (XdocsTemplateParser)
                    plexus.lookup(Parser.ROLE, XDOCS_TEMPLATE_HINT);
            try (Reader reader = ReaderFactory.newReader(inputFile,
                    String.valueOf(StandardCharsets.UTF_8))) {
                parser.parse(reader, sink);
            }
            finally {
                sink.close();
            }
            final StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;
            Files.copy(tempFile.toPath(), outputFile.toPath(), copyOption);
        }
    }
}