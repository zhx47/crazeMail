package xyz.zhx47.crazemail.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailRecord {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private String fromAddress;
    private String recipientAddress;
    private String subject;
    private String raw;
    private String rawBody;
    private LocalDateTime receivedOn;

}