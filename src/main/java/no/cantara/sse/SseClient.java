package no.cantara.sse;


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
/*
        log.info("Subscribing to SSE events from {}", sseUrl);
        Client client = ClientBuilder.newClient();
        Feature feature = //FIXME How to find the correct Bearer_Token feature?
        client.register()
        WebTarget target = client.target(sseUrl);
        try (SseEventSource source = SseEventSource.target(target).build()) {
            source.register((inboundSseEvent) -> System.out.println(inboundSseEvent));
            source.open();
            Thread.sleep(1000);
            log.info("Connection is open? {}", source.isOpen());
            Thread.sleep(3600000);

            source.close();
        }

 */
        /*
//        Client client = ClientBuilder.newClient();
//        WebClient client = WebClient.create(sseUri);
//        client.header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
//        Feature feature = OAuth2ClientSupport.feature(bearerToken);
//        client.register(feature);
        WebClient wc = WebClient.create(sseUri);
        wc.header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        TLSConfiguration sscConfig = null;
        Configuration config;
        config = null;
        WebTargetImpl target = new ClientImpl(config, sscConfig).WebTargetImpl(wc, sseUri);
        Client client = new ClientImpl(ClientBuilder.newClient());
          target = client.
                  new WebTargetImpl(client, sseUri);
//                .request(MediaType.SERVER_SENT_EVENTS)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
//                .
//                .buildGet();
//        target.property("Authentication", "Bearer " + bearerToken);
        SseEventSource eventSource = SseEventSource.target(target).build();
        eventSource.register(event -> {
            log.info("Received Event. {}", event);
            System.out.println(event);
            log.trace("Event Name: {}, Data: {}, Id: {}", event.getName(), event.readData(), event.getId());
//            System.out.println(event.readData());
        });

        log.info("Opening target {}", target);

        int sec = 3600;
        log.info("Open connection for {} sec.", sec);
        try {
            eventSource.open();
        } catch (Exception e) {
            log.error("Error opening connection to {}", sseUrl, e);
            System.exit(1);
        }
         Thread.sleep(1000);
        log.info("Connection is open? {}", eventSource.isOpen());
        Thread.sleep(sec *1000);

        eventSource.close();
        `
         */

    }
}
