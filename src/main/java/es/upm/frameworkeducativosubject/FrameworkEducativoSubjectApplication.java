package es.upm.frameworkeducativosubject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FrameworkEducativoSubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkEducativoSubjectApplication.class, args);
    }

}
