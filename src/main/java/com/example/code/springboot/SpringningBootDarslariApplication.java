package com.example.code.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SpringningBootDarslariApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringningBootDarslariApplication.class, args);
    }

    /*	@Scheduled(fixedRate = 1000L)
        public void startRate(){
            System.out.println("New rate"+ new Date());
        }

        @Scheduled(fixedDelay = 1000L)
        public void startDalay(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("New Delay : "+ new Date());
        }*/
	@Scheduled(cron = "0 15 21 * * *")
    public void cronStart() {
        System.out.println("New cronStart : " + new Date());
    }

}
