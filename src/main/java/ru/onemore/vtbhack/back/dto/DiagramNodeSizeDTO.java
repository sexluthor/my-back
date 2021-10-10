package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagramNodeSizeDTO {
	private Integer width = 120;
	private Integer height = 180;
}
