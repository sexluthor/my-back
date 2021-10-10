package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.dto.FilteredRequestDatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.DatasetTag;
import ru.onemore.vtbhack.back.repository.DatasetRepository;
import ru.onemore.vtbhack.back.repository.DatasetTagsRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class DatasetCatalogService {

	private final DatasetRepository datasetRepository;
	private final DatasetTagsRepository datasetTagsRepository;

	@Transactional
	public List<DatasetEntity> getAll() {
		return datasetRepository.findAllBy();
	}

	public FilteredRequestDatasetDTO getDefaultFilterData() {
		CompletableFuture<List<String>> tags = CompletableFuture.supplyAsync(() ->
				datasetTagsRepository.getDistinctName());
		FilteredRequestDatasetDTO filteredRequestDatasetDTO = datasetRepository.getDefaultFilterData();
		filteredRequestDatasetDTO.setTags(tags.join());
		return filteredRequestDatasetDTO;
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
