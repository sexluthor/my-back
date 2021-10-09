package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.onemore.vtbhack.back.enumeration.RolesEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private RolesEnum role;
}
