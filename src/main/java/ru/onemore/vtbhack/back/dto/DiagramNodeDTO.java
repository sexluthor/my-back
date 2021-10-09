package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagramNodeDTO {
	private String id;
	private String title;
	private DiagramNodeSizeDTO size = new DiagramNodeSizeDTO();
	private DiagramCoordinatesDTO coordinates = new DiagramCoordinatesDTO();
	private Map<String, String> portsIn;
	private Map<String, String> portsOut;
}
