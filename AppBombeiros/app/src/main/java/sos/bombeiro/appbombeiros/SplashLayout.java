package sos.bombeiro.appbombeiros;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Gabriel on 05/07/2016.
 */
public class SplashLayout extends ActionBarActivity {

    //variavel que define o tempo em que a Splash Screen será exibida
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashsactivity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //metodo para trocar de tela automaticamente após o tempo do SPLASH_TIME_OUT
                Intent i = new Intent(SplashLayout.this, Tela1.class);
                startActivity(i);

                finish();

            }
        },SPLASH_TIME_OUT);


    }
}