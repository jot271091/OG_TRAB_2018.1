package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrafoLista {
	private int n;
	private int m;
	NoAresta[] l;

	public GrafoLista(int n) {
		super();
		this.n = n;
		l = new NoAresta[this.n];
	}	
	
	public GrafoLista LerGrafoDoArquivo(String nomeArquivo) {
		
		int[] arestas = lerGrafo(nomeArquivo);
		
		this.l = new NoAresta[this.n + 1];
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
		novo.setVertice(v);
		novo.setAresta(l[u]);
		this.l[u] = novo;
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
		for(int i = 1; i < l.length; i++) {
			NoAresta no = l[i];
			if(no.getVertice() == v){
				no.setVertice(-1);
			}
			while(no != null && no.getAresta() != null) {
				if(no.getAresta().getVertice() == v){
					no.setAresta(no.getAresta().getAresta());
					break;
				}
				no = no.getAresta();
			}
			if(i != v){
				novo[i] = no;
			}
		}
		l = novo;
	}
	
	public NoAresta retornaVizinhos(int v) {
		return l[v];
	}
	
	public void imprimeVizinhos(NoAresta no) {
		System.out.print("Lista de vizinhos: " + no.getVertice() + "->");
		while(no.getAresta() != null){
			no = no.getAresta();
			if(no.getAresta() != null)
				System.out.print(no.getVertice() + "->");
			else
				System.out.print(no.getVertice());
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
					System.out.print(i + "->");
				}else{
					System.out.print(i + "->" + no.getVertice());
				}
				while(no.getAresta() != null){
					no = no.getAresta();
					System.out.print("->" + no.getVertice());
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
