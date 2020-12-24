package com.example.part2imagecode.controller;

import com.example.part2imagecode.filter.VerificationCodeFilter;
import com.google.code.kaptcha.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class KaptchaController {

    @Resource
    private Producer producer;

    @GetMapping("kaptcha.jpg")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String kaptchaText = producer.createText();
        request.getSession().setAttribute(VerificationCodeFilter.VERIFICATION_CODE_KEY, kaptchaText);
        BufferedImage image = producer.createImage(kaptchaText);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", outputStream);
        try {
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}
