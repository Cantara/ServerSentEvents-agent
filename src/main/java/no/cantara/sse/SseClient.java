package no.cantara.sse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class SseClient {
    private static final Logger log = getLogger(SseClient.class);
    private final Client client;

    public SseClient() {
        client = init();
    }
    // For testing
    protected SseClient(Client client) {
        this.client = client;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -cp \"target/ServerSentEvents-<version>.jar\" no.cantara.sse.SseClient <sseUrl> <bearerToken>");
            System.exit(1);
        }
        String sseUrl = args[0];
        String bearerToken = args[1];

       SseClient sseClient = new SseClient();
       sseClient.openStream(sseUrl, bearerToken, new StreamListener() {
           @Override
           public void onEvent(StreamEvent streamEvent) {
              log.info("Received event: {}", streamEvent);
           }
       });

    }

    public void openStream(String sseUrl, String bearerToken, StreamListener streamListener) {
        EventInput eventInput = client.target(sseUrl)
                .request()
                .header("Authorization", "Bearer " + bearerToken)
                .get(EventInput.class);
        try {
            while (!eventInput.isClosed()) {
                InboundEvent inboundEvent = eventInput.read();
                if (inboundEvent == null) {
                    // Reconnect logic (you can add a delay here before reconnecting)
                    eventInput.close();
                    Thread.sleep(100);

                    eventInput = client.target(sseUrl)
                            .request()
                            .header("Authorization", "Bearer " + bearerToken)
                            .get(EventInput.class);
                } else {
                    String data = inboundEvent.readData(String.class);
                    System.out.println("Received Event: " + data);
                    log.trace("Received Event: id: {}, name: {}, comment: {}, \ndata: {}", inboundEvent.getId(), inboundEvent.getName(), inboundEvent.getComment(), data);
                    StreamEvent streamEvent = EventInputMapper.toStreamEvent(inboundEvent);
                    streamListener.onEvent(streamEvent);
                }
            }
        } catch (InterruptedException e) {
            //Do nothing based on sleep interupted
        }

    }

    private static Client init() {
        Client client = ClientBuilder.newBuilder()
                .register(SseFeature.class)
                .build();
        return client;
    }
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}

