package com.innervuse.ivenrollment.implementation.services.dao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/greeting")
public class IvenrollmentDao
{
    @GET
    public String message() {
        return "Hi REST!";
    }

    @POST
    public String lowerCase(final String message) {
        return "Hi REST!".toLowerCase();
    }
}