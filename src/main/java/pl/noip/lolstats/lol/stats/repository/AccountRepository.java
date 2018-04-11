package pl.noip.lolstats.lol.stats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.noip.lolstats.lol.stats.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

}
