
import java.util.LinkedList;

class QuestaoTrabGrafos {
    static final int qntVertices = 6;

    static boolean bfs(int grafo[][], int origem, int destino, int parente[]) {
        boolean retorno = false;
        boolean jaVisitado[] = new boolean[qntVertices];
        for (int i = 0; i < qntVertices; ++i)
            jaVisitado[i] = false;

        LinkedList<Integer> lista = new LinkedList<Integer>();
        lista.add(origem);
        jaVisitado[origem] = true;
        parente[origem] = -1;

        while (lista.size() != 0) {
            int u = lista.poll();

            for (int v = 0; v < qntVertices; v++) {
                if (jaVisitado[v] == false && grafo[u][v] > 0) {
                    if (v == destino) {
                        parente[v] = u;
                        retorno=true;
                    }
                    lista.add(v);
                    parente[v] = u;
                    jaVisitado[v] = true;
                }
            }
        }
        return retorno;
    }

    static void fordFulkersonAdaptado(int Grafo[][], int origem, int destino) {
        int u, v;

        int grafo[][] = new int[qntVertices][qntVertices];

        for (u = 0; u < qntVertices; u++){
            for (v = 0; v < qntVertices; v++){
                grafo[u][v] = Grafo[u][v];
            }
        }
                
        int parente[] = new int[qntVertices];
        int max_flow = 0;
        System.out.println("Caminhos ={\n");
        while (bfs(grafo, origem, destino, parente)) {
            int fluxoDeCaminho = Integer.MAX_VALUE;
            for (v = destino; v != origem; v = parente[v]) {
                u = parente[v];
                fluxoDeCaminho = Math.min(fluxoDeCaminho, grafo[u][v]);
            }
            for (v = destino; v != origem; v = parente[v]) {
                u = parente[v];
                grafo[u][v] -= fluxoDeCaminho;
                grafo[v][u] += fluxoDeCaminho;
                System.out.println(parente[v]);
            }
            System.out.println();
             max_flow += fluxoDeCaminho;
            
        }
        System.out.println("}\nFim caminhos.\n");
        System.out.println("Quantidade de caminhos = " + max_flow );
    }

    public static void main(String[] args) {
   
        int Grafo[][] = new int[][] {//priemiro grafo do latex
            //    a  b  c  d  e  f
                { 0, 1, 0, 1, 1, 0 }, // a
                { 0, 0, 1, 1, 1, 0 }, // b
                { 0, 0, 0, 0, 0, 1 }, // c
                { 0, 0, 1, 0, 0, 1 }, // d
                { 0, 0, 1, 0, 0, 1 }, // e
                { 0, 0, 0, 0, 0, 0 }  // f
        };
    
   
    //  int Grafo[][] = new int[][] { //segundo grafo do latex
    //         //    a  b  c  d  e  f
    //             { 0, 1, 1, 0, 1, 0 }, // a
    //             { 0, 0, 0, 1, 0, 0 }, // b
    //             { 0, 0, 0, 0, 0, 1 }, // c
    //             { 0, 0, 0, 0, 0, 1 }, // d
    //             { 0, 0, 0, 0, 0, 1 }, // e
    //             { 0, 0, 0, 0, 0, 0 }  // f
    //     };

     
    //  int Grafo[][] = new int[][] { // terceiro grafo do latex
    //         //    a  b  c  d  e  f
    //             { 0, 1, 1, 1, 0, 0 }, // a
    //             { 0, 0, 0, 0, 1, 1 }, // b
    //             { 0, 0, 0, 1, 0, 0 }, // c
    //             { 0, 0, 0, 0, 1, 0 }, // d
    //             { 0, 0, 0, 0, 0, 1 }, // e
    //             { 0, 0, 0, 0, 0, 0 }  // f
    //     };

        fordFulkersonAdaptado(Grafo, 0, 5);
    }
}
