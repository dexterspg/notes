package com.ai.SpringAiDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.SpringAiDemo.service.ChatService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class GenAiController {

    private final ChatService chatService;

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

}
