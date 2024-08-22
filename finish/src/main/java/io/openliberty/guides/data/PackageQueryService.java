package io.openliberty.guides.data;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/packageQuery")
public class PackageQueryService {

    @Inject
    Packages packages;

    //TODO ignore queries that require an Object as a parameter e.g. delete requires an Entity

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


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String callQuery(String jsonString) {
        System.out.println(jsonString);
        JsonObject json = Json.createReader(new StringReader(jsonString)).readObject();
        try {
            Method method = Packages.class.getMethod(json.getString("method"));
            Object result = method.invoke(packages);

            JsonArrayBuilder returnList = Json.createArrayBuilder();
            if (result instanceof Stream) {
                ((Stream<?>)result).forEach(p -> {
                    returnList.add(((Package) p).toJson());
                });
            }
            return returnList.build().toString();
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Reply to Client with error message
            e.printStackTrace();
        }
        return "";
    }
}
