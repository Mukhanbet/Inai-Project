package com.example.InaiProject.dto.chatGPT;

import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
