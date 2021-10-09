package ru.onemore.vtbhack.back.util;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import ru.onemore.vtbhack.back.dto.UserDTO;
import ru.onemore.vtbhack.back.enumeration.RolesEnum;
import ru.onemore.vtbhack.back.exception.AuthorizationException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SecurityUtil {

	public static final String preferredUsername = "preferred_username";
	public static final String realmAccess = "realm_access";
	public static final String roles = "roles";
	public static final String email = "email";
	public static final String givenName = "given_name";
	public static final String familyName = "family_name";

	public static String getUserLogin() {
		return (String) getClaims().get(preferredUsername);
	}

	public static boolean containsRole(RolesEnum[] roles) {
		List<RolesEnum> authorities = Arrays.asList(getUserRoles());
		for(RolesEnum role: roles) {
			if(authorities.contains(role)) return true;
		}
		return false;
	}

	public static boolean containsRole(RolesEnum role) {
		return containsRole(new RolesEnum[]{role});
	}

	public static RolesEnum[] getUserRoles() {
		JSONObject jsonRealmAccess = (JSONObject) getClaims().get(realmAccess);
		JSONArray jsonRoles = (JSONArray) jsonRealmAccess.get(roles);
		return jsonRoles
				.stream()
				.map(String::valueOf)
				.map(RolesEnum::valueOf)
				.toArray(RolesEnum[]::new);
	}

	public static RolesEnum getUserRole() {
		return getUserRoles()[0];
	}

//	public static JwtPayloadDTO getUserDetails() {
//		return (JwtPayloadDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
//	}

	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.isAuthenticated();
	}

	public static UserDTO getUserInfo() {
		Map<String, Object> claims = getClaims();
		return new UserDTO(
			getUserLogin(),
			(String) claims.get(email),
			(String) claims.get(givenName),
			(String) claims.get(familyName),
			getUserRole()
		);
	}

	private static Map<String, Object> getClaims() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof String)
			throw new AuthorizationException();
		Jwt jwt = ((Jwt) authentication.getPrincipal());
		return jwt.getClaims();
	}

}
