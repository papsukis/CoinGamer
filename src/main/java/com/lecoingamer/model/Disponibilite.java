package com.lecoingamer.model;

public enum Disponibilite {

    En_Stock("En Stock"),
    Sur_Comande("Sur Comande"),
    Disponible("Disponible");

    private String description;

    Disponibilite(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
