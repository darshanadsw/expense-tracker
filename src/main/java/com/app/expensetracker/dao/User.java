package com.app.expensetracker.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXPT_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_generator_seq", initialValue = 100, allocationSize = 50)
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email_address")
    String email;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    List<UserAddress> userAddressList = new ArrayList<>();

    public void setUserAddressList(List<UserAddress> userAddressList) {
        this.userAddressList = userAddressList;
        for (UserAddress userAddress : userAddressList) {
            userAddress.setUser(this);
        }
    }

    public void removeAddress(UserAddress address) {
        address.setUser(null);
        userAddressList.remove(address);
    }
}
