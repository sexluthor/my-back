package ru.onemore.vtbhack.back.repository;

import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;

import java.util.List;

public interface DatasetRepositoryCustom {
	FilteredRequestDatasetDTO getDefaultFilterData();
//	List<DatasetEntity> findFiltered(FilteredRequestDatasetDTO requestDatasetDTO);
}
