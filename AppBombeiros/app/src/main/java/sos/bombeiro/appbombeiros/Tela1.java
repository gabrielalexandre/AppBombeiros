package sos.bombeiro.appbombeiros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import dao.CadastroDAO;


public class Tela1 extends AppCompatActivity {

    private EditText ednome;
    private EditText ednsc;
    private EditText edcpf;
    private EditText edmae;
    private Button concluir;
    private Toolbar mToolbar;
    private CadastroDAO conecte;
    private static String cpf, nome, mae, nsc;

    public static Map<String, String> conectar(){
        //HashMap
        Map<String, String> mapa = new HashMap<>();
        mapa.put("cpf", cpf);
        mapa.put("nomeCidadao",nome);
        mapa.put("nomeMae",mae);
        mapa.put("dataNascimento", nsc);

        return mapa;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);



        mToolbar = (Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("S.O.S");
        mToolbar.setSubtitle("Cadastro");
        //mToolbar.setLogo(R.drawable.icon);
        setSupportActionBar(mToolbar);

        final EditText campo_data_nascimento = (EditText) findViewById(R.id.editnsc);
        //utilização de mascaras no campo data de nascimento
        campo_data_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_data_nascimento));
        final EditText campo_cpf = (EditText) findViewById(R.id.editcpf);
        //utilização de mascaras no campo de cpf
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        //transere o conteudo da tela para variáveies Java
        ednome = (EditText) findViewById(R.id.editNome);
        ednsc = (EditText) findViewById(R.id.editnsc);
        edcpf = (EditText) findViewById(R.id.editcpf);
        edmae = (EditText) findViewById(R.id.editmae);

        conecte = new CadastroDAO(this);

        //metodo para recuperar botao
        concluir = (Button) findViewById(R.id.concluir);
        concluir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //Variaveis tipo String para validação de vazio
                nome = ednome.getText().toString();
                nsc = ednsc.getText().toString();
                cpf = edcpf .getText().toString();
                mae = edmae.getText().toString();



                //variaveis tipo boolean para comparação e validação dos valores
                boolean cpf_valido = Validator.validateCPF(campo_cpf.getText().toString());
                boolean validar = true;

                //condições para validação
                if(nome == null || nome.equals("")){
                    validar = false;
                    ednome.setError(getString(R.string.error_nome));
                }
                if(nsc == null || nsc.equals("")){
                    validar = false;
                    ednsc.setError(getString(R.string.error_nsc));
                }
                if(cpf == null || cpf.equals("")){
                    validar = false;
                    edcpf.setError(getString(R.string.error_cpf1));
                }
                if(!cpf_valido){
                    edcpf.setError(getString(R.string.error_cpf2));
                    edcpf.setFocusable(true);
                    edcpf.requestFocus();
                }
                if(mae == null || mae.equals("")){
                    validar = false;
                    edmae.setError(getString(R.string.error_mae));
                }
                if(validar && cpf_valido){
                    if(conecte.logar(nome, nsc, cpf, mae)) {
                        Intent trocatela = new
                        Intent(Tela1.this, Tela2.class);
                        Tela1.this.startActivity(trocatela);
                        Tela1.this.finish();
                    }
                }
            }



        });
    }

}