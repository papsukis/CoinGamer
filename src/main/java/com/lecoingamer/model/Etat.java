package com.lecoingamer.model;

public enum Etat {

    A_Livrer("A livrer"),
    Paye("Payé"),
    En_Attente_Paiement("En attente de paiment"),
    En_Attente_Livraison("En attente de livraison"),
    Livre("Livré"),
    Annule("Annulé"),
    Nouvelle("Nouvelle");

    private String description;

    Etat(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
