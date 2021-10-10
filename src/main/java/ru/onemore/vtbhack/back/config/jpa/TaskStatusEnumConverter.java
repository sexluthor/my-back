package ru.onemore.vtbhack.back.config.jpa;

import ru.onemore.vtbhack.back.enumeration.TaskStatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TaskStatusEnumConverter implements AttributeConverter<TaskStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(TaskStatusEnum enumeration) {
        if (enumeration == null) {
            return null;
        }
        return enumeration.name();
    }

    @Override
    public TaskStatusEnum convertToEntityAttribute(String enumeration) {
        if (enumeration == null) {
            return null;
        }

        return Stream.of(TaskStatusEnum.values())
                .filter(c -> c.name().equals(enumeration))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
