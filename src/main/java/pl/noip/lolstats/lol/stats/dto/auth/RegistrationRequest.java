package pl.noip.lolstats.lol.stats.dto.auth;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
public class RegistrationRequest {

    @Email(message = "invalid email")
    private String email;

    @Length(min = 4, message = "too short password")
    private String password;

}