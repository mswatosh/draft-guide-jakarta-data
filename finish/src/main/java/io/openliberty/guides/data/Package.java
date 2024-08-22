package io.openliberty.guides.data;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public record Package(int id, float length, float width, float height) {
    
    JsonObjectBuilder toJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("id", id);
        json.add("length", length);
        json.add("width", width);
        json.add("height", height);
        return json;
    }
}
