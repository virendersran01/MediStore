package com.virtualstudios.medistore.data.remote;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.virtualstudios.medistore.data.volley.ApiErrors;
import com.virtualstudios.medistore.data.volley.ApiUrls;
import com.virtualstudios.medistore.data.volley.VolleyCallBacks;
import com.virtualstudios.medistore.data.volley.VolleyHelper;
import com.virtualstudios.medistore.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static com.virtualstudios.medistore.data.volley.VolleyCallBacks.TYPE.INVALID_AUTHENTICATION;
import static com.virtualstudios.medistore.data.volley.VolleyCallBacks.TYPE.INVALID_VALUE_ERROR;
import static com.virtualstudios.medistore.data.volley.VolleyCallBacks.TYPE.JSON_ERROR;
import static com.virtualstudios.medistore.data.volley.VolleyCallBacks.TYPE.NETWORK_ERROR;
import static com.virtualstudios.medistore.data.volley.VolleyCallBacks.TYPE.NO_RESULT_FOUND;

public class UserApi {
    private static final String TAG = "UserApi";

    private Context mContext;

    public UserApi(Context mContext) {
        this.mContext = mContext;
    }

    public String urlUserProfileImage = null;

    public void loginWithUsernamePassword(String userName, String password, VolleyCallBacks callBacks) {

        JSONObject postParams = new JSONObject();
        try {
            postParams.put("username", userName);
            postParams.put("password", password);
            if (Constants.getSPreferences(mContext).getSourceDevice() == null) {
                Constants.getSPreferences(mContext).putSourceDevice(UUID.randomUUID().toString());
            }
            postParams.put("source", Constants.getSPreferences(mContext).getSourceDevice());
            Log.d(TAG, "loginWithUsernamePassword: "+postParams.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlLogin, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "onResponse: "+response.toString());
                    JSONObject object = response.getJSONObject("response");
                    Constants.getSPreferences(mContext).putUserId(object.getString("user_id"));
                    Constants.getSPreferences(mContext).putUserName(object.getString("username"));
                    Constants.getSPreferences(mContext).putUserEmail(object.getString("email"));
                    Constants.getSPreferences(mContext).putAuthKey(object.getString("access_token"));
                    Constants.getSPreferences(mContext).putRefreshToken(object.getString("refresh_token"));
                    Constants.getSPreferences(mContext).setLoginStatus(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callBacks.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Map<String, String> errorList = new HashMap<>();
                if (error.networkResponse.statusCode == 409) {
                    errorList.put("message", ApiErrors.error409);
                    callBacks.onError(VolleyCallBacks.TYPE.UNCAUGHT_ERROR, errorList);
                } else if (error.networkResponse.statusCode == 422) {
                    errorList.put("message", ApiErrors.error422);
                    callBacks.onError(INVALID_VALUE_ERROR, errorList);
                } else if (error.networkResponse.statusCode == 500) {
                    errorList.put("message", ApiErrors.error405);
                    callBacks.onError(VolleyCallBacks.TYPE.UNCAUGHT_ERROR, errorList);
                } else {
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }
            }
        });

