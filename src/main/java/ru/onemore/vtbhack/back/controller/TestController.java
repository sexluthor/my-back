package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.service.DataHubService;
import ru.onemore.vtbhack.back.util.SecurityUtil;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class TestController {

	private final DataHubService dataHubService;

	@GetMapping("/login")
	public String test() {
		return SecurityUtil.getUserLogin();
	}

//	@SneakyThrows
//	@GetMapping("/datahub")
//	public String getDatahubData() {
//		return dataHubService.getData();
//	}

}
