package com.example.android.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FusedLocationProviderClient client;
    private static final int PICK_IMAGE_REQUEST = 234;
    private ImageView photo_to_upload;
    private EditText name,addr;
    Button buttonChoose,buttonUpload;
    Double lat,long1;
    private Uri filepath;
    private StorageReference storageReference;
    private DatabaseReference databasereference;
    public static final String FB_STORAGE_PATH ="image/";
    TextView textView,textView2;
    public static final String FB_DATABASE_PATH ="image";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageReference = FirebaseStorage.getInstance().getReference();
        databasereference= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        photo_to_upload = (ImageView)findViewById(R.id.image);
        buttonChoose=(Button)findViewById(R.id.browse);
        buttonUpload=(Button)findViewById(R.id.submit);
        name=(EditText)findViewById(R.id.name);
        addr=(EditText)findViewById(R.id.addr);
        textView=(TextView) findViewById(R.id.textView);
        textView2=(TextView) findViewById(R.id.textView2);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
    }
    private void showFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }
    private void uploadFile() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }
        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null)
                {
                    textView = findViewById(R.id.location);
                    lat=location.getLatitude();
                    long1=location.getLongitude();
                    textView.setText(lat.toString());
                    textView2.setText(long1.toString());
                }
            }
        });
        if (filepath != null) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Uploading..");
            pd.show();

            StorageReference riversRef = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(filepath));

            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"file uploaded",Toast.LENGTH_LONG).show();
                            ImageUpload imageUpload = new ImageUpload(name.getText().toString(),taskSnapshot.getDownloadUrl().toString(),addr.getText().toString(),textView.getText().toString(),textView2.getText().toString());
                           // ImageUpload imageUpload1 = new ImageUpload(addr.getText().toString(),taskSnapshot.getDownloadUrl().toString());
                            String uploadId = databasereference.push().getKey();

                            databasereference.child(uploadId).setValue(imageUpload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"failure occured",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            pd.setMessage(((int)progress)+"%uploaded");
                        }
                    });

        }
        else
        {
            Toast.makeText(this,"Please select Image..",Toast.LENGTH_SHORT).show();
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1 );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filepath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                photo_to_upload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getImageExt(Uri uri)
    {
        ContentResolver contenResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contenResolver.getType(uri));
    }

    @Override
    public void onClick(View v) {
        if(v==buttonChoose)
        {
            showFileChooser();
        }
        else if(v==buttonUpload)
        {
            uploadFile();
        }
    }
}
