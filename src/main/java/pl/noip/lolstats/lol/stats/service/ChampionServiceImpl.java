package pl.noip.lolstats.lol.stats.service;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChampionServiceImpl implements ChampionService {

    @Value("classpath:static/json/champion.json")
    private Resource champJson;

    private String champJsonData() {
        try (InputStream is = champJson.getInputStream()) {
            return new String(Files.readAllBytes(champJson.getFile().toPath()));
        } catch (IOException ex) {
            System.out.println("Could not find file ");
        }
        return champJsonData();
    }

    private ChampsInfo convert(String json) {

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ChampsInfo champsInfo = new ChampsInfo();

        try {
            champsInfo = mapper.readValue(json, ChampsInfo.class);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return champsInfo;
    }

    private Map<String, String> createChampionIdMap() {

        String json = champJsonData();
        ChampsInfo champsInfo = convert(json);

        HashMap<String, String> champsId = new HashMap<String, String>();

        champsInfo.getData().forEach((key, value) ->
        {
            champsId.put(value.getKey(), key);
        });
        return champsId;
    }

    public String getChampionNameData(String id) {

        List<String> list = new ArrayList<>();

        createChampionIdMap().forEach((key, value) ->
        {
            if (key.equals(id)) {
                list.add(value);
            }
        });

        String name = list.get(0);
        return name;
    }

    @PostConstruct
    void init() {
        createChampionIdMap();
    }
}
