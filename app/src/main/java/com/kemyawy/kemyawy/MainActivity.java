package com.kemyawy.kemyawy;

import android.Manifest;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kemyawy.kemyawy.Base.BaseActivity;


public class MainActivity extends BaseActivity {


    static WebView myWebView;
    // ProgressBar progressBar ;
   static CustomProgressBar customProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView) findViewById(R.id.mywebView);


        final CustomProgressBar customProgressBar;
        customProgressBar = new CustomProgressBar(this);


        if(!checkInternetConection()){
            myWebView.setBackgroundResource(R.drawable.offline);
            myWebView.setBackgroundColor(0x00000000);


        }else  {

// Load your Url

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            /**if(Build.VERSION.SDK_INT >17){
                myWebSettings.setMediaPlaybackRequiresUserGesture(false );

            }*/
              //myWebSettings.setMediaPlaybackRequiresUserGesture(false );

              webSettings.setAllowContentAccess(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.getUseWideViewPort();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setAllowContentAccess(true);
            webSettings.setEnableSmoothTransition(true);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setSupportZoom(false);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
            String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
            webSettings.setAppCachePath(appCachePath);
            webSettings.setAllowFileAccess(true);    // Readable file cache
            webSettings.setAppCacheEnabled(true);
             webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSupportMultipleWindows(true);
            webSettings.setPluginState(WebSettings.PluginState.ON);





















            myWebView.setWebViewClient(new WebViewClient());

            // progressBar.setVisibility(View.VISIBLE);
            myWebView.setBackgroundResource(R.color.white);

            customProgressBar.show();
            //
            myWebView.loadUrl("https://kemyawy22.blogspot.com");

            permissionsToRequest.add(Manifest.permission.RECORD_AUDIO);
            permissionsToRequest.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);




         myWebView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    myWebView.setBackgroundColor(0x00000000);
                    customProgressBar.dismiss();



                }
            });

        }









    }









    public boolean checkInternetConection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService
                (this.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected ;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()== KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK :
                    if(myWebView.canGoBack()){
                        myWebView.goBack();
                    }else{
                        finish();}
                    return true ;
            }

        }



        return super.onKeyDown(keyCode, event);
    }
}