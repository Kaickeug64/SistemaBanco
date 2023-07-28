import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int tam = 100;

        boolean idverificador[] = new boolean[tam];
        for (int i = 0; i < tam; i++) {
            idverificador[i] = false;
            //sinalizando que niguem usou essa conta ainda
        }

        Conta cliente[] = new Conta[tam];
        int x, cont = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Digite o número da operação : ");
            System.out.println("0 - Enserrar");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Loguin");
            System.out.println("3 - Desbloquear conta");
            x = sc.nextInt();

            switch (x) {
                case 0:
                    System.out.println("Obrigado por testar o sistema!");
                    break;

                case 1: {
                    boolean teste = false;

                    System.out.print("Digite seu nome : ");
                    cliente[cont] = new Conta();
                    cliente[cont].status = true;
                    cliente[cont].nome = sc.next();
                    cliente[cont].id = newid(idverificador, cont, tam);
                    do {
                        System.out.print("Digite uma senha : ");
                        cliente[cont].senha = sc.next();
                        teste = testesenha(cliente[cont].senha);
                    } while (!teste);
                    System.out.println(cliente[cont].nome + " seu id é : " + cliente[cont].id);
                    cont++;
                }
                break;
                case 2: {
                    System.out.println("Digite seu id : ");
                    int idloguin = sc.nextInt();
                    int posicao = idposi(idloguin, cliente);
                    if (posicao > -1 && cliente[posicao].status) {
                        int erros = 0;
                        do {
                            if (erros > 0)
                                System.out.println("apenas " + (3 - erros) + " tentativas restantes");
                            System.out.print("Digite a senha : ");
                            String Senha = sc.next();
                            if (cliente[posicao].senha.equals(Senha)) {
                                int op=4;
                                System.out.println("Loguin realizado");
                                System.out.println("Seja bem vindo " + cliente[posicao].nome);
                                do {
                                    System.out.println("Qual a operação desejada ?");
                                    System.out.println("1 - consultar saldo");
                                    System.out.println("2 - Depositar");
                                    System.out.println("3 - Sacar");
                                    System.out.println("4 - deslogar");
                                    op = sc.nextInt();
                                    switch (op){
                                        case 1 : {
                                            System.out.println(cliente[posicao].nome+" seu saldo é : "+cliente[posicao].saldo);
                                        }break;
                                        case 2 : {
                                            System.out.println("Qual o valor do deposito ? ");
                                            float deposito = sc.nextFloat();
                                            cliente[posicao].depositar(cliente[posicao].saldo,deposito);
                                        }break;
                                        case 3:{
                                            System.out.println("Qual o valor do saque ? ");
                                            float saque = sc.nextFloat();
                                            cliente[posicao].sacar(cliente[posicao].saldo,saque);
                                        }break;
                                        case 4 :
                                            System.out.println(cliente[posicao].nome+" obtigado por usar o sistema!");
                                    }
                                }while(op != 4);
                                break;
                            } else
                                erros++;
                            if (erros == 3) {
                                cliente[posicao].status = false;
                                System.out.println("A conta " + idloguin + " foi bloqueada");
                            }
                        } while (erros < 3);
                    } else if (cliente[posicao] != null && !cliente[posicao].status)
                        System.out.println("Conta bloqueada! Loguin negado");
                    else {
                        System.out.println("Erro!Id ivalido");
                    }
                    System.out.println();
                }
                break;

                case 3: {
                    System.out.print("Digite seu id : ");
                    int idloguin = sc.nextInt();
                    int posicao = idposi(idloguin, cliente);
                    posicao = idposi(idloguin, cliente);

                    if (posicao > -1 && !cliente[posicao].status) {
                        int erros = 0;
                        do {
                            if (erros > 0)
                                System.out.println("apenas " + (3 - erros) + " tentativas restantes");
                            System.out.println("Digite a senha de ADM : ");
                            String senhaadm = sc.next();
                            if (senhaadm.equals("kaickcraft13")) {
                                System.out.println("Acesso liberado!");
                                System.out.print("Deseja desbloquear a conta de " + cliente[posicao].nome + "? (formato 'sim' ou 'não')");
                                String resp = sc.next();
                                if (resp.equals("sim")) {
                                    cliente[posicao].status = true;
                                    System.out.println("Conta desbloqueada");
                                    System.out.println("Deseja alterar a senha ?");
                                    resp = sc.next();

                                    boolean teste;
                                    if (resp.equals("sim")) {
                                        do {
                                            System.out.print("Digite uma senha : ");
                                            cliente[posicao].senha = sc.next();
                                            teste = testesenha(cliente[cont].senha);
                                        } while (!teste);
                                        System.out.println("Senha alterada com sucesso");
                                    }
                                    break;
                                }

                            }
                        else
                            erros++;
                        } while (erros < 3);
                    } else
                        System.out.println("Id invalido!");
                }
                break;

            }

        }while (x != 0) ;
    }
        public static int newid ( boolean vtr[], int cont, int tam){
            int aleatorio;
            if (cont <= tam) {
                do {
                    Random rdm = new Random();
                    aleatorio = rdm.nextInt(vtr.length - 1) + 1;
                } while (vtr[aleatorio] == true);
                vtr[aleatorio] = true;
                return aleatorio;
            } else {
                System.out.println("Todos os id's preenchidos!");
                return 0;
            }
        }
        public static boolean testesenha (String senha){
            boolean letra = false, numero = false, espaco = false, tamanho = false;
            for (char c : senha.toCharArray()) {
                if (Character.isDigit(c)) {
                    numero = true;
                } else if (Character.isLetter(c)) {
                    letra = true;
                } else if (Character.isWhitespace(c)) {
                    espaco = true;
                }
            }
            if (letra && numero && !espaco && senha.length() >= 8)
                return true;
            else {
                System.out.println("A senha deve conter pelo menos 1 número uma letra.");
                System.out.println("A senha deve conter no mínimo 8 carácteres e não pode conter espaços");
                return false;
            }
        }

        public static int idposi ( int idloguin, Conta[] cliente ){
            int posicao = -1;
            for (int i = 0; i < cliente.length; i++) {
                if (cliente[i] != null && cliente[i].id == idloguin) {
                    posicao = i;
                    break;
                }
            }
            return posicao;
        }

}