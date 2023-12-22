package org.acme.models;

public class ObjectModel {

    public String title;
    public String description;
    public String contact;
    public int price;
    public State state;

    public ObjectModel(String title, String description, String contact, int price, State state) {
        this.title = title;
        this.description = description;
        this.contact = contact;
        this.price = price;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ObjectModel [title=" + title + ", description=" + description + ", contact=" + contact + ", price="
                + price + ", state=" + state + "]";
    }

}
