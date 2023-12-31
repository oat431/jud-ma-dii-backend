package panomete.judsue.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.security.entity.Authorities;
import panomete.judsue.security.entity.Location;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;

import java.util.UUID;

public interface AuthDao {
    Users getUserByUsername(String username);
    Users getUserByEmail(String email);
    Users getUserById(UUID id);
    Users saveUser(Users user);

    Users deleteUser(Users user);

    Authorities saveAuthority(Authorities authority);
    Authorities getAuthorityByName(Roles name);

    Location saveLocation(Location location);

    Page<Users> getAllLockedUser(PageRequest pageRequest);

    Page<Users> getAllUsers(PageRequest pageRequest);
}
