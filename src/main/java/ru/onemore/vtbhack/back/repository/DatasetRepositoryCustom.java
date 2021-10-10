package ru.onemore.vtbhack.back.repository;

import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;

public interface DatasetRepositoryCustom {
	FilteredRequestDatasetDTO getDefaultFilterData();
//	List<DatasetEntity> findFiltered(FilteredRequestDatasetDTO requestDatasetDTO);
}
