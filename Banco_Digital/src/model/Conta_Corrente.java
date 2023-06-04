package model;

import repository.Conta;

public class Conta_Corrente extends Conta {
    
    public Conta_Corrente() {
    }

    public Conta_Corrente(int numeroConta, int senha, Cliente cliente){
        super.cliente = cliente;
        super.senhaConta = senha;
        super.numeroConta = numeroConta;
    }

    public Conta_Corrente(int numeroConta, int senhaConta, Cliente cliente, double saldo){
        super.cliente = cliente;
        super.senhaConta = senhaConta;
        super.numeroConta = numeroConta;
        super.saldo = saldo;
    }

 
}
