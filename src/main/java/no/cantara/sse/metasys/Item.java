package no.cantara.sse.metasys;

public class Item {

    private String id;
    private Number presentValue;
    private String itemReference;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(Number presentValue) {
        this.presentValue = presentValue;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }
}
