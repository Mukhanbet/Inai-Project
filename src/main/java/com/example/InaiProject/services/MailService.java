package com.example.InaiProject.services;

import com.example.InaiProject.model.MailType;
import com.example.InaiProject.model.User;

import java.util.Properties;

public interface MailService {
    void sendEmail(User user, MailType mailType, Properties properties);
}
