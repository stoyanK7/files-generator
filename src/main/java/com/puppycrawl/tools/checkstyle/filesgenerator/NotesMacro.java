package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.nio.file.Path;

import org.apache.maven.doxia.macro.AbstractMacro;
import org.apache.maven.doxia.macro.Macro;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
import org.codehaus.plexus.component.annotations.Component;

import com.puppycrawl.tools.checkstyle.site.JavadocScraperResultUtil;
import com.puppycrawl.tools.checkstyle.site.ModuleJavadocParsingUtil;
import com.puppycrawl.tools.checkstyle.site.SiteUtil;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * A macro that inserts a notes subsection of module from its Javadoc.
 */
@Component(role = Macro.class, hint = "notes")
public class NotesMacro extends AbstractMacro {

    @Override
    public void execute(Sink sink, MacroRequest request) throws MacroExecutionException {
        final Path modulePath = Path.of((String) request.getParameter("modulePath"));
        final String moduleName = CommonUtil.getFileNameWithoutExtension(modulePath.toString());

        SiteUtil.processModule(moduleName, modulePath);
        final String moduleNotes = JavadocScraperResultUtil.getModuleNotes();

        ModuleJavadocParsingUtil.writeOutJavadocPortion(moduleNotes, sink);

    }

}
