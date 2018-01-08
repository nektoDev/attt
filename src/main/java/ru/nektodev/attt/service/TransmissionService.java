package ru.nektodev.attt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nektodev.attt.model.TransmissionException;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class TransmissionService {
    public static final Logger LOG = LoggerFactory.getLogger(TransmissionService.class);

    @Value("${transmission.url}")
    private String TRANSMISSION_URL;

    public TransmissionService() {
    }

    @Nullable
    public String addToTransmission(String downloadDir, String magnet) throws TransmissionException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TRANSMISSION_URL);
            post.addHeader("X-Transmission-Session-Id", getSession());
            String body = "{\n" +
                    "\t\"method\" : \"torrent-add\",\n" +
                    "\t\"arguments\" : {\n" +
                    "\t\t\"filename\":\"" + magnet + "\",\n" +
                    "\t\t\"download-dir\": \"" + downloadDir + "\" \n" +
                    "\t}\n" +
                    "}";
            StringEntity params = new StringEntity(body);
            post.setEntity(params);
            HttpResponse response = client.execute(post);

            String hash = getHash(response.getEntity().getContent());
            if (hash == null || hash.isEmpty()) {
                LOG.error(String.format("Couldn't add torrent with magnet %s to transmission. The hash is null or empty", magnet));
                throw new TransmissionException(String.format("Cannot add magnet %s to Transmission. The hash is null or empty", magnet));
            }
            return hash;
        } catch (IOException e) {
            LOG.error("Couldn't add torrent to transmission.", e);
            throw new TransmissionException(String.format("Cannot add magnet %s to Transmission.", magnet));
        }
    }

    @Null
    private String getHash (InputStream content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(content, Map.class);
        if ("success".equalsIgnoreCase((String) jsonMap.get("result"))) {
            Object torrent = ((Map) jsonMap.get("arguments")).get("torrent-added");
            if (torrent == null) {
                torrent = ((Map) jsonMap.get("arguments")).get("torrent-duplicate");
            }

            return (String) ((Map) torrent).get("hashString");
        } else {
            return null;
        }
    }

    private String getSession() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(TRANSMISSION_URL);
        try {
            HttpResponse response = client.execute(post);
            return response.getFirstHeader("X-Transmission-Session-Id").getValue();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
