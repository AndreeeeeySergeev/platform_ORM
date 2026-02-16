package service;

import exam.platform.entity.User;
import exam.platform.entity.User.Role;
import exam.platform.repository.UserRepository;
import exam.platform.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateAndFindUser() {
        // Given: подготавливаем данные пользователя
        User user = new User();
        user.setFirstName("Andey");
        user.setLastName("Sergeev");
        user.setEmail("andrey@example.com");
        user.setRole(Role.STUDENT);

        // When: создаём пользователя через сервис
        User createdUser = userService.createUser(user);

        // Then: проверяем корректность создания
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getFirstName()).isEqualTo("Andrey");
        assertThat(createdUser.getLastName()).isEqualTo("Sergeev");

        // Проверяем получение по ID
        Optional<User> foundUser = userRepository.findById(createdUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("andrey@example.com");
    }


    @Test
    void shouldDeleteUser() {
        // Given: создаём пользователя
        User user = createUser("Anton", "Fedorov", "delete@example.com", Role.STUDENT);

        // When: удаляем пользователя
        userService.deleteUser(user.getId());

        // Then: проверяем, что пользователь удалён
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertThat(deletedUser).isEmpty();
    }

    private User createUser(String firstName, String lastName, String email, Role role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);
        return userRepository.save(user);
    }
}