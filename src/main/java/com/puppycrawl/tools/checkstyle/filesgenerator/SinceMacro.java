package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.nio.file.Path;

import org.apache.maven.doxia.macro.AbstractMacro;
import org.apache.maven.doxia.macro.Macro;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
import org.codehaus.plexus.component.annotations.Component;

import com.puppycrawl.tools.checkstyle.site.SiteUtil;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * A macro that inserts a {@code @since} version of module from its Javadoc.
 */
@Component(role = Macro.class, hint = "since")
public class SinceMacro extends AbstractMacro {

    @Override
    public void execute(Sink sink, MacroRequest request) throws MacroExecutionException {
        final Path modulePath = Path.of((String) request.getParameter("modulePath"));
        final String moduleName = CommonUtil.getFileNameWithoutExtension(modulePath.toString());

        final String moduleSinceVersion = SiteUtil.getModuleSinceVersion(moduleName, modulePath);
        sink.paragraph();
        sink.text("Since Checkstyle " + moduleSinceVersion);
        sink.paragraph_();
    }

}
