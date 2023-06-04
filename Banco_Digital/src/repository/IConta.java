package repository;
public interface IConta {
	/* Operações comuns para ambas as contas */
	public void Saque(double montante);
	public void Deposito(double montante);
}
