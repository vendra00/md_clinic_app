package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for frequency of the patient's health.
 */
@Getter
public enum Frequency {
    ONCE_A_DAY("Once a day"),
    TWICE_A_DAY("Twice a day"),
    THREE_TIMES_A_DAY("Three times a day"),
    FOUR_TIMES_A_DAY("Four times a day"),
    FIVE_TIMES_A_DAY("Five times a day"),
    SIX_TIMES_A_DAY("Six times a day"),
    SEVEN_TIMES_A_DAY("Seven times a day"),
    EIGHT_TIMES_A_DAY("Eight times a day"),
    NINE_TIMES_A_DAY("Nine times a day"),
    TEN_TIMES_A_DAY("Ten times a day"),
    ELEVEN_TIMES_A_DAY("Eleven times a day"),
    TWELVE_TIMES_A_DAY("Twelve times a day"),
    THIRTEEN_TIMES_A_DAY("Thirteen times a day"),
    FOURTEEN_TIMES_A_DAY("Fourteen times a day"),
    FIFTEEN_TIMES_A_DAY("Fifteen times a day"),
    SIXTEEN_TIMES_A_DAY("Sixteen times a day"),
    SEVENTEEN_TIMES_A_DAY("Seventeen times a day"),
    EIGHTEEN_TIMES_A_DAY("Eighteen times a day"),
    NINETEEN_TIMES_A_DAY("Nineteen times a day"),
    TWENTY_TIMES_A_DAY("Twenty times a day"),
    TWENTY_ONE_TIMES_A_DAY("Twenty one times a day"),
    TWENTY_TWO_TIMES_A_DAY("Twenty two times a day"),
    TWENTY_THREE_TIMES_A_DAY("Twenty three times a day"),
    TWENTY_FOUR_TIMES_A_DAY("Twenty four times a day"),
    ONCE_A_WEEK("Once a week"),
    TWICE_A_WEEK("Twice a week"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Frequency of the patient's health.
     * @param displayString string
     */
    Frequency(String displayString) {this.displayString = displayString;}
}
