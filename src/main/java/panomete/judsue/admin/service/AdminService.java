package panomete.judsue.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;

public interface AdminService {
    Users assignRole(String uuid, Roles role);
    Users getUser(String uuid);
    Page<Users> getAllUsers(PageRequest pageRequest);
    Page<Users> getAllLockedUsers(PageRequest pageRequest);
}
