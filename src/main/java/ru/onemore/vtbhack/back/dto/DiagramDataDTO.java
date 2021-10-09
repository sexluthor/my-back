package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagramDataDTO {
	private Map<String, DiagramNodeDTO> nodes;
	private Map<String, DiagramLinkDTO> links;
}
