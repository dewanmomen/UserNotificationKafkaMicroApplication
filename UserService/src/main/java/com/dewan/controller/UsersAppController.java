package com.dewan.controller;

import com.dewan.request.CreateUserAccRequest;
import com.dewan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/")
public class UsersAppController {

    private final Logger log = LoggerFactory.getLogger(UsersAppController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    //@PostMapping(value="/user_registration", produces =MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "user_registration",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createUserAcc(HttpServletRequest request, @RequestBody Map req)
    {
        log.info("Create New User API called");
        log.info("username :"+req.get("username").toString());
        log.info("password :"+req.get("password").toString());
        //kafkaTemplate.send("TOPIC", req);
        kafkaTemplate.send("TOPIC", req.get("username"), req.get("password"));
        //addDeviceInfoRepository.save(addDeviceInfoModel);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type","application/json");
        return ResponseEntity.ok().headers(responseHeaders).body("Data Added Successfully");
    }



    @PostMapping
    public String testPost(@RequestBody Map req) {
        kafkaTemplate.send("TOPIC", req.get("username"), req.get("password"));
        return "testResponse";
    }
}
