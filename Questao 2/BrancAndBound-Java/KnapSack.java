import java.util.ArrayList;
import java.util.List;

public class KnapSack {

    // troca os valores de posição no array.
    static Item[] Swap(Item[] arr, int i, int j) {
        Item aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
        return arr;
    }

    // bubbleSort para ordenar o array de acordo com o valor/peso.
    static Item[] Sort(Item arr[], int n) {
        for (int i = (n - 1); i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((double) arr[j].getValue() / arr[j].getWeight() < (double) arr[j + 1].getValue()
                        / arr[j + 1].getWeight()) {
                    arr = Swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    static int Bound(Node u, int n, int W, Item arr[]) {

        // se o peso for maior que a capacidade do saco o retorno é 0.
        if (u.getWeight() >= W)
            return 0;

        // inicializa a variavel com o valor do valor de u.
        int profit_bound = u.getProfit();

        // começa incluindo +1 para o atual index de item
        int j = u.getLevel() + 1;
        int totWeight = (int) u.getWeight();

        // faz a checagem da condição do index e a capacidade da mochila
        while ((j < n) && (totWeight + arr[j].getWeight() <= W)) {
            totWeight += arr[j].getWeight();
            profit_bound += arr[j].getValue();
            j++;
        }

        if (j < n)
            profit_bound += (W - totWeight) * arr[j].getValue() / arr[j].getWeight();

        return profit_bound;
    }

    static int Mochila(int W, Item arr[], int n) {
        // ordena com base no valor por peso
        arr = Sort(arr, arr.length);

        // Lista para atravessar os nós e inicializa o nó u com os valores passados no
        // construtor
        List<Node> Q = new ArrayList<Node>();
        Node u = new Node(-1, 0, 0);
        Node v = new Node(0, 0, 0, 0);
        Q.add(u);

        // extrai os itens da arvore de decisão, computa o profit de cada filho do item
        // extraido e salva no profitMaximo
        int maxProfit = 0;
        while (!Q.isEmpty()) {
            // desenfila a lista
            u = Q.get(0); // ta errado aqui, encontrar o metodo certo
            Q.remove(0);

            // seta como 0 caso seja o nó de inicio
            if (u.getLevel() == -1)
                v.setLevel(0);

            // se não tiver nada no proximo nivel
            if (u.getLevel() == n - 1)
                continue;

            // Se não é o ultimo nó incrementa o level e computa o profit dos nós filhos
            v.setLevel(u.getLevel() + 1);

            // v pegando o peso com base na soma do peso do U com o peso do nó presente na
            // posição do level de v.
            v.setWeight(u.getWeight() + arr[v.getLevel()].getWeight());
            // v pegando o lucro com base na soma do lucro do U com o valor do nó presente
            // na posição do level de v.
            v.setProfit(u.getProfit() + arr[v.getLevel()].getValue());

            // maxProfit é atualizado caso o peso acumulado é menor que W e o lucro é maior
            // que o lucro anterior.
            if (v.getWeight() <= W && v.getProfit() > maxProfit)
                maxProfit = v.getProfit();

            // pegar o maior bound para decidir se adiciona v a lista de nós ou não
            v.setBound(Bound(v, n, W, arr));

            // se valor do bound for maior que o MaxProfit, adiciona os valores a um novo
            // array e o adiciona na lista de nós
            if (v.getBound() > maxProfit) {
                Node c = new Node(v.getLevel(), v.getProfit(), v.getBound(), v.getWeight());
                Q.add(c);
            }

            // adiciona os valores de u para v.
            v.setWeight(u.getWeight());
            v.setProfit(u.getProfit());

            // seta o maior Bound para V
            v.setBound(Bound(v, n, W, arr));

            // faz a mesma verificacao que a anterior
            if (v.getBound() > maxProfit) {
                Node c = new Node(v.getLevel(), v.getProfit(), v.getBound(), v.getWeight());
                Q.add(c);
            }

        }
        return maxProfit;

    }

    public static void main(String[] args) {
        double comecoTempo = System.currentTimeMillis();

        int W = 10; // Weight of knapsack

        Item a = new Item(2, 40);
        Item b = new Item((float) 3.14, 50);
        Item c = new Item(7, 300);
        Item d = new Item((float) 9.912, 500);
        Item e = new Item((float) 1.3123, 312);
        Item f = new Item((float) 3.131, 231);
        Item g = new Item((float) 0.91, 3);
        Item h = new Item((float) 1.98, 100);
        Item i = new Item((float) 5, 95);
        Item j = new Item(3, 30);

        Item arr[] = { a, b, c, d, e, f, g, h, i, j };
        int n = arr.length;
        System.out.println("MaxProfit = " + Mochila(W, arr, n));

        double fimTempo = System.currentTimeMillis();
        double tempoTotal = (fimTempo - comecoTempo) / 1000;// TimeUnit.MILLISECONDS.toSeconds(fimTempo - comecoTempo);
        System.out.println("Tempo = " + tempoTotal);
    }

}
