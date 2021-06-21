package com.mycompany.mock.sso.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDetails {

    private String customerId;
    private String email;

    //Ommiting other details

}
