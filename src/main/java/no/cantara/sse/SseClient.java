package no.cantara.sse;

//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.sse.SseEventSource;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.SseEventSource;
import org.slf4j.Logger;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;

public class SseClient {
    private static final Logger log = getLogger(SseClient.class);

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java -cp \"target/ServerSentEvents-0.1.0-SNAPSHOT.jar\" no.cantara.sse.SseClient <sseUrl> <bearerToken>");
            System.exit(1);
        }
        String sseUrl = args[0];
        String bearerToken = args[1];

        URI sseUri = new URI(sseUrl);

        log.info("Subscribing to SSE events from {}", sseUrl);

        WebTarget target = ClientBuilder.newClient().target(sseUri);
        target.request(MediaType.SERVER_SENT_EVENTS).header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
//        target.property("Authentication", "Bearer " + bearerToken);
        SseEventSource eventSource = SseEventSource.target(target).build();
        eventSource.register(event -> {
            log.info("Received Event. {}", event);
//            System.out.println(event);
            log.trace("Event Name: {}, Data: {}, Id: {}", event.getName(), event.readData(), event.getId());
//            System.out.println(event.readData());
        });

        int sec = 60;
        log.info("Open connection for {} sec.", sec);
        eventSource.open();

        Thread.sleep(sec *1000);

        eventSource.close();
    }
}
