package com.cityGuideTL.backend.Repository;
import com.cityGuideTL.backend.Controllers.RouteController;
import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class CityModelRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CitiesRepository citiesRepository;

    @Test
    public void whenFindAll() {
        CityModel newCityModel = new CityModel();
        newCityModel.setPlaceId("just place");
        newCityModel.setLongitude("23.3232");
        newCityModel.setLatitude("32.9434");
        newCityModel.setWoe_name("woe");
        newCityModel.setContent("athens");
        citiesRepository.save(newCityModel);

        CityModel secondCityModel = new CityModel();
        secondCityModel.setPlaceId("just place");
        secondCityModel.setLongitude("23.3232");
        secondCityModel.setLatitude("32.9434");
        secondCityModel.setWoe_name("woe");
        secondCityModel.setContent("athens");
        citiesRepository.save(secondCityModel);

        List<CityModel> cityModels = citiesRepository.findAll();

        assertThat(cityModels.size()).isEqualTo(2);
        assertThat(cityModels.get(0)).isEqualTo(newCityModel);
        assertThat(cityModels.get(1)).isEqualTo(secondCityModel);
    }
    @Test
    public void whenFindAllById() {
        CityModel cityModel = new CityModel();
        cityModel.setContent("Dimitrios");
        citiesRepository.save(cityModel);

        Integer id = cityModel.getId();

        CityModel testCityModel = citiesRepository.findById(id).get();

        assertThat(testCityModel.getContent()).isEqualTo(cityModel.getContent());
    }

}
