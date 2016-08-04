package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel on 18/04/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tasks";
    private static final int VERSAO = 1;


    public DataBaseHelper(Context context){
        super(context, BANCO_DADOS,null, VERSAO);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tabela de cadastro
        db.execSQL("CREATE TABLE cadastro(_id integer primary key autoincrement, " +
                "nome_completo text not null, datansc date not null, cpf text not null," +
                "nome_mae text not null)");

        //tabela de atualizaçao de registros
        db.execSQL("CREATE TABLE tarefas(_id integer primary key autoincrement, " +
                "tarefa text not null, dt_criacao datetime default current_datetime, dt_completo date)");


        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //transferencia das variaveis sql para java
    public static class Cadastro{
        public static final String TABELA = "cadastro";
        public static final String _ID = "_id";
        public static final String NOME= "nome_completo";
        public static final String DATANSC = "datansc";
        public static final String CPF= "cpf";
        public static final String NOMEMAE = "nome_mae";



        public static final String[] COLUNAS = new String[] {
                _ID,NOME,DATANSC, CPF, NOMEMAE
        };
    }
    //idem
    public static class Tarefa{
        public static final String TABELA = "tarefas";
        public static final String _ID = "_id";
        public static final String TAREFA = "tarefa";
        public static final String DT_CRIACAO = "dt_criacao";
        public static final String DT_COMPLETADO = "dt_completo";


        public static final String[] COLUNAS = new String[]{
                _ID, TAREFA, DT_CRIACAO, DT_COMPLETADO
        };

    }

}