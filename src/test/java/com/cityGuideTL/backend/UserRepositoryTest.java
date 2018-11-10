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
import org.springframework.test.context.junit4.SpringRunner;

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
        entityManager.persist(newUser);
        entityManager.flush();

        User secondUser = new User();
        secondUser.setFirstname("alex");
        secondUser.setEmail("alexbadrishvili");
        secondUser.setLastname("badrishvili");
        entityManager.persist(secondUser);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0)).isEqualTo(newUser);
        assertThat(users.get(1)).isEqualTo(secondUser);
    }
    @Test
    public void whenFindAllById() {
        User user = new User();
        user.setFirstname("Dimitrios");
        entityManager.persist(user);
        entityManager.flush();
        Integer id = user.getId();

        User testUser = userRepository.findById(id).get();

        assertThat(testUser.getFirstname()).isEqualTo(user.getFirstname());
    }

}
