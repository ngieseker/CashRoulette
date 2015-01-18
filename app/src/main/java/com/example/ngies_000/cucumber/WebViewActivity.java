package com.example.ngies_000.cucumber;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {
    private WebView webView;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        webView  = new WebView(this);

        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                description = "Failure";
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        webView .loadUrl("https://api.venmo.com/v1/oauth/authorize?client_id=2286&scope=make_payments&response_type=token");
        setContentView(webView);
        //Intent intent2 = new Intent(WebViewActivity.this, WebViewActivity.class);
        //startActivity(intent2);

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com");
        //String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
        //webView.loadData(customHtml, "text/html", "UTF-8");*/
    }
}
