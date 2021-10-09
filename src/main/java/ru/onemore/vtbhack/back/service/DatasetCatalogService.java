package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.repository.DatasetRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class DatasetCatalogService {

	private final DatasetRepository datasetRepository;

	@Transactional
	public List<DatasetEntity> getAll(String username) {
		return datasetRepository.findAllBy();
	}

	@Transactional
	public DatasetEntity getOne(Long id, String username) {
		return datasetRepository.getById(id);
	}

}
