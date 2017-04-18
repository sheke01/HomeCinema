package com.example.sheke.homecinema;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public final class QueryUtils {
        static String BASE_URL;
        static String PARAM;
        static String API_KEY;


        private QueryUtils() {
        }

        public static URL buildUrl(String sorting, Context context) {
            BASE_URL = context.getString(R.string.base_url);
            PARAM = context.getString(R.string.param);
            API_KEY = context.getString(R.string.api_key);

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendEncodedPath(sorting)
                    .appendQueryParameter(PARAM, API_KEY)
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        @SuppressLint("NewApi")
        public static String getResponseFromHttpUrl(URL url) throws IOException {
            HttpURLConnection urlConnection = null;
            StringBuilder builder;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                int response = urlConnection.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK)
                {
                    builder = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(urlConnection.getInputStream())))
                    {
                        String line;

                        while ((line = reader.readLine()) != null){
                            builder.append(line);
                        }
                    }
                    return builder.toString();
                }
            }catch (Exception e)
            {

            } finally {
                urlConnection.disconnect();
            }
            return null;
        }


        public static List<Movie> sonng(String movieJsonStr) throws JSONException{


            List<Movie> dope = new ArrayList<>();

            try {


                JSONObject root = new JSONObject((movieJsonStr));
                JSONArray fist = root.getJSONArray("results");


                for (int i=0;i<fist.length();i++){
                    JSONObject move = fist.getJSONObject(i);

                    String a = move.getString("original_title");
                    String b = move.getString("overview");
                    String c = move.getString("poster_path");
                    String d = move.getString("release_date");
                    Double e = move.getDouble("vote_average");


                    dope.add(new Movie(a, b, c, d, e));

                }

            } catch (JSONException e) {
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }

            return dope;
        }

    }

