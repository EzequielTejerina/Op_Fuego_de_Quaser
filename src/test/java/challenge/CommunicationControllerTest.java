package challenge;

import challenge.model.Satellite;
import challenge.request.TopSecretRequest;
import challenge.response.ObjectResponse;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommunicationControllerTest {

    private static final String CONTENT_TYPE_JSON = "application/json";

    @LocalServerPort
    private int port;
    private Gson gson;
    private URL baseUrl;

    @Before
    public void setUp() throws Exception {
        this.baseUrl = new URL("http://localhost:" + this.port + "/challenge/");
        this.gson = new Gson();
    }

    String exampleBodyRequestTopSecretSplitKenobi = "{" +
            "            \"distance\": 100.0," +
            "            \"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"]" +
            "        }";
    String exampleBodyRequestTopSecretSplitSkywalker = "{" +
            "            \"distance\": 115.5," +
            "            \"message\": [\"\", \"es\", \"\", \"\", \"secreto\"]" +
            "        }";
    String exampleBodyRequestTopSecretSplitSato = "{" +
            "            \"distance\": 142.7," +
            "            \"message\": [\"este\", \"\", \"un\", \"\", \"\"]" +
            "        }";


    @Test
    public void testTopSecret() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es un mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"Este", "", "un", "", ""};

        TopSecretRequest topSecretRequest = new TopSecretRequest();
        topSecretRequest.setSatellites(new ArrayList<Satellite>());
        topSecretRequest.getSatellites().add(new Satellite("kenobi", 100.0F, messages1));
        topSecretRequest.getSatellites().add(new Satellite("skywalker", 115.5F, messages2));
        topSecretRequest.getSatellites().add(new Satellite("sato", 142.7F, messages3));

        HttpEntity<String> request = new HttpEntity<>(this.gson.toJson(topSecretRequest), headers);
        ResponseEntity<String> response = template.postForEntity(baseUrl.toString() + "topsecret/", request, String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplit1() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es un mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"Este", "", "un", "", ""};

        TopSecretRequest topSecretRequest = new TopSecretRequest();
        topSecretRequest.setSatellites(new ArrayList<Satellite>());
        topSecretRequest.getSatellites().add(new Satellite("kenobi", 100.0F, messages1));
        topSecretRequest.getSatellites().add(new Satellite("skywalker", 115.5F, messages2));
        topSecretRequest.getSatellites().add(new Satellite("sato", 142.7F, messages3));

        HttpEntity<String> request = new HttpEntity<>(this.gson.toJson(topSecretRequest), headers);
        ResponseEntity<String> response = template.postForEntity(baseUrl.toString() + "topsecret/", request, String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplit2() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es un mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"Este", "", "un", "", ""};

        TopSecretRequest topSecretRequest = new TopSecretRequest();
        topSecretRequest.setSatellites(new ArrayList<Satellite>());
        topSecretRequest.getSatellites().add(new Satellite("kenobi", 100.0F, messages1));
        topSecretRequest.getSatellites().add(new Satellite("skywalker", 115.5F, messages2));
        topSecretRequest.getSatellites().add(new Satellite("sato", 142.7F, messages3));

        HttpEntity<String> request = new HttpEntity<>(this.gson.toJson(topSecretRequest), headers);
        ResponseEntity<String> response = template.postForEntity(baseUrl.toString() + "topsecret/", request, String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplit3() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es un mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"Este", "", "un", "", ""};

        TopSecretRequest topSecretRequest = new TopSecretRequest();
        topSecretRequest.setSatellites(new ArrayList<Satellite>());
        topSecretRequest.getSatellites().add(new Satellite("kenobi", 100.0F, messages1));
        topSecretRequest.getSatellites().add(new Satellite("skywalker", 115.5F, messages2));
        topSecretRequest.getSatellites().add(new Satellite("sato", 142.7F, messages3));

        HttpEntity<String> request = new HttpEntity<>(this.gson.toJson(topSecretRequest), headers);
        ResponseEntity<String> response = template.postForEntity(baseUrl.toString() + "topsecret/", request, String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }
}
