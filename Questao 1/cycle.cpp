#include<bits/stdc++.h>
using namespace std;


#define p(x) cout << #x << " is " << (x) << " on Line:" << __LINE__ << " \n";
#define pl(x) for(auto &y:x) cout << y << " "; cout << "\n";

void dfs(int src, int parent, vector <int> &stack, int visited_nodes[], vector <vector <int>> &graph){
	
    //status: 0 - ainda não visitado; 1 - visitado; 2 - visitado e analisado. 
	visited_nodes[src] = 1;
	stack.emplace_back(src);
	for(auto &node: graph[src]){ //para cada nó na lista de adjacencia do nó node
		if(visited_nodes[node] == 0){ //se ainda náo foi visitado
			dfs(node,src,stack,visited_nodes,graph); //faz a busca em profundidade nele 
		} 
		else if(visited_nodes[node] == 1){ //se já foi visitado
			if(node != parent){  //se o nó for diferente do pai passado ä funcao, é porque existe um ciclo. 
				cout << "cycle \n";
				cout << node << " ";  //o ciclo será composto do nó atual + os elementos da pilha que estiverem no final dela até aquele nó.  
				int pos = ((int)stack.size())-1;
				while(pos >= 0 && stack[pos] != node){
					cout << stack[pos] << " ";
					--pos;
				}
				cout << "\n";
			}
		}
	}
	visited_nodes[src] = 2; //indicar que o nó já foi analisado. 
	stack.pop_back(); //Removes the last element in the vector. 
}

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  int numberOfVertexes,numberOfEdges;
  cin >> numberOfVertexes >> numberOfEdges;
  vector <vector <int>> graph(numberOfVertexes, vector <int> (0));
  for(int i = 0; i < numberOfEdges; ++i){
	  int v1,v2;
	  cin >> v1 >> v2;
	  graph[v1].emplace_back(v2); //appends a new element to the end of the graph (adjacency list).
	  graph[v2].emplace_back(v1); 
  }
  int visited_nodes[numberOfVertexes] = {0};
  vector <int> stack; //stack auxiliar para manter toda a pilha de vértices visitados. 
  for(int i = 0; i < numberOfVertexes; ++i){
	  if(visited_nodes[i] == 0){  //se ainda náo foi visitado
		  dfs(i,-1,stack,visited_nodes,graph); // depth-first search - busca em largura.
	  }
  }
}
//Sample Input

//12 13
//0 1 
//1 2 
//2 3
//3 4
//4 5
//5 6
//6 7
//7 8
//8 5
//5 9
//9 10
//10 11
//11 3

//Sample Output

//cycle 
//5 8 7 6
//cycle 
//3 11 10 9 5 4

//https://codeforces.com/blog/entry/66161