package panomete.judsue.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import panomete.judsue.security.entity.Authorities;
import panomete.judsue.security.entity.Location;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.repository.AuthRepository;
import panomete.judsue.security.repository.AuthoritiesRepository;
import panomete.judsue.security.repository.LocationRepository;

import java.sql.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("=====Application Started======");
    }
}