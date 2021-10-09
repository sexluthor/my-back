package ru.onemore.vtbhack.back.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Value("${keycloak.host}")
	private String host;

	@Value("${keycloak.realm}")
	private String realm;

	@Value("${keycloak.client-id}")
	private String clientId;

	@SneakyThrows
	public JsonNode auth(String username, String password) {
		String url = String.format("%s/auth/realms/%s/protocol/openid-connect/token", host, realm);
		String body = Jsoup.connect(url)
				.method(Connection.Method.POST)
				.data("client_id", clientId)
				.data("username", username)
				.data("password", password)
				.data("grant_type", "password")
				.ignoreContentType(true)
				.execute().body();
		return new ObjectMapper().readTree(body);
	}

}
