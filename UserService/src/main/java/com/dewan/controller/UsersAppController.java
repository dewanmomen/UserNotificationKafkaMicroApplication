package com.dewan.controller;

import com.dewan.dto.UserRegistrationRequestDtO;
import com.dewan.entity.UserEntity;
import com.dewan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value="/api/v1/")
public class UsersAppController {

    private final Logger log = LoggerFactory.getLogger(UsersAppController.class);

    @Autowired
    private UserService userService;

    //@PostMapping(value="/user_registration", produces =MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "user_registration",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserEntity createUserAcc(@Valid @RequestBody UserEntity userEntityParam)
    {
        log.info("Create New User API called");
        UserEntity userEntity = this.userService.createNewUser(userEntityParam);
        //log.info("User registration status after: "+userEntity.getUserRegistrationStatus());

        return userEntity;
    }

}
