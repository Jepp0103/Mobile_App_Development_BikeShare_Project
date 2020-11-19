package com.example.bikeshare.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.Bike;
import com.example.bikeshare.BikeApplication;
import com.example.bikeshare.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class BikeActivity extends Activity {
    // GUI variables
    private Button mRegisterBike;
    private Button mCamera;
    private TextView mNewId;
    private TextView mNewType;
    private TextView mNewPrice;
    private ImageView mImageView;

    private Bike mBike = new Bike(0, "", 0, new byte[0]);
    private Realm bikeRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);

        bikeRealm = Realm.getDefaultInstance();

        // Button
        mRegisterBike = (Button) findViewById(R.id.registerBike_button);
        mCamera = (Button) findViewById(R.id.camera_button);


        // Texts
        mNewId = (TextView) findViewById(R.id.new_id);
        mNewType = (TextView) findViewById(R.id.new_typeOfBike);
        mNewPrice = (TextView) findViewById(R.id.new_price);

        //Image view
        mImageView = (ImageView) findViewById(R.id.show_photo);

        //View products click event
        mRegisterBike.setOnClickListener(view -> {
            if ((mNewId.getText().length() > 0) && (mNewPrice.getText().length() > 0) && (mNewPrice.getText().length() > 0)) {
                try {
                    //Passing ints for id and price
                    String stringId = mNewId.getText().toString();
                    int id = Integer.parseInt(stringId);

                    String stringPrice = mNewPrice.getText().toString();
                    int price = Integer.parseInt(stringPrice);

                    String type = mNewType.getText().toString().trim();

                    if (id > 0 && id < 9999999 && price > 0 && price < 9999999) {
                        mBike.setBikeId(id);
                        mBike.setPrice(price);
                        mBike.setType(type);
                        mBike.setmImage(getBytesFromBitmap());
                        bikeRealm.executeTransaction(realm -> bikeRealm.insert(mBike));
                        //PopUp to show success
                        Toast toast = Toast.makeText(BikeActivity.this, "Bike registred", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, -200);
                        toast.show();
                    }

                } catch (NumberFormatException e) {
                    Log.e("Numberformat exception", e.toString());
                    Toast toastTwo = Toast.makeText(BikeActivity.this, "Id or price invalid.", Toast.LENGTH_SHORT);
                    toastTwo.setGravity(Gravity.CENTER, 0, -200);
                    toastTwo.show();
                }

                // Reset text fields
                mNewId.setText("");
                mNewType.setText("");
                mNewPrice.setText("");
            }
        });

        mCamera.setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bikeRealm.close();
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
                // Error occurred while creating the File
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.bikeshare",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // convert from bitmap to byte array
    public synchronized byte[] getBytesFromBitmap() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
        return stream.toByteArray();
    }
}
