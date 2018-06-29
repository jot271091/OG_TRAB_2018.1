package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args){
		GrafoLista g = new GrafoLista(10).LerGrafoDoArquivo("grafo.txt");
		System.out.println("N : " + g.getN());
		System.out.println("M : " + g.getM());
		
//		g.busca();
//		g.buscaCompleta();
		
//		System.out.println("É Conexo? " + g.eConexo());
		System.out.println("e Arvore? " + g.eArvore());
//		g.obterFlorestaGeradora().imprimeGrafo();
		System.out.println();
//		long a = System.currentTimeMillis();
//		long b = 0;
//		for(int i = 0; i < 1000000000; i++){
//			
//		}
//		b = System.currentTimeMillis();
//		System.out.println(b - a);
		
//		g.removeAresta(1,2);
//		g.insereAresta(1,2);
//		g.imprimeVizinhos(g.retornaVizinhos(0));
//		g.insereVertice();
		g.imprimeGrafo();
		System.out.println();
		g.removeVertice(2);
		
		g.imprimeGrafo();
//		System.out.println(g.l.length);
	}
}
