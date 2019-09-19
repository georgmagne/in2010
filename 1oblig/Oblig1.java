import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Node {
	private int label;
	private boolean visited = false;
	private List<Node> neighbors = new LinkedList<Node>();

	public Node(int label) {
		this.label = label;
	}

	public int getLabel() {
		return label;
	}

	public List<Node> getNeighbors() {
		return neighbors;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public void unVisit() { // Lagt til av meg.
		visited = false;
	}

	public void addNeighbor(Node n) {
		// legger til en uretta kant fra this til n
		if (!neighbors.contains(n)) {
			neighbors.add(n);
			n.addNeighbor(this);
		}
	}

	public void addSuccessor(Node n) {
		// legger til en retta kant fra this til n
		if (!neighbors.contains(n)) {
			neighbors.add(n);
		}
	}

	public String toString() {
		return Integer.toString(label);
	}
}

class Graph {
	private Node[] nodes;

	public Graph(Node[] nodes) {
		this.nodes = nodes;
	}

	public void printNeighbors() {
		for (Node n1 : nodes) {
			String s = n1.toString() + ": ";
			for (Node n2 : n1.getNeighbors()) {
				s += n2.toString() + " ";
			}
			System.out.println(s.substring(0, s.length() - 1));
		}
	}

	private static Graph buildExampleGraph() {
	    // ukeoppgave
		Node[] nodes = new Node[7];
		for (int i = 0; i < 7; i++) {
			nodes[i] = new Node(i);
		}
		nodes[0].addNeighbor(nodes[1]);
		nodes[0].addNeighbor(nodes[2]);
		nodes[1].addNeighbor(nodes[2]);
		nodes[2].addNeighbor(nodes[3]);
		nodes[2].addNeighbor(nodes[5]);
		nodes[3].addNeighbor(nodes[4]);
		nodes[4].addNeighbor(nodes[5]);
		nodes[5].addNeighbor(nodes[6]);
		return new Graph(nodes);
	}

	private static Graph buildRandomSparseGraph(int numberofV, long seed) {
		// seed brukes av java.util.Random for å generere samme sekvens for samme frø
		// (seed) og numberofV
		java.util.Random tilf = new java.util.Random(seed);
		int tilfeldig1 = 0, tilfeldig2 = 0;
		Node[] nodes = new Node[numberofV];

		for (int i = 0; i < numberofV; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < numberofV; i++) {
			tilfeldig1 = tilf.nextInt(numberofV);
			tilfeldig2 = tilf.nextInt(numberofV);
			if (tilfeldig1 != tilfeldig2)
				nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
		}
		return new Graph(nodes);
	}

	private static Graph buildRandomDenseGraph(int numberofV, long seed) {
		java.util.Random tilf = new java.util.Random(seed);
		int tilfeldig1 = 0, tilfeldig2 = 0;
		Node[] nodes = new Node[numberofV];

		for (int i = 0; i < numberofV; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < numberofV * numberofV; i++) {
			tilfeldig1 = tilf.nextInt(numberofV);
			tilfeldig2 = tilf.nextInt(numberofV);
			if (tilfeldig1 != tilfeldig2)
				nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
		}
		return new Graph(nodes);
	}

	private static Graph buildRandomDirGraph(int numberofV, long seed) {
		java.util.Random tilf = new java.util.Random(seed);
		int tilfeldig1 = 0, tilfeldig2 = 0;
		Node[] nodes = new Node[numberofV];

		for (int i = 0; i < numberofV; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < 2 * numberofV; i++) {
			tilfeldig1 = tilf.nextInt(numberofV);
			tilfeldig2 = tilf.nextInt(numberofV);
			if (tilfeldig1 != tilfeldig2)
				nodes[tilfeldig1].addSuccessor(nodes[tilfeldig2]);
		}
		return new Graph(nodes);
	}

	public void DFS(Node s) {
		s.visit();

		for (int i = 0; i < s.getNeighbors().size(); i++) {
			if (!s.getNeighbors().get(i).isVisited()) {
				this.DFS(s.getNeighbors().get(i));
			}
		}

	}

	public void DFSFull() {
		for (Node n : nodes) {
			if (!n.isVisited()) {
				this.DFS(n);
			}
		}
	}

	public void resetVisit() { // Lagt til av meg.
		for (Node n : nodes) {
			n.unVisit();
		}
	}

        public int numberOfComponents() {
	    	// Oppgave 1A
					int naborekker = 0; //

					for (Node n : nodes){
						if (!n.isVisited()){
							naborekker++;
							DFS(n);
						}
					}
					resetVisit();
	    		return naborekker;
				}

        public Graph transformDirToUndir() {
	    		// Oppgave 1B

					// Node[] dirNode = this.nodes;
					Node[] unDirNodes = new Node[nodes.length];

					// Oppretter nye noder som er like som de i retta graf, uten naboer.
					for(int i = 0; i < nodes.length; i++){
						unDirNodes[i] = new Node(nodes[i].getLabel());
					}

					// Legger til nabo med lik label/index som naboene til retta graf.
					// addNeighbor() gjør naboene uretta, og den sjekker om de allerede er naboer.
					// Trenger derfor ikke å bry meg om dette når jeg skal sette nye naboer her.
					for (int i = 0; i < nodes.length; i++){
						for (Node naboNode : nodes[i].getNeighbors()){
							unDirNodes[i].addNeighbor(unDirNodes[naboNode.getLabel()]);
						}
					}

					Graph unDir = new Graph(unDirNodes);
					return unDir;
				}

					// Prøvde først å lage BFS søk for å gå gjennom grafen og gjøre den uretta.
					// Fikk ikke med alle nodene i søket, ettersom noen var "gjemt".
					// Dette gjaldt først og fremst når jeg kun startet BFS i en node.
					// Queue<Node> queue = new LinkedList<>();
					//
					// if (dirGraph.nodes.length == 0) { // Sjekker at dirGraph har noder.
					// 	return null;
					// }
					//
					// queue.add(dirGraph.nodes[0]); // Legger til første element
					//
					// while (queue.peek() != null) { // -- O(n)?
					// 	Node aktuellNode = queue.poll(); // Tar ut en node.
					// 	aktuellNode.visit();
					//
					// 	for (Node n : aktuellNode.getNeighbors()) { // Går gjennom naboer -- O(n^2)?
					// 		n.addNeighbor(aktuellNode);
					// 		if (!n.isVisited()) {
					// 			queue.add(n);
					// 		}
					// 	}
					// }

					// Hvis jeg uansett på starte BFS i alle nodene, kan jeg like gjerne gjøre det slik.
					// Det virket som kjøretiden var lik. Med større grafer også.
					// ca 10ms lengre kjøretid med BFS algoen, når grafen var liten.
					// Ikke så rart da begge algoritmene er O(n^2)?

			// 		for (Node n : nodes) {
			// 			if(!n.isVisited()) {
			// 				n.visit();
			// 				for (Node aktuellNode : n.getNeighbors()) {
			// 					aktuellNode.addNeighbor(n);
			// 				}
			// 			}
			// 		}
			// 		resetVisit();
	    // 		return this; // returner en NY graf
			// }



        public boolean isConnected(){
	    		// Oppgave 1C

					// Hvis grafen er sammenhengende har den kun ett komponent.
					return transformDirToUndir().numberOfComponents() == 1;
				}


        public Graph biggestComponent() {
	    		// Oppgave 1D

					if (numberOfComponents() == 1) { // Kun ett komponent.
						return this;
					}

					if (numberOfComponents() == 0) { // Tom
						return null;
					}

					Graph unDir = transformDirToUndir(); // For å være sikker.

					ArrayList<Node> tmpNodes = new ArrayList<>();
					ArrayList<ArrayList<Node>> components = new ArrayList<ArrayList<Node>>(); //2d ArrayList på formen [component][node]

					for (Node n : unDir.nodes) {
						if(!n.isVisited()) {
							n.visit();
							tmpNodes.add(n); //"Første" noden i komponenten blir lagt til.

							//Foretar BFS søk for hvert komponent.
							Queue<Node> queue = new LinkedList<>();
							queue.add(n); // Legger til start noden til søket.

							while(queue.peek() != null) { // Så lenge det fins ubehandlede noder.
								Node aktuellNode = queue.poll(); // Henter ut fra køen.
								aktuellNode.visit(); // Markerer noden.

								for (Node naboNode : aktuellNode.getNeighbors()) { // Går gjennom naboNoder.
									if (!n.isVisited()) { // Hvis node ikke er besøkt, er den ikke behandlet.
										queue.add(naboNode); // Legger til ubehandlet nabo node.
										tmpNodes.add(naboNode); // Legger til en tidligere usett node i komponent til ArrayList som skal "huskes".
									}
								}
							}

							components.add(tmpNodes); // Legger til liste med noder som utgjør et komponent
						}
					}

					int indexBiggestComponent = 0; // Hjelpe int, skal vise til største komponent i components, ArrayList.
					ArrayList<Node> currBiggestComp = new ArrayList<Node>(); // Hjelpe ArrayList, skal inneholde den største del ArrayList fra components.
					ArrayList<Node> checkBiggerComp; // Skal sjekkes mot currBiggestComp.

					for (int i = 0; i < components.size(); i++) {
						// Henter ut de to ArrayListene som skal sammenlignes.
						currBiggestComp = components.get(indexBiggestComponent);
						checkBiggerComp = components.get(i);

						if (checkBiggerComp.size() > currBiggestComp.size()) { // Hvis et større komponent blir funnet.
							indexBiggestComponent = i;
						}
					}
					Node[] nodesBiggestComponent = new Node[currBiggestComp.size()]; // Må gjøre om fra ArrayList til Node[]

					for (int i = 0; i < currBiggestComp.size() - 1; i++) {
						nodesBiggestComponent[i] = currBiggestComp.get(i);
					}

				Graph biggestComponent = new Graph(nodesBiggestComponent); // Oppretter en ny graf som kun inneholder den lengste komponenten.
	    	return biggestComponent;
				}


				public int[][] buildAdjacencyMatrix() {
					// Oppgave 1E

					int size = nodes.length;
					int[][] adjacencyMatrix = new int[size][size];

					for (int i = 0; i < size; i++) {
						for (Node nabo : nodes[i].getNeighbors()) {
							adjacencyMatrix[i][nabo.getLabel()] = 1;
						}
					}

	    		return adjacencyMatrix;
				}

	public static void main(String[] args) {
		Graph graph = buildExampleGraph();
		graph.printNeighbors();
		System.out.println("ExampleGraph "+graph.numberOfComponents()); // Test 1A
		System.out.println(graph.isConnected()); // Test 1C
		System.out.println("");
		graph.isConnected();

		graph = buildRandomSparseGraph(11, 201909202359L);
		graph.printNeighbors();
		System.out.println("RandomSparseGraph "+graph.numberOfComponents()); // Test 1A
		System.out.println(graph.isConnected()); // Test 1C
		System.out.println("");

		graph = buildRandomDenseGraph(15, 201909202359L);
		graph.printNeighbors();
		System.out.println("RandomDenseGraph "+graph.numberOfComponents()); // Test 1A
		System.out.println(graph.isConnected()); // Test 1C
		System.out.println("");

		// Test 1B
		System.out.println("Test 1B");
		graph = buildRandomDirGraph(11, 201909202359L);
		graph.printNeighbors();
		System.out.println();
		Graph unDir = graph.transformDirToUndir();
		unDir.printNeighbors();

		// Test 1C
		graph = buildRandomDirGraph(11,201909202359L);
		// unDir = graph.transformDirToUndir();
		System.out.println(graph.isConnected());
		// System.out.println(unDir.isConnected());


		// Test 1E
		graph = buildExampleGraph();
		int[][] nabomatise = graph.buildAdjacencyMatrix();
		for (int i = 0; i < nabomatise.length; i++){
			System.out.println("");
			for (int j = 0; j < nabomatise.length; j++){
				System.out.print(nabomatise[i][j]);
			}
		}
		System.out.println();
	}
}
