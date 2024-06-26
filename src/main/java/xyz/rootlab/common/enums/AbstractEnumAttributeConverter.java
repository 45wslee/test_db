package xyz.rootlab.common.enums;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;

@Converter
public abstract class AbstractEnumAttributeConverter<E extends Enum<E> & EntityEnumerable> implements AttributeConverter<E, String> {

    private final Class<E> targetEnumClass;

    /**
     * nullable = false 이면, 변환할 값이 null 로 들어왔을 때 예외가 발생한다.
     * nullable = true 이면, 변환할 값이 null 로 들어왔을 때 예외가 발생하지 않으며, DB 에는 빈 문자열("") 로 들어간다.
     */
    private final boolean nullable;

    private final String enumName;

    public AbstractEnumAttributeConverter(Class<E> enumClass, boolean nullable, String enumName) {
        this.targetEnumClass = enumClass;
        this.nullable = nullable;
        this.enumName = enumName;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if(!nullable && attribute == null) {
            throw new IllegalArgumentException(String.format("[%s](은)는 NULL 로 지정할 수 없습니다.", enumName));
        }

        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if(!nullable && StringUtils.isBlank(dbData)) {
            throw new IllegalArgumentException(String.format("%s(이)가 DB에 NULL 혹은 Empty 로(%s) 저장되어 있습니다.", enumName, dbData));
        }

        return EnumSet.allOf(targetEnumClass).stream()
                .filter(v -> v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("enum=[%s], legacyCode=[%s]가 존재하지 않습니다.", targetEnumClass.getName(), dbData)));
    }
}
