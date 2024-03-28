package xyz.zhx47.crazemail.config;

import lombok.Getter;
import org.apache.james.protocols.api.handler.ProtocolHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.zhx47.crazemail.handler.SMTPServer;
import xyz.zhx47.crazemail.service.EmailRecordService;

import java.util.Collection;

/**
 * Smtp 服务端配置
 */
@Configuration
@ConfigurationProperties(prefix = "smtp.server")
public class SmtpServerConfiguration {

    @Getter
    private static int port = 25;

    @Bean(initMethod = "start", destroyMethod = "stop")
    @ConditionalOnBean(EmailRecordService.class)
    public SMTPServer smtpServer(Collection<ProtocolHandler> handlers) {
        return new SMTPServer(handlers);
    }

    public void setPort(int port) {
        SmtpServerConfiguration.port = port;
    }
}