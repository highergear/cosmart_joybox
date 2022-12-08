package com.library.cosmart_joybox.constant;

public enum Messages {


    MISSING_PARAMETER_ERROR("Error due to missing parameter: subject"),
    REST_API_CALL_ERROR("Something went wrong when getting data from open library website"),
    EMPTY_DATA_ERROR("No data found for the requested subject");

    public final String message;

    private Messages(String message) {
        this.message = message;
    }

    public String get() {
        return this.message;
    }
}
