package datadives.io.msreviews.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static datadives.io.msreviews.common.UsuarioConstants.*;
import static org.mockito.Mockito.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void create() {
    }

    @Test
    void findByEmail_user_exists() throws Exception {
        when(service.findByEmail("valid_email")).thenReturn(USER1);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/valid_email")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senha").value("strongPassword"));
    }

    @Test
    void findByEmail_user_does_not_exist() throws Exception {
        doThrow(ResponseStatusException.class).when(service).findByEmail("invalid_email");

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/invalid_email")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void findAll() throws Exception {
        List<String> emailList = USER_LIST.stream().map(Usuario::getEmail).toList();
        when(service.findAll()).thenReturn(emailList);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void create_valid_user() throws Exception {
        doNothing().when(service).create(USER1);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .content(objectToJson(USER1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"));
    }

    @Test
    void create_invalid_user() throws Exception {
        doThrow(ResponseStatusException.class).when(service).create(USER1);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .content(objectToJson(USER1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    void update_valid_user() throws Exception {
        doNothing().when(service).update(USER1);

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/user")
                        .content(objectToJson(USER1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"));
    }

    @Test
    void update_inivalid_user() throws Exception {
        doThrow(ResponseStatusException.class).when(service).update(USER1);

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/user")
                        .content(objectToJson(USER1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"));
    }

    @Test
    void deleteByEmail_user_exists() throws Exception {
        doNothing().when(service).deleteByEmail("valid_email");

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/email/valid_email")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"));
    }

    @Test
    void deleteByEmail_user_does_not_exists() throws Exception {
        doThrow(ResponseStatusException.class).when(service).deleteByEmail("invalid_email");

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/email/invalid_email")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"));
    }

    public String objectToJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}