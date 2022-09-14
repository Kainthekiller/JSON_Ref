package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.hamcrest.Matchers.is;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ConversionTest {

    @Autowired
    MockMvc mvc;

    @Test
public void testSimplfiedDetailed() throws Exception
    {
        String json = getJSON("/request.json");
        MockHttpServletRequestBuilder request = post("/activities/simplify")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/vnd.galvanize.detailed+json")
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("personal@example.com"))
                .andExpect(jsonPath("$[0].user").value("someuser"))
                .andExpect(jsonPath("$[0].userId").value(1467))
                .andExpect(jsonPath("$[0].date").value("2017-04-02 01:32"))
                .andExpect(jsonPath("$[0].statusText").value("Just went snowboarding today!"))
                .andExpect(jsonPath("$[1].email").value("otherprimary@example.com"))
                .andExpect(jsonPath("$[1].user").value("otheruser"))
                .andExpect(jsonPath("$[1].userId").value(98732))
                .andExpect(jsonPath("$[1].date").value("2017-04-02 01:32"))
                .andExpect(jsonPath("$[1].statusText").value("Great times!"));
//                .andExpect(content().json(
//  """
//  [
//  {
//    "userId": 1467,
//    "user": "someuser",
//    "email": "personal@example.com",
//    "date": "2017-04-02 01:32",
//    "statusText": "Just went snowboarding today!"
//  },
//  {
//    "userId": 98732,
//    "user": "otheruser",
//    "email": "otherprimary@example.com",
//    "date": "2017-04-02 01:32",
//    "statusText": "Great times!"
//  }
//]
//            """ ));

    }


    @Test
    public void testSimplfiedCompact() throws Exception {
        String json = getJSON("/request.json");
        MockHttpServletRequestBuilder request = post("/activities/simplify")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/vnd.galvanize.compact+json")
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].user").value("someuser"))
                .andExpect(jsonPath("$[0].date").value("2017-04-02 01:32"))
                .andExpect(jsonPath("$[0].statusText").value("Just went snowboarding today!"))
                .andExpect(jsonPath("$[1].user").value("otheruser"))
                .andExpect(jsonPath("$[1].date").value("2017-04-02 01:32"))
                .andExpect(jsonPath("$[1].statusText").value("Great times!"));

    }
//                .andExpect(content().json(
//                        """
//  [
//  {
//    "user": "someuser",
//    "date": "2017-04-02 01:32",
//    "statusText": "Just went snowboarding today!"
//  },
//  {
//    "user": "otheruser",
//    "date": "2017-04-02 01:32",
//    "statusText": "Great times!"
//  }
//]
//                                  """ ));
//
//    }

    private String getJSON(String path) throws Exception
    {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    private String getJson(String path) throws Exception
    {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }




}
