package com.javanei.retrocenter.server.teste;

import com.google.gson.Gson;
import com.javanei.retrocenter.datafile.Datafile;
import java.io.File;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class UploadDirectoryDatafiles {
    private static final String SERVER_URL = "http://localhost:8080/retrocenter/";
    private static final String TARGET_URL = SERVER_URL + "datafiles/";
    //private static final String BASE_DIR = "F:/Downloads/Emulator/TOSEC/TOSEC";
    private static final String BASE_DIR = "F:/Desenv/Fontes/Java/retrocenter/resources/no-intro";
    private static final boolean DELETE_FILE = true;

    public static void main(String[] args) {
        try {
            processFile(new File(BASE_DIR));
            System.out.println("========== FIM ==========");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processFile(File file) throws Exception {
        if (file.isFile()) {
            if (file.getName().toLowerCase().endsWith(".dat") || file.getName().toLowerCase().endsWith(".xml")) {
                System.out.println("Processando arquivo: " + file);
                Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
                WebTarget webTarget = client.target(TARGET_URL);
                MultiPart multiPart = new MultiPart();
                multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
                FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                        file, MediaType.APPLICATION_OCTET_STREAM_TYPE);
                multiPart.bodyPart(fileDataBodyPart);

                Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(multiPart, multiPart.getMediaType()));
                int status = response.getStatus();
                if (status == 200) {
                    System.out.println("--- " + response.getStatus() + ": " + response.getStatusInfo());
                    Gson gson = new Gson();
                    Datafile d = gson.fromJson(response.readEntity(String.class), Datafile.class);
                    System.out.println("====== Games: " + d.getArtifacts().size());
                    if (DELETE_FILE) {
                        file.delete();
                    }
                } else {
                    System.err.println("EEE " + response.getStatus() + ": " + response.getStatusInfo());
                    System.err.println("EEEEE " + response.readEntity(String.class));
                }
            }
        } else if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                processFile(f);
            }
        }
    }
}
