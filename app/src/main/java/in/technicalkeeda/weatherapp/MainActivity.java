package in.technicalkeeda.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public  class Event {
        String main="errod";
        String descreption="error";
        String name="error";



    }

    EditText editText;
    Event event=new Event();
    private  static  String callingurl="http://api.openweathermap.org/data/2.5/weather?q=";
    private  static String apiid="&appid=4940715a24f242f0e6010ebb3efe322f";
    public  void updateui(Event Result){
        TextView textView1=findViewById(R.id.displayresult);
        String s=Result.name+"\n"+Result.descreption+"\n"+Result.main+"\n";
        Log.d("msg",s);
        textView1.setText(s);

    }
    public  void btnclicked(View view){
        EditText editText2=findViewById(R.id.editText2);
        String cityname=editText2.getText().toString();
        String url=callingurl+cityname+apiid;
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
         JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("cod") == 401 || response.getInt("cod") == 404) {
                        Toast.makeText(MainActivity.this, "some error occured please enter valid city name ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try{
                    JSONArray weatherArray=response.getJSONArray("weather");
                    try{
                        JSONObject weatherobject= weatherArray.getJSONObject(0);
                        try{
                            event.main= weatherobject.getString("main");

                            try{
                                event.descreption=weatherobject.getString("description");
                            }
                            catch (JSONException e){

                            }

                        }
                        catch (JSONException e){

                        }

                    }
                    catch (JSONException e){

                    }
                }
                catch (JSONException e){

                }



                try{
                    event.name= response.getString("name");
                    updateui(event);
                }
                catch (JSONException e){

                }






            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error could not find city please enter another city", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText2);
        String cityname=editText.getText().toString();
      // callingurl= callingurl+cityname+apiid;
        //imageView=findViewById(R.id.imageView);
       // imageView.setImageResource(R.drawable.weatherbackgroud);
    }

    public  class DownloadTast extends AsyncTask<String,Void,Event>{
        public Event event=new Event();
        @Override
        protected Event doInBackground(String... urls) {
           String url;

           url=urls[0];
           /* RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "http://api.openweathermap.org/data/2.5/weather?q=delhi&appid=4940715a24f242f0e6010ebb3efe322f", null, new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                   try{
                       JSONArray weatherArray=response.getJSONArray("weather");
                       try{
                           JSONObject weatherobject= weatherArray.getJSONObject(0);
                           try{
                               event.main= weatherobject.getString("main");
                               event.descreption=weatherobject.getString("description");
                           }
                           catch (JSONException e){

                           }
                       }
                       catch (JSONException e){

                       }
                   }
                   catch (JSONException e){

                   }



            try{
                event.name= response.getString("name");
            }
            catch (JSONException e){

            }






               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Toast.makeText(MainActivity.this, "error could not find city please enter another city", Toast.LENGTH_SHORT).show();
               }
           });
            requestQueue.add(jsonObjectRequest);*/





            return event;
        }

        @Override
        protected void onPostExecute(Event event) {
            super.onPostExecute(event);
            updateui(event);

        }
    }
}
