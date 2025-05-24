package com.example.pricecomparatorapp.Config;

import com.example.pricecomparatorapp.Service.CsvImportService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializeData(CsvImportService csvImportService) {
        return args -> {
            try {

                String productsPath = Paths.get(
                        ClassLoader.getSystemResource("data/lidl_2025-05-01.csv").toURI()).toString();
                csvImportService.importProductsFromCsv(productsPath);


                String discountsPath = Paths.get(
                        ClassLoader.getSystemResource("data/lidl_discounts_2025-05-01.csv").toURI()).toString();
                csvImportService.importDiscountsFromCsv(discountsPath, "Lidl");

            } catch (Exception e) {
                System.err.println("Error during data import: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}