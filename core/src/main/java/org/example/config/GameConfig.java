package org.example.config;

import org.example.GuessCount;
import org.example.MaxNumber;
import org.example.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "org.example")
public class GameConfig {

//    fields
    @Value("${game.minNumber:0}")
    private int minNumber;
    @Value("${game.maxNumber:1024}")
    private int maxNumber;
    @Value("${game.guessCount:10}")
    private int guessCount;
//    bean methods

    @Bean
    @MinNumber
    public int minNumber(){ return minNumber;}
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int GuessCount(){
        return guessCount;
    }
}
