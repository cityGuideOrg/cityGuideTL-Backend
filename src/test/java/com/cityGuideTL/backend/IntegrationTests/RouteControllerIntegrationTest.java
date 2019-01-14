package com.cityGuideTL.backend.IntegrationTests;

import com.cityGuideTL.backend.BackendApplication;
import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Models.CityModel;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void addCity() {
        CityModel newCityModel = new CityModel();
        newCityModel.setLongitude("23.3232");
        newCityModel.setLatitude("32.9434");
        newCityModel.setWoe_name("woe");
        newCityModel.setContent("athens");

        City city = new City();
        city.setLongitude(newCityModel.getLongitude());
        city.setLatitude(newCityModel.getLatitude());
        city.setWoe_name(newCityModel.getWoe_name());
        city.setContent(newCityModel.getContent());

        HttpEntity<City> entity = new HttpEntity<City>(city, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/city"),
                HttpMethod.POST, entity, String.class);

        String actual;
        try {
            actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        }catch (NullPointerException e) {
            actual =  "";
        }

        assertTrue(actual.contains("/city"));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
