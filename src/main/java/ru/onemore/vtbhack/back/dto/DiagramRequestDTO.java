package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagramRequestDTO {
	private List<Long> datasetIds;
}
