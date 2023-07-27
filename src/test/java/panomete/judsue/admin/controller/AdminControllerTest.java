package panomete.judsue.admin.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import panomete.judsue.admin.mock.UsersMock;
import panomete.judsue.security.payload.response.AuthDto;
import panomete.judsue.security.payload.response.PageAuthDto;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    @Mock
    AdminController adminController;

    @DisplayName("assign role")
    @Test void assignRole() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UsersMock usersMock = new UsersMock();
        AuthDto authDto = usersMock.getAuthDtoMock();
        when(adminController.assignRole("uuid", null)).thenReturn(ResponseEntity.ok(authDto));
        ResponseEntity<AuthDto> users = adminController.assignRole("uuid", null);
        Assertions.assertEquals(Objects.requireNonNull(users.getBody()).id(), "uuid");
        Assertions.assertEquals(users.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @DisplayName("get user by uuid")
    @Test void getUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UsersMock usersMock = new UsersMock();
        AuthDto authDto = usersMock.getAuthDtoMock();
        when(adminController.getUser("uuid")).thenReturn(ResponseEntity.ok(authDto));
        ResponseEntity<AuthDto> users = adminController.getUser("uuid");
        Assertions.assertEquals(Objects.requireNonNull(users.getBody()).id(), "uuid");
    }

    @DisplayName("get all users")
    @Test void getAllUsers() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UsersMock usersMock = new UsersMock();
        AuthDto authDto = usersMock.getAuthDtoMock();
        PageAuthDto pageAuthDto = new PageAuthDto(
                1,
                1,
                1,
                1,
                List.of(authDto)
        );
        when(adminController.getAllUsers(1,1,false)).thenReturn(ResponseEntity.ok(pageAuthDto));
        ResponseEntity<PageAuthDto> users = adminController.getAllUsers(1,1,false);
        Assertions.assertEquals(Objects.requireNonNull(users.getBody()).content().get(0).id(), "uuid");
        Assertions.assertEquals(users.getStatusCode(), HttpStatusCode.valueOf(200));
    }
}