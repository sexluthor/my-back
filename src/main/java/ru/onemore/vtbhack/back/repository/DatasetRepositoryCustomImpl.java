package ru.onemore.vtbhack.back.repository;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.enumeration.GlobalTagEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.onemore.vtbhack.back.jooq.Tables.DATASET_CATALOG;
import static ru.onemore.vtbhack.back.jooq.Tables.DATASET_FIELD;
import static ru.onemore.vtbhack.back.jooq.Tables.DATASET_TAG;

@AllArgsConstructor
public class DatasetRepositoryCustomImpl implements DatasetRepositoryCustom {

	private final DSLContext dslContext;

	@Override
	public FilteredRequestDatasetDTO getDefaultFilterData() {
		FilteredRequestDatasetDTO requestDatasetDTO = dslContext.select(
			DSL.min(DATASET_CATALOG.LAST_UPDATED).as("date_from"),
			DSL.max(DATASET_CATALOG.LAST_UPDATED).as("date_to"),
			DSL.min(DATASET_CATALOG.PRICE).as("price_from"),
			DSL.max(DATASET_CATALOG.PRICE).as("price_to")
		).from(DATASET_CATALOG).fetchOneInto(FilteredRequestDatasetDTO.class);
		requestDatasetDTO.setTags(
			Arrays.asList(GlobalTagEnum.values())
					.stream().map(GlobalTagEnum::name)
					.collect(Collectors.toList())
		);
		return requestDatasetDTO;
	}



//	@Override
//	public List<DatasetEntity> findFiltered(FilteredRequestDatasetDTO requestDatasetDTO) {
//		return dslContext.select(
//			DATASET_CATALOG.ID,
//			DATASET_CATALOG.NAME,
//			DATASET_CATALOG.DESCRIPTION,
//			DATASET_CATALOG.PRICE,
//			DATASET_CATALOG.LAST_UPDATED,
//			DSL.select().from(DATASET_FIELD).where(DATASET_FIELD.DATASET_ID.eq(DATASET_CATALOG.ID)).field("fields"),
//			DSL.select().from(DATASET_TAG).where(DATASET_TAG.DATASET_ID.eq(DATASET_CATALOG.ID)).field("tags")
//		)
//			.from(DATASET_CATALOG)
//		.fetchInto(DatasetEntity.class);
//	}
}
