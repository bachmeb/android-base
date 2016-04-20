package com.bachmeb.basepkg.controller.web;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by b on 11/26/14.
 */
public class SetGetGoClient {

    private static final String BASE_URL = "http://randomword.setgetgo.com/get.php";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        System.out.println("client.get");
        //client.get(getAbsoluteUrl(url), params, responseHandler);
        System.out.println(BASE_URL);

        client.get(BASE_URL, params, responseHandler);

        System.out.println("client got");
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}