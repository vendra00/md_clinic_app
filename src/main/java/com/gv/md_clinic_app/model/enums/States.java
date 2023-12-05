package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for the states of Spain
 */
@Getter
public enum States {

    //Spain
    Andalusia("Andalusia"),
    Aragon("Aragon"),
    Asturias("Asturias"),
    BalearicIslands("Balearic Islands"),
    BasqueCountry("Basque Country"),
    CanaryIslands("Canary Islands"),
    Cantabria("Cantabria"),
    CastileandLeon("Castile and Leon"),
    CastileLaMancha("Castile-La Mancha"),
    Catalonia("Catalonia"),
    Extramadura("Extramadura"),
    Galicia("Galicia"),
    LaRioja("La Rioja"),
    CommunityOfMadrid("Madrid"),
    RegionOfMurcia("Murcia"),
    Navarre("Navarre"),
    ValencianCommunity("Valencian Community");

    private final String displayString;

    /**
     * Constructor for the enum
     * @param displayString the string to be displayed
     */
    States(String displayString) {this.displayString = displayString;}
}
