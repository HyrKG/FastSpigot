package cn.hyrkg.fastspigot.fast.forgeui;

public class PropertyShader {
    public final JsonProperty property;

    public PropertyShader(JsonProperty property) {
        this.property = property;
    }

    public JsonProperty getProperty() {
        return property;
    }

    public JsonContent content(String key) {
        return new JsonContent(property, key);
    }
}
