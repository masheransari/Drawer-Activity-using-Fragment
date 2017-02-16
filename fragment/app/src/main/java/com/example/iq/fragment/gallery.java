package com.example.iq.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class gallery extends Fragment {

//    @Nullable
    int RESULT_LOAD_IMG = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_gallery, container, false);
        // Inflate the layout for this fragment
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        return v;
    }

    String imageDecode;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data){
            Uri ImageUri = data.getData();
            String [] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(ImageUri,filePathColumn,null,null,null);
            cursor.moveToFirst();

            int Index = cursor.getColumnIndex(filePathColumn[0]);
            imageDecode = cursor.getString(Index);
//                Log.e(getActivity().getPackageName(),"fragment = "+filePathColumn[0]);
//            Log.e(getActivity().getPackageName(),""+imageDecode);   //basically imageDecode me hamare image ka path save hai....
            ImageView im = (ImageView)getView().findViewById(R.id.imageView2);
//            yeh apki image ke path ko convert kr rha hai apki image me,....
            im.setImageBitmap(BitmapFactory.decodeFile(imageDecode));
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), "You Have not pcked Image", Toast.LENGTH_SHORT).show();
        }


    }
}
