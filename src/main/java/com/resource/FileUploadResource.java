package com.resource;

import com.model.FileUploadForm;
import com.service.FileUploadService;
//import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
//import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.annotation.security.*;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/upload")
//@SecurityScheme(securitySchemeName = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class FileUploadResource {

    @Inject
    FileUploadService fileUploadService;

    @POST
    @RolesAllowed("uploader")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@MultipartForm FileUploadForm form) {
        fileUploadService.processUploadedFile(form.file, form.fileName);
        return Response.ok().build();
    }
}