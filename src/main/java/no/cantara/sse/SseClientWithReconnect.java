package no.cantara.sse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.core.GenericType;

public class SseClientWithReconnect {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -cp \"target/ServerSentEvents-0.1.0-SNAPSHOT.jar\" no.cantara.sse.SseClientWithReconnect <sseUrl> <bearerToken>");
            System.exit(1);
        }
        String sseUrl = args[0];
        String bearerToken = args[1];

        Client client = ClientBuilder.newBuilder()
                .register(SseFeature.class)
                .build();

        EventInput eventInput = client.target(sseUrl)
                .request()
                .header("Authorization", "Bearer " + bearerToken)
                .get(EventInput.class);

        while (!eventInput.isClosed()) {
            InboundEvent inboundEvent = eventInput.read();
            if (inboundEvent == null) {
                // Reconnect logic (you can add a delay here before reconnecting)
                eventInput.close();
                eventInput = client.target(sseUrl)
                        .request()
                        .header("Authorization", "Bearer " + bearerToken)
                        .get(EventInput.class);
            } else {
                String data = inboundEvent.readData(String.class);
                System.out.println("Received Event: " + data);
            }
        }

        client.close();
    }
}

