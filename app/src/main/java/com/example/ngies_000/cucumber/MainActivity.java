package com.example.ngies_000.cucumber;

        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.hardware.Sensor;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.apache.http.client.HttpClient;

        import java.net.URI;
        import java.util.Random;

public class MainActivity extends Activity {
    private Button button;
    private String limit;
    private int turn_count = 12;
    private int player_count = 0;
    private String player;


    /*public class ShakeEventManager implements SensorEventListener {
        public void init(Context ctx) {
            sManager = (SensorManager)  ctx.getSystemService(Context.SENSOR_SERVICE);
            s = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            register();
        }

        public void register() {
            sManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }*/

    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText top = (EditText) findViewById(R.id.max_pay);
        button = (Button) findViewById(R.id.buttonUrl);


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
              //  Intent intent = new Intent(context, WebViewActivity.class);
               // startActivity(intent);
                Random rand = new Random();
                turn_count -= rand.nextInt(3);
                player_count++;


                limit = String.valueOf(top.getText());
                //

                final Integer cent_limit = (int) (Double.parseDouble(limit) * 100);

                final Integer x = rand.nextInt(((cent_limit) + 1));
                //Toast.makeText(MainActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
                //int x = rand.nextInt((2* (Integer .valueOf(limit)))+1)-(Integer.valueOf(limit));
                final double amt = ((double) x) / 100;

                if ((player_count%2) == 0 ){
                    player ="Player One";
                }
                else{
                    player= "Player Two";
                }




                button.setText(player);
                final String url = "http://104.131.233.74:7000/cucumber/venmo?amount=" + amt + "&token=a908hu&to=teddy";//&from=";
                //final String url = "http://104.131.233.74:7000/cucumber/yo

                if (turn_count < 0)
                {
                Toast.makeText(MainActivity.this, player+" Wins! Markan Still pays Ted!", Toast.LENGTH_SHORT).show();
                turn_count = 1000;
                new Thread(new Runnable(){
                    @Override
                    public void run() {

                        try{
                            HttpClient httpclient = new DefaultHttpClient();

                            HttpGet request = new HttpGet();
                            URI website = new URI(url);
                            request.setURI(website);
                            httpclient.execute(request);

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }



                    }
                }).start();}
            }
        });
    }
}