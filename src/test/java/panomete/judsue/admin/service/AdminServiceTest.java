package panomete.judsue.admin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.security.entity.Users;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    AdminService adminService;

    @DisplayName("assign role")
    @Test void assignRole() {
        when(adminService.assignRole("uuid", null)).thenReturn(null);
        Users users = adminService.assignRole("uuid", null);
        Assertions.assertNull(users);
    }

    @DisplayName("get user by uuid")
    @Test void getUser() {
        when(adminService.getUser("uuid")).thenReturn(null);
        Users users = adminService.getUser("uuid");
        Assertions.assertNull(users);
    }

    @DisplayName("get all users")
    @Test void getAllUsers() {
        PageRequest pageRequest = PageRequest.of(1, 10);
        when(adminService.getAllUsers(pageRequest)).thenReturn(null);
        Assertions.assertNull(adminService.getAllUsers(pageRequest));
    }

    @Test
    void getAllLockedUsers() {
        PageRequest pageRequest = PageRequest.of(1, 10);
        when(adminService.getAllLockedUsers(pageRequest)).thenReturn(null);
        Assertions.assertNull(adminService.getAllLockedUsers(pageRequest));
    }
}