package com.puppycrawl.tools.checkstyle.filesgenerator;

import java.io.Writer;

import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkFactory;
import org.apache.maven.doxia.sink.impl.AbstractTextSinkFactory;
import org.codehaus.plexus.component.annotations.Component;

/**
 * Xdoc template implementation of the {@link SinkFactory}.
 * This module will be removed once
 * <a href="https://github.com/checkstyle/checkstyle/issues/13426">#13426</a> is resolved.
 */
@Component(role = SinkFactory.class, hint = "xdocs-template")
public class XdocsTemplateSinkFactory extends AbstractTextSinkFactory {

    /**
     * Create a Sink instance.
     *
     * @param writer writer to use.
     * @param encoding encoding of the writer.
     * @return Sink instance.
     */
    @Override
    public Sink createSink(Writer writer, String encoding) {
        return new XdocsTemplateSink(writer, encoding);
    }
}
