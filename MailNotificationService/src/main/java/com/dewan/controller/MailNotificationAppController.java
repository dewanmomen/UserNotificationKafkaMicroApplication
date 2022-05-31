package com.dewan.controller;


import com.dewan.event.EmailKafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/")
public class MailNotificationAppController {

    private final Logger log = LoggerFactory.getLogger(EmailKafkaListener.class);

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    //@RequestMapping(value = "/users_mail_list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("users_mail_list/{user_id}")
    @ResponseBody
    public ResponseEntity<Object> getUsersMailList(HttpServletRequest request, @RequestBody Map map)
    {
        log.info("User email list api called");
        log.info("get user_id :"+map.get("user_id").toString());
        //addDeviceInfoRepository.save(addDeviceInfoModel);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type","application/json");
        return ResponseEntity.ok().headers(responseHeaders).body("Data Added Successfully");
    }
}
