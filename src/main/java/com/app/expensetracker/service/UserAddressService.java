package com.app.expensetracker.service;

import com.app.expensetracker.dao.UserAddress;
import com.app.expensetracker.repository.jpa.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public void saveUserAddressDetails(UserAddress userAddress) {
        userAddressRepository.save(userAddress);
    }

    public List<UserAddress> findAllUserAddressDetails(){
        return userAddressRepository.findAll();
    }

}
