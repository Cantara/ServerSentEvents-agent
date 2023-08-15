package no.cantara.sse.metasys;

import no.cantara.sse.StreamEvent;

public class MetasysObservedValueEvent extends StreamEvent {
    public static final String name = "object.values.update";
    private ObservedValue observedValue;

    public MetasysObservedValueEvent(String id, String comment, String data) {
        super(id, name, comment, data);
        this.observedValue = StreamEventMapper.mapFromJson(data);
    }

    public MetasysObservedValueEvent(String id, String comment, String data, ObservedValue observedValue) {
        super(id, name, comment, data);
        this.observedValue = observedValue;
    }

    public ObservedValue getObservedValue() {
        return observedValue;
    }
}
