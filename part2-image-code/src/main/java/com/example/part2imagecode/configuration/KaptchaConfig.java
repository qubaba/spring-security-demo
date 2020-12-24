package com.example.part2imagecode.configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {

    @Bean
    public Producer producer(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.image.width","150");
        properties.setProperty("kaptcha.textproducer.char.string","0123456789");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
