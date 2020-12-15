package jogos;
import java.util.Scanner;
import java.util.Random;

public class Jogo15 {
	
	static Scanner sc = new Scanner(System.in);
	static Random sort = new Random();
	static int contaMovimento = 0;
	
	public static void main(String args[]) {
		boolean resultado = false;
		int matriz[][] = new int[3][3];
		
		matriz = geraMatriz();
		resultado = moveMatriz(matriz);
		
		if(resultado) {
			System.out.println("\n\nParabéns, você conseguiu!");
			System.out.println("O número de movimento que você realizou para vencer foi: "+contaMovimento);
		}
	}
	
	static boolean confereMatriz(int matriz[][]) {
		boolean retorno = false;
		if
		(
			(matriz[0][0] == 0) &&
			(matriz[0][1] == 1) &&
			(matriz[0][2] == 2) &&
			(matriz[1][0] == 3) &&
			(matriz[1][1] == 4) &&
			(matriz[1][2] == 5) &&
			(matriz[2][0] == 6) &&
			(matriz[2][1] == 7) &&
			(matriz[2][2] == 8)
		) 
		{
			retorno = true; 
		}
		return retorno;
	}
	
	static String[] movimentoPossível(int matriz[][], int numeroMexer) {
		String retorno[] = new String[3];
		for(int l = 0; l < matriz.length; l++) {
			for(int c = 0; c < matriz[0].length; c++) {
				if(matriz[l][c] == 0) {
					retorno[0] = String.valueOf(l);
					retorno[1] = String.valueOf(c);
				}
				if(matriz[l][c] == numeroMexer) {
					if(l == 0 && c == 0) {
						if(matriz[l][c+1] == 0 || matriz[l+1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
						
					} else if(l == 0 && c == 1) {
						if(matriz[l][c+1] == 0 || matriz[l+1][c] == 0 || matriz[l][c-1] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 0 && c == 2) {
						if(matriz[l+1][c] == 0 || matriz[l][c-1] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 1 && c == 0) {
						if(matriz[l][c+1] == 0 || matriz[l+1][c] == 0 || matriz[l-1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 1 && c == 1) {
						if(matriz[l][c+1] == 0 || matriz[l][c-1] == 0 || matriz[l-1][c] == 0 || matriz[l+1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 1 && c == 2) {
						if(matriz[l][c-1] == 0 || matriz[l+1][c] == 0 || matriz[l-1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 2 && c == 0) {
						if(matriz[l][c+1] == 0 || matriz[l-1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 2 && c == 1) {
						if(matriz[l][c+1] == 0 || matriz[l][c-1] == 0 || matriz[l-1][c] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					} else if(l == 2 && c == 2) {
						if(matriz[l][c-1] == 0 || matriz[l][c-1] == 0) {
							retorno[2] = "true";
							contaMovimento++;
						} else {
							retorno[2] = "false";
						}
					}
				}
			}
		}
		return retorno;
	}
	
	static boolean moveMatriz(int matriz[][]) {
		int mover = 0,
			x = 0;
		boolean moveu = false,
				confereMovimento = false,
				resultado = false;
		String possibilidade[] = new String[3];
		imprimeMatriz(matriz);
		do {
			do {
				x = 0;
				System.out.println("\n");
				System.out.print("Entre com o número que você quer mover: ");
				mover = sc.nextInt();
				if(mover > 0 && mover <= 8) {
					possibilidade = movimentoPossível(matriz, mover);
					if(possibilidade[2] == "true") {
						x = 1;
					} else {
						System.out.println("Movimento inválido!");
					}
				} else {
					System.out.println("Número inexistente!");
				}
			}while(x != 1);
			
			for(int l = 0; l < matriz.length; l++) {
				for(int c = 0; c < matriz[0].length; c++) {
					if(matriz[l][c] == mover) {
						moveu = false;
						if(!moveu) {
							matriz[Integer.parseInt(possibilidade[0])][Integer.parseInt(possibilidade[1])] = mover;
							matriz[l][c] = 0;
							l = matriz.length;
							c = matriz[0].length;
						}
					}
				}
			}
			
			imprimeMatriz(matriz);
			
			confereMovimento = confereMatriz(matriz);
			if(confereMovimento) {
				resultado = true;
			}
		}while(!confereMovimento);
		return resultado;
	}
	static void imprimeMatriz(int matriz[][]) {
		for(int l = 0; l < matriz.length; l++) {
			System.out.println("\n");
			for(int c = 0; c < matriz[0].length; c++) {
				if(matriz[l][c] == 0) {
					System.out.print("\t__");
				} else {
					System.out.print("\t"+matriz[l][c]);
				}
			}
		}
	}
	
	static int[][] geraMatriz() {
		int matriz[][] = new int[3][3];
		String numero = "";
		int nmr = 0;
		
		for(int l = 0; l < matriz.length; l++) {
			for(int c = 0; c < matriz[0].length; c++) {
				nmr = sort.nextInt(9);
				if(l == 0 && c == 0) {
					matriz[l][c] = nmr;
					numero += String.valueOf(nmr);
				} else {
					if(numero.contains(String.valueOf(nmr))) {
						do {
							nmr = sort.nextInt(9);
						}while(numero.contains(String.valueOf(nmr)));
						matriz[l][c] = nmr;
						numero += String.valueOf(nmr);
					} else {
						matriz[l][c] = nmr;
						numero += String.valueOf(nmr);
					}
				}
			}
		}
		
		return matriz;
	}
}
