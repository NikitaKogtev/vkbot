package ru.kogtev.vkbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.service.VkBotService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/", produces = APPLICATION_JSON_VALUE)
public class VkBotController {

    private final VkBotService vkBotService;

    public VkBotController(VkBotService vkBotService) {
        this.vkBotService = vkBotService;
    }

    @PostMapping
    public String doResponse(@RequestBody VkEvent vkEvent) {
        return vkBotService.doResponse(vkEvent);
    }
}
