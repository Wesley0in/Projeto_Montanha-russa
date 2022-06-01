import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadCarro implements Runnable {
    Semaphore sem;
    private String nome;//nome da thread
    private String ED;//
    Thread t;//objeto da thread

    private static Passageiros passag = new Passageiros();//objeto de passageiros
    private static Carros carros = new Carros();//objeto de carros

    public ThreadCarro(String nome, String ED, Semaphore sem) {
        this.nome = nome;//nome da thread
        this.ED = ED;
        this.sem = sem;//semáforo
        t = new Thread(this, nome);//parametro da thread
        t.start();//inicialização da thread

    }

    public void setED(String ED) {
        this.ED = ED;
    }

    public String getED() {
        return ED;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    private int teste;

    @Override
    public void run() {

        // System.out.println(this.nome + "comecou");
        int aux = 1;
        int aux2 = 0;// garante a saida dos passageiros com o carrinho cheio
        int viagens = 1;
        int min = 0;
        int max = 0;
        long calcmin = 0;
        long auxcalcmin = 500000000;
        long calcmax = 0;
        long auxcalcmax = 0;
        long TMV = 0;
        long TT = 0;
        long TU;
        long somamedio=0;//armazena as medias anteriores
        // int calcmin=0;
        // int calcmax=0;
        int tmin;
        int result = 0;
        
        while (aux==1) {
            while (true) {//loop dos scripts/ para sempre se reiniciarem

              

                switch (ED) {// o switch case foi utilizado para pegarmos o parametro "embarque" e inicializarmos o codigo
                    case "embarque":
                        long tempototal = System.currentTimeMillis();// tempo total do script
                        long minimo = System.currentTimeMillis();//tempo minimo na fila
                        long maximo = System.currentTimeMillis();//tempo maximo na fila
                        long medio = System.currentTimeMillis();//tempo medio na fila
                        
                        //cada carrinho tem o seu tempo minimo,maximo e medio

                        if (passag.n >= carros.L) {//se o numero de pessoas na fila for maior que os lugares, então existem pessoas na fila
                            

                            try {//aqui temos um semaforo para um controle dos carrinhos e do preenchimento dos lugares dentro dos carrinhos

                              //  System.out.println(t.getName() + " está solicitando autorizaçao");//thread que chegou depois, pedindoa acesso/
                                sem.acquire();

                               // System.out.println(t.getName() + " obteve autorizaçao");//chegou primeiro
                                System.out.println();

                                Thread.sleep(500);

                                if (carros.C > passag.carrinhoCheio) {//se a quantidade de carros for maior que a quantidade de carrinhos cheios/se inicia a viagem
                                    viagens = viagens + 1;
                                    calcmin = System.currentTimeMillis() - minimo;//calculo do tempo minimo
                                    if (calcmin < auxcalcmin && calcmin > 0) {
                                        auxcalcmin = calcmin;//menor tempo de todos
                                       
                                    }
                                    System.out.println("Embarcando carrinho: " + t.getName());

                                    try {
                                        // System.out.println(passag.Te);
                                        TimeUnit.SECONDS.sleep(passag.Te);//Tempo de embarque
                                        
                                    } catch (InterruptedException ie) {
                                        Thread.currentThread().interrupt();
                                    }
                                  

                                    passag.n = passag.n - carros.L;//carrinho preenchido a fila-lugares
                                    //Validação da fila
                                    while (passag.n < carros.L) {

                                        try {
                                        	//System.out.println("Aguardando passageiros");
                                           // se o numero de passageiros for menor que o numero de lugares no carrinho, fica preso aqui ate a fila encher o proximo carrinho
                                            TimeUnit.SECONDS.sleep(15);

                                        } catch (InterruptedException ie) {
                                            Thread.currentThread().interrupt();
                                        }
                                    }
                                   
                                    passag.carrinhoCheio = passag.carrinhoCheio + 1;
                                    
                                   

                                }
                            } catch (InterruptedException e) {
                               
                                e.printStackTrace();
                            }
                           
                           
                            // zona critica
                            if (passag.carrinhoCheio <= carros.C && passag.carrinhoCheio >-1) {//validação para verificar se o carrinho está cheio
                            	 //validação da fila
                            	while (passag.n < carros.L) {

                                      try {
                                      //	System.out.println("Aguardando passageiros");
                                         // se o numero de passageiros for menor que o numero de lugares no carrinho, fica preso aqui ate a fila encher o proximo carrinho
                                          TimeUnit.SECONDS.sleep(15);

                                      } catch (InterruptedException ie) {
                                          Thread.currentThread().interrupt();
                                      }
                                  }
                            	 sem.release();
                                System.out.println(t.getName()+" cheio, se segure, vamos iniciar**");
                                // aqui que tem q ficar o semaforo, acha que da?
                                //System.out.println(t.getName() + " liberou recurso");
                               
                               
                                    //System.out.println(t.getName() + " solicitando autorizaçao para iniciar o passeio");
                                   // sem.acquire();
                                   // System.out.println(t.getName() + " obteve autorizaçao para iniciar o passeio");
                                    

                                    //Thread.sleep(5);
                                    
                                    try {
                                       
                                    	long tempomovimentando = System.currentTimeMillis();//carrinho começa a se mover
                                        TimeUnit.SECONDS.sleep(passag.Tp);// passeando
                                        System.out.println(t.getName() + " Fim do passeio, desembarcando");
                                        TMV = (System.currentTimeMillis() - tempomovimentando);
                                        
                                       
                                        try {
                                            // System.out.println(passag.Te);
                                            TimeUnit.SECONDS.sleep(passag.Te);//desembarcando passageiros
                                            passag.carrinhoCheio = passag.carrinhoCheio - 1;//carrinho esvaziado
                                        } catch (InterruptedException ie) {
                                            Thread.currentThread().interrupt();
                                            
                                        }

                                       
                                    } catch (InterruptedException ie) {
                                        Thread.currentThread().interrupt();

                                    }
                                    System.out.println(t.getName() + " voltou para o inicio");//finalizou a viagem
                                  //  sem.release();
                                    System.out.println(" ");
                                    System.out.println("% Tempos %");
                                
                                System.out.println("Tempo minimo de fila para o carrinho: " + t.getName() + " e " + (auxcalcmin / 1000)% 60);
                                // auxcalcmax = calcmax;
                                calcmax = System.currentTimeMillis() - maximo;//calculo do tempo maximo na fila
                                if (calcmax > auxcalcmax) {
                                    auxcalcmax = calcmax;//maior tempo de todos na fila

                                }

                                System.out.println("Tempo maximo de fila para o carrinho: " + t.getName() + " e " + (auxcalcmax / 1000)% 60);
                                System.out.println("Tempo medio de fila para o carrinho: " + t.getName() + " e " + (medio / 1000)% 60);
                                
                                medio=(medio/ 1000)% 60;
                                medio = (medio+somamedio) / viagens;//media de tempo na fila
                                somamedio=somamedio+medio;
                                //medio=medio/1000;
                                
                                // Fim do tempo total/
                                TT = (System.currentTimeMillis() - tempototal);
                                
                                System.out.println(t.getName() + " O tempo de utilizaçao deste carrinho e " + (TT / TMV));//Tempo total divido pelo Tempo movimentado
                                System.out.println("% Tempos %");
                                System.out.println(" ");
                                sem.release();//libera o proximo
                            }
                        }

                        
                        
                        
                        while (passag.n < carros.L) {

                            try {
                            	System.out.println("Aguardando passageiros");
                               // se o numero de passageiros for menor que o numero de lugares no carrinho, fica preso aqui ate a fila encher o primeiro carrinho
                                TimeUnit.SECONDS.sleep(15);

                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    
                
                }

            }}
        }

       
    

 
}
