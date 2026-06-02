package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.maven.doxia.macro.AbstractMacro;
import org.apache.maven.doxia.macro.Macro;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.module.xdoc.XdocSink;
import org.apache.maven.doxia.sink.Sink;
import org.codehaus.plexus.component.annotations.Component;

/**
 * A macro that inserts a list of the violation messages.
 */
@Component(role = Macro.class, hint = "violation-messages")
public class ViolationMessagesMacro extends AbstractMacro {
    @Override
    public void execute(Sink sink, MacroRequest request) throws MacroExecutionException {
        // until https://github.com/checkstyle/checkstyle/issues/13426
        if (!(sink instanceof XdocSink xdocSink)) {
            throw new MacroExecutionException("Expected Sink to be an XdocSink.");
        }
        final String checkName = (String) request.getParameter("checkName");
        final Object instance = SiteUtil.getModuleInstance(checkName);
        final Class<?> clss = instance.getClass();
        final Set<String> messageKeys = SiteUtil.getMessageKeys(clss);
        createListOfMessages(xdocSink, clss, messageKeys);
    }

    /**
     * Iterates through the fields of the class and creates an unordered list.
     *
     * @param sink the sink to write to.
     * @param clss the class of the fields.
     * @param messageKeys the List of message keys to iterate through.
     */
    private static void createListOfMessages(
            XdocSink sink, Class<?> clss, Set<String> messageKeys) {
        final String indentLevel8 = SiteUtil.getNewlineAndIndentSpaces(8);

        // This is a hack to prevent a newline from being inserted by the default sink.
        // Once we get rid of the custom parser, we can remove this.
        // until https://github.com/checkstyle/checkstyle/issues/13426
        sink.setInsertNewline(false);
        sink.list();
        sink.setInsertNewline(true);

        for (String messageKey : messageKeys) {
            createListItem(sink, clss, messageKey);
        }
        sink.rawText(indentLevel8);
        sink.list_();
    }

    /**
     * Creates a list item for the given field.
     *
     * @param sink the sink to write to.
     * @param clss the class of the field.
     * @param messageKey the message key.
     */
    private static void createListItem(XdocSink sink, Class<?> clss, String messageKey) {
        final String messageKeyUrl = constructMessageKeyUrl(clss, messageKey);
        final String indentLevel10 = SiteUtil.getNewlineAndIndentSpaces(10);
        final String indentLevel12 = SiteUtil.getNewlineAndIndentSpaces(12);
        final String indentLevel14 = SiteUtil.getNewlineAndIndentSpaces(14);
        // Place the <li>.
        sink.rawText(indentLevel10);
        // This is a hack to prevent a newline from being inserted by the default sink.
        // Once we get rid of the custom parser, we can remove this.
        // until https://github.com/checkstyle/checkstyle/issues/13426
        sink.setInsertNewline(false);
        sink.listItem();
        sink.setInsertNewline(true);

        // Place an <a>.
        sink.rawText(indentLevel12);
        sink.link(messageKeyUrl);
        // Further indent the text.
        sink.rawText(indentLevel14);
        sink.rawText(messageKey);

        // Place closing </a> and </li> tags.
        sink.rawText(indentLevel12);
        sink.link_();
        sink.rawText(indentLevel10);
        sink.listItem_();
    }

    /**
     * Constructs a URL to GitHub that searches for the message key.
     *
     * @param clss the class of the module.
     * @param messageKey the message key.
     * @return the URL to GitHub.
     */
    private static String constructMessageKeyUrl(Class<?> clss, String messageKey) {
        final String query = "path:src/main/resources/"
                + clss.getPackage().getName().replace('.', '/')
                + " path:**/messages*.properties repo:checkstyle/checkstyle \""
                + messageKey + "\"";

        return "https://github.com/search?q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8);
    }
}
