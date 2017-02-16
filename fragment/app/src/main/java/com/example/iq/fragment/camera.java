package com.example.iq.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

public class camera extends Fragment {

    ImageView mImageView;
    Bitmap bitmap;
    float angle = 0;
//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        startActivity(intent);
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        dispatchTakePictureIntent();
        mImageView = (ImageView)v.findViewById(R.id.imageView1);

        Button button = (Button)v.findViewById(R.id.rot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle +=90;
                bitmap = rotateImage(bitmap,angle);
                mImageView.setImageBitmap(bitmap);
            }
        });

        return v;
    }

    private void dispatchTakePictureIntent(){
        Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getContext().getPackageManager())!=null){
            startActivityForResult(i,1);
        }
    }
    public static Bitmap rotateImage(Bitmap sourceImage, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(sourceImage, 0, 0, sourceImage.getWidth(), sourceImage.getHeight(), matrix, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();

            bitmap = (Bitmap) bundle.get("data");
            mImageView.setImageBitmap(bitmap);
        }
    }
}
