package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;
import ru.onemore.vtbhack.back.repository.DatasetRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class DatasetCatalogService {

	private final DatasetRepository datasetRepository;

	@Transactional
	public List<DatasetEntity> getAll() {
		return datasetRepository.findAllBy();
	}

	public FilteredRequestDatasetDTO getDefaultFilterData() {
		return datasetRepository.getDefaultFilterData();
	}

	@Transactional
	public List<DatasetEntity> getFiltered(FilteredRequestDatasetDTO requestDatasetDTO) {
		return datasetRepository.findFiltered(
			requestDatasetDTO.getDateFrom(),
			requestDatasetDTO.getDateTo(),
			requestDatasetDTO.getTags(),
			requestDatasetDTO.getPriceFrom(),
			requestDatasetDTO.getPriceTo()
		);
	}

	@Transactional
	public DatasetEntity getOne(Long id) {
		return datasetRepository.getById(id);
	}

}
