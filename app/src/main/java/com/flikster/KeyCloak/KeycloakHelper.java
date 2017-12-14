package com.flikster.KeyCloak;

import android.app.Activity;
import android.util.Log;

import org.jboss.aerogear.android.authorization.AuthorizationConfiguration;
import org.jboss.aerogear.android.authorization.AuthorizationManager;
import org.jboss.aerogear.android.authorization.AuthzModule;
import org.jboss.aerogear.android.authorization.oauth2.OAuth2AuthorizationConfiguration;
import org.jboss.aerogear.android.core.Callback;

import java.net.URL;

/**
 * Created by steven on 8/5/16.
 */
public class KeycloakHelper {
    //http://staging-592298326.us-west-2.elb.amazonaws.com
    private static final String AUTH_SERVER_URL = "http://staging-592298326.us-west-2.elb.amazonaws.com";
    private static final String AUTHZ_URL = AUTH_SERVER_URL + "/auth";
    //"http://staging-592298326.us-west-2.elb.amazonaws.com/auth/realms/Flikster_Android/protocol/openid-connect/auth"
    private static final String AUTHZ_ENDPOINT = "/realms/Flikster_Android/protocol/openid-connect/auth";
    private static final String ACCESS_TOKEN_ENDPOINT = "/realms/Flikster_Android/protocol/openid-connect/token";
    private static final String REFRESH_TOKEN_ENDPOINT = "/realms/Flikster_Android/protocol/openid-connect/token";
    private static final String AUTHZ_ACCOOUNT_ID = "keycloak-token";
    private static final String AUTHZ_CLIENT_ID = "flikster-android-abhi";
    private static final String AUTHZ_REDIRECT_URL = "http://flikster.com/home";
    private static final String MODULE_NAME = "Flikster";

    private static final String TAG = KeycloakHelper.class.getSimpleName();

    static {
        try {
            AuthorizationManager.config(MODULE_NAME, OAuth2AuthorizationConfiguration.class)
                    .setBaseURL(new URL(AUTHZ_URL))
                    .setAuthzEndpoint(AUTHZ_ENDPOINT)
                    .setAccessTokenEndpoint(ACCESS_TOKEN_ENDPOINT)
                    .setRefreshEndpoint(REFRESH_TOKEN_ENDPOINT)
                    .setAccountId(AUTHZ_ACCOOUNT_ID)
                    .setClientId(AUTHZ_CLIENT_ID)
                    .setRedirectURL(AUTHZ_REDIRECT_URL)
                    .asModule();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void connect(final Activity activity, final Callback<String> callback) {
        Log.i(TAG, "Run Connect "+AuthorizationManager.getModule(MODULE_NAME));
        final AuthzModule authzModule = AuthorizationManager.getModule(MODULE_NAME);

        authzModule.requestAccess(activity, new Callback<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "loging data " + data);
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, e.getMessage(), e);
                callback.onFailure(e);
            }
        });
    }

    public static boolean isConnected() {
        Log.e("inside isconnected", "check is connected!");
        return AuthorizationManager.getModule(MODULE_NAME).isAuthorized();
    }
}
