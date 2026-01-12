package com.drivedreal.drivedreal.dto ;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreationRequest {
    private String name;
    private String address;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
