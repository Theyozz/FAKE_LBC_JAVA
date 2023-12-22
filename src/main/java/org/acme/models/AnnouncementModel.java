package org.acme.models;

import java.util.*;

public class AnnouncementModel {

    public Date date;
    public boolean online;
    public String creator;
    public ObjectModel object;
    public static int idCounter = 1;
    public int id;

    public AnnouncementModel(boolean online, String creator, ObjectModel object) {
        this.date = new Date();
        this.online = online;
        this.creator = creator;
        this.object = object;
        this.id = idCounter++;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ObjectModel getObject() {
        return object;
    }

    public void setObject(ObjectModel object) {
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Annonce numéro : " + id;
    }
}
