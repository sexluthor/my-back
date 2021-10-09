package ru.onemore.vtbhack.back.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.service.client.DataHubClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class DataHubService {

	private final DataHubClient dataHubClient;

	private final String username = "datahub";
	private final String password = "datahub";

	private final String testData = "{\"operationName\":\"getBrowseResults\",\"variables\":{\"input\":{\"type\":\"DATASET\",\"path\":[\"prod\",\"hive\"],\"start\":0,\"count\":10,\"filters\":null}},\"query\":\"query getBrowseResults($input: BrowseInput!) {\\n  browse(input: $input) {\\n    entities {\\n      urn\\n      type\\n      ... on Dataset {\\n        name\\n        origin\\n        description\\n        platform {\\n          name\\n          info {\\n            logoUrl\\n            __typename\\n          }\\n          __typename\\n        }\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        globalTags {\\n          ...globalTagsFields\\n          __typename\\n        }\\n        glossaryTerms {\\n          ...glossaryTerms\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on Dashboard {\\n        urn\\n        type\\n        tool\\n        dashboardId\\n        info {\\n          name\\n          description\\n          externalUrl\\n          access\\n          lastModified {\\n            time\\n            __typename\\n          }\\n          __typename\\n        }\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        globalTags {\\n          ...globalTagsFields\\n          __typename\\n        }\\n        glossaryTerms {\\n          ...glossaryTerms\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on GlossaryTerm {\\n        name\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        glossaryTermInfo {\\n          definition\\n          termSource\\n          sourceRef\\n          sourceUrl\\n          customProperties {\\n            key\\n            value\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on Chart {\\n        urn\\n        type\\n        tool\\n        chartId\\n        info {\\n          name\\n          description\\n          externalUrl\\n          type\\n          access\\n          lastModified {\\n            time\\n            __typename\\n          }\\n          __typename\\n        }\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        globalTags {\\n          ...globalTagsFields\\n          __typename\\n        }\\n        glossaryTerms {\\n          ...glossaryTerms\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on DataFlow {\\n        urn\\n        type\\n        orchestrator\\n        flowId\\n        cluster\\n        info {\\n          name\\n          description\\n          project\\n          __typename\\n        }\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        globalTags {\\n          ...globalTagsFields\\n          __typename\\n        }\\n        glossaryTerms {\\n          ...glossaryTerms\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on MLFeatureTable {\\n        urn\\n        type\\n        name\\n        description\\n        featureTableProperties {\\n          description\\n          mlFeatures {\\n            urn\\n            __typename\\n          }\\n          mlPrimaryKeys {\\n            urn\\n            __typename\\n          }\\n          __typename\\n        }\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        platform {\\n          name\\n          info {\\n            logoUrl\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on MLModel {\\n        name\\n        origin\\n        description\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        globalTags {\\n          ...globalTagsFields\\n          __typename\\n        }\\n        platform {\\n          name\\n          info {\\n            logoUrl\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on MLModelGroup {\\n        name\\n        origin\\n        description\\n        ownership {\\n          ...ownershipFields\\n          __typename\\n        }\\n        platform {\\n          name\\n          info {\\n            logoUrl\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    groups {\\n      name\\n      count\\n      __typename\\n    }\\n    start\\n    count\\n    total\\n    metadata {\\n      path\\n      totalNumEntities\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\\nfragment ownershipFields on Ownership {\\n  owners {\\n    owner {\\n      ... on CorpUser {\\n        urn\\n        type\\n        username\\n        info {\\n          active\\n          displayName\\n          title\\n          email\\n          firstName\\n          lastName\\n          fullName\\n          __typename\\n        }\\n        editableInfo {\\n          pictureLink\\n          __typename\\n        }\\n        __typename\\n      }\\n      ... on CorpGroup {\\n        urn\\n        type\\n        name\\n        info {\\n          email\\n          admins {\\n            urn\\n            username\\n            info {\\n              active\\n              displayName\\n              title\\n              email\\n              firstName\\n              lastName\\n              fullName\\n              __typename\\n            }\\n            editableInfo {\\n              pictureLink\\n              teams\\n              skills\\n              __typename\\n            }\\n            __typename\\n          }\\n          members {\\n            urn\\n            username\\n            info {\\n              active\\n              displayName\\n              title\\n              email\\n              firstName\\n              lastName\\n              fullName\\n              __typename\\n            }\\n            editableInfo {\\n              pictureLink\\n              teams\\n              skills\\n              __typename\\n            }\\n            __typename\\n          }\\n          groups\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    type\\n    __typename\\n  }\\n  lastModified {\\n    time\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment globalTagsFields on GlobalTags {\\n  tags {\\n    tag {\\n      urn\\n      name\\n      description\\n      __typename\\n    }\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment glossaryTerms on GlossaryTerms {\\n  terms {\\n    term {\\n      urn\\n      name\\n      __typename\\n    }\\n    __typename\\n  }\\n  __typename\\n}\\n\"}";
	private static Map<String, String> dataHubClientCookies;

//	@PostConstruct
//	@SneakyThrows
//	public void setUp() {
//		dataHubClientCookies = dataHubClient.auth(username, password);
//	}
//
//	public String getData() throws IOException {
//		try {
//			return dataHubClient.graphRequest(testData, dataHubClientCookies);
//		} catch (IOException e) {
//			setUp();
//			return dataHubClient.graphRequest(testData, dataHubClientCookies);
//		}
//	}

//	public  getCatalog() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		String body;
//	}

}
