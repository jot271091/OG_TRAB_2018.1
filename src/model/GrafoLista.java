package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GrafoLista {
	private int n;
	private int m;
	NoAresta[] l;
	
	int[] arestas;
	
	private boolean[] visitado;
	private boolean[][] explorado;
	private boolean[][] descoberto;

	public GrafoLista(int n) {
		super();
		this.n = n;
		l = new NoAresta[this.n];
	}	
	
	private void rotularVisitado(){
		visitado = new boolean[n];
		for(int i = 0; i < n; i++){
			visitado[i] = false;
		}
		
		
	}
	
	private void rotularExplorado(){
		explorado = new boolean[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				explorado[i][j] = false;
			}
		}
	}
	
	private void rotularDescoberto(){
		descoberto = new boolean[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				descoberto[i][j] = false;
			}
		}
	}
	
	public void busca(){
		rotularVisitado();
		rotularExplorado();
		rotularDescoberto();
		
		busca(0);
	}
	
	public void busca(int r){
		visitado[r] = true;
		for(int i = 0; i < arestas.length; i+=2){
			if(visitado[arestas[i]-1] && !explorado[arestas[i]-1][arestas[i+1]-1]){
				explorado[arestas[i]-1][arestas[i+1]-1] = true;
//				descoberto[arestas[i]-1][arestas[i+1]-1] = true;
			}else if(!visitado[arestas[i+1]-1]){
				visitado[arestas[i+1]-1] = true;
				descoberto[arestas[i]-1][arestas[i+1]-1] = true;
			}	
		}
		System.out.println();
	}
	
	int a,b = 0;
	public void buscaProfundidade(int v){
		visitado[v] = true;	
		for(int w = 1; w < n; w+=2){
			if(visitado[w]){
				if(!explorado[v][w]){
					explorado[v][w] = true;
				}
			}else{
				explorado[v][w] = true;
				descoberto[v][w] = true;
				buscaProfundidade(w);
			}
		}
	}
	
	public void buscaLargura(int v){
		Queue<Integer> fila = new LinkedList<Integer>();
		visitado[v] = true;
		fila.add(v);
		while(fila.size() > 0){
			v = fila.poll();
			for(int w = 1; w < arestas.length; w+=2){
				if(visitado[w]){
					if(!explorado[v][w]){
						explorado[v][w] = true;
					}
				}else{
					explorado[v][w] = true;
					descoberto[v][w] = true;
					visitado[w] = true;
					fila.add(w);
				}
			}
		}
	}
	
	public boolean eFloresta(){
		return !temCiclo();
	}
	
	public boolean eArvore() {
		return (!temCiclo() && eConexo());
	}
	
	public void buscaCompleta() {
		rotularVisitado();
		rotularExplorado();
		rotularDescoberto();
		
		for(int r = 0; r < n; r++){
			if(!visitado[r]){
				busca(r);
			}
		}
	}
	
	public boolean eConexo() {
		busca();
		for(int i = 0; i < n; i++){
			if(!visitado[i]){
				return false;
			}
		}
		return true;
	}
	
	public boolean temCiclo() {
		buscaCompleta();
		for(int i = 0; i < arestas.length; i+=2){
			if(!descoberto[arestas[i] - 1][arestas[i+1] - 1]){
				return true;
			}
		}
		
		return false;
	}
	
	public GrafoLista obterFlorestaGeradora() {
		GrafoLista t = new GrafoLista(n);
		
		buscaCompleta();
		for(int i = 0; i < arestas.length; i+=2) {
			if(descoberto[arestas[i]-1][arestas[i+1]-1]){
				t.insereAresta(arestas[i], arestas[i+1]);
				t.insereAresta(arestas[i+1], arestas[i]);
			}
		}
		
		return t;
	}
	
	private NoAresta[] copiaNoAresta(){
		NoAresta[] novo = new NoAresta[l.length];
		for(int i = 0; i < novo.length; i++){
			novo[i] = new NoAresta(l[i].getVertice(), l[i]);
		}
		return novo;
	}
	
	public GrafoLista LerGrafoDoArquivo(String nomeArquivo) {
		
		arestas = lerGrafo(nomeArquivo);
		
		this.l = new NoAresta[this.n];
	    for(int i = 0; i < arestas.length; i+=2){
	    	int u = arestas[i];
	    	int v = arestas[i + 1];
	    	
	    	insereAresta(u, v);
	    	insereAresta(v, u);
	    }
        return this;
		
	}
	
	public void insereAresta(int u, int v) {
		NoAresta novo = new NoAresta();
		novo.setVertice(v-1);
		novo.setAresta(l[u-1]);
		this.l[u-1] = novo;
	}
	
	public void removeAresta(int u, int v) {
		NoAresta[] novo = new NoAresta[l.length];
		for(int i = 1; i < l.length; i++) {
			if(i == u) {
				NoAresta no = l[i];
				while(no.getAresta() != null) {
					if(no.getAresta().getVertice() == v){
						no.setAresta(no.getAresta().getAresta());
						break;
					}
					no = no.getAresta();
				}
			}else if(i == v) {
				NoAresta no = l[i];
				while(no.getAresta() != null) {
					if(no.getAresta().getVertice() == u){
						no.setAresta(no.getAresta().getAresta());
						break;
					}
					no = no.getAresta();
				}
			}
			novo[i] = l[i];
		}
	}
	
	public void insereVertice() {
		NoAresta[] novo = new NoAresta[l.length + 1];
		for(int i = 0; i < l.length; i++){
			novo[i] = l[i];
		}
		novo[l.length] = new NoAresta();
		l = novo;
	}
	
	public void removeVertice(int v){
		NoAresta[] novo = new NoAresta[l.length];
		for(int i = 0; i < l.length; i++) {
			NoAresta no = l[i];
			if(no.getVertice() == v){
				no.setVertice(-1);
			}
			while(no != null) {
				if(no.getAresta() != null){
					if(no.getAresta().getVertice() == v){
						no.setAresta(no.getAresta().getAresta());
						break;
					}
				}
				no = no.getAresta();
			}
			if(i != v){
				novo[i] = no;
			}
		}
		l = novo;
	}
	
	//retorna vizinhos
	public NoAresta retornaVizinhos(int v) {
		return l[v];
	}
	
	public void imprimeVizinhos(NoAresta no) {
		System.out.print("Lista de vizinhos: " + (no.getVertice() + 1) + "->");
		while(no.getAresta() != null){
			no = no.getAresta();
			if(no.getAresta() != null)
				System.out.print((no.getVertice() + 1) + "->");
			else
				System.out.print((no.getVertice() + 1));
		}
		System.out.println();
	}

	public int[] lerGrafo(String nomeArquivo){
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(nomeArquivo));
		
	        int[] words = null;
	        String lineJustFetched = null;
	        String[] wordsArray;
	        int line = 0;
	        int vertices = 0;
	        int arestas = 0;
	        int i = 0;
	        while(true){
	            lineJustFetched = buf.readLine();
	            if(lineJustFetched == null){  
	                break; 
	            }else{
	            	wordsArray = lineJustFetched.split("\t");
	            	if(line == 0){
	            		this.n = Integer.parseInt(wordsArray[0]);
	            		this.m = Integer.parseInt(wordsArray[1]);
	            		words = new int[m*2];
	            	}else{
		                for(String each : wordsArray){
		                    if(!"".equals(each)){
		                        words[i] = Integer.parseInt(each);
		                        i++;
		                    }
		                }
	            	}
	            	line++;
	            }
	        }
	        
	        buf.close();
	        
	        return words;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void imprimeGrafo() {
		for(int i = 0; i < l.length; i++) {
			NoAresta no = l[i];
			if(no != null){
				if(no.getVertice() == -1){
					System.out.print((i + 1) + "->");
				}else{
					System.out.print((i + 1) + "->" + (no.getVertice() + 1));
				}
				while(no.getAresta() != null){
					no = no.getAresta();
					System.out.print("->" + (no.getVertice() + 1));
				}
				System.out.println();
			}
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public NoAresta[] getL() {
		return l;
	}

	public void setL(NoAresta[] l) {
		this.l = l;
	}
	
	
}
