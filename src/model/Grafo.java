package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Grafo {
	
	private int[][] criaMatriz(int m) {
		int[][] matriz = new int[m][m];
		return matriz;
	}
	
	public int[] pegavizinho(int[][] m, int vertice) {
			int n = m.length;
			int l = 0;
			int[] vert = new int[n];
			for (int j = 0; j<n; j++) {
				if(m[vertice-1][j] == 1) {
					System.out.println("Vertice: "+ (j+1) + " ");
					vert[l] = j+1;
					l++;
				}
			}
		return vert;
	}
	

	public int[][] insereVertice(int[][] m, int qtd) {
		int matriz[][] = criaMatriz(qtd);
		int n = m.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = m[i][j];
			}
		}
		return matriz;
	}

	public int[][] removeVertice(int[][] m, int qtd) {
		int matriz[][] = criaMatriz(qtd);
		int n = qtd;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = m[i][j];
			}
		}
		return matriz;
	}

	public void imprimeMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (j == (matriz.length - 1)) {
					System.out.print(matriz[i][j] + "");
					System.out.println();
				} else {
					System.out.print(matriz[i][j] + ",");
				}
			}
		}
	}
	 
	public int[][] insereAresta(int[][] m, int i, int j) {
		m[i - 1][j - 1] = 1;
		m[j - 1][i - 1] = 1;
		return m;
	}

	public int[][] removeAresta(int[][] m, int i, int j) {
		m[i - 1][j - 1] = 0;
		m[j - 1][i - 1] = 0;
		return m;
	}

	public int[][] lerGrafo(String nomeArquivo) {
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(nomeArquivo));

			String lineJustFetched = null;
			String[] wordsArray;
			int matriz[][] = null;
			int line = 0;
			int vertices = 0;
			int arestas = 0;
			while (true) {
				lineJustFetched = buf.readLine();
				if (lineJustFetched == null) {
					break;
				} else {
					wordsArray = lineJustFetched.split("\t");
					if (line == 0) {
						matriz = criaMatriz(Integer.parseInt(wordsArray[0]));
					} else {
						matriz = insereAresta(matriz, Integer.parseInt(wordsArray[0]), Integer.parseInt(wordsArray[1]));
					}
				}

				line++;
			}

			buf.close();

			return matriz;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
