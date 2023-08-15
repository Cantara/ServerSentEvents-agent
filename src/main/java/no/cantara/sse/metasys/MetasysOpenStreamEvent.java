package no.cantara.sse.metasys;

import no.cantara.sse.StreamEvent;

public class MetasysOpenStreamEvent extends StreamEvent {
    public static final String name = "hello";
    private final String subscriptionId;

    public MetasysOpenStreamEvent(String id, String data) {
        super(id, name, null, data);
        this.subscriptionId = data;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public String toString() {
        return "MetasysOpenStreamEvent{} " + super.toString();
    }
}
