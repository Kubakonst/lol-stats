package pl.noip.lolstats.lol.stats.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ChampionServiceImpl implements ChampionService {

    @Value("classpath:static/json/champion.json")
    private Resource champJson;

    private Map<String, String> champsId = new HashMap<String, String>();

    private String champJsonData() {
        try {
            return new String(Files.readAllBytes(champJson.getFile().toPath()));
        } catch (IOException ex) {
            log.error("Could not find file ");
        }
        return null;
    }

    private ChampionsInfo convert(String json) {

        ObjectMapper mapper = new ObjectMapper();

        ChampionsInfo champsInfo = null;

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            champsInfo = mapper.readValue(json, ChampionsInfo.class);
        } catch (IOException e) {
            log.error("Cant convert json to object");
        }
        return champsInfo;
    }

    private void createChampionIdMap() {

        String json = champJsonData();

        convert(json).getData().forEach((key, value) ->

                champsId.put(value.getKey(), key)
        );
    }

    public String getChampionName(String id) {

        return champsId.get(id);
    }

    @PostConstruct
    void init() {
        createChampionIdMap();
    }
}
