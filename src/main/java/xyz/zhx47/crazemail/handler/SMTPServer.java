package xyz.zhx47.crazemail.handler;

import org.apache.james.protocols.api.Protocol;
import org.apache.james.protocols.api.handler.ProtocolHandler;
import org.apache.james.protocols.netty.NettyServer;
import org.apache.james.protocols.smtp.SMTPConfigurationImpl;
import org.apache.james.protocols.smtp.SMTPProtocol;
import org.apache.james.protocols.smtp.SMTPProtocolHandlerChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.zhx47.crazemail.config.SmtpServerConfiguration;

import java.net.InetSocketAddress;
import java.util.Collection;

@Component
public class SMTPServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMTPServer.class);

    private final Collection<ProtocolHandler> handlers;

    private NettyServer server;

    public SMTPServer(Collection<ProtocolHandler> handlers) {
        this.handlers = handlers;
    }

    public void start() throws Exception {
        SMTPProtocolHandlerChain chain = new SMTPProtocolHandlerChain(new RecordingMetricFactory());
        chain.addAll(0, handlers);
        chain.wireExtensibleHandlers();

        Protocol protocol = new SMTPProtocol(chain, new SMTPConfigurationImpl());

        server = new NettyServer.Factory()
                .protocol(protocol)
                .build();
        server.setListenAddresses(new InetSocketAddress(SmtpServerConfiguration.getPort()));
        server.bind();
        LOGGER.info("SMTP Server started on port: {}", SmtpServerConfiguration.getPort());
    }


    public void stop() {
        server.unbind();
        LOGGER.info("SMTP Server stopped on port: {}", SmtpServerConfiguration.getPort());
    }

}
