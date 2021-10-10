package ru.onemore.vtbhack.back.repository;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;

import java.util.ArrayList;

import static ru.onemore.vtbhack.back.jooq.Tables.DATASET_CATALOG;

@AllArgsConstructor
public class DatasetRepositoryCustomImpl implements DatasetRepositoryCustom {

	private final DSLContext dslContext;

	@Override
	public FilteredRequestDatasetDTO getDefaultFilterData() {
		FilteredRequestDatasetDTO filteredRequestDatasetDTO = dslContext.select(
			DSL.min(DATASET_CATALOG.LAST_UPDATED).as("date_from"),
			DSL.max(DATASET_CATALOG.LAST_UPDATED).as("date_to"),
			DSL.min(DATASET_CATALOG.PRICE).as("price_from"),
			DSL.max(DATASET_CATALOG.PRICE).as("price_to")
		).from(DATASET_CATALOG).fetchOneInto(FilteredRequestDatasetDTO.class);
		filteredRequestDatasetDTO.setTags(new ArrayList<>());
		return filteredRequestDatasetDTO;
	}

}
