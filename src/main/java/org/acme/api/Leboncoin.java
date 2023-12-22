package org.acme.api;

import java.util.ArrayList;

import org.acme.models.AnnouncementModel;
import org.acme.models.ObjectModel;
import org.acme.models.State;
import org.acme.service.LeboncoinService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class Leboncoin {

    @Inject
    LeboncoinService leboncoinService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList<AnnouncementModel> getAnnouncements() {
        return this.leboncoinService.getAnnouncements();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public AnnouncementModel addAnnouncement(
            @FormParam("value") boolean value,
            @FormParam("creator") String creator,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("contact") String contact,
            @FormParam("price") int price,
            @FormParam("state") State state) {

        AnnouncementModel announcement = new AnnouncementModel(value, creator,
                new ObjectModel(title, description, contact, price, state));
        this.leboncoinService.announcements.add(announcement);
        this.leboncoinService.setAnnouncements(this.leboncoinService.announcements);
        return announcement;
    }

    @DELETE
    @Path("/delete/{id}")
    public Response removeAnnouncement(@PathParam("id") int id) {
        try {
            leboncoinService.removeAnnouncementById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la suppression de l'annonce").build();
        }
    }
}
