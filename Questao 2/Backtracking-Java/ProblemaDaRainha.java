import java.util.ArrayList;
import java.util.List;

public class ProblemaDaRainha {
    //  Esse metodo é para percorrer o tabuleiro final
    //  e salvar em uma lista de tabuleiros
    public static void SalvarTabuleiro(String[][] tabuleiro, List<List<String>> todostabuleiros) {
        String fileira = ""; 
        List<String> novoTabuleiro = new ArrayList<>();
       
        for(int i=0; i<tabuleiro.length; i++) {
            fileira = "";
            for(int j=0; j<tabuleiro[0].length; j++) {
                if(tabuleiro[i][j] == " R ")
                    fileira += " R ";
                else
                    fileira += " . ";
            }
            novoTabuleiro.add(fileira);
        }
       
        todostabuleiros.add(novoTabuleiro);
    }
    
    //   .   .   .
    //   .   R   .
    //   .   .   .
    //  Não pode existir nenhuma Rainha aonde a Rainha pode 
    //  realizar seus movimentos (horizontal, vertical, diagonais)
    //  No caso do tabuleiro acima, não pode existir em nenhum lugar aonde tem "."
    public static boolean ProcuraRainhas(int fileira, int coluna, String[][] tabuleiro) {
        //horizontal
        for(int j=0; j<tabuleiro.length; j++) 
            if(tabuleiro[fileira][j] == " R ") return false;
        
        //vertical
        for(int i=0; i<tabuleiro.length; i++) 
            if(tabuleiro[i][coluna] == " R ") return false;
       
        //diagonal cima esquerda
        int f = fileira;
        for(int c=coluna; c>=0 && f>=0; c--, f--)
            if(tabuleiro[f][c] == " R ") return false;
       
        //diagonal cima direita
        f = fileira;
        for(int c=coluna; c<tabuleiro.length && f>=0; f--, c++) 
            if(tabuleiro[f][c] == " R ") return false;
       
        //diagonal baixo esquerda
        f = fileira;
        for(int c=coluna; c>=0 && f<tabuleiro.length; f++, c--) 
            if(tabuleiro[f][c] == " R ") return false;
            
        //diagonal baixo direita
        for(int c=coluna; c<tabuleiro.length && f<tabuleiro.length; c++, f++) 
            if(tabuleiro[f][c] == " R ") return false;
       
            
        return true;
    }
   

   // Método ResolverProblemaRainhas é para chamar o metodo ProcuraRainhas
   // e adicionar Rainhas e espaços vazios
    public static void ResolverProblemaRainhas(String[][] tabuleiro, List<List<String>> todostabuleiros, int coluna) {
        if(coluna == tabuleiro.length) { // caso a coluna seja do tamanho do tabuleiro, salva o tabuleiro e retorna
            SalvarTabuleiro(tabuleiro, todostabuleiros);
            return;
        }
       
        for(int fileira=0; fileira < tabuleiro.length; fileira++) { // faz um loop em cada fileira, procurando passar em todos espaços do tabuleiro, caso não exista uma rainha no "range" de uma outra rainha, uma rainha é adicionada e é feita uma chamada recursiva(indo pra proxima coluna) , após isso é adicionado "." aonde não existe rainha.
            if(ProcuraRainhas(fileira, coluna, tabuleiro)) {
                tabuleiro[fileira][coluna] = " R ";
                ResolverProblemaRainhas(tabuleiro, todostabuleiros, coluna+1);
                tabuleiro[fileira][coluna] = " . ";
            }
        }
    }
   
    public static void main(String[] args) {
        int tamTabuleiro = 8; 
        List<List<String>> todosTabuleiros = new ArrayList<>();

        ResolverProblemaRainhas(new String[tamTabuleiro][tamTabuleiro], todosTabuleiros, 0);
        
        for(List<String> a : todosTabuleiros) {
            System.out.println("Tabuleiro:");
            for(String b : a){
                System.out.println(b);
            }
            System.out.println();
        }
    }
 }
 