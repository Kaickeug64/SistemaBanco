public class Conta {
    int id;
    String nome;
    float saldo;
    String senha;

    //status da conta , false = bloqueada (impossivel de fazer traslações)
    boolean status;


    void sacar(float saldo , float valor){
        if (saldo >= valor && this.status == true){
            saldo =- valor;
            System.out.println("Saque de "+valor+" realizado com sucesso!");
        }
        else if (this.status == false){
            System.out.println("Erro! Conta bloqueada");
        }
        else {
            System.out.println("Erro! Saldo insuficiente");
            this.status = false;
        }
    }
    void depositar(float saldo , float valor){
        if (valor >= 0 && this.status == true){
            System.out.println("Deposito aprovado e realisado");
        }
        else if (this.status == false){
            System.out.println("Erro! Conta bloqueada");
        }
        else {
            System.out.println("Erro! Valor de deposito invalido");
            this.status = false;
        }
    }
}
