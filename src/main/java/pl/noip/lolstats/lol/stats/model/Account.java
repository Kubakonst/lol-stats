package pl.noip.lolstats.lol.stats.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder
@Getter

public class Account {

    @Id
    private String email;
    private String passwordHash;
    @Setter
    private String sumName;
    @Setter
    private String region;
    @Setter
    private String accountId;
    @Setter
    private String id;
}