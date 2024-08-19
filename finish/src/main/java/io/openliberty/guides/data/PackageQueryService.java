package io.openliberty.guides.data;

import java.lang.reflect.Method;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/packageQuery")
public class PackageQueryService {
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String queries() {
        Method[] methods = Packages.class.getMethods();

        JsonArrayBuilder queryList = Json.createArrayBuilder();

        for (Method m : methods) {
            queryList.add(m.getName());
        }

        return queryList.build().toString();

    }
}
