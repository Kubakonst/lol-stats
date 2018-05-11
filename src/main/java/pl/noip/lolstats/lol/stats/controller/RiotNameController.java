package pl.noip.lolstats.lol.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import javax.lang.model.element.Name;

public class RiotNameController {

    RestTemplate restTemplate;

    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public String checkName(@ModelAttribute("SpiderWeb") Name name,
                            Model model){

        String url = "someurl";

//        String result = restTemplate.exchange(url.toString(), String.class);
//                       model.addAttribute("result", result);

        return "result";

    }

}