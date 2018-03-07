package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.Request;
import pl.noip.lolstats.lol.stats.dto.Response;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String getTest() {  return "Hello from get";
    }

    @PostMapping
    public Response postTest(@RequestBody Request request) {
            return new Response(request.getA() + request.getB());
    }

    @PutMapping
    public String putTest(@RequestParam String name) {
        return "Hello " + name;
    }

}

