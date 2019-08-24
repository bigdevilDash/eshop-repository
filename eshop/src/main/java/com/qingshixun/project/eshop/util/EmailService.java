/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
 *********************************************/
package com.qingshixun.project.eshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送邮件
     *
     * @param to          收件人邮箱
     * @param subject     标题
     * @param htmlContent html邮件内容
     *
     * @throws MessagingException
     */
    public void sendMail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mailMessage, true, "utf-8");
        mail.setFrom(username);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(htmlContent, true);
        mailSender.send(mailMessage);
    }
}
