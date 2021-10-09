package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.dto.DiagramDataDTO;
import ru.onemore.vtbhack.back.dto.DiagramLinkDTO;
import ru.onemore.vtbhack.back.dto.DiagramNodeDTO;
import ru.onemore.vtbhack.back.dto.DiagramRequestDTO;
import ru.onemore.vtbhack.back.service.DiagramService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/diagram")
public class DiagramController {

	private DiagramService diagramService;

	@PostMapping("/nodes")
	public List<DiagramNodeDTO> getNodes(@RequestBody DiagramRequestDTO diagramRequestDTO) {
		return diagramService.getNodes(diagramRequestDTO.getDatasetIds());
	}

	@PostMapping("/links")
	public Set<DiagramLinkDTO> getLinks(@RequestBody DiagramRequestDTO diagramRequestDTO) {
		return diagramService.getLinks(diagramRequestDTO.getDatasetIds());
	}

	@PostMapping("/data")
	public DiagramDataDTO getDiagramData(@RequestBody DiagramRequestDTO diagramRequestDTO) {
		return diagramService.getDiagramData(diagramRequestDTO.getDatasetIds());
	}

}
