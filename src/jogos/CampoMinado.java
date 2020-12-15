package jogos;
import java.util.Scanner;
import java.util.Random;

public class CampoMinado {
	
	static Scanner sc = new Scanner(System.in);
	static Random sort = new Random();
	
	static String testaQtd(int numLinhas, int numColunas, int qtdBombas) {
		String status = "";
		if((numLinhas == 1 && numColunas == 1)) {
			System.out.println("Tamanho de matriz inválido!");
			status = "incorreto";
		}
		else {
			if(qtdBombas > 0 && qtdBombas < (numLinhas*numColunas)) {
				status = "correto";
			} else {
				System.out.println("Quantidade de bombas inválida!");
				status = "incorreto";
			}
		}
		return status;
	}
	
	static void imprimeTabuleiro(String[][] mascara) {
		System.out.println("\n");
		for(int c = 0; c < mascara[0].length; c++) {
			System.out.print("\t["+c+"]");
		}
		for(int l = 0; l < mascara.length; l++) {
			System.out.println("\n");
			System.out.print("["+l+"]");
			for(int c = 0; c < mascara[0].length; c++) {
				System.out.print("\t"+mascara[l][c]);
			}
		}
	}
	
	static String[] jogadaUsuario(String mascara[][], int[][] matriz, int qtdCertas) {
		int jogadaLinha = 0,
			jogadaColuna = 0,
			pontos = 0,
			x = 0,
			saveqtd = qtdCertas;
		String status[] = new String[2];
		boolean morte = false,
				rejogada = false;
		
		do {
			do {
				rejogada = false;
				System.out.println("\n");
				System.out.print("Entre com a linha que deseja jogar: ");
				jogadaLinha = sc.nextInt();
				System.out.print("Entre com a coluna que deseja jogar: ");
				jogadaColuna = sc.nextInt();
				if(mascara[jogadaLinha][jogadaColuna] != "x" && mascara[jogadaLinha][jogadaColuna] != "b") {
					x = 1;
				} else {
					System.out.println("Você já jogou nesta posição. Jogue novamente");
					rejogada = true;
				}
			}while(x != 1);
			if(matriz[jogadaLinha][jogadaColuna] == -1 && !rejogada) {
				mascara[jogadaLinha][jogadaColuna] = "b";
				imprimeTabuleiro(mascara);
				morte = true;
			} else if(matriz[jogadaLinha][jogadaColuna] != -1 && !rejogada){
				mascara[jogadaLinha][jogadaColuna] = "x";
				morte = false;
				imprimeTabuleiro(mascara);
				warning(jogadaLinha, jogadaColuna, matriz);
				qtdCertas--; 
				pontos++;
			} else {
				imprimeTabuleiro(mascara);
			}
		}while(!morte && pontos != saveqtd);
		status[0] = String.valueOf(morte);
		status[1] = String.valueOf(pontos);
		return status;
	}
	
