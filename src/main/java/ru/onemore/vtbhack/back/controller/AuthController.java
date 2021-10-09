package ru.onemore.vtbhack.back.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.dto.AuthDTO;
import ru.onemore.vtbhack.back.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping()
	public JsonNode auth(@RequestBody AuthDTO dto) {
		return authService.auth(dto.getUsername(), dto.getPassword());
	}

}
