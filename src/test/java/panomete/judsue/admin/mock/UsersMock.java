package panomete.judsue.admin.mock;

import panomete.judsue.security.payload.response.AuthDto;
import panomete.judsue.security.payload.response.LocationDto;

public class UsersMock {
    public AuthDto getAuthDtoMock() {
        LocationDto locationDto = new LocationDto(
                "somewhere",
                "somewhere",
                "somewhere",
                "somewhere",
                "somewhere"
        );
        return new AuthDto(
                "uuid",
                "username",
                "email",
                "nickname",
                "role",
                "profile",
                21,
                "providerId",
                locationDto,
                "enabled"
        );
    }
}
