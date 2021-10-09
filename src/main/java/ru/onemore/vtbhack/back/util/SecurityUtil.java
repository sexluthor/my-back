package ru.onemore.vtbhack.back.util;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import ru.onemore.vtbhack.back.enumeration.RolesEnum;
import ru.onemore.vtbhack.back.exception.AuthorizationException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SecurityUtil {

	public static String preferredUsername = "preferred_username";
	public static String realmAccess = "realm_access";
	public static String roles = "roles";

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

//	public static JwtPayloadDTO getUserDetails() {
//		return (JwtPayloadDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
//	}

	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.isAuthenticated();
	}

	private static Map<String, Object> getClaims() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof String)
			throw new AuthorizationException();
		Jwt jwt = ((Jwt) authentication.getPrincipal());
		return jwt.getClaims();
	}

}
