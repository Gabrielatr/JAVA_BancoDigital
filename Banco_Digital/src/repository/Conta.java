package repository;

import model.Cliente;
import service.ParcelamentoInvalidoException;
import service.SaldoInsuficienteException;

public class Conta implements IConta {
	protected int numeroConta;
    protected int senhaConta;
    protected Cliente cliente;
	protected double saldo;

    /* Construtor */

    public Conta(){}

    public boolean Autenticar(int numeroConta, int senha){
        if(this.numeroConta == numeroConta && this.senhaConta == senha){
            System.out.println("Autenticação realizada com sucesso!\n");
            return true;
        }else{
            return false;
        }
    }

    public double getSaldoAtual(){
        return this.saldo;
    };

    public int getNumero(){
        return this.numeroConta;
    };

    public void setNomeCliente(String nome){
        this.cliente.nome = nome;
    }

    /* Operações */

    @Override
	public void Saque(double valor) {
        try{
            VerificarSaldo(valor);
            this.saldo -= valor;
        }catch(SaldoInsuficienteException erroSaldo){
            System.out.println("Saldo insuficiente");
        }
	}
    
	@Override
	public void Deposito(double valor) {
		this.saldo += valor;
	}

	public void Emprestimo(double valor, int meses) {
        double taxa = 0;

        if(meses > 3){ taxa = (meses*0.01); }
        
        try {
            if(VerificarEmprestimo(valor+taxa, meses)){
                this.Saque(valor+taxa);
            };
		}catch (ParcelamentoInvalidoException ex){
			System.out.println("Valor inválido para parcelamento!");
		}

	}

    public void Transferencia(double valor, Conta destino) {
        double taxa;

        if(valor > 100)                       { taxa = 0.02; } //2%
        else if(valor > 500 && valor < 1000)  { taxa = 0.05; } //5%
        else                                  { taxa = 0.15; } //15%

		this.Saque(valor+taxa);
		destino.Deposito(valor);
	}

    /* Exceções */

    public void VerificarSaldo(double valor) throws SaldoInsuficienteException{
        if(valor > this.saldo){
            throw new SaldoInsuficienteException();
        }
    }

    public Boolean VerificarEmprestimo(double valor, int parcelas) throws ParcelamentoInvalidoException{
        //Verifica se o valor é inferior a 150 e se as parcelas são menores que 50 euros por mes
        if (valor < 150 || (valor/parcelas) < 50) {
            throw new ParcelamentoInvalidoException();
        }
        return true;
    }
}
