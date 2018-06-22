package model;

/**
 * @author jaimeterra
 *
 */
public class NoAresta {
	
	private int vertice;
	private NoAresta aresta;
	
	public NoAresta() {
		vertice = -1;
		aresta = null;
	}
	
	public NoAresta(int vertice, NoAresta aresta) {
		super();
		this.vertice = vertice;
		this.aresta = aresta;
	}

	public int getVertice() {
		return vertice;
	}
	public void setVertice(int vertice) {
		this.vertice = vertice;
	}
	public NoAresta getAresta() {
		return aresta;
	}
	public void setAresta(NoAresta aresta) {
		this.aresta = aresta;
	}
	
}
