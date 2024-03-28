package xyz.zhx47.crazemail.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xyz.zhx47.crazemail.dao.EmailRecordMapper;
import xyz.zhx47.crazemail.model.EmailRecord;
import xyz.zhx47.crazemail.service.EmailRecordService;

@Service
public class EmailRecordServiceImpl extends ServiceImpl<EmailRecordMapper, EmailRecord> implements EmailRecordService {
    @Override
    public Page<EmailRecord> qeuryByEmialAndKeyword(String email, String subject) {
        return this.page(new Page<>(1, 20),
                Wrappers.<EmailRecord>lambdaQuery()
                        .like(StringUtils.isNotBlank(email), EmailRecord::getRecipientAddress, email)
                        .like(StringUtils.isNotBlank(subject), EmailRecord::getSubject, subject));
    }
}
