package xyz.rootlab.common.file.enums;

import lombok.Getter;
import xyz.rootlab.common.enums.AbstractEnumAttributeConverter;
import xyz.rootlab.common.enums.CommonTypeHandler;
import xyz.rootlab.common.enums.EntityEnumerable;

@Getter
public enum FileAuth implements EntityEnumerable {
    ALL("FAC001","모두"),
    LOGIN_USER("", "로그인한 사용자"),
    OWNER("FAC002","소유자"),
    ADMIN("FAC003","관리자");

    private final String code;
    private final String desc;

    FileAuth(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static class Converter extends AbstractEnumAttributeConverter<FileAuth> {
        public Converter() {
            super(FileAuth.class, false, "파일 권한");
        }
    }

    /**
     * mybatis 용 handler
     */
    public static class TypeHandler extends CommonTypeHandler<FileAuth> {
        public TypeHandler() {
            super(FileAuth.class);
        }
    }
}