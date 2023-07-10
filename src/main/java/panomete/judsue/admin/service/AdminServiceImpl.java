package panomete.judsue.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import panomete.judsue.security.dao.AuthDao;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    final AuthDao authDao;

    @Override
    public Users assignRole(String uuid, Roles role) {
        Users user = authDao.getUserById(UUID.fromString(uuid));
        user.setEnables(true);
        user.setAuthorities(authDao.getAuthorityByName(role));
        return authDao.saveUser(user);
    }

    @Override
    public Users getUser(String uuid) {
        return authDao.getUserById(UUID.fromString(uuid));
    }

    @Override
    public Page<Users> getAllUsers(PageRequest pageRequest) {
        return authDao.getAllUsers(pageRequest);
    }

    @Override
    public Page<Users> getAllLockedUsers(PageRequest pageRequest) {
        return authDao.getAllLockedUser(pageRequest);
    }
}
