package com.kemyawy.kemyawy;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;

import static com.kemyawy.kemyawy.MainActivity.customProgressBar;

public abstract class  myWebViewClient extends WebViewClient {

    public MediaPlayer mp;
    private Context context = null;

    public myWebViewClient(Context context) {
        this.context = context;
        mp = new MediaPlayer();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {


        if (url.endsWith(".mp3")) {
            url = url.replace("file:///android_asset/webpages/", "");
            try {
                AssetFileDescriptor afd = context.getAssets().openFd(url);
                mp = new MediaPlayer();
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mp.prepare();
                mp.start();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }
        return true;
    }
}

