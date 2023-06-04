package model;

import repository.Conta;

public class Conta_Poupanca extends Conta {

    public Conta_Poupanca(){}
    
    public Conta_Poupanca(int numeroConta, int senha, Cliente cliente){
        super.cliente = cliente;
        super.senhaConta = senha;
        super.numeroConta = numeroConta;
    }

    public Conta_Poupanca(int numeroConta, int senhaConta, Cliente cliente, double saldo){
        super.cliente = cliente;
        super.senhaConta = senhaConta;
        super.numeroConta = numeroConta;
        super.saldo = saldo;
    }

    
}
