package pw.react.backend.reactbackend;

import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ReactBackendApplicationTests {
    private UserController userController;

    private UserService service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        service = spy(new UserService(repository));
    }

    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            service.updateUser(user);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User not found with id : '1'")));
        }
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        service.updateUser(user);

        verify(repository, times(1)).save(eq(user));
    }


    @Test
    public void givenExistingUser_whenDeleteUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        service.deleteUser(user.getId());

        verify(repository, times(0)).save(eq(user));
    }

    @Test
    public void givenNotExistingUser_whenDeleteUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThat(service.deleteUser(user.getId()), is(equalTo(false)));
        verify(repository, times(0)).save(any(User.class));
    }

    @Test(expected = NullPointerException.class)
    public void givenInvalidUserId_whenGetUserByIdIsInvoked_thenThrowException() {
        // given
        given(repository.findById(-1l)).willReturn(null);

        when(userController.retrieveById(-1l)).thenThrow(NullPointerException.class);
    }









}
