package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilteredRequestDatasetDTO {

	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private List<String> tags;
	private Integer priceFrom;
	private Integer priceTo;

}
