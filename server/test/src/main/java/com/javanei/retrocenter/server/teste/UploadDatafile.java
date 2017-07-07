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

public class UploadDatafile {
    private static final String TARGET_URL = "http://localhost:8080/retrocenter/datafiles/";

    public static void main(String[] args) {
        try {
            Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            WebTarget webTarget = client.target(TARGET_URL);
            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            //File file = new File("server/test/src/main/resources/mame.xml");
            //File file = new File("server/test/src/main/resources/mame-test.xml");
            //File file = new File("server/test/src/main/resources/mame0186.xml");
            File file = new File("F:/Downloads/Emulator/MAME/mame0187.xml");
            //File file = new File("server/test/src/main/resources/logiqx-n64.dat");
            //File file = new File("server/test/src/main/resources/cmpro-vita.dat");
            //File file = new File("F:/Desenv/Fontes/Java/retrocenter/resources/no-intro/Commodore - Plus-4 (20090105-000000_CM).dat");
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                    file, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            multiPart.bodyPart(fileDataBodyPart);

            Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(multiPart, multiPart.getMediaType()));

            Gson gson = new Gson();
            Datafile d = gson.fromJson(response.readEntity(String.class), Datafile.class);
            System.out.println(d.toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
