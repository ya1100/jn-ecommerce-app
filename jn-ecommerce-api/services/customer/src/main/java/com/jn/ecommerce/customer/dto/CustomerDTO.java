package com.jn.ecommerce.customer.dto;

import com.jn.ecommerce.customer.model.Address;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Address> address;
}
