// package com.cityGuideTL.backend;

// import com.cityGuideTL.backend.Models.UserModel;
// import com.cityGuideTL.backend.Repository.UserRepository;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.junit4.SpringRunner;

// import java.util.List;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
// @DataJpaTest
// @AutoConfigureTestDatabase(replace = NONE)
// public class UserModelRepositoryTest {
//     @Autowired
//     private TestEntityManager entityManager;

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     public void whenFindAll() {
//         UserModel newUserModel = new UserModel();
//         newUserModel.setFirstname("alex");
//         newUserModel.setEmail("alexbadrishvili");
//         newUserModel.setLastname("badrishvili");
//         userRepository.save(newUserModel);

//         UserModel secondUserModel = new UserModel();
//         secondUserModel.setFirstname("alex");
//         secondUserModel.setEmail("alexbadrishvili");
//         secondUserModel.setLastname("badrishvili");
//         userRepository.save(secondUserModel);

//         List<UserModel> userModels = userRepository.findAll();

//         assertThat(userModels.size()).isEqualTo(2);
//         assertThat(userModels.get(0)).isEqualTo(newUserModel);
//         assertThat(userModels.get(1)).isEqualTo(secondUserModel);
//     }
//     @Test
//     public void whenFindAllById() {
//         UserModel userModel = new UserModel();
//         userModel.setFirstname("Dimitrios");
//         userRepository.save(userModel);

//         Integer id = userModel.getId();

//         UserModel testUserModel = userRepository.findById(id).get();

//         assertThat(testUserModel.getFirstname()).isEqualTo(userModel.getFirstname());
//     }

// }
