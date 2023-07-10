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
    final AuthRepository authRepository;
    final LocationRepository locationRepository;
    final AuthoritiesRepository authoritiesRepository;
    final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        log.info("===== Mock Data Started ======");
//        addAuthorities();
//        mockAdmin();
//        mockUser();
//        mockRequester();
//        mockPurchaster();
//        mockAppover();
        log.info("=====Application Started======");
    }

    private void addAuthorities() {
        authoritiesRepository.saveAll(List.of(
                Authorities.builder().name(Roles.ROLE_ADMIN).build(),
                Authorities.builder().name(Roles.ROLE_USER).build(),
                Authorities.builder().name(Roles.ROLE_REQUESTER).build(),
                Authorities.builder().name(Roles.ROLE_PURCHASER).build(),
                Authorities.builder().name(Roles.ROLE_APPROVER).build()
        ));
    }

    private void mockAdmin() {
        log.info("=====Mock Admin======");
        Authorities admin = authoritiesRepository.findByName(Roles.ROLE_ADMIN);
        Location location1 = Location.builder()
                .address("somewhere")
                .state("somestate")
                .city("somecity")
                .zip("12345")
                .build();
        Location location2 = Location.builder()
                .address("somewhere2")
                .state("somestate2")
                .city("somecity2")
                .zip("12345")
                .build();
        locationRepository.saveAll(List.of(location1, location2));
        Users admin1 = Users.builder()
                .username("admin1")
                .password(encoder.encode("admin1"))
                .firstName("adminf1")
                .lastName("adminl1")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.oneAdmin")
                .email("admin1@admin.com")
                .enables(true)
                .tel("010-1234-5678")
                .authorities(admin)
                .birthday(Date.valueOf("1990-01-01"))
                .location(location1)
                .build();
        Users admin2 = Users.builder()
                .username("admin2")
                .password(encoder.encode("admin2"))
                .firstName("adminf2")
                .lastName("adminl2")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.twoAdmin")
                .email("admin2@admin.com")
                .enables(true)
                .tel("010-3333-5678")
                .authorities(admin)
                .birthday(Date.valueOf("1991-01-01"))
                .location(location2)
                .build();
        authRepository.saveAll(List.of(admin1, admin2));
    }

    private void mockUser() {
        log.info("=====Mock User======");
        Authorities user = authoritiesRepository.findByName(Roles.ROLE_USER);
        Location location1 = Location.builder()
                .address("Usomewhere")
                .state("Usomestate")
                .city("Usomecity")
                .zip("12345")
                .build();
        Location location2 = Location.builder()
                .address("Usomewhere2")
                .state("Usomestate2")
                .city("Usomecity2")
                .zip("12345")
                .build();
        locationRepository.saveAll(List.of(location1, location2));
        Users user1 = Users.builder()
                .username("user1")
                .password(encoder.encode("user1"))
                .firstName("userf1")
                .lastName("userl1")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.oneUser")
                .email("user1@user.com")
                .tel("010-4444-5678")
                .authorities(user)
                .birthday(Date.valueOf("1992-01-01"))
                .location(location1)
                .build();
        Users user2 = Users.builder()
                .username("user2")
                .password(encoder.encode("user2"))
                .firstName("userf2")
                .lastName("userl2")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.twoUser")
                .email("user2@user.com")
                .tel("010-5555-5678")
                .authorities(user)
                .birthday(Date.valueOf("1993-01-01"))
                .location(location2)
                .build();
        authRepository.saveAll(List.of(user1, user2));
    }

    private void mockRequester() {
        log.info("=====Mock Requester======");
        Authorities requester = authoritiesRepository.findByName(Roles.ROLE_REQUESTER);
        Location location1 = Location.builder()
                .address("Rsomewhere")
                .state("Rsomestate")
                .city("Rsomecity")
                .zip("12345")
                .build();
        Location location2 = Location.builder()
                .address("Rsomewhere2")
                .state("Rsomestate2")
                .city("Rsomecity2")
                .zip("12345")
                .build();
        locationRepository.saveAll(List.of(location1, location2));
        Users requester1 = Users.builder()
                .username("requester1")
                .password(encoder.encode("requester1"))
                .firstName("requesterf1")
                .lastName("requesterl1")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.oneRequester")
                .email("requester1@requester.com")
                .tel("010-6666-5678")
                .authorities(requester)
                .birthday(Date.valueOf("1994-01-01"))
                .location(location1)
                .enables(true)
                .build();
        Users requester2 = Users.builder()
                .username("requester2")
                .password(encoder.encode("requester2"))
                .firstName("requesterf2")
                .lastName("requesterl2")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.twoRequester")
                .email("requester2@requester.com")
                .tel("010-7777-5678")
                .authorities(requester)
                .birthday(Date.valueOf("1995-01-01"))
                .location(location2)
                .build();
        authRepository.saveAll(List.of(requester1, requester2));
    }

    private void mockPurchaster() {
        log.info("=====Mock Purchaser======");
        Authorities purchaser = authoritiesRepository.findByName(Roles.ROLE_PURCHASER);
        Location location1 = Location.builder()
                .address("Psomewhere")
                .state("Psomestate")
                .city("Psomecity")
                .zip("12345")
                .build();
        Location location2 = Location.builder()
                .address("Psomewhere2")
                .state("Psomestate2")
                .city("Psomecity2")
                .zip("12345")
                .build();
        locationRepository.saveAll(List.of(location1, location2));
        Users purchaser1 = Users.builder()
                .username("purchaser1")
                .password(encoder.encode("purchaser1"))
                .firstName("purchaserf1")
                .lastName("purchaserl1")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.onePurchaser")
                .email("purchater1@purchater.com")
                .tel("010-8888-5678")
                .authorities(purchaser)
                .birthday(Date.valueOf("1996-01-01"))
                .location(location1)
                .build();
        Users purchaser2 = Users.builder()
                .username("purchaser2")
                .password(encoder.encode("purchaser2"))
                .firstName("purchaserf2")
                .lastName("purchaserl2")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.twoPurchaser")
                .email("purchater2@purchater.com")
                .tel("010-9999-5678")
                .authorities(purchaser)
                .birthday(Date.valueOf("1997-01-01"))
                .location(location2)
                .build();
        authRepository.saveAll(List.of(purchaser1, purchaser2));
    }

    private void mockAppover() {
        log.info("=====Mock Approver======");
        Authorities approver = authoritiesRepository.findByName(Roles.ROLE_APPROVER);
        Location location1 = Location.builder()
                .address("APsomewhere")
                .state("APsomestate")
                .city("APsomecity")
                .zip("12345")
                .build();
        Location location2 = Location.builder()
                .address("APsomewhere2")
                .state("APsomestate2")
                .city("APsomecity2")
                .zip("12345")
                .build();
        locationRepository.saveAll(List.of(location1, location2));
        Users approver1 = Users.builder()
                .username("approver1")
                .password(encoder.encode("approver1"))
                .firstName("approverf1")
                .lastName("approverl1")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.oneApprover")
                .email("approver1@approver.com")
                .tel("010-1010-5678")
                .authorities(approver)
                .birthday(Date.valueOf("1998-01-01"))
                .location(location1)
                .build();
        Users approver2 = Users.builder()
                .username("approver2")
                .password(encoder.encode("approver2"))
                .firstName("approverf2")
                .lastName("approverl2")
                .profilePicture("https://picsum.photos/200/300")
                .platformName("Mr.twoApprover")
                .email("approver2@approver.com")
                .tel("010-1111-5678")
                .authorities(approver)
                .birthday(Date.valueOf("1999-01-01"))
                .location(location2)
                .build();
        authRepository.saveAll(List.of(approver1, approver2));
    }
}