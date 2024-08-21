package io.openliberty.guides.data;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
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
            JsonObjectBuilder function = Json.createObjectBuilder();
            function.add("name", m.getName());
            JsonArrayBuilder params = Json.createArrayBuilder();
            for (Parameter p : m.getParameters()) {
                params.add(p.getName());
                System.out.println(p.getName());
            }
            function.add("parameters", params);
            queryList.add(function);
        }

        return queryList.build().toString();

    }
}
