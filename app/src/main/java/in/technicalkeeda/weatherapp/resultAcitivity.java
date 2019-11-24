package in.technicalkeeda.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class resultAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_acitivity);
        TextView textView1=findViewById(R.id.cityname);
        TextView textView2=findViewById(R.id.weatherdescreption);
        ImageView imageView=findViewById(R.id.weathericon);
        Intent intent=getIntent();
       String cityname= intent.getStringExtra("name");
        String main=intent.getStringExtra("main");
        String descreption=intent.getStringExtra("descreption");



        Log.d("msg",cityname+main+descreption);
        textView1.setText(cityname);
        textView2.setText(main+"\n"+descreption);
        imageView.setImageResource(R.drawable.sun);

    }
}
