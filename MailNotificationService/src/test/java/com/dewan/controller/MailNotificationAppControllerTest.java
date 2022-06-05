package com.dewan.controller;

import com.dewan.entity.DefaultMailListEntity;
import com.dewan.service.DefaultMailListService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class MailNotificationAppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultMailListService defaultMailListService;

    @Test
    void getUsersMailList() throws Exception{
        DefaultMailListEntity defaultMailListEntity = new DefaultMailListEntity(Long.parseLong("6"),Long.parseLong("30"),"zayandewan@gmail2.com");

        //DefaultMailListEntity mock = Mockito.mock(DefaultMailListEntity.class);
        Mockito.when(
                defaultMailListService.getUsersMailList(Mockito.anyLong())).thenReturn(defaultMailListEntity);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/users_mail_list/30").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        //String expected = "{id:6,userid:30,email:zayandewan@gmail2.com}";
        String expected = "{\n" +
                "    \"id\": 6,\n" +
                "    \"userid\": 30,\n" +
                "    \"email\": \"zayandewan@gmail2.com\"\n" +
                "}";
        //JSONObject expectedJson = new JSONObject(expected);
        //JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());

        String actual = result.getResponse().getContentAsString();
        System.out.println("actual: "+actual);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void getAllMailList() throws Exception{
        List<DefaultMailListEntity> defaultMailListEntityList = new ArrayList<>();
        DefaultMailListEntity defaultMailListEntity1 = new DefaultMailListEntity(Long.parseLong("6"),Long.parseLong("30"),"zayandewan@gmail2.com");
        DefaultMailListEntity defaultMailListEntity2 = new DefaultMailListEntity(Long.parseLong("7"),Long.parseLong("31"),"zayandewan@gmail3.com");
        defaultMailListEntityList.add(defaultMailListEntity1);
        defaultMailListEntityList.add(defaultMailListEntity2);

        Mockito.when(
                defaultMailListService.getAllMailList()).thenReturn(defaultMailListEntityList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/getAllMailList").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        //String expected = "{id:6,userid:30,email:zayandewan@gmail2.com}";
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"userid\": 30,\n" +
                "        \"email\": \"zayandewan@gmail2.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"userid\": 31,\n" +
                "        \"email\": \"zayandewan@gmail3.com\"\n" +
                "    }\n" +
                "]";
        //JSONObject expectedJson = new JSONObject(expected);
        //JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());

        String actual = result.getResponse().getContentAsString();
        System.out.println("actual: "+actual);
        JSONAssert.assertEquals(expected, actual, false);
    }
}