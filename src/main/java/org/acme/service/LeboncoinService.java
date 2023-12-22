package org.acme.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.acme.models.AnnouncementModel;
import org.acme.models.ObjectModel;
import org.acme.models.State;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

@ApplicationScoped
@Named("Leboncoin")
public class LeboncoinService {

    String title;
    String description;
    String contact;
    int price;
    public State state;
    public Date date;
    public boolean online;
    public String creator;
    public ObjectModel object;
    public State optionValue;
    public String operationFilter = "Tous";

    public AnnouncementModel announcement;
    public ArrayList<AnnouncementModel> announcements = new ArrayList<AnnouncementModel>(Arrays.asList());

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

    public AnnouncementModel getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementModel announcement) {
        this.announcement = announcement;
    }

    public ArrayList<AnnouncementModel> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(ArrayList<AnnouncementModel> announcements) {
        this.announcements = announcements;
    }

    public String addAnouncement() {
        AnnouncementModel announcement = new AnnouncementModel(true, creator,
                new ObjectModel(title, description, contact, price, optionValue));
        this.announcements.add(announcement);
        return "addAnnouncement";
    }

    public void removeAnnouncement(AnnouncementModel announcement) {
        this.announcements.remove(announcement);
    }

    public List<State> displayStateOptions() {
        List<State> result = Arrays.asList(State.OK, State.GOOD, State.VERYGOOD);
        return result;
    }

    public State getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(State optionValue) {
        this.optionValue = optionValue;
    }

    public List<AnnouncementModel> getFiltredAnnouncement() {
        if (operationFilter.equals("Tous")) {
            return this.announcements;
        } else if (operationFilter.equals(State.GOOD.toString())) {
            return this.announcements.stream()
                    .filter(o -> o.getObject().getState().toString().equals(State.GOOD.toString()))
                    .collect(Collectors.toList());
        } else if (operationFilter.equals(State.OK.toString())) {
            return this.announcements.stream()
                    .filter(o -> o.getObject().getState().toString().equals(State.OK.toString()))
                    .collect(Collectors.toList());
        } else {
            return this.announcements.stream()
                    .filter(o -> o.getObject().getState().toString().equals(State.VERYGOOD.toString()))
                    .collect(Collectors.toList());

        }
    }

    public void changeOperationFilter(ValueChangeEvent event) {
        String newValue = (String) event.getNewValue();
        System.out.println(newValue);
        this.setOperationFilter(newValue);
    }

    public String getOperationFilter() {
        return operationFilter;
    }

    public void setOperationFilter(String operationFilter) {
        this.operationFilter = operationFilter;
    }

    public List<String> displayOptions() {
        List<String> result = Arrays.asList("Tous", State.OK.toString(), State.GOOD.toString(),
                State.VERYGOOD.toString());
        return result;
    }

    private AnnouncementModel findAnnouncementById(int id) {
        for (AnnouncementModel announcement : announcements) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }

    public void removeAnnouncementById(int id) throws Exception {
        AnnouncementModel announcement = findAnnouncementById(id);
        if (announcement != null) {
            announcements.remove(announcement);
        } else {
            throw new Exception("Annonce avec l'ID " + id + " non trouvée");
        }
    }
}
