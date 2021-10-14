package com.meodlia.oauth.api.config;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbli@trip.com
 * @date 2021/8/12
 */
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class MainController {
    @GetMapping(value = "/public")
    public MessageHolder publicEndpoint() {
        return new MessageHolder("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public MessageHolder privateEndpoint() {
        return new MessageHolder("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    public MessageHolder privateScopedEndpoint() {
        return new MessageHolder("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }
}
