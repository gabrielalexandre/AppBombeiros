package sos.bombeiro.appbombeiros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.Override;






public class Tela3 extends AppCompatActivity {

    ImageView bt_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);
        bt_voltar = (ImageView) findViewById(R.id.bt_voltar);

            bt_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent trocatela = new Intent(Tela3.this, Tela2.class);
                    Tela3.this.startActivity(trocatela);
                    Tela3.this.finish();

                }

            });
        }

    }

