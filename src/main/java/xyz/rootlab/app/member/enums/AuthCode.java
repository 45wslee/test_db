package xyz.rootlab.app.member.enums;

import lombok.Getter;
import xyz.rootlab.common.enums.AbstractEnumAttributeConverter;
import xyz.rootlab.common.enums.EntityEnumerable;

@Getter
public enum AuthCode implements EntityEnumerable {
    SYSTEM_ADMIN("AUTH01", "시스템 관리자"),
    INSTN_ADMIN("AUTH02", "기관 관리자"),
    INSTN_USER("AUTH03", "기관 이용자"),
    LEANER("AUTH04", "학습자")
    ;

    private final String code;
    private final String desc;

    AuthCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthCode findByCode(String code) {
        for (AuthCode authCode : AuthCode.values()) {
            if (authCode.getCode().equalsIgnoreCase(code)) {
                return authCode;
            }
        }
        return null;
    }

    public static AuthCode findByName(String str) {
        for (AuthCode authCode : AuthCode.values()) {
            if (authCode.name().equalsIgnoreCase(str)) {
                return authCode;
            }
        }
        return null;
    }

    public static class Converter extends AbstractEnumAttributeConverter<AuthCode> {
        public Converter() {
            super(AuthCode.class, false, "사용자 권한");
        }
    }
}
