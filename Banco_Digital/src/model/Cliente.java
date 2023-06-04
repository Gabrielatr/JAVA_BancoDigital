package model;

public class Cliente {
    public String nome;
    public String tipoConta;

    public Cliente(){
    }

    public Cliente(String nome, String tipo){
        this.nome = nome;
        this.tipoConta = tipo;
    }

    public void setTipoConta(String tipo){
        this.tipoConta = tipo;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public String getTipoConta(){
        return this.tipoConta;
    }

}
