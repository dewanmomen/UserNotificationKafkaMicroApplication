package com.dewan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserRegistrationDto {

    private Long user_id;
    private  String username;
    private  String password;
    private  String email;
}
