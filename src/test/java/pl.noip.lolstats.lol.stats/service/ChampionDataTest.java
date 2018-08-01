package pl.noip.lolstats.lol.stats.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ChampionDataTest {

    @Autowired
    private ChampionServiceImpl championServiceImpl;

    @Test
    public void getjsondata() {
        assertEquals(championServiceImpl.getChampionName("266"),"Aatrox");

    }

}