	static void warning(int linha, int coluna, int matriz[][]) {
		if(linha == 0 && coluna == 0) {
			if
			(
				(matriz[linha][coluna+1] == -1) || 
				(matriz[linha+1][coluna] == -1) || 
				(matriz[linha+1][coluna+1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if(linha == 0 && (coluna > 0 && coluna < matriz[0].length-1)) {
			if
			(
				(matriz[linha][coluna+1] == -1) || 
				(matriz[linha+1][coluna] == -1) || 
				(matriz[linha+1][coluna+1] == -1) ||
				(matriz[linha+1][coluna-1] == -1) ||
				(matriz[linha-1][coluna-1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if(linha == 0 && coluna == matriz[0].length-1) {
			if
			(
				(matriz[linha][coluna-1] == -1) || 
				(matriz[linha+1][coluna] == -1) || 
				(matriz[linha+1][coluna-1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if(linha == matriz.length-1 && coluna == 0) {
			if
			(
				(matriz[linha-1][coluna] == -1) || 
				(matriz[linha-1][coluna+1] == -1) || 
				(matriz[linha][coluna+1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if(linha == matriz.length-1 && (coluna > 0 && coluna < matriz[0].length-1)) {
			if
			(
				(matriz[linha][coluna+1] == -1) || 
				(matriz[linha][coluna-1] == -1) || 
				(matriz[linha-1][coluna-1] == -1) ||
				(matriz[linha-1][coluna] == -1) ||
				(matriz[linha-1][coluna+1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if(linha == matriz.length-1 && coluna == matriz[0].length-1) {
			if
			(
				(matriz[linha][coluna-1] == -1) || 
				(matriz[linha-1][coluna] == -1) || 
				(matriz[linha-1][coluna-1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if((linha != 0 && linha != matriz.length-1) && coluna == 0) {
			if
			(
				(matriz[linha-1][coluna] == -1) || 
				(matriz[linha-1][coluna+1] == -1) || 
				(matriz[linha][coluna+1] == -1) ||
				(matriz[linha+1][coluna] == -1) ||
				(matriz[linha][coluna+1] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if((linha != 0 && linha != matriz.length-1) && coluna == matriz[0].length-1) {
			if
			(
				(matriz[linha][coluna-1] == -1) || 
				(matriz[linha+1][coluna] == -1) || 
				(matriz[linha+1][coluna-1] == -1) ||
				(matriz[linha-1][coluna-1] == -1) ||
				(matriz[linha-1][coluna] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
		
		if((linha != 0 && linha != matriz.length-1) && (coluna != 0 && coluna != matriz[0].length-1)) {
			if
			(
				(matriz[linha][coluna-1] == -1) || 
				(matriz[linha][coluna+1] == -1) || 
				(matriz[linha-1][coluna-1] == -1) ||
				(matriz[linha-1][coluna+1] == -1) ||
				(matriz[linha-1][coluna] == -1) ||
				(matriz[linha+1][coluna-1] == -1) ||
				(matriz[linha+1][coluna+1] == -1) ||
				(matriz[linha+1][coluna] == -1)
			) 
			{
				System.out.println("\n\nCuidado: bomba próxima!");
			}
		}
	}
	
	public static void main(String args[]) {
		int numLinhas = 0,
			numColunas = 0,
			qtdBombas = 0,
			posicaoL = 0,
			posicaoC = 0,
			qtdCertas = 0;
		String testaQtd = "";
		String[] resultado = new String[2];
		
		do {
			System.out.print("Entre com o número de linhas: ");
			numLinhas = sc.nextInt();
			System.out.print("Entre com o número de colunas: ");
			numColunas = sc.nextInt();
			System.out.print("Entre com o número de bombas: ");
			qtdBombas = sc.nextInt();
			testaQtd = testaQtd(numLinhas, numColunas, qtdBombas);
		}while(testaQtd != "correto");
		
		int matriz[][] = new int[numLinhas][numColunas];
		String mascara[][] = new String[numLinhas][numColunas];
		int bombas[][] = new int[qtdBombas][2];
		
		for(int l = 0; l < bombas.length; l++) {
			posicaoL = sort.nextInt(numLinhas);
			posicaoC = sort.nextInt(numColunas);
			
			if(l == 0) {
				bombas[l][0] = posicaoL;
				bombas[l][1] = posicaoC;
			} else {
				for(int j = 0; j < l; j++) {
					if(bombas[j][0] != posicaoL || bombas[j][1] != posicaoC) {
						bombas[l][0] = posicaoL;
						bombas[l][1] = posicaoC;
					} else {
						posicaoL = sort.nextInt(numLinhas);
						posicaoC = sort.nextInt(numColunas);
						j = 0;
					}
				}
			}
		}
		
		for(int l = 0; l < matriz.length; l++) {
			for(int c = 0; c < matriz[0].length; c++) {
				for(int bomba = 0; bomba < bombas.length; bomba++) {
					if(l == bombas[bomba][0] && c == bombas[bomba][1]) {
						matriz[l][c] = -1;
						break;
					} else {
						matriz[l][c] = 0;
					}
				}
			}
		}
		
		for(int l = 0; l < mascara.length; l++) {
			for(int c = 0; c < mascara[0].length; c++) {
				mascara[l][c] = "__";
			}
		}
		imprimeTabuleiro(mascara);
		
		qtdCertas = (numLinhas*numColunas) - qtdBombas;
		
		resultado = jogadaUsuario(mascara, matriz, qtdCertas);
		
		if(resultado[0] == "true") {
			System.out.println("\n\nGame Over");
			System.out.println("Você obteve "+resultado[1]+"/"+qtdCertas+" pontos.");
		} else {
			System.out.println("\n\nParabéns, você ganhou o jogo");
			System.out.println("Você obteve "+resultado[1]+"/"+qtdCertas+" pontos.");
		}
	}
}
