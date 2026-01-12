package com.app.expensetracker.repository;

import com.app.expensetracker.dao.User;

import java.util.List;

/**
 * Repository interface for User data access operations.
 * Implementations are profile-specific:
 * - Local profile: JDBC implementation
 * - Staging profile: JPA implementation (to be added)
 */
public interface UserRepository {
    
    void saveUser(User user);
    
    List<User> findAllUsers();
    
    User findById(Integer id);
    
    void deleteById(Integer id);
    
    void updateUser(User user);
}
