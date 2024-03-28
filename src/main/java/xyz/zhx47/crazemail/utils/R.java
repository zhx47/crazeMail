package xyz.zhx47.crazemail.utils;

import java.util.HashMap;

/**
 * 响应信息主体
 *
 * @author zhx47
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", "200");
        put("body", new HashMap<>());
        put("desc", "");
    }

    public static R ok(Integer code, String desc) {
        R r = new R();
        r.put("code", code);
        r.put("desc", desc);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R putBodyByObject(Object value) {
        super.put("body", value);
        return this;
    }
}