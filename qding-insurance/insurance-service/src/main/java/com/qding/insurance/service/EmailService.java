package com.qding.insurance.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qding.framework.common.email.EmailSender;
import com.qding.framework.common.email.MultipartEmail;

@Service("emailService")
public class EmailService {

    @Resource
    private EmailSender sender;

    @Value("#{configproperties_disconf[to_email_address]}")
    private String toEmailAddress;

    @Value("#{configproperties_disconf[bcc_email_address]}")
    private String bccEmailAddress;

    @Value("#{configproperties_disconf[need_email]}")
    private String needEmail;

    /**
     * 发送提醒邮件
     */
    public void sendEmail(String title, String content) {

        if (!"Y".equals(needEmail)) {
            return;
        }

        MultipartEmail email = MultipartEmail.builder().to(toEmailAddress).bcc(bccEmailAddress).subject(title)
                .content(content).build();
        sender.asyncSend(email);
    }

}
