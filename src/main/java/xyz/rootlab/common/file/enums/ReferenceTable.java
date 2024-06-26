package xyz.rootlab.common.file.enums;


import lombok.Getter;
import xyz.rootlab.common.enums.AbstractEnumAttributeConverter;
import xyz.rootlab.common.enums.CommonTypeHandler;
import xyz.rootlab.common.enums.EntityEnumerable;

@Getter
public enum ReferenceTable implements EntityEnumerable {
    NOTICE("notice","공지사항"),
    FAQ("faq", "FAQ"),
    INQUIRY("inquiry", "1:1문의"),
	CKEDITOR("ckeditor", "CKEditor");

    private final String code;
    private final String desc;

    ReferenceTable(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static class Converter extends AbstractEnumAttributeConverter<ReferenceTable> {
        public Converter() {
            super(ReferenceTable.class, false, "파일 참조 테이블");
        }
    }

    /**
     * mybatis 용 handler
     */
    public static class TypeHandler extends CommonTypeHandler<ReferenceTable> {
        public TypeHandler() {
            super(ReferenceTable.class);
        }
    }
}
