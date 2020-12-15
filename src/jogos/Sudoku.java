package jogos;
import java.util.Scanner;
public class Sudoku {
	
	static Scanner sc = new Scanner(System.in);
	static int tentativasCertas = 0;
	
	public static int vetorDeSudokus[][][] = { { { 1, 2, 3, 4 },
												 { 4, 3, 2, 1 },
												 { 3, 4, 1, 2 },
												 { 2, 1, 4, 3 } },
												 { { 2, 1, 4, 3 },
												 { 3, 4, 1, 2 },
												 { 1, 2, 3, 4 },
												 { 4, 3, 2, 1 } },
												 { { 3, 4, 1, 2 },
												 { 2, 1, 4, 3 },
												 { 4, 3, 2, 1 },
												 { 1, 2, 3, 4 } },
												 { { 4, 3, 1, 2 },
												 { 1, 2, 4, 3 },
												 { 3, 4, 2, 1 },
												 { 2, 1, 3, 4 } }};
	public static void main(String[] args) {
		int matrizDoSudoku[][] = new int[4][4];
		jogarSudoku(matrizDoSudoku);
	}
	
	public static void jogarSudoku(int matriz[][]) {
		int nivel = 0,
			tentativas = 0;
		int retorno[] = new int[2];
		boolean sudokuCorreto = false;
		
		sorteiaSudoku(matriz, vetorDeSudokus);
		do {
			System.out.print("Entre com o nível que deseja jogar, 1(fácil), 2(médio) ou 3(difícil): ");
			nivel = sc.nextInt();
			if(nivel > 0 && nivel<= 3) {
				break;
			}
		} while(1 == 1);
		
		setaNivelDoSukoku(nivel, matriz);
		imprimeSudoku(matriz);
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				if(matriz[i][j] == 0) {
					tentativas++;
				}
			}
		}	

		do {
			retorno = testaEntrada(matriz);
			imprimeSudoku(matriz);
		} while(retorno[0] == 1 && retorno[1] < tentativas);
		
		sudokuCorreto = verificaSudoku(matriz);
		
		if(sudokuCorreto) {
			System.out.println("\nParabéns, a solução do Sudoku está correta!");
		} else {
			System.out.println("\nA solução está incorreta!");
		}
		
	}
	
	public static void sorteiaSudoku(int matriz[][], int vetorDeMatrizes[][][]) {
		int t = (int) Math.random() * vetorDeMatrizes.length;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = vetorDeMatrizes[t][i][j];
			}
		}
	}
	
	public static void imprimeSudoku(int matriz[][]) {
		System.out.println("|---|---|---|---|");
		for (int i = 0; i < 4; i++) {
			System.out.print("|");
			for (int j = 0; j < 4; j++) {
				if (matriz[i][j] == 0) {
				System.out.print("   |");
				} else
					System.out.print(" " + matriz[i][j] + " |");
			}
			System.out.println("\n|---|---|---|---|");
		}
	}
	
	public static void setaNivelDoSukoku(int n, int matriz[][]) {
		int i, j, contador = 0;
		
		switch (n) {
			case 1: // Nível fácil
				do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
				} while (contador < 4);
				break;
			case 2: // Nível médio
				do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
				} while (contador < 8);
				break;
			case 3: // Nível difícil
				do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
					if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
					}
				} while (contador < 12);
				break;
			default:
				System.out.println("Opções válidas: 1 (fácil), 2 (médio) e 3(difícil)");
		}
	}
	public static boolean verificaSudoku(int matriz[][]) {
		// Verifica linhas
		for (int i = 0; i < 4; i++)
			if ((matriz[i][0] + matriz[i][1] + matriz[i][2] + matriz[i][3]) != 10)
			return false;
		// Verifica colunas
		for (int j = 0; j < 4; j++)
			if ((matriz[0][j] + matriz[1][j] + matriz[2][j] + matriz[3][j]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[0][0] + matriz[0][1] + matriz[1][0] + matriz[1][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[0][2] + matriz[0][3] + matriz[1][2] + matriz[1][3]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][0] + matriz[2][1] + matriz[3][0] + matriz[3][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][2] + matriz[2][3] + matriz[3][2] + matriz[3][3]) != 10)
			return false;
		
		return true;
	}
	
	public static int[] testaEntrada(int matriz[][]) {
		int numeroJogar = 0,
				linha = 0,
				coluna = 0;
		int[] resultado = new int[2];
		
		do {
			System.out.print("Entre com o número  que deseja jogar (1, 2, 3 ou 4): ");
			numeroJogar = sc.nextInt();
		} while(numeroJogar < 1 && numeroJogar > 4);
		
		do {
			System.out.print("Entre com a linha que deseja jogar (1, 2, 3 ou 4): ");
			linha = sc.nextInt();
			System.out.print("Entre com a coluna  que deseja jogar (1, 2, 3 ou 4): ");
			coluna = sc.nextInt();
			if((linha > 0 && linha <= 4) && (coluna > 0 && coluna <= 4) && (matriz[linha-1][coluna-1] == 0)) {
				tentativasCertas++;
				break;
			} else {
				System.out.println("Uma das informações inseridas é inválida! Tente novamente!\n");
			}
		}while(1 == 1);
		
		matriz[linha-1][coluna-1] = numeroJogar;
		resultado[0] = 1;
		resultado[1] = tentativasCertas;
		
		return resultado;
	}
}