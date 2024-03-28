package xyz.zhx47.crazemail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zhx47.crazemail.model.EmailRecord;

public interface EmailRecordService extends IService<EmailRecord> {

    Page<EmailRecord> qeuryByEmialAndKeyword(String email, String subject);
}
