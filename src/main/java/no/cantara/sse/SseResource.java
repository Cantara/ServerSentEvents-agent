package no.cantara.sse;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/sse")
public class SseResource {
    private static final Logger log = getLogger(SseResource.class);

    @GET
    @Produces("text/event-stream")
    public void sse() {
        // https://stackoverflow.com/questions/63239403/how-to-send-server-sent-events-from-jax-rs
//        Sse sse = Sse.newSse();
//        SseEventSink eventSink = sse.newEventSink();

        /*
        try {
            eventSink.send("Hello, throw new RuntimeException(e);world!");
            eventSink.send("This is an event from the server.");

            for (int i = 0; i < 10; i++) {
                eventSink.send("Event #" + i);
                Thread.sleep(1000);
            }

            eventSink.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
    }

    @GET
    @Path("/test")
    @Produces("text/event-stream")
    public void runTest(@Context Sse sse, @Context SseEventSink sseEventSink)
    {
        int lastEventId = 0;// ..;
        while (lastEventId < 10) {
            OutboundSseEvent stringEvent = sse.newEventBuilder()
                    .name("" + lastEventId)
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .data("hello world")
                    .id(String.valueOf(lastEventId))
                    .reconnectDelay(4000) // .comment("price change")
                    .build();
            sseEventSink.send(stringEvent);
            lastEventId++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
