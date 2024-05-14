package com.controller;

import com.service.KafkaProducerService;
import io.quarkus.security.Authenticated;
//import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/upload")
@Authenticated
public class UploadController {

    private static final Logger LOG = Logger.getLogger(UploadController.class);

    @Inject
    JsonWebToken jwt;

    @ConfigProperty(name = "quarkus.kafka.topic")
    String kafkaTopic;

    @Inject
    KafkaProducerService kafkaProducerService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed("uploader")
    public Response uploadFile(InputStream fileInputStream) {
        try {
            // Process the uploaded file
            // Example: Parse CSV/XLS file
            // Your implementation here

            // Convert file data to a string or bytes
            // Example: String data = convertInputStreamToString(fileInputStream);

            // Publish data to Kafka topic
            kafkaProducerService.publishToKafka(kafkaTopic);

            return Response.ok("File uploaded successfully").build();
        } catch (Exception e) {
            LOG.error("Failed to upload file", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to upload file").build();
        }
    }
}
