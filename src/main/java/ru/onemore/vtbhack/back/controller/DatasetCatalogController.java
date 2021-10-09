package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.dto.DatasetDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.service.DatasetCatalogService;
import ru.onemore.vtbhack.back.util.SecurityUtil;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dataset-catalog")
public class DatasetCatalogController {

	private final DatasetCatalogService datasetCatalogService;

	@GetMapping
	public List<DatasetDTO> getAll() {
		return datasetCatalogService.getAll(SecurityUtil.getUserLogin());
	}

}
