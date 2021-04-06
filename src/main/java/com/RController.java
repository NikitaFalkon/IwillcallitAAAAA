package com;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class RController {
    @GetMapping(value = "/{file}")
    public RedirectView Fil(@PathVariable("file") String filename) throws IOException {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(filename);
        System.out.println(mimeType);
        switch (mimeType) {
            case ("application/octet-stream") :
                return new RedirectView("/pdf/{file}");
            case ("image/jpeg"):
                return new RedirectView("/jpeg/{file}");
            case ("text/plain"):
                return new RedirectView("/txt/{file}");
            default:
            return null;
        }
    }
    @GetMapping(value = "/pdf/{file}", produces = { MediaType.APPLICATION_PDF_VALUE })
    public byte[] getPDF(@PathVariable("file") String file) throws IOException {
        return Files.readAllBytes(new File("c:\\FilesForJava\\"  + file).toPath());
    }
    @GetMapping(value = "/jpeg/{file}", produces = { MediaType.IMAGE_JPEG_VALUE })
    public byte[] getJPEG(@PathVariable("file") String file) throws IOException {
        return Files.readAllBytes(new File("c:\\FilesForJava\\"  + file).toPath());
    }
    @GetMapping(value = "/txt/{file}")
    public byte[] getTXT(@PathVariable("file") String file) throws IOException {
        return Files.readAllBytes(new File("c:\\FilesForJava\\"  + file).toPath());
    }


}
