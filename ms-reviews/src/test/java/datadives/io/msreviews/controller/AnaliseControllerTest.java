package datadives.io.msreviews.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import datadives.io.msreviews.service.AnaliseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AnaliseController.class)
class AnaliseControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnaliseService service;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    void findAll() {
    }

    public String objectToJson(Object obj) throws JsonProcessingException{
        return mapper.writeValueAsString(obj);
    }
}