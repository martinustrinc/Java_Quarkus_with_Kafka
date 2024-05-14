package com.service;

// DataProcessingService.java
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataProcessingService {

    @Incoming("upload-data")
    public void processData(String fileData) {
        // Process the file data
        // e.g., store in a database, perform transformations, etc.
        System.out.println("Received data: " + fileData);
    }
}