package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetField;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasetDTO {
	private Long id;
	private String name;
	private String description;
	private List<DatasetField> fields;
	private List<DatasetTag> tags;
	private Boolean ordered = false;

	public static DatasetDTO ofEntity(DatasetEntity entity, Boolean ordered) {
		return DatasetDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.description(entity.getDescription())
				.fields(entity.getFields())
				.tags(entity.getTags())
				.ordered(ordered)
				.build();
	}
}
