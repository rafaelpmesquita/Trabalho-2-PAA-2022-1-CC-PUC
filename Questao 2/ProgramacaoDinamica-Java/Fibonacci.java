

//estou usando long para receber o valor do fibonacci, caso queira um numero muito alto, o valor não vai funcionar por passar a faixa do long
public class Fibonacci{
    public static long fibonacci(int n){ 
        long memo[] = new long[n+1];
        for(int i = 0; i <= n; i++) memo[i] = -1;
        return fibonacci(n,memo);
    }

    public static long fibonacci(int n,long[] memo){
        if(memo[n]>=0)  return memo[n];
        if(n<=2) memo[n]=1;
        else memo[n] = fibonacci(n-1,memo) + fibonacci(n-2, memo);
        return memo[n];
    }
    
    public static void main(String[] args) {
        double comecoTempo = System.currentTimeMillis();

        System.out.println(fibonacci(3));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(30));
        
        double fimTempo = System.currentTimeMillis();
        double tempoTotal = (fimTempo - comecoTempo) / 1000;
        System.out.println("Tempo = " + tempoTotal);
    }
}