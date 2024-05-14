package com.service;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@ApplicationScoped
public class FileUploadService {

    @Inject
    @Channel("upload-data")
    Emitter<String> emitter;

    public void processUploadedFile(InputStream fileStream, String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream))) {
            String fileData = reader.lines().collect(Collectors.joining("\n"));
            emitter.send(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}