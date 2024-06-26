package xyz.rootlab.common.file.enums;

import lombok.Getter;
import xyz.rootlab.common.enums.AbstractEnumAttributeConverter;
import xyz.rootlab.common.enums.CommonTypeHandler;
import xyz.rootlab.common.enums.EntityEnumerable;

@Getter
public enum FileType implements EntityEnumerable {
    NORMAL("CODE", "일반");

    private final String code;
    private final String desc;

    FileType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static class Converter extends AbstractEnumAttributeConverter<FileType> {
        public Converter() {
            super(FileType.class, false, "파일 타입");
        }
    }

    /**
     * mybatis 용 handler
     */
    public static class TypeHandler extends CommonTypeHandler<FileType> {
        public TypeHandler() {
            super(FileType.class);
        }
    }
}
