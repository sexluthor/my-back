package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.dto.UserDTO;
import ru.onemore.vtbhack.back.util.SecurityUtil;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

	@GetMapping("/about-me")
	public UserDTO aboutMe() {
		return SecurityUtil.getUserInfo();
	}

}
