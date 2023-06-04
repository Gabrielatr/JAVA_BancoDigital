import java.util.Random;
import java.util.Scanner;

import model.Cliente;
import model.Conta_Corrente;
import model.Conta_Poupanca;
import repository.Conta;


public class Main {
    

    public static int menu(Conta conta, Conta contaDestino, Cliente cliente, Scanner sc){
        int operacao = 1, resposta;
        double valor;

        while(true){
            System.out.println("\n--- Menu ---\n");
            System.out.println("1- Ver dados\n2- Operações\n3- Sair\n4- Logout\n");
            resposta = sc.nextInt();

            if(resposta == 1){
                System.out.println("\nNome: " + cliente.getNome() + "\nTipo de Conta: " + cliente.getTipoConta() + "\n");
                System.out.println("Número do banco: " + conta.getNumero());
                System.out.println("Saldo: " + conta.getSaldoAtual() + "\n");
                
            }else if(resposta == 2){
                while(true){
                    System.out.println("--- Operações ---\n\n");
                    System.out.println("1- Saque");
                    System.out.println("2- Depósito");
                    System.out.println("3- Transferência");
                    System.out.println("4- Empréstimo");
                    System.out.println("0- Sair");
                    operacao = sc.nextInt();

                    if(operacao == 0){ break; }

                    System.out.println("Insira o valor: ");
                    valor = sc.nextDouble();

                    switch(operacao){
                        case 1: conta.Saque(valor); break;
                        case 2: conta.Deposito(valor); break;
                        case 3:
                            conta.Transferencia(valor, contaDestino);
                            break;
                        case 4: 
                            System.out.println("Indique quantos meses: ");
                            int meses = sc.nextInt();
                            conta.Emprestimo(valor, meses);
                            break;
                        default: System.out.println("Valor inválido. Tente novamente.\n");
                    }
                }  
            }else{break;}
        }
        return resposta;
    }

    public static Conta criarConta(Scanner sc, Cliente cliente){
        Conta conta;
        int tipoConta, senhaCartao;
        String numeroConta = "", nome;
        Random gerador = new Random();

        System.out.println("\n--- Cadastro ---\n");

        System.out.println("Nome do Cliente: ");
            nome = sc.next();
            cliente.setNome(nome);

        System.out.println("Tipo de cartão: (0-Corrente, 1-Poupança)");
            tipoConta = sc.nextInt();

        System.out.println("Defina a senha da sua conta: ");
            senhaCartao = sc.nextInt();

        for(int i = 1; i<5; i++){
            numeroConta += String.valueOf(gerador.nextInt(9)+1);
        }

        System.out.println("O número da conta atribuído é: " + numeroConta);
        int nConta = Integer.valueOf(numeroConta);
        

        if(tipoConta == 0) 
        { cliente.setTipoConta("Conta Corrente"); conta = new Conta_Corrente(nConta, senhaCartao, cliente); }
        else
        { cliente.setTipoConta("Conta Poupanca"); conta = new Conta_Poupanca(nConta, senhaCartao, cliente); }
        
        return conta;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int resposta;
        int nConta, senhaConta, i=3;
        int iniciarNovamente = 0;

        Cliente cliente = new Cliente();
        Cliente clienteConta1 = new Cliente();
        Cliente clienteConta2 = new Cliente("Dani Ferreira", "Conta Corrente");
        Conta conta = new Conta();
        Conta contaDestino = new Conta_Corrente();
        Conta conta1 = new Conta();
        Conta conta2 = new Conta_Corrente(2134, 1234, clienteConta2, 500);

        while(iniciarNovamente != 3){
            /* INICIO DO PROGRAMA */
            System.out.println("\n--- Bem vindo ao Banco Digital! ---\n\n");
            System.out.println("Já é nosso cliente? (0-não, 1-sim)");
            resposta = sc.nextInt();

            if(resposta == 0){
                cliente = clienteConta1;
                conta = criarConta(sc, cliente);
                conta1=conta;
                contaDestino = conta2;
            }else{
                while(i!=0){
                    System.out.println("Número da conta: ");
                    nConta = sc.nextInt();
                    System.out.println("Senha: ");
                    senhaConta = sc.nextInt();

                    if(conta1.Autenticar(nConta, senhaConta)){
                        conta = conta1;
                        contaDestino = conta2;
                        cliente = clienteConta1;
                        System.out.println(
                            "Seja bem vindo novamente " + cliente.getNome() + "\n"
                        );
                        break;
                    }else if(conta2.Autenticar(nConta, senhaConta)){
                        conta = conta2;
                        contaDestino = conta1;
                        cliente = clienteConta2;
                        System.out.println(
                            "Seja bem vindo novamente " + cliente.getNome() + "\n"
                        );
                        break;
                    }else{
                        i--;
                        System.out.println("Erro ao autenticar, verifique as credenciais!\n");
                        System.out.println("Resta mais " + i + " tentativas.");
                    }
                }
            }

            if(i==0){
                System.out.println("Cartão bloqueado.");
            }else{
                iniciarNovamente = menu(conta, contaDestino, cliente, sc);
            }
        }
        System.out.println("\n--- Obrigado pela preferência. Boa continuação! ---");
    }
}
