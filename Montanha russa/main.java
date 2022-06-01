import static java.lang.Math.floor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        Passageiros passag = new Passageiros();
        Carros carros = new Carros();

        System.out.println("Entre com uma quantidade de pessoas (Capacidade maxima 100): ");
        passag.n = ler.nextInt();

        System.out.println("Entre com a quantidade de carros(minimo 1 // maximo 8): ");
        carros.C = ler.nextInt();

        System.out.println("Entre com a quantidade de lugares no carrinho: ");
        carros.L = ler.nextInt();

        System.out.println("Entre com o tempo de Embarque/Desembarque: ");
        passag.Te = ler.nextInt();

        System.out.println("Entre com o tempo do Passeio da Montanha Russa: ");
        passag.Tp = ler.nextInt();

     

        int ca = carros.C;

       
     
        Semaphore sem = new Semaphore(1);
        //Switch Case- O Switch Case está aqui para controlar quantas threads(carrinhos) serão criados;
        switch (ca) {
            case 1:
                ThreadCarro t1 = new ThreadCarro("Carrinho1", "embarque", sem);
                break;
            case 2:
                ThreadCarro t2 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t3 = new ThreadCarro("Carrinho2", "embarque", sem);
                break;
            case 3:
                ThreadCarro t37 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t38 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t39 = new ThreadCarro("Carrinho2", "embarque", sem);
                break;
            case 4:
                ThreadCarro t7 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t8 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t9 = new ThreadCarro("Carrinho3", "embarque", sem);
                ThreadCarro t10 = new ThreadCarro("Carrinho4", "embarque", sem);
                break;
            case 5:
                ThreadCarro t11 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t12 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t13 = new ThreadCarro("Carrinho3", "embarque", sem);
                ThreadCarro t14 = new ThreadCarro("Carrinho4", "embarque", sem);
                ThreadCarro t15 = new ThreadCarro("Carrinho5", "embarque", sem);
                break;
            case 6:
                ThreadCarro t16 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t17 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t18 = new ThreadCarro("Carrinho3", "embarque", sem);
                ThreadCarro t19 = new ThreadCarro("Carrinho4", "embarque", sem);
                ThreadCarro t20 = new ThreadCarro("Carrinho5", "embarque", sem);
                ThreadCarro t21 = new ThreadCarro("Carrinho6", "embarque", sem);
                break;
            case 7:
                ThreadCarro t22 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t23 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t24 = new ThreadCarro("Carrinho3", "embarque", sem);
                ThreadCarro t25 = new ThreadCarro("Carrinho4", "embarque", sem);
                ThreadCarro t26 = new ThreadCarro("Carrinho5", "embarque", sem);
                ThreadCarro t27 = new ThreadCarro("Carrinho6", "embarque", sem);
                ThreadCarro t28 = new ThreadCarro("Carrinho7", "embarque", sem);
                break;
            case 8:
                ThreadCarro t29 = new ThreadCarro("Carrinho1", "embarque", sem);
                ThreadCarro t30 = new ThreadCarro("Carrinho2", "embarque", sem);
                ThreadCarro t31 = new ThreadCarro("Carrinho3", "embarque", sem);
                ThreadCarro t32 = new ThreadCarro("Carrinho4", "embarque", sem);
                ThreadCarro t33 = new ThreadCarro("Carrinho5", "embarque", sem);
                ThreadCarro t34 = new ThreadCarro("Carrinho6", "embarque", sem);
                ThreadCarro t35 = new ThreadCarro("Carrinho7", "embarque", sem);
                ThreadCarro t36 = new ThreadCarro("Carrinho8", "embarque", sem);
                break;

        }
        
        while (true) {
            if (passag.n <= 100) {//Controle da Fila, aqui definimos quantas pessoas poderam chegar na Fila, nunca passará acima de 110

                Random aleatorio = new Random();
                int valor = aleatorio.nextInt(30);//Tempo aleatório para chegada de novas pessoas na fila(vai de 0 ate 30 segundos)
                int PR = aleatorio.nextInt(5);//Quantidade de pessoas que chegaram na fila, após o tempo aleatório(vai de 0 ate 5 pessoas
               
                passag.Tr = valor;

                try {
                    // Ao fim do tempo aleatório é apresentado na tela, a quantidade de pessoas na fila
                	//quantidade de carrinhos cheios e a quantidade de carros selecionada

                    TimeUnit.SECONDS.sleep(passag.Tr);
                    passag.n = passag.n + PR;
                    if(passag.n<0) {
                    	System.out.println(" -----------------");
                    	System.out.println("Agurdando " + (passag.n*-1)+(" para encher o carrinho"));
                    }else {
                    	System.out.println("-------------- ");
                    System.out.println("Fila atualmente " + passag.n);}
                    // quando o número ficar negativo, o ultimo carrinho que não foi preenchido totalmene, irá esperar, preencher todos os lugares para sair.
                    

                    
                    System.out.println("Carrinhos cheios = " + passag.carrinhoCheio);
                    System.out.println("Quantidade de carros = " + carros.C);
                    System.out.println("-------------------- ");
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }

    }

}
