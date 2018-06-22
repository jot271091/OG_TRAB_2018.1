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
		
		System.out.println();
		
//		g.removeAresta(1,2);
//		g.insereAresta(1,2);
		g.imprimeVizinhos(g.retornaVizinhos(1));
//		g.insereVertice();
//		g.removeVertice(2);
		
		g.imprimeGrafo();
//		System.out.println(g.l.length);
	}
}
