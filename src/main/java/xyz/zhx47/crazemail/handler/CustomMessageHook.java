package xyz.zhx47.crazemail.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.james.protocols.smtp.MailEnvelope;
import org.apache.james.protocols.smtp.SMTPSession;
import org.apache.james.protocols.smtp.hook.HookResult;
import org.apache.james.protocols.smtp.hook.MessageHook;
import org.springframework.stereotype.Component;
import xyz.zhx47.crazemail.model.EmailRecord;
import xyz.zhx47.crazemail.service.EmailRecordService;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Properties;

@Slf4j
@Component
public class CustomMessageHook implements MessageHook {

    private final EmailRecordService emailRecordService;

    public CustomMessageHook(EmailRecordService emailRecordService) {
        this.emailRecordService = emailRecordService;
    }

    @Override
    public HookResult onMessage(SMTPSession session, MailEnvelope mail) {
        try {
            Session defaultSession = Session.getDefaultInstance(new Properties());
            MimeMessage mimeMessage = new MimeMessage(defaultSession, mail.getMessageInputStream());
            MimeMessageParser mimeMessageParser = new MimeMessageParser(mimeMessage);
            mimeMessageParser.parse();

            String emailContentRaw = StringUtils.defaultIfEmpty(mimeMessageParser.getHtmlContent(), mimeMessageParser.getPlainContent());

            EmailRecord emailRecord = new EmailRecord();
            emailRecord.setSubject(mimeMessageParser.getSubject());
            emailRecord.setRaw(IOUtils.toString(mail.getMessageInputStream(), StandardCharsets.UTF_8));
            emailRecord.setRawBody(emailContentRaw);
            emailRecord.setReceivedOn(LocalDateTime.now());
            emailRecord.setFromAddress(mimeMessageParser.getFrom());
            emailRecord.setRecipientAddress(mimeMessageParser.getTo().toString());

            emailRecordService.save(emailRecord);
        } catch (Exception e) {
            log.error("保存邮件失败", e);
        }
        return HookResult.OK;
    }
}
