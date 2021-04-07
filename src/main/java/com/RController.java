package com;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class RController {
    @GetMapping(value = "/{file}")
    public RedirectView Fil(@PathVariable("file") String filename) throws IOException, MagicParseException, MagicException, MagicMatchNotFoundException {
        /*MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        File f = new File ("c:/temp/mime/test.doc");
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(f);
        System.out.println(mimeTypes);*/
        File file = new File("c:\\FilesForJava\\"+filename);
        String mimeType = Magic.getMagicMatch(file, false).getMimeType();
        switch (mimeType) {
            case ("application/pdf") :
                return new RedirectView("/pdf/{file}");
            case ("image/jpeg"):
                return new RedirectView("/jpeg/{file}");
            case ("text/plain"):
                return new RedirectView("/txt/{file}");
            case ("application/json"):
                return new RedirectView("/json/{file}");
            case ("text/xml"):
                return new RedirectView("/xml/{file}");
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
    @GetMapping(value = "/json/{file}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public byte[] getJSON(@PathVariable("file") String file) throws IOException {
        return Files.readAllBytes(new File("c:\\FilesForJava\\"  + file).toPath());
    }
    @GetMapping(value = "/xml/{file}", produces = { MediaType.TEXT_XML_VALUE })
    public byte[] getXML(@PathVariable("file") String file) throws IOException {
        return Files.readAllBytes(new File("c:\\FilesForJava\\"  + file).toPath());
    }


}
