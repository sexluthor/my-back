package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.dto.DatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.pojos.OrderedDataset;
import ru.onemore.vtbhack.back.repository.DatasetRepository;
import ru.onemore.vtbhack.back.repository.OrderedDatasetRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DatasetCatalogService {

	private final DatasetRepository datasetRepository;
	private final OrderedDatasetRepository orderedDatasetRepository;

	@Transactional
	public List<DatasetDTO> getAll(String username) {
		CompletableFuture<List<OrderedDataset>> featureOrderedDataset =
				CompletableFuture.supplyAsync(() -> orderedDatasetRepository.findAllByUsername(username));
		List<DatasetEntity> entity = datasetRepository.findAllBy();
		List<Long> orderedDatasetIds = featureOrderedDataset
				.join()
				.stream()
				.map(OrderedDataset::getDatasetId)
				.collect(Collectors.toList());
		return entity
				.stream()
				.map(item -> {
					return DatasetDTO.ofEntity(item, orderedDatasetIds.contains(item.getId()));
				}).collect(Collectors.toList());
	}

	@Transactional
	public DatasetDTO getOne(Long id, String username) {
		CompletableFuture<DatasetEntity> featureEntity = CompletableFuture.supplyAsync(() -> datasetRepository.getById(id));
		Boolean ordered = orderedDatasetRepository.existsByDatasetIdAndUsername(id, username);
		return DatasetDTO.ofEntity(featureEntity.join(), ordered);
	}

}
