package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import exam.platform.dto.request.UserRequest;
import exam.platform.entity.User;
import exam.platform.exception.DuplicateEntityException;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException("User with this email already exists.");
        }
    }

    public User updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        if (userRequest.getFirstName() != null) {
            existingUser.setFirstName(userRequest.getFirstName());
        }

        if (userRequest.getLastName() != null) {
            existingUser.setFirstName(userRequest.getLastName());
        }

        if (userRequest.getTelephone() != null) {
            existingUser.setTelephone(userRequest.getTelephone());
        }

        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }

        if (userRequest.getRole() != null) {
            existingUser.setRole(User.Role.valueOf(userRequest.getRole()));
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
    }
}
