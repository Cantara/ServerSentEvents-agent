package no.cantara.sse;

public class StreamEvent {
    private final String id;
    private final String name;
    private final String comment;
    private final String data;

    public StreamEvent(String id, String name, String comment, String data) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "StreamEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
