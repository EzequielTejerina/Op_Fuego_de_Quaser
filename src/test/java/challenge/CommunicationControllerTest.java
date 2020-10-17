package challenge;

import challenge.dto.SatelliteDTO;
import challenge.exception.ServiceException;
import challenge.model.Satellite;
import challenge.request.TopSecretRequest;
import challenge.request.TopSecretSplitRequest;
import challenge.response.ObjectResponse;
import com.google.gson.Gson;
import org.junit.Assert;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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
    public void testTopSecretSplitWithThreeSatellites() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es un mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"Este", "", "un", "", ""};

        TopSecretSplitRequest topSecretSplitRequest1 = new TopSecretSplitRequest();
        topSecretSplitRequest1.setDistance(100.0F);
        topSecretSplitRequest1.setMessage(messages1);

        TopSecretSplitRequest topSecretSplitRequest2 = new TopSecretSplitRequest();
        topSecretSplitRequest2.setDistance(115.5F);
        topSecretSplitRequest2.setMessage(messages2);

        TopSecretSplitRequest topSecretSplitRequest3 = new TopSecretSplitRequest();
        topSecretSplitRequest3.setDistance(142.7F);
        topSecretSplitRequest3.setMessage(messages3);


        HttpEntity<String> request1 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest1), headers);
        ResponseEntity<String> response1 = template.postForEntity(baseUrl.toString() + "topsecret_split/kenobi", request1, String.class);

        HttpEntity<String> request2 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest2), headers);
        ResponseEntity<String> response2 = template.postForEntity(baseUrl.toString() + "topsecret_split/skywalker", request2, String.class);

        HttpEntity<String> request3 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest3), headers);
        ResponseEntity<String> response3 = template.postForEntity(baseUrl.toString() + "topsecret_split/sato", request3, String.class);

        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(HttpStatus.OK, response3.getStatusCode());

        ResponseEntity<String> response = template.getForEntity(new URI(baseUrl.toString() + "topsecret_split/"), String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplitWithTwoSatellites() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este es mensaje secreto";
        String[] messages1 = {"Este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};

        TopSecretSplitRequest topSecretSplitRequest1 = new TopSecretSplitRequest();
        topSecretSplitRequest1.setDistance(100.0F);
        topSecretSplitRequest1.setMessage(messages1);

        TopSecretSplitRequest topSecretSplitRequest2 = new TopSecretSplitRequest();
        topSecretSplitRequest2.setDistance(115.5F);
        topSecretSplitRequest2.setMessage(messages2);


        HttpEntity<String> request1 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest1), headers);
        ResponseEntity<String> response1 = template.postForEntity(baseUrl.toString() + "topsecret_split/kenobi", request1, String.class);

        HttpEntity<String> request2 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest2), headers);
        ResponseEntity<String> response2 = template.postForEntity(baseUrl.toString() + "topsecret_split/skywalker", request2, String.class);

        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.OK, response2.getStatusCode());

        ResponseEntity<String> response = template.getForEntity(new URI(baseUrl.toString() + "topsecret_split/"), String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplitWithOneSatellite() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este mensaje";
        String[] messages1 = {"Este", "", "", "mensaje", ""};

        TopSecretSplitRequest topSecretSplitRequest1 = new TopSecretSplitRequest();
        topSecretSplitRequest1.setDistance(100.0F);
        topSecretSplitRequest1.setMessage(messages1);


        HttpEntity<String> request1 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest1), headers);
        ResponseEntity<String> response1 = template.postForEntity(baseUrl.toString() + "topsecret_split/kenobi", request1, String.class);

        assertEquals(HttpStatus.OK, response1.getStatusCode());

        ResponseEntity<String> response = template.getForEntity(new URI(baseUrl.toString() + "topsecret_split/"), String.class);
        ObjectResponse objectResponse = this.gson.fromJson(response.getBody(), ObjectResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, objectResponse.getMessage());
    }

    @Test
    public void testTopSecretSplitWithOneInvalidSatellite()throws ServiceException{
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        headers.add("Content-Type", CONTENT_TYPE_JSON);
        String expected = "Este mensaje";
        String[] messages1 = {"Este", "", "", "mensaje", ""};

        TopSecretSplitRequest topSecretSplitRequest1 = new TopSecretSplitRequest();
        topSecretSplitRequest1.setDistance(100.0F);
        topSecretSplitRequest1.setMessage(messages1);

        try{
            HttpEntity<String> request1 = new HttpEntity<>(this.gson.toJson(topSecretSplitRequest1), headers);
            template.postForEntity(baseUrl.toString() + "topsecret_split/kenob", request1, String.class);
            Assert.fail();
        }
        catch(HttpClientErrorException ex){
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        }
        
    }
}
