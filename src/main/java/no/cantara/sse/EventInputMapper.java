package no.cantara.sse;

import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class EventInputMapper {
    private static final Logger log = getLogger(EventInputMapper.class);
    public static StreamEvent toOpenEvent(EventInput eventInput) {
//        String data = eventInput.read();
        log.trace("Received EventInput: {}", eventInput);
        return null;
    }

    public static StreamEvent toObservedEvent(InboundEvent inboundEvent) {
        String data = inboundEvent.readData();
        log.trace("Received Event: id: {}, name: {}, comment: {}, \ndata: {}", inboundEvent.getId(), inboundEvent.getName(), inboundEvent.getComment(), data);
        return null;
    }
}
