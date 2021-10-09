package ru.onemore.vtbhack.back.service.client;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class DataHubClient {

	private final String loginUrl = "http://datahub.yc.pbd.ai:9002/logIn";
	private final String graphqlUrl = "http://datahub.yc.pbd.ai:9002/api/v2/graphql";

	public Map<String, String> auth(String username, String password) throws IOException {
		JSONObject authData = new JSONObject();
		authData.appendField("username", username);
		authData.appendField("password", password);
		Connection.Response response = Jsoup.connect(loginUrl)
				.method(Connection.Method.POST)
				.header("Content-Type", "application/json;charset=UTF-8")
				.ignoreContentType(true)
				.requestBody(authData.toString())
				.execute();
		return response.cookies();
	}

	public String graphRequest(String graphql, Map<String, String> cookies) throws IOException {
		return Jsoup.connect(graphqlUrl)
				.method(Connection.Method.POST)
				.header("Content-Type", "application/json;charset=UTF-8")
				.ignoreContentType(true)
				.cookies(cookies)
				.requestBody(graphql)
				.execute()
				.body();
	}

}
