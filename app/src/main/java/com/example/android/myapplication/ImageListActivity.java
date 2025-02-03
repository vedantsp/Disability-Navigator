package com.example.android.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity{

    private DatabaseReference mdatabase;
    private List<ImageUpload> imglist;
    private ListView lv;
    //private TextView tx;
    boolean clicked=false;
    String lon;
   // PhotoViewAttacher photoViewAttacher;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    ImageUpload img;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        imglist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list_item);
        button=(Button)findViewById(R.id.got_map);
//        photoViewAttacher = new PhotoViewAttacher(lv);
      //  tx = findViewById(R.id.todisplay);

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("pls wait loading list image...");
        progressDialog.show();

        mdatabase = FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);
        //user = FirebaseAuth.getInstance().getCurrentUser();
       // uid  = user.getUid();

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    img = snapshot.getValue(ImageUpload.class);
                    imglist.add(img);
                     lon = img.getLongg();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            //change boolean value
                            clicked=true;
                        }
                    });

                    //then on another method or where you want
                    if(clicked)
                    {
                        Intent intent = new Intent(ImageListActivity.this,Main3Activity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("value",lon);
                            intent.putExtras(bundle);
                            startActivity(intent);
                    }
//                    String lat = dataSnapshot.child(uid).child("lat").getValue(String.class);
//                    String longg = dataSnapshot.child(uid).child("longg").getValue(String.class);
//                    //Toast.makeText(getApplicationContext(),lat,Toast.LENGTH_SHORT).show();
//                    tx.setText(lat);
                }

                adapter = new ImageListAdapter(ImageListActivity.this, R.layout.image_item, imglist);
                lv.setAdapter(adapter);

                //lv.
              //  adapter.getView(1,TextView.inflate(ImageListAdapter,),MapsActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
//    public void gotoMap(View view)
//    {
//
//
//
//        Intent intent = new Intent(ImageListActivity.this,Main3Activity.class);
//        Bundle bundle = new Bundle();
//        //adapter.get
//       // bundle.putDouble("first_name",adapter.getItemId(2));
//       // bundle.putDouble("last_name",adapter.getItemId(3));
//       // bundle.putDouble("",18.7675);
//        bundle.putString("value",lon);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }
}

