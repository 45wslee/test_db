package xyz.rootlab.app.member.enums;

import lombok.Getter;
import xyz.rootlab.common.enums.AbstractEnumAttributeConverter;
import xyz.rootlab.common.enums.EntityEnumerable;

@Getter
public enum MbrStatCode implements EntityEnumerable {
    APPLY("MSC001", "신청"),
    APPROVED("MSC002", "승인"),
    REJECT("MSC003", "반려"),
    STOP("MSC004", "이용자 계정 정지"),
    LOCK("MSC005", "로그인 5회 이상 실패 시 잠금")
    ;

    private final String code;
    private final String desc;

    MbrStatCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static class Converter extends AbstractEnumAttributeConverter<MbrStatCode> {
        public Converter() {
            super(MbrStatCode.class, false, "사용자 상태");
        }
    }
}
