

import java.util.*;
class Ciclos_caminhamento {

    static final int N = 100000;

    static Vector<Integer>[] graph = new Vector[N];//lista de adjacencia para criação do grafo
    static Vector<Integer>[] cycles = new Vector[N];//lista de adjacencia para ciclos encontrados

    static int cyclenumber;//numero de ciclos no grafo
    static int operations = 0;//numero de operacoes realizadas

    static void dfs_cycle(int u, int p, int[] color,int[] mark, int[] par) {

        if (color[u] == 2) {

        }
        else if (color[u] == 1) {

            cyclenumber++;
            int cur = p;
            mark[cur] = cyclenumber;

            while (cur != u) {
                cur = par[cur];
                mark[cur] = cyclenumber;
            }

        }else {
            par[u] = p;

            color[u] = 1;

            for (int v : graph[u]) {


                if (v != par[u]) {
                    dfs_cycle(v, u, color, mark, par);
                }

            }

            color[u] = 2;
        }
    }


    static void addEdge(int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }


    static void printCycles(int edges, int[] mark) {


        for (int i = 1; i <= edges; i++) {
            if (mark[i] != 0)
                cycles[mark[i]].add(i);
        }
        for (int i = 1; i <= cyclenumber; i++) {
            System.out.printf("Cycle Number %d: ", i);
            for (int x : cycles[i])
                System.out.printf("%d ", x);
            System.out.println();
        }
    }
    public static void printAdjacencyList(Vector<Integer>[] graph){
        for(Vector g:graph){
            System.out.println();
            for (int h:g){
                System.out.print(h + "-> ");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < N; i++) {
            graph[i] = new Vector<>();
            cycles[i] = new Vector<>();
        }

        // Desenhar grafo exemplo
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 6);
        addEdge(4, 7);
        addEdge(5, 6);
        addEdge(3, 5);
        addEdge(7, 8);
        addEdge(6, 10);
        addEdge(5, 9);
        addEdge(10, 11);
        addEdge(11, 12);
        addEdge(11, 13);
        addEdge(12, 13);

        int[] color = new int[N];
        int[] par = new int[N];
        int[] mark = new int[N];

        cyclenumber = 0;
        int edges = 13;
        printAdjacencyList(graph);
        dfs_cycle(1, 0, color, mark, par);

        printCycles(edges, mark);
    }
}