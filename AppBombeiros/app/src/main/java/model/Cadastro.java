package model;


import java.util.HashMap;
import java.util.Map;

public class Cadastro {
    private Integer _id;
    private String nome_completo;
    private String datansc;
    private String cpf;
    private String nome_mae;


    public Cadastro(Integer id, String nomecompleto, String datansc, String cpf, String nome_mae){
        this._id=id;
        this.nome_completo=nomecompleto;
        this.datansc=datansc;
        this.cpf=cpf;
        this.nome_mae=nome_mae;

    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nomecompleto) {
        this.nome_completo = nomecompleto;
    }

    public String getDatansc() {
        return datansc;
    }

    public void setDatansc(String datansc) {
        this.datansc = datansc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }
}
