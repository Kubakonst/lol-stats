package pl.noip.lolstats.lol.stats.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class RegistrationRequest {

    @Email (message = "invalid email")
    private String email;

    @Length (min = 4, message = "too short password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
