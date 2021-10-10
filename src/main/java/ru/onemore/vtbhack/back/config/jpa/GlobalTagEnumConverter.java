package ru.onemore.vtbhack.back.config.jpa;

import ru.onemore.vtbhack.back.enumeration.GlobalTagEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GlobalTagEnumConverter implements AttributeConverter<GlobalTagEnum, String> {

    @Override
    public String convertToDatabaseColumn(GlobalTagEnum enumeration) {
        if (enumeration == null) {
            return null;
        }
        return enumeration.name();
    }

    @Override
    public GlobalTagEnum convertToEntityAttribute(String enumeration) {
        if (enumeration == null) {
            return null;
        }

        return Stream.of(GlobalTagEnum.values())
                .filter(c -> c.name().equals(enumeration))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
