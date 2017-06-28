package com.example.dhrup_034.twitter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ProfileActivity extends AppCompatActivity {
    //Image Loader object
    private ImageLoader imageLoader;
    Button twi_share;
    EditText tweetText;

    //NetworkImageView Ojbect
    private NetworkImageView profileImage;

    //TextView object
    private TextView textViewUsername;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileImage = (NetworkImageView) findViewById(R.id.profileImage);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        tweetText = (EditText) findViewById(R.id.tweetText);
        twi_share = (Button) findViewById(R.id.twi_share);

        //Getting the intent
        Intent intent = getIntent();

        //Getting values from intent
        String username = intent.getStringExtra(MainActivity.KEY_USERNAME);
        String profileImageUrl = intent.getStringExtra(MainActivity.KEY_PROFILE_IMAGE_URL);

        //Loading image
        imageLoader = CustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(profileImageUrl, ImageLoader.getImageListener(profileImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        profileImage.setImageUrl(profileImageUrl, imageLoader);

        //Setting the username in textview
        textViewUsername.setText("@"+username);



        twi_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String msg = tweetText.getText().toString();
                Uri uri = Uri
                        .parse("https://cdn.pixabay.com/photo/2017/01/16/15/18/soap-bubble-1984310__340.jpg");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/jpeg");
                intent.setPackage("com.example.dhrup_034.twitter");
                startActivity(intent);

            }
        });

    }
}
