package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherMap extends AppCompatActivity {
    String weatherWebserviceURL;
    TextView temperature, humidity;
    ImageView weatherBackground;
    JSONObject jsonObj;
    ProgressDialog pDialog;
    EditText cityname;
    String getcity;
    double temp,hum;
    String weather;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_map);
        temperature = (TextView)findViewById(R.id.temperature);
        weatherBackground = (ImageView)findViewById(R.id.weatherimg);
        humidity = (TextView)findViewById(R.id.humidity);
        cityname =(EditText)findViewById(R.id.cityname);
        Button enter = (Button)findViewById(R.id.enter);
        Button home = (Button)findViewById(R.id.homebttn);
        sp = PreferenceManager.getDefaultSharedPreferences(this);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcity = cityname.getText().toString();
                weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q="+getcity+"&appid=470bea1936b1a684b0ce0e53f138c786&units=metric";
                weather(weatherWebserviceURL);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherMap.this,MainActivity.class));
            }
        });

    }

    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Layali", response.toString());
                try{
                    JSONObject main = response.getJSONObject("main");
                     temp = main.getDouble("temp");
                     hum = main.getDouble("humidity");
                    humidity.setText(String.valueOf(hum));
                    temperature.setText(String.valueOf(temp) +" °C");
                    Log.d("layali",String.valueOf(temp) + " "+hum);
                    JSONArray weatherArray = response.getJSONArray("weather");
                    icon(weatherArray);

                }
                catch (JSONException e){
                    e.printStackTrace();
                    Log.d("layali",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Layali", "Error retrieving URL " +error);
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);


    }
    public void icon(JSONArray jsonarray){
        for(int i =0; i<jsonarray.length();i++){
            try{
                JSONObject oneobject = jsonarray.getJSONObject(i);
                weather = oneobject.getString("main");
                editor = sp.edit();
                editor.putString("ww",weather);
                editor.apply();

                Log.d("Layali", "Weather: " +weather.toString());
                if(weather.equals("Clear")){
                   weatherBackground.setImageResource(R.drawable.clear);
                }
                if(weather.equals("Clouds")){
                    weatherBackground.setImageResource(R.drawable.cloudy);
                }
                if(weather.equals("Rain")){
                    weatherBackground.setImageResource(R.drawable.rainy);
                }
            }
            catch (JSONException e){
                Log.d("Layali", "Error retrieving jsonarray " +e);
            }
        }

    }
}