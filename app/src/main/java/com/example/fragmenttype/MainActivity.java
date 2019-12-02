package com.example.fragmenttype;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmenttype.ui.main.MainFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.fragmenttype.R;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    ImageView like = null;
    ImageView haha = null;
    ImageView wow = null;
    ImageView angry = null;
    ImageView love = null;
    ImageView sad = null;

    RoundImage roundedImage;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        imageView1 = (ImageView) findViewById(R.id.profilepicture);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.me);
        roundedImage = new RoundImage(bm);
        imageView1.setImageDrawable(roundedImage);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        init();
    }
    public void init() {
        like = (ImageView)findViewById(R.id.like);
        haha = (ImageView)findViewById(R.id.haha);
        wow = (ImageView)findViewById(R.id.wow);
        love = (ImageView)findViewById(R.id.love);
        angry = (ImageView)findViewById(R.id.angry);
        sad = (ImageView)findViewById(R.id.sad);


        like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("like");
            }
        });

        haha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("haha");

            }
        });

        wow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("wow");

            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("love");

            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("angry");

            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley("sad");

            }
        });
    }
    public void volley(String reaction) {
        final TextView textView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(reaction);
        String url ="http://172.16.11.3:3000/reaction/" + reaction;
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("the response"+response);
                        textView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
