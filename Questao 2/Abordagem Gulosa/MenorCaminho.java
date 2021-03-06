public class MenorCaminho {

	public static int V = 9;

	static int DistanciaMinima(int distancia[], Boolean passo[])
	{
		int minimo = Integer.MAX_VALUE, tamanhoMinimo = -1;
		for (int v = 0; v < V; v++)
			if (passo[v] == false && distancia[v] <= minimo) {
				minimo = distancia[v];
				tamanhoMinimo = v;
			}

		return tamanhoMinimo;
	}

	static void PrintarSolucao(int distancia[])
	{
		System.out.println("Vertice - Distancia do primeiro:");
		for (int i = 0; i < V; i++)
			System.out.println(i + " \t\t " + distancia[i]);
	}

	static void Dijkstra(int grafo[][], int src)
	{
		int distancia[] = new int[V]; 
		Boolean passo[] = new Boolean[V];
		for (int i = 0; i < V; i++) {
			distancia[i] = Integer.MAX_VALUE;
			passo[i] = false;
		}
		distancia[src] = 0;
		for (int count = 0; count < V - 1; count++) {
			int u = DistanciaMinima(distancia, passo);
			passo[u] = true;
			for (int v = 0; v < V; v++)
				if (!passo[v] && grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE && distancia[u] + grafo[u][v] < distancia[v])
					distancia[v] = distancia[u] + grafo[u][v];
		}

		PrintarSolucao(distancia);
	}

	public static void main(String[] args)
	{
        double comecoTempo = System.currentTimeMillis();
		int grafo1[][] = new int[][] {
             { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
			 { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
	         { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
			 { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
			 { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
			 { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
			 { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
			 { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
			 { 0, 0, 2, 0, 0, 0, 6, 7, 0 } 
                                };
		Dijkstra(grafo1, 0);


        int grafo2[][] = new int[][] {//priemiro grafo do latex
            //    a  b  c  d  e  f
                { 0, 1, 0, 1, 1, 0 }, // a
                { 0, 0, 1, 1, 1, 0 }, // b
                { 0, 0, 0, 0, 0, 1 }, // c
                { 0, 0, 1, 0, 0, 1 }, // d
                { 0, 0, 1, 0, 0, 1 }, // e
                { 0, 0, 0, 0, 0, 0 }  // f
        };
        V=6;
		Dijkstra(grafo2, 0);

          int grafo3[][] = new int[][] { 
                    //    a  b  c  d  e  f
                        { 0, 1, 1, 1, 0, 0 }, // a
                        { 0, 0, 0, 0, 1, 1 }, // b
                        { 0, 0, 0, 1, 0, 0 }, // c
                        { 0, 0, 0, 0, 1, 0 }, // d
                        { 0, 0, 0, 0, 0, 1 }, // e
                        { 0, 0, 0, 0, 0, 0 }  // f
                };
		Dijkstra(grafo3, 0);

        
        double fimTempo = System.currentTimeMillis();
        double tempoTotal = (fimTempo - comecoTempo) / 1000;
        System.out.println("Tempo = " + tempoTotal);
	}
}
