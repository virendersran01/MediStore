package com.virtualstudios.medistore.data.volley;

import java.util.Map;

public interface VolleyCallBacks {

    void onSuccess();

    void onError(TYPE type, Map<String, String> errorList);

    enum TYPE {
        NETWORK_ERROR, JSON_ERROR, INVALID_VALUE_ERROR, UNCAUGHT_ERROR, FACEBOOK_SIGNUP_VALUES,
        FACEBOOK_LOGIN_CANCEL, NO_RESULT_FOUND, INVALID_AUTHENTICATION, VALUE_EXISTS, MISSING_INFORMATION, SERVER_ERROR
    }
}
