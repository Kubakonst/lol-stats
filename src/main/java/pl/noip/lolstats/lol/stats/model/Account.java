package pl.noip.lolstats.lol.stats.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@Setter
public class Account {
    @Id
    private String email;
    private String passwordHash;
    private String sumName;
    private String region;
    private String accountId;
    private String id;
}