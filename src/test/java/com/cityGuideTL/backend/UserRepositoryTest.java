package com.cityGuideTL.backend;

import com.cityGuideTL.backend.Models.User;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindAll() {
        User newUser = new User();
        newUser.setFirstname("alex");
        newUser.setEmail("alexbadrishvili");
        newUser.setLastname("badrishvili");
        userRepository.save(newUser);

        User secondUser = new User();
        secondUser.setFirstname("alex");
        secondUser.setEmail("alexbadrishvili");
        secondUser.setLastname("badrishvili");
        userRepository.save(secondUser);

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0)).isEqualTo(newUser);
        assertThat(users.get(1)).isEqualTo(secondUser);
    }
    @Test
    public void whenFindAllById() {
        User user = new User();
        user.setFirstname("Dimitrios");
        userRepository.save(user);

        Integer id = user.getId();

        User testUser = userRepository.findById(id).get();

        assertThat(testUser.getFirstname()).isEqualTo(user.getFirstname());
    }

}
