import java.util.Random;

/**
 * Created by Rafael on 23/02/16.
 */
public class Teste {
    public static void main(String[] args)
    {
        Random gerador = new Random();

        for(int i = 0; i <= 100000; i++){
            if((gerador.nextInt(100)/10 - 5) == 5){
                System.out.println("foi");
            }
        }
    }
}
