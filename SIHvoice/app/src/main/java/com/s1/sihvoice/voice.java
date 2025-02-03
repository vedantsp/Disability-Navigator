package com.s1.sihvoice;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class voice extends AppCompatActivity implements View.OnClickListener {

    static final String[] texts = {
        "Welcome to helping app","Swipe left for blind mode","Swipe right for normal view"
    };

    TextToSpeech tts;
//
//    @Override
//    protected void onPause() {
//
//        if(tts!= null)
//        {
//            tts.stop();
//            tts.shutdown();
//        }
//        super.onPause();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
//
//        Button b = (Button) findViewById(R.id.bTextToVoice);
//        b.setOnClickListener(this);

        tts = new TextToSpeech(voice.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR)
                {
                    tts.setLanguage(Locale.ENGLISH);
//                    String random = texts[0];
//                    tts.speak(random, TextToSpeech.QUEUE_ADD, null);
//                    random = texts[1];
//                    try {
//                        tts.wait(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    tts.speak(random, TextToSpeech.QUEUE_ADD, null);
//                    random = texts[2];
//                    try {
//                        tts.wait(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    tts.speak(random, TextToSpeech.QUEUE_ADD, null);

                    String random = texts[0];
                    tts.speak(random, TextToSpeech.QUEUE_ADD, null);
//                    try {
//                        tts.wait(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    String random1 = texts[1];
                    tts.speak(random1, TextToSpeech.QUEUE_ADD, null);
//                    try {
//                        tts.wait(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    tts.setSpeechRate(0.5f);
                    String random2 = texts[2];
                    tts.speak(random2, TextToSpeech.QUEUE_ADD, null);

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
       // Random r = new Random();

    }
}
