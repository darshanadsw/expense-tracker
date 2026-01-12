package com.app.expensetracker.repository.jpa;

import com.app.expensetracker.dao.UserAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {

    List<UserAddress> findAll();
}
