package xyz.zhx47.crazemail.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.zhx47.crazemail.model.EmailRecord;
import xyz.zhx47.crazemail.service.EmailRecordService;
import xyz.zhx47.crazemail.utils.R;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailRecordService emailRecordService;

    @GetMapping(value = "/email/{email}")
    public R emailDetail(@PathVariable("email") String email,
                         @RequestParam(required = false) String subject) {
        Page<EmailRecord> list = emailRecordService.qeuryByEmialAndKeyword(email, subject);
        return R.ok(200, "获取成功").putBodyByObject(list);
    }

}
