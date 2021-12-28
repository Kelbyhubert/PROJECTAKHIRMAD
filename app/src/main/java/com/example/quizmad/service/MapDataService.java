package com.example.quizmad.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.quizmad.model.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapDataService {

    public static final String LOCATION_URI = "https://raw.githubusercontent.com/acad600/JSONRepository/master/ISYS6203/O212-ISYS6203-SO02-00-BlueDoll.json";
    private Context context;

    public MapDataService(Context context){
        this.context = context;
    }

    public void getLocationData(final VolleyResponseListener volleyResponseListener){
        List<Map> maps = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LOCATION_URI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("markers");

                    for(int i =0; i<jsonArray.length(); i++){
                        JSONObject marker = jsonArray.getJSONObject(i);
                        String name = marker.getString("name");

                        JSONObject location = marker.getJSONObject("location");
                        String lat = location.getString("lat");
                        String lng = location.getString("lng");

                        Map map = new Map (name, lat, lng);
                        maps.add(map);

                    }
                    volleyResponseListener.onResponse(maps);

                    for (int j =0; j<maps.size(); j++){
                        Log.i("Location", maps.get(j).getName());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyResponseListener.onError();
            }
        }) ;


        RequestQueueSingleton.getInstances(context).addToRequestQueue(request);
    }


    public interface VolleyResponseListener{
        void onError();
        void onResponse(List<Map> maps);

    }
}
