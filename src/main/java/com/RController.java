package com;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
public class RController {
//    private StorageService storageService;
/*
    @Autowired
    public RController(StorageService storageService) {
        this.storageService = storageService;
    }*/

    /*@GetMapping("/{filename}")
    private byte[] displayFileContent(@PathVariable("filename") String filename) throws Exception 
        {
            byte[] buffer = new byte[0];
            try(FileInputStream fin=new FileInputStream("c:\\FilesForJava\\" + filename))
                {
                    buffer = new byte[fin.available()];
                    fin.read(buffer, 0, buffer.length);
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            return buffer;
        }*/
    @GetMapping("/{filename}")
    private byte[] displayFileContent(@PathVariable("filename") String filename) throws Exception
    {
        return Files.readAllBytes(new File("c:\\FilesForJava\\" + filename).toPath());
    }
    @GetMapping("/image/{filename}")
    private BufferedImage displayImageContent(@PathVariable("filename") String filename) throws Exception
    {
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        BufferedImage image = ImageIO.read(new File("c:\\FilesForJava\\" + filename));
        return image;*/
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("c:\\FilesForJava\\" + filename));
        } catch (IOException e) {
        }
        return img;
    }
    @GetMapping(value = "/eh/{filename}")
    public @ResponseBody BufferedImage getImage(@PathVariable("filename") String filename) throws Exception {
        System.out.println(filename);
        filename = "c:\\FilesForJava\\21.jpg";
        BufferedImage in = displayImageContent(filename); //getResourceAsStream("c:\\FilesForJava\\Text.txt");
        System.out.println(in);
       // return IOUtils.toByteArray(in);
        return in;
    }
    @GetMapping("/write/{filename}")
    private ResponseEntity<?> AddInformationToFile(@PathVariable("filename") String filename, @RequestParam("text") String text) throws Exception
    {

        try(FileOutputStream fos=new FileOutputStream("c:\\FilesForJava\\" + filename))
        {
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        boolean wr=true;
        return wr
                ?new ResponseEntity<>(HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
/*    @GetMapping("/upload/{filename}")
    @ResponseBody
    public Resource serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource("c:\\FilesForJava\\" + filename);
        return file;
    }*/

}
