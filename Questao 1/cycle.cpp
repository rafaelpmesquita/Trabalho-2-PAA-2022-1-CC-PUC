#include <bits/stdc++.h>
#include <algorithm>
#include <iostream>
#include <sstream>
using namespace std;
#define p(x) cout << #x << " is " << (x) << " on Line:" << __LINE__ << " \n";
#define pl(x)             \
    for (auto &y : x)     \
        cout << y << " "; \
    cout << "\n";

void dfs(int src, int parent, vector<int> &stack, int visited_nodes[], vector<vector<int>> &graph)
{

    // status: 0 - ainda não visitado; 1 - visitado; 2 - visitado e analisado.
    visited_nodes[src] = 1;
    stack.emplace_back(src);
    for (auto &node : graph[src])
    { // para cada nó na lista de adjacencia do nó node
        if (visited_nodes[node] == 0)
        {                                                // se ainda náo foi visitado
            dfs(node, src, stack, visited_nodes, graph); // faz a busca em profundidade nele
        }
        else if (visited_nodes[node] == 1)
        { // se já foi visitado
            if (node != parent)
            { // se o nó for diferente do pai passado ä funcao, é porque existe um ciclo.
                cout << "cycle \n";
                cout << node << " "; // o ciclo será composto do nó atual + os elementos da pilha que estiverem no final dela até aquele nó.
                int pos = ((int)stack.size()) - 1;
                while (pos >= 0 && stack[pos] != node)
                {
                    cout << stack[pos] << " ";
                    --pos;
                }
                cout << "\n";
            }
        }
    }
    visited_nodes[src] = 2; // indicar que o nó já foi analisado.
    stack.pop_back();       // Removes the last element in the vector.
}
bool contains(vector<int> graph, int target)
{
    for (int index : graph)
    {
        if (index == target)
            return true;
    }
    return false;
}

bool contains(string prefix, int number)
{
    for (int i = 0; i < prefix.length(); i++)
    {
        char nString = number;
        char c = '0' + number;
        if (prefix[i] == c)
        {
            return true;
        }
    }
    return false;
}
bool contains(vector<vector<int>> v_out, vector<int> v_in)
{
    bool result;
    for (vector<int> v : v_out)
    {
        result = (v == v_in);
    }
    return result;
}
void isCycle(vector<int> permutation, int permutation_lenght, int numberOfVertexes, vector<vector<int>> &graph)
{
    bool cycle = true;
    for (int i = 0; i < permutation_lenght; i++)
    { // para cada um dos numeros da permutação
        if (i != permutation_lenght - 1)
        {
            if (!contains(graph[permutation[i]], permutation[i + 1]))
            {
                cycle = false;
            } // contains - daquela pos da permutação para o próximo da permutação.
        }
        else
        {
            if (!contains(graph[permutation[permutation_lenght - 1]], permutation[0]))
            {
                cycle = false;
            } // contains - da última posição da permutação para o início da permutação
        }
    }
    if (cycle)
    {
        cout << " IS CYCLE";
        cout << endl;
    }
    else
    {
        cout << "NOT CYCLE" << endl;
    }
}

void tokenize(std::string const &str, const char *delim,
              std::vector<std::string> &out)
{
    char *token = strtok(const_cast<char *>(str.c_str()), delim);
    while (token != nullptr)
    {
        out.push_back(std::string(token));
        token = strtok(nullptr, delim);
    }
}
vector<vector<int>> permutations;
void printAllKLengthRec(vector<int> set, string prefix,
                        int n, int k, vector<vector<int>> &graph)
{
    if (k < 0)
    {

        int number;
        vector<int> permutation;
        const char *delim = " ";
        std::vector<std::string> out;
        tokenize(prefix, delim, out);
        if (out.size() >= 3)
        {
            for (auto &s : out)
            {
                if (s != "" || s != " ")
                {
                    stringstream ss;
                    ss << s;
                    ss >> number;
                    permutation.emplace_back(number);
                }
            }

            permutations.emplace_back(permutation);
            cout << "permutation : ";
            for (int p : permutation)
            {
                cout << p << " ";
            }
            cout << endl;

            isCycle(permutation, out.size(), n, graph);
        }
        return;
    }

    for (int i = 0; i < n; i++)
    {
        string newPrefix;
        std::string s = std::to_string(set[i]);
        char const *pchar = s.c_str();
        if (!contains(prefix, set[i]))
            newPrefix = prefix + " " + pchar;
        else
            newPrefix = prefix;

        printAllKLengthRec(set, newPrefix, n, k - 1, graph);
    }
}

void printAllKLength(vector<int> set, int k, int n, vector<vector<int>> &graph)
{
    printAllKLengthRec(set, "", n, k, graph);
}

void permutation_vertices(vector<int> &vertices, int numVertexes, vector<vector<int>> &graph)
{

    for (int i = 3; i < numVertexes; i++)
    {
        printAllKLengthRec(vertices, "", numVertexes, i, graph);
    }
}

int main()
{

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int numberOfVertexes, numberOfEdges;
    cin >> numberOfVertexes >> numberOfEdges;
    vector<vector<int>> graph(numberOfVertexes, vector<int>(0));
    vector<int> vertexes;
    for (int i = 0; i < numberOfEdges; ++i)
    {
        int v1, v2;
        cin >> v1 >> v2;
        graph[v1].emplace_back(v2); // adiciona um novo elemento À lista de adjacencia.
        graph[v2].emplace_back(v1);
        if (!contains(vertexes, v1))
        {
            vertexes.emplace_back(v1);
        }
        if (!contains(vertexes, v2))
        {
            vertexes.emplace_back(v2);
        }
    }
    cout << " ======================================================= CAMINHAMENTO ================================================================================= ";
    int visited_nodes[numberOfVertexes] = {0};
    vector<int> stack; // stack auxiliar para manter toda a pilha de vértices visitados.
    std::clock_t start;
    double duration;

    start = std::clock();
    for (int i = 0; i < numberOfVertexes; ++i)
    {
        if (visited_nodes[i] == 0)
        {                                            // se ainda náo foi visitado
            dfs(i, -1, stack, visited_nodes, graph); // depth-first search - busca em profundidade.
        }
    }
    duration = (std::clock() - start) / (double)CLOCKS_PER_SEC;

    std::cout << "Duração: " << duration << '\n';
    cout << " ======================================================= PERMUTAÇÃO ================================================================================= ";
    for (int v : vertexes)
    {
        cout << v << " ";
    }
    start = std::clock();
    permutation_vertices(vertexes, numberOfVertexes, graph);
    duration = (std::clock() - start) / (double)CLOCKS_PER_SEC;
    std::cout << "Duração: " << duration << '\n';
}