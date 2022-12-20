package persistence;

import org.json.JSONObject;

//CITE: Writable based on the format of Writeable from JsonSerializationDemo-Master
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
