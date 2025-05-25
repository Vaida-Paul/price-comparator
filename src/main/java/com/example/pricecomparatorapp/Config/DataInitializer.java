package com.example.pricecomparatorapp.Config;

import com.example.pricecomparatorapp.Service.CsvImportService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;


import com.example.pricecomparatorapp.Service.CsvImportService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;


@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializeData(CsvImportService csvImportService) {
        return args -> {
            // Import all product files (any date)
            importFiles(csvImportService, "data/*_*.csv", true);

            // Import all discount files (any date)
            importFiles(csvImportService, "data/*_discounts_*.csv", false);
        };
    }

    private void importFiles(CsvImportService csvImportService, String pattern, boolean isProductFile) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath:" + pattern);

            for (Resource resource : resources) {
                try {
                    String filePath = Paths.get(resource.getURI()).toString();
                    String filename = resource.getFilename();

                    if (isProductFile && filename.contains("_discounts_")) {
                        continue;
                    }
                    if (!isProductFile && !filename.contains("_discounts_")) {
                        continue;
                    }

                    String storeName = extractStoreName(filename);

                    if (isProductFile) {
                        csvImportService.importProductsFromCsv(filePath);
                    } else {
                        csvImportService.importDiscountsFromCsv(filePath, storeName);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing file: " + resource.getFilename());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to locate CSV files: " + e.getMessage());
        }
    }

    private String extractStoreName(String filename) {

        return filename.substring(0, filename.indexOf('_'))
                .substring(0, 1).toUpperCase() +
                filename.substring(0, filename.indexOf('_')).substring(1);
    }
}