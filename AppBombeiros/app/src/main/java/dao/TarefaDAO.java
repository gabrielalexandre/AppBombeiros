package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tarefa;


/**
 * Created by Gabriel on 16/04/2016.
 */
public class TarefaDAO {

    private DataBaseHelper databaseHelper;
    private SQLiteDatabase database;

    public TarefaDAO(Context context){
        databaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database==null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Tarefa novaTarefa (Cursor cursor){
        Tarefa model = new Tarefa(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Tarefa._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefa.TAREFA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefa.DT_CRIACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefa.DT_COMPLETADO))
        );

        return model;
    }

    public List<Tarefa> listarTarefa(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Cadastro.TABELA, DataBaseHelper.Cadastro.COLUNAS, null, null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<>();
        while(cursor.moveToNext()){
            Tarefa model = novaTarefa(cursor);
            tarefas.add(model);
        }
        cursor.close();
        return tarefas;
    }

    public long salvarTarefa(Tarefa tarefa){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Tarefa.TAREFA, tarefa.getTarefa());



        if(tarefa.get_id() != null){
            return getDatabase().update(DataBaseHelper.Tarefa.TABELA, valores, "_id = ?", new String[]{tarefa.get_id().toString()});
        }

        return getDatabase().insert(DataBaseHelper.Tarefa.TABELA, null, valores);
    }
    public Tarefa buscarTarefaPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Tarefa.TABELA,
                DataBaseHelper.Tarefa.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null,
                null, null);
        if(cursor.moveToNext()){
            Tarefa model = novaTarefa(cursor);
            cursor.close();
            return model;
        }
        return null;

    }

}