        VolleyHelper.getInstance(mContext).addToRequestQueue(request);

    }

    public void requestSignUp(String fullName, String email, String phone,
                              String username, String password, String businessName, String address,
                              VolleyCallBacks callBacks) {

        JSONObject postParams = new JSONObject();

        try {
            postParams.put("full_name", fullName);
            postParams.put("username", username);
            postParams.put("email", email);
            postParams.put("phone", phone);
            postParams.put("password", password);
            postParams.put("business_name", businessName);
            postParams.put("address", address);


            if (Constants.getSPreferences(mContext).getSourceDevice() == null) {
                Constants.getSPreferences(mContext).putSourceDevice(UUID.randomUUID().toString());
            }
            postParams.put("source", Constants.getSPreferences(mContext).getSourceDevice());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "requestSignUp: "+postParams);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlSignUp, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loginWithUsernamePassword(username, password, callBacks);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Map<String, String> errorList = new HashMap<>();
                if (error.networkResponse.statusCode == 409) {
                    JSONObject errorMessageList;
                    try {
                        errorMessageList = new JSONObject(new String(error.networkResponse.data, StandardCharsets.UTF_8)).getJSONObject("response");
                        Log.d(TAG, "onErrorResponse: " + errorMessageList.toString());
                        Iterator<String> keys = errorMessageList.keys();
                        while (keys.hasNext()) {
                            String keyValue = keys.next();
                            errorList.put(keyValue, errorMessageList.getString(keyValue));
                        }
                        callBacks.onError(INVALID_VALUE_ERROR, errorList);
                    } catch (JSONException e) {
                        Map<String, String> errorList1 = new HashMap<>();
                        errorList1.put("message", "Error parsing response contact developer");
                        callBacks.onError(JSON_ERROR, errorList1);
                    }
                } else if (error.networkResponse.statusCode == 422) {
                    errorList.put("message", ApiErrors.error422);
                    callBacks.onError(INVALID_VALUE_ERROR, errorList);
                } else if (error.networkResponse.statusCode == 500) {
                    errorList.put("message", ApiErrors.error500);
                    callBacks.onError(VolleyCallBacks.TYPE.UNCAUGHT_ERROR, errorList);
                } else {
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }
            }
        });

        VolleyHelper.getInstance(mContext).addToRequestQueue(objectRequest);
    }

    public void changePasswords(String oldPassword, String newPassword, VolleyCallBacks callBacks) {

        JSONObject postParams = new JSONObject();
        try {
            postParams.put("old_password", oldPassword);
            postParams.put("new_password", newPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlForgotPassword, postParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBacks.onSuccess();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Map<String, String> errorList = new HashMap<>();
                if (error.networkResponse.statusCode == 409) {
                    errorList.put("message", ApiErrors.error409);
                    callBacks.onError(VolleyCallBacks.TYPE.UNCAUGHT_ERROR, errorList);
                } else if (error.networkResponse.statusCode == 422) {
                    errorList.put("message", ApiErrors.error422);
                    callBacks.onError(INVALID_VALUE_ERROR, errorList);
                } else if (error.networkResponse.statusCode == 401) {
                    errorList.put("message", ApiErrors.error401);
                    callBacks.onError(INVALID_AUTHENTICATION, errorList);
                } else {
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiUrls.getHeaders(mContext);
            }
        };

        VolleyHelper.getInstance(mContext).addToRequestQueue(objectRequest);
    }

    public void getUserDetails(VolleyCallBacks callBacks){

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlUserDetails, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        {
//                            "response": {
//                            "username": "virender01",
//                                    "email": "virendersran01@gmail.com",
//                                    "phone": "7009111410",
//                                    "full_name": null,
//                                    "player_id": null,
//                                    "player_name": "viren01"
//                        }
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Map<String, String> errorList = new HashMap<>();
                if (error.networkResponse.statusCode == 401){
                    errorList.put("message", ApiErrors.error401);
                    callBacks.onError(INVALID_AUTHENTICATION, errorList);
                }else if (error.networkResponse.statusCode == 404){
                    errorList.put("message", ApiErrors.error404);
                    callBacks.onError(NO_RESULT_FOUND, errorList);
                }else{
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiUrls.getHeaders(mContext);
            }
        };

        VolleyHelper.getInstance(mContext).addToRequestQueue(objectRequest);
    }

    public void upoadUserProfilePicture(String imageString, VolleyCallBacks callBacks) {

        JSONObject postParams = new JSONObject();
        try {
            postParams.put("image_string", imageString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlUserUploadImage, postParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBacks.onSuccess();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { // 200,422,401
                Map<String, String> errorList = new HashMap<>();

                if (error.networkResponse.statusCode == 422){
                    errorList.put("message", ApiErrors.error422);
                    callBacks.onError(INVALID_VALUE_ERROR, errorList);
                }else if (error.networkResponse.statusCode == 401){
                    errorList.put("message", ApiErrors.error401);
                    callBacks.onError(INVALID_AUTHENTICATION, errorList);
                }else if (error instanceof NetworkError){
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiUrls.getHeaders(mContext);
            }
        };

        VolleyHelper.getInstance(mContext).addToRequestQueue(objectRequest);
    }

    public void userProfileImage(VolleyCallBacks callBacks){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiUrls.urlUserProfileImage, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("response");
                            urlUserProfileImage = jsonObject.getString("image");
                            callBacks.onSuccess();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callBacks.onError(JSON_ERROR,null);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //404401
                Map<String, String> errorList = new HashMap<>();
                if (error.networkResponse.statusCode == 404){
                    errorList.put("message", ApiErrors.error404);
                    callBacks.onError(NO_RESULT_FOUND, errorList);
                }else if (error.networkResponse.statusCode == 401){
                    errorList.put("message", ApiErrors.error401);
                    callBacks.onError(INVALID_AUTHENTICATION, errorList);
                }else if (error instanceof NetworkError){
                    errorList.put("message", ApiErrors.errorNetwork);
                    callBacks.onError(NETWORK_ERROR, errorList);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiUrls.getHeaders(mContext);
            }
        };

        VolleyHelper.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
    }
}

