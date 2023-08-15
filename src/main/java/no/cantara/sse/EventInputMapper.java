package no.cantara.sse;

import no.cantara.sse.metasys.MetasysHeartbeatStreamEvent;
import no.cantara.sse.metasys.MetasysObservedValueEvent;
import no.cantara.sse.metasys.MetasysOpenStreamEvent;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class EventInputMapper {
    private static final Logger log = getLogger(EventInputMapper.class);


    public static StreamEvent toStreamEvent(InboundEvent inboundEvent) {
        String name = inboundEvent.getName();
        StreamEvent streamEvent = null;
        switch (name) {
            case MetasysOpenStreamEvent.name:
                streamEvent = new MetasysOpenStreamEvent(inboundEvent.getId(), inboundEvent.readData());
                break;
            case MetasysObservedValueEvent.name:
               streamEvent = new MetasysObservedValueEvent(inboundEvent.getId(), inboundEvent.getComment(), inboundEvent.readData());
                break;
            case MetasysHeartbeatStreamEvent.name:
                streamEvent = new MetasysHeartbeatStreamEvent(inboundEvent.getId(), inboundEvent.readData());
                break;
            default:
                streamEvent = new StreamEvent(inboundEvent.getId(), name, inboundEvent.getComment(), inboundEvent.readData());
        }
        log.trace("Received Event: id: {}, name: {}, comment: {}, \ndata: {}", streamEvent.getId(), streamEvent.getName(), streamEvent.getComment(), streamEvent.getData());
        return streamEvent;
    }
}
