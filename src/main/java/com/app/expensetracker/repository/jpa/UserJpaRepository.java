package com.app.expensetracker.repository.jpa;

import com.app.expensetracker.dao.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA repository interface for User entity.
 * Used internally by UserJpaRepositoryImpl.
 */
interface UserJpaRepository extends CrudRepository<User, Integer> {
}
