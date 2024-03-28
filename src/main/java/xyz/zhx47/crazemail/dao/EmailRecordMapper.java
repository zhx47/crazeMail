package xyz.zhx47.crazemail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.zhx47.crazemail.model.EmailRecord;

@Mapper
public interface EmailRecordMapper extends BaseMapper<EmailRecord> {
    
}