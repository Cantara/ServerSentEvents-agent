package no.cantara.sse.stream;

import no.cantara.realestate.observations.ObservationMessage;
import no.cantara.sse.StreamEvent;

public class MetasysObservedValueStreamEvent extends StreamEvent {
    private static final String name = "object.values.update";
    private final ObservationMessage observationMessage;

    public MetasysObservedValueStreamEvent(String id, String comment, String data, ObservationMessage observationMessage) {
        super(id, name, comment, data);
        this.observationMessage = observationMessage;
    }

    public ObservationMessage getObservationMessage() {
        return observationMessage;
    }

    @Override
    public String toString() {
        return "MetasysObservedValueStreamEvent{" +
                "observationMessage=" + observationMessage +
                "} " + super.toString();
    }
}
