package pw.react.backend.reactbackend;

import org.junit.Before;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = {"it"})
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MainIT {
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;
    @Autowired
    public UserController controller;

    @Before
    public void setup(){this.mockMvc=MockMvcBuilders.standaloneSetup(this.controller).build();}

    @Test
    public void givenNothing_whenGetForUserIsRequested_thenReturnErrorStatus() throws Exception {
        this.mockMvc.perform(get("/users/login/sjdafgjdsaf")).andExpect(status().isNotFound());
    }

    @Test
    public void givenNotPresentLogin_expectNotFoundStatus() throws Exception{
        MockHttpServletRequestBuilder getUser=get("/users/login/jht");
        mockMvc.perform(getUser).andExpect(status().isNotFound());
    }

    @Test
    public void givenNotPresentId_expectNotFoundStatus() throws Exception{
        MockHttpServletRequestBuilder getUser=get("/users/id/-2");
        mockMvc.perform(getUser).andExpect(status().isNotFound());
    }




}
