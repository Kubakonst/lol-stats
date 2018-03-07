package pl.noip.lolstats.lol.stats.dto;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<RegistrationRequest, String> {

     RegistrationRequest findByEmail(String email);


}
