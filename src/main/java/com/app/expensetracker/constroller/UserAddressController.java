package com.app.expensetracker.constroller;

import com.app.expensetracker.dao.UserAddress;
import com.app.expensetracker.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @PostMapping
    public ResponseEntity<?> createAddress(UserAddress address) {
        userAddressService.saveUserAddressDetails(address);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserAddress>> findAllAddress(){
        return ResponseEntity.ok(userAddressService.findAllUserAddressDetails());
    }

}
