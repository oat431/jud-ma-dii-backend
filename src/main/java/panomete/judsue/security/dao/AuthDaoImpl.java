package panomete.judsue.security.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import panomete.judsue.security.entity.Authorities;
import panomete.judsue.security.entity.Location;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.repository.AuthRepository;
import panomete.judsue.security.repository.AuthoritiesRepository;
import panomete.judsue.security.repository.LocationRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {
    final AuthRepository authRepository;
    final AuthoritiesRepository authoritiesRepository;
    final LocationRepository locationRepository;
    @Override
    public Users getUserByUsername(String username) {
        return authRepository.findByUsername(username);
    }

    @Override
    public Users getUserByEmail(String email) {
        return authRepository.findByEmail(email);
    }

    @Override
    public Users getUserById(UUID id) {
        return authRepository.findById(id);
    }

    @Override
    public Users saveUser(Users user) {
        return authRepository.save(user);
    }

    @Override
    public Users deleteUser(Users user) {
        authRepository.delete(user);
        return user;
    }

    @Override
    public Authorities saveAuthority(Authorities authority) {
        return authoritiesRepository.save(authority);
    }

    @Override
    public Authorities getAuthorityByName(Roles name) {
        return authoritiesRepository.findByName(name);
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }
}
