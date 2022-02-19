package br.com.pedrodavi.msworkday;

import br.com.pedrodavi.msworkday.model.ResponseDTO;
import br.com.pedrodavi.msworkday.service.WorkdayCheckService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/v1/workday")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkdayResource {

    @GET
    @Path("{date}")
    public ResponseDTO checkWorkday(@PathParam("date") String req) throws ParseException {
        return WorkdayCheckService.checkWorkday(req);
    }

}