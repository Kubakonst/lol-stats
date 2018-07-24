package pl.noip.lolstats.lol.stats.service;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChampData {

    @Value("classpath:static/json/champion.json")
    private Resource champJson;

    @PostConstruct
    public String champJsonData() {
        try (InputStream is = champJson.getInputStream()) {
            return new String(Files.readAllBytes(champJson.getFile().toPath()));
        } catch (IOException ex) {
            System.out.println("Could not find file ");
        }
        return champJsonData();
    }

    public void convert() {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = new HashMap<String, Object>();

            map = mapper.readValue(champJsonData(), new TypeReference<Map<String, String>>() {});

            System.out.println(map);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
