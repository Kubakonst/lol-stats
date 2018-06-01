package pl.noip.lolstats.lol.stats.model;


import org.springframework.data.annotation.Id;

public class Account {
    @Id

    private String email;
    private String passwordHash;
    private String sumName;
    private String region;

    public Account(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Account(String email, String passwordHash, String sumName, String region) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.sumName = sumName;
        this.region = region;
    }

    public Account() {
    }

    public String getSumName() {
        return sumName;
    }

    public void setSumName(String sumName) {
        this.sumName = sumName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

