package pl.noip.lolstats.lol.stats.model;


import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id



    @NonNull
    private String email;
    @NonNull
    private String passwordHash;
    private String sumName;
    private String region;



}

