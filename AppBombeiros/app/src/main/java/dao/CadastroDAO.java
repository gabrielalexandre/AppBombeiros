package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Cadastro;

/**
 * Created by Gabriel on 16/04/2016.
 */
public class CadastroDAO {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;


    public CadastroDAO(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database==null){
            database = dataBaseHelper.getWritableDatabase();
        }
        return database;
    }

    //inserir novos registros
    private Cadastro novoCadastro(Cursor cursor){
        Cadastro model = new Cadastro(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Cadastro._ID)),
                                      cursor.getString(cursor.getColumnIndex(DataBaseHelper.Cadastro.NOME)),
                                      cursor.getString(cursor.getColumnIndex(DataBaseHelper.Cadastro.DATANSC)),
                                      cursor.getString(cursor.getColumnIndex(DataBaseHelper.Cadastro.CPF)),
                                      cursor.getString(cursor.getColumnIndex(DataBaseHelper.Cadastro.NOMEMAE)));

        return model;
    }

    //listar cadastros ja no BD
    public List<Cadastro> listarCadastros(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Cadastro.TABELA, DataBaseHelper.Cadastro.COLUNAS, null,
                null, null, null, null);

        List<Cadastro> cadastros = new ArrayList<>();
            while(cursor.moveToNext()){
                Cadastro model = novoCadastro(cursor);
                cadastros.add(model);
            }
        cursor.close();
        return cadastros;
    }

    //salvar novos cadastros
    public long salvarCadastro(Cadastro cadastro){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Cadastro.NOME, cadastro.getNome_completo());
        valores.put(DataBaseHelper.Cadastro.DATANSC, cadastro.getDatansc());
        valores.put(DataBaseHelper.Cadastro.CPF, cadastro.getCpf());
        valores.put(DataBaseHelper.Cadastro.NOMEMAE, cadastro.getNome_mae());

        if(cadastro.get_id() != null){
            return getDatabase().update(DataBaseHelper.Cadastro.TABELA, valores, "_id = ?", new String[]{ cadastro.get_id().toString()});
        }

        return getDatabase().insert(DataBaseHelper.Cadastro.TABELA, null, valores);
    }
    public Cadastro buscarPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Cadastro.TABELA,
                DataBaseHelper.Cadastro.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null,
                null, null);
        if(cursor.moveToNext()){
            Cadastro model = novoCadastro(cursor);
            cursor.close();
            return model;
        }
    return null;

    }

    //metodo para ligaçao da tela com o BD
    public boolean logar(String nome, String nsc, String cpf, String mae){

        Cursor cursor = getDatabase().query(DataBaseHelper.Cadastro.TABELA, null, "nome_completo = ? AND datansc = ? AND cpf = ? AND nome_mae = ?", new String[]{nome, nsc,
                cpf, mae}, null, null, null);
        if(cursor.moveToFirst()){
            return false;
        }
        return true;

    }

    public void fechar(){
        dataBaseHelper.close();
        database = null;
    }


}
