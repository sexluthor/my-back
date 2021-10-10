package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.dto.DiagramDataDTO;
import ru.onemore.vtbhack.back.dto.DiagramLinkDTO;
import ru.onemore.vtbhack.back.dto.DiagramNodeDTO;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetField;
import ru.onemore.vtbhack.back.repository.DatasetFieldsRepository;
import ru.onemore.vtbhack.back.repository.DatasetRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagramService {

	private final DatasetFieldsRepository datasetFieldsRepository;
	private final DatasetRepository datasetRepository;

	public List<DiagramNodeDTO> getNodes(List<Long> datasetIds) {
		List<DatasetEntity> datasetList = datasetRepository.findAllByIdIn(datasetIds);
		AtomicInteger atomicInteger = new AtomicInteger();
		return datasetList
				.stream()
				.map(dataset -> {
					int index = atomicInteger.getAndIncrement();
					DiagramNodeDTO node = new DiagramNodeDTO();

					Map<String, String> portsIn = new HashMap<>();
					Map<String, String> portsOut = new HashMap<>();

					dataset.getFields().forEach(item -> {
						portsIn.put(item.getName(), item.getName());
						portsOut.put(item.getName(), "");
					});

					node.setId(dataset.getId().toString());
					node.setTitle(dataset.getName());
					node.setPortsIn(portsIn);
					node.setPortsOut(portsOut);
					node.getSize().setHeight(portsOut.size() * 20 + 20);
					node.getCoordinates().setX(200 * index);
					return node;
				}).collect(Collectors.toList());
	}

	public Set<DiagramLinkDTO> getLinks(List<Long> datasetIds) {
		Set<DiagramLinkDTO> linkDTOSet = new HashSet<>();
		Map<Long, List<DatasetField>> fields =
			datasetFieldsRepository.getAllByDatasetIdIn(datasetIds)
			.stream()
			.collect(Collectors.groupingBy(DatasetField::getDatasetId));
		for (int datasetIndex = 0; datasetIndex < datasetIds.size(); datasetIndex++) {
			Long datasetId = datasetIds.get(datasetIndex);
			List<DatasetField> datasetFields = fields.get(datasetId);

			for (int secondDatasetIndex = datasetIndex + 1; secondDatasetIndex < datasetIds.size(); secondDatasetIndex++) {
				Long secondDatasetId = datasetIds.get(secondDatasetIndex);
				List<DatasetField> secondDatasetFields = fields.get(secondDatasetId);

				if (secondDatasetFields == null) break;

				datasetFields.forEach(datasetField -> {
					secondDatasetFields.forEach(secondDatasetField -> {
						if (!datasetField.getDatasetFieldType().equals(secondDatasetField.getDatasetFieldType()))
							return;
						DiagramLinkDTO linkDTO = new DiagramLinkDTO();
						linkDTO.setId(datasetField.getDatasetFieldType() + "(" + datasetField.getDatasetId() + "_" + secondDatasetField.getDatasetId() + ")");
						linkDTO.setStart_id(datasetId.toString());
						linkDTO.setStart_port(datasetField.getName());
						linkDTO.setEnd_id(secondDatasetId.toString());
						linkDTO.setEnd_port(secondDatasetField.getName());
						linkDTOSet.add(linkDTO);
					});
				});
			}
		}
		return linkDTOSet;
	}

	public DiagramDataDTO getDiagramData(List<Long> datasetIds) {
		CompletableFuture<Map<String, DiagramLinkDTO>> linkFeature = CompletableFuture.supplyAsync(() -> {
			return getLinks(datasetIds).stream().collect(Collectors.toMap(DiagramLinkDTO::getId, Function.identity()));
		});
		Map<String, DiagramNodeDTO> nodes = getNodes(datasetIds).stream().collect(Collectors.toMap(DiagramNodeDTO::getId, Function.identity()));
		return new DiagramDataDTO(
			nodes,
			linkFeature.join()
		);
	}

}
