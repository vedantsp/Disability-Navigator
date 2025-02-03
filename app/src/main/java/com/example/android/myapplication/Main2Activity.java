package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    SearchAdapter searchAdapter;
    EditText edit1;
    FirebaseUser firebaseUser;
    DatabaseReference databasereference;
    RecyclerView recyclerView;

    ArrayList<String> nameList;
    ArrayList<String> placeList;
    ArrayList<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edit1 =  findViewById(R.id.edit);
        recyclerView = findViewById(R.id.recycle);

        databasereference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        nameList = new ArrayList<>();
        placeList = new ArrayList<>();
        imageList = new ArrayList<>();


        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    setAdapter(editable.toString());
                }
                else
                {
                    nameList.clear();
                    placeList.clear();
                    imageList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }
    private void setAdapter(final String searchedString)
    {
        databasereference.child("image").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameList.clear();
                placeList.clear();
                imageList.clear();
                recyclerView.removeAllViews();
                int counter=0;

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid = snapshot.getKey();
                    String addr= snapshot.child("addr").getValue(String.class);
                    String name= snapshot.child("name").getValue(String.class);
                    String img= snapshot.child("url").getValue(String.class);

                    if(addr.toLowerCase().contains(searchedString.toLowerCase()))
                    {
                        nameList.add(name);
                        placeList.add(addr);
                        imageList.add(img);
                        counter++;
                    }
                    else if(name.toLowerCase().contains(searchedString.toLowerCase() )) {
                        nameList.add(name);
                        placeList.add(addr);
                        imageList.add(img);
                        counter++;
                    }
                    if(counter==15)
                    {
                        break;
                    }

                }
                searchAdapter=new SearchAdapter(Main2Activity.this,placeList,nameList,imageList);
                recyclerView.setAdapter(searchAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

