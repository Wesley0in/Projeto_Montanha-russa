/*import java.util.Timer;
import java.util.TimerTask;*/

public class Passageiros {
    public static int n;//número de passageiros
    public static int carrinhoCheio = 0;
    int Tr;/* Random */
    public static int Te;//Tempo de embarque
    public static int Tp;//Tempo de passeio

    public Passageiros() {

    }

    /*
     * public Passageiros(int seconds) {
     * Tp = new Timer();
     * Tp.schedule(new RemindTask(), seconds*1000);
     * 
     * }
     */

    /*
     * public Passageiros(int Tp, int Te){
     * this.Te = Te;
     * this.Tp = Tp;
     * 
     * }
     */
    public int getN() {
        return n;
    }

    public int getTp() {
        return Tp;
    }

    public int getTe() {
        return Te;
    }

    public int getTm() {
        return Tr;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setTm(int Tm) {
        this.Tr = Tm;
    }

    public void setTp(int Tp) {
        this.Tp = Tp;
    }

    public void setTe(int Te) {
        this.Te = Te;
    }
}
