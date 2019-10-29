package pw.react.backend.reactbackend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("dev")
//@SpringBootTest --for integration test
@WebMvcTest
public class ReactBackendApplicationTests {

    @InjectMocks
    UserService service;

    @MockBean
    UserRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/users/update/{id}}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult.getResponse());

        verify(repository).findAll();
    }


}
