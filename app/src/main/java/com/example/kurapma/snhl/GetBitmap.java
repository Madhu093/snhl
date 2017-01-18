package com.example.kurapma.snhl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kurapma on 1/14/17.
 */

public class GetBitmap extends AsyncTask<Void,Void,Void> {

    private Activity context;
    private String[] urls;
    private ProgressDialog loading;

    public GetBitmap(Activity context) {
        this.context = context;
    }

    public GetBitmap(Activity context, String[] urls){
        this.context = context;
        this.urls = urls;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context,"Loading","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        loading.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i=0; i<urls.length; i++){
            NewsConfig.bitmaps[i] = getImage(urls[i]);
        }
        return null;
    }

    public Bitmap getImage(String bitmapUrl){
        URL url;
        Bitmap image = null;
        try {
            url = new URL(bitmapUrl);
            InputStream in = url.openConnection().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in,1024*8);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len=0;
            byte[] buffer = new byte[1024];
            while((len = bis.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
            out.close();
            bis.close();

            byte[] data = out.toByteArray();
            image = BitmapFactory.decodeByteArray(data, 0, data.length);

        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
