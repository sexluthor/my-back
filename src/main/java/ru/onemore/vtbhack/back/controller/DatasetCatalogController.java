package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.service.DatasetCatalogService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dataset-catalog")
public class DatasetCatalogController {

	private final DatasetCatalogService datasetCatalogService;

	@GetMapping
	public List<DatasetEntity> getAll() {
		return datasetCatalogService.getAll();
	}

	@GetMapping("/{id}")
	public DatasetEntity getOne(@PathVariable Long id) {
		return datasetCatalogService.getOne(id);
	}

}
