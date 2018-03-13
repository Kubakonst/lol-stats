package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.RegistrationRequest;
import pl.noip.lolstats.lol.stats.dto.Repository;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private Repository repository;

    public RegistrationController(Repository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void register(@RequestBody RegistrationRequest registrationReguest) {
        repository.save(registrationReguest);
    }
}
