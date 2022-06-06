package com.dewan.controller;

import com.dewan.dto.UserRegistrationRequestDtO;
import com.dewan.entity.UserEntity;
import com.dewan.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UsersAppControllerTest {

    private final Logger log = LoggerFactory.getLogger(UsersAppControllerTest.class);

    @InjectMocks
    UsersAppController usersAppController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    String exampleUsersJson = "{\n" +
            "    \"username\":\"dewanmomen\",\n" +
            "    \"password\":\"dewan@12345\",\n" +
            "    \"email\":\"momendewan@gmail2.com\"\n" +
            "}";

    @Test
    public void save_emptyUsername_emptyPass_emptyEmail_400() throws Exception {
        String exampleUsersJson1 = "{\n" +
                "    \"username\":\"\",\n" +
                "    \"password\":\"\",\n" +
                "    \"email\":\"\"\n" +
                "}";
        mockMvc.perform(post("/api/v1/user_registration")
                        .content(exampleUsersJson1)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(4)))
                .andExpect(jsonPath("$.errors", hasItem("*Please provide a unique user name")))
                .andExpect(jsonPath("$.errors", hasItem("*Please provide your password")))
                .andExpect(jsonPath("$.errors", hasItem("*Please provide your email")));

        verify(userService, times(0)).createNewUser(any(UserEntity.class));

    }

    @Test
    public void save_lengthPassword_400() throws Exception {
        String exampleUsersJson2 = "{\n" +
                "    \"username\":\"dewanmomen\",\n" +
                "    \"password\":\"dewan@12\",\n" +
                "    \"email\":\"momendewan@gmail2.com\"\n" +
                "}";
        mockMvc.perform(post("/api/v1/user_registration")
                        .content(exampleUsersJson2)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItem("*Your password must have at least 10 characters long")));

        verify(userService, times(0)).createNewUser(any(UserEntity.class));

    }


    @Test
    void createUserAcc() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUserid(Long.parseLong("31"));
        userEntity.setUsername("dewanmomen");
        userEntity.setPassword("dewan@12345");
        userEntity.setEmail("momendewan@gmail2.com");

        // userService.createNewUser to respond back with userEntity
        Mockito.when(
                userService.createNewUser( Mockito.any(UserEntity.class))).thenReturn(userEntity);

        // Send user as body to /api/v1/user_registration end point
        RequestBuilder requestBuilder = post("/api/v1/user_registration")
                .accept(MediaType.APPLICATION_JSON).content(exampleUsersJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String responseContent = response.getContentAsString();
        //assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        log.info("responseContent: {}", responseContent);

        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        actualJson.toString();

        assertNotNull(responseContent);

        JSONObject expectedJson = new JSONObject(exampleUsersJson);
        expectedJson.toString();
        assertThat(expectedJson.getString("username")).isEqualTo(actualJson.getString("username"));
        assertThat(expectedJson.getString("password")).isEqualTo(actualJson.getString("password"));
        assertThat(expectedJson.getString("email")).isEqualTo(actualJson.getString("email"));
//        assertEquals("http://localhost/api/v1/user_registration/31",
//                response.getHeader(HttpHeaders.LOCATION));

    }
}