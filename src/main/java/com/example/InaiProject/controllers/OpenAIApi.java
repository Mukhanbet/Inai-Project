package com.example.InaiProject.controllers;

import com.example.InaiProject.dto.chatGPT.ChatGPTRequest;
import com.example.InaiProject.dto.chatGPT.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class OpenAIApi {

    @Value("${openai.model}")
    private String modelName;

    @Value("${openai.api.url}")
    private String apiURL;
    private final  RestTemplate template;

    @GetMapping("/chat")
    @ResponseBody
    public Map<String, String> chat(@RequestParam("prompt") String prompt) {
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(modelName, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, chatGPTRequest, ChatGPTResponse.class);
        Map<String, String> responseMap = new HashMap<>();
        if (chatGPTResponse != null && !chatGPTResponse.getChoices().isEmpty() && chatGPTResponse.getChoices().get(0).getMessage() != null) {
            responseMap.put("response", chatGPTResponse.getChoices().get(0).getMessage().getContent());
        } else {
            responseMap.put("response", "No response received");
        }
        return responseMap;
    }
}
