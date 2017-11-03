package com.javanei.retrocenter.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.javanei.retrocenter")
@EntityScan(basePackages = {"com.javanei.retrocenter.datafile.entity",
        "com.javanei.retrocenter.mame.entity",
        "com.javanei.retrocenter.clrmamepro.entity",
        "com.javanei.retrocenter.logiqx.entity",
        "com.javanei.retrocenter.hyperlist.entity",
        "com.javanei.retrocenter.platform.entity",
        "com.javanei.retrocenter.gamedb.launchbox.entity"
})
@EnableJpaRepositories(basePackages = {"com.javanei.retrocenter.mame.persistence",
        "com.javanei.retrocenter.clrmamepro.persistence",
        "com.javanei.retrocenter.datafile.persistence",
        "com.javanei.retrocenter.logiqx.persistence",
        "com.javanei.retrocenter.hyperlist.persistence",
        "com.javanei.retrocenter.platform.persistence",
        "com.javanei.retrocenter.gamedb.launchbox.persistence"
})
@EnableTransactionManagement
@EnableAsync
public class RetroCenter {
    public static final String REPOSITORY_BASE_DIR = System.getProperty("retrocenter.repository.basedir", System.getenv("retrocenter.repository.basedir"));

    public static void main(String[] args) throws Exception {
        if (REPOSITORY_BASE_DIR == null) {
            throw new Exception("Property not found: retrocenter.repository.basedir");
        }
        
        SpringApplication.run(RetroCenter.class, args);
    }
}