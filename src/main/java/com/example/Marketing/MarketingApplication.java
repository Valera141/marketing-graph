package com.example.Marketing;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketingApplication.class, args);
	}


    // // --- AÑADE ESTE MÉTODO COMPLETO ---
    // @Bean
    // public CommandLineRunner printHash(PasswordEncoder passwordEncoder) {
    //     return args -> {
    //         String passwordToHash = "pass123";
    //         String correctHash = passwordEncoder.encode(passwordToHash);

    //         System.out.println("====================================================================");
    //         System.out.println("EL HASH CORRECTO PARA '" + passwordToHash + "' ES:");
    //         System.out.println(correctHash);
    //         System.out.println("====================================================================");
    //         System.out.println("USE ESTE HASH EN SU COMANDO SQL UPDATE Y REINICIE LA APP.");
    //     };
    // }
    // // ------------------------------------

}
