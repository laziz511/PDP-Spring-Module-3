package uz.pdp.online.springbootapplication.hateoas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.hateoas.entity.AuthUserEntity;
import uz.pdp.online.springbootapplication.hateoas.repository.AuthUserEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserEntityRepository userRepository;

    public List<AuthUserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public AuthUserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AuthUserEntity createUser(AuthUserEntity user) {
        return userRepository.save(user);
    }

    public Optional<AuthUserEntity> updateUser(Long id, AuthUserEntity updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        });
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    public Page<AuthUserEntity> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
}
