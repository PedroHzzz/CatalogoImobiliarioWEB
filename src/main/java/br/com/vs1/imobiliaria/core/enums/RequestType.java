package br.com.vs1.imobiliaria.core.enums;

public enum RequestType {

    XML("xml"),
    JSON("json"),
    YAML("yaml"),
    CSV("csv");

    private String value;

    RequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
