package com.app.expensetracker.repository.jpa;

import com.app.expensetracker.dao.User;
import com.app.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA implementation of UserRepository.
 * Only active when 'staging' profile is enabled.
 * <p>
 * This class implements UserRepository interface and delegates to
 * Spring Data JPA repository (UserJpaRepository) internally.
 */
@Repository
@Profile("local")
@RequiredArgsConstructor
public class UserJpaDao implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void saveUser(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userJpaRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userJpaRepository.save(user); // JPA save works for both insert and update
    }
}
