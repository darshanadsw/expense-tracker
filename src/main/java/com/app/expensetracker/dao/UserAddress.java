package com.app.expensetracker.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EXPT_USER_ADDRESS")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_generator_seq", initialValue = 100, allocationSize = 50)
    Integer id;

    @Column(name = "address_line1")
    String addressLine1;
    @Column(name = "address_line2")
    String addressLine2;
    String city;
    String state;
    @Column(name = "zip_code")
    String zipCode;
    String country;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("userAddressList")
    User user;
}
