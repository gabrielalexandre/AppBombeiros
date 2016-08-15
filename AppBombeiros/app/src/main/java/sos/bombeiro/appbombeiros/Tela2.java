package sos.bombeiro.appbombeiros;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import model.Cadastro;

public class Tela2 extends AppCompatActivity {
    private RadioButton emclinica, actransito, incendio;
    private EditText edlatitude, edlongitude;
    private Button sos;
    private String s;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);


        sos = (Button) findViewById(R.id.sos);
        sos.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        HttpHelper enviaDados = new HttpHelper();
                        try {
                            s = enviaDados.doPost("http://10.0.2.2:8080/ExemploWebService/rest/ocorrencia/todas", Tela1.conectar(), "UTF-8");
                            GPS gps = new GPS(null);
                            gps.ativaGPS();
                        } catch (IOException e) {

                        }
                    }
                }.start();
                Toast.makeText(getApplicationContext(), "Ocorrencia enviada.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}









