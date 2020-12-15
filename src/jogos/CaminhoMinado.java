package jogos;
import java.util.Scanner;
import java.util.Random;

public class CaminhoMinado {
	
	static Scanner sc = new Scanner(System.in);
	static Random sort = new Random();
	
	public static void main(String args[]) {
		int tamanhoCaminho = 0,
			qtdBombas = 0,
			x = 0,
			z = 0,
			posicaoBomba = 0,
			posicaoJogada = 0,
			qtdCertas = 0,
			pontos = 0;
		String status = "";
		
		do {
			System.out.print("Entre com o tamanho do caminho: ");
			tamanhoCaminho = sc.nextInt();
			if(tamanhoCaminho > 1) {
				z = 1;
			} else {
				System.out.println("Tamanho inválido!");
			}
		}while(z != 1);
		
		int vetorInteiro[] = new int[tamanhoCaminho];
		String vetorMascara[] = new String[tamanhoCaminho];
		
		do {
			System.out.print("Entre com a quantidade de bombas: ");
			qtdBombas = sc.nextInt();
			if(qtdBombas > 0 && qtdBombas < tamanhoCaminho) {
				x = 1;
			} else {
				System.out.println("Quantidade de bombas inválida!");
			}
		}while(x != 1);
		
		int possibilidadeBomba[] = new int[qtdBombas];
		
		for(int i = 0; i < qtdBombas; i++) {
			posicaoBomba = sort.nextInt(tamanhoCaminho);
			if(i == 0) {
				possibilidadeBomba[i] = posicaoBomba;
			} else {
				for(int j = 0; j < i; j++) {
					if(possibilidadeBomba[j] != posicaoBomba) {
						possibilidadeBomba[i] = posicaoBomba;
					} else {
						posicaoBomba = sort.nextInt(tamanhoCaminho);
						j = 0;
					}
				}
			}
			
		}
		
		qtdCertas = tamanhoCaminho - qtdBombas;
		
		for(int i = 0; i < vetorMascara.length; i++) {
			vetorMascara[i] = "__";
		}
		
		for(int i = 0; i< vetorInteiro.length; i++) {
			for(int j = 0; j < possibilidadeBomba.length; j++) {
				if(i == possibilidadeBomba[j]) {
					vetorInteiro[i] = -1;
					break;
				} else {
					vetorInteiro[i] = 0;
				}
			}
		}
		System.out.println("\n");
		for(int i = 0; i < vetorMascara.length; i++) {
			System.out.print(vetorMascara[i]+"\t");
		}
		System.out.println("\n");
		for(int i = 0; i < vetorMascara.length; i++) {
			System.out.print("["+(i)+"]\t");
		}
		
		do {
			
			System.out.print("\n\nEntre com a posição da jogada: ");
			posicaoJogada = sc.nextInt();
			if(vetorInteiro[posicaoJogada] == -1) {
				vetorMascara[posicaoJogada] = "b";
				status = "morreu";
				for(int i = 0; i < vetorMascara.length; i++) {
					System.out.print(vetorMascara[i]+"\t");
				}
				System.out.println("\n");
				for(int i = 0; i < vetorMascara.length; i++) {
					System.out.print("["+(i)+"]\t");
				}
				if(posicaoJogada > 0) {
					if(vetorInteiro[posicaoJogada -1] == -1 || vetorInteiro[posicaoJogada + 1] == -1) {
						System.out.println("\nCuidado: bomba próxima!");
					}
				} else {
					if(vetorInteiro[posicaoJogada + 1] == -1) {
						System.out.println("\nCuidado: bomba próxima!");
					}
				}
			} else {
				vetorMascara[posicaoJogada] = "x";
				qtdCertas--;
				pontos++;
				status = "vivo";
				for(int i = 0; i < vetorMascara.length; i++) {
					System.out.print(vetorMascara[i]+"\t");
				}
				System.out.println("\n");
				for(int i = 0; i < vetorMascara.length; i++) {
					System.out.print("["+(i)+"]\t");
				}
				if(posicaoJogada > 0) {
					if(vetorInteiro[posicaoJogada -1] == -1 || vetorInteiro[posicaoJogada + 1] == -1) {
						System.out.println("\nCuidado: bomba próxima!");
					}
				} else {
					if(vetorInteiro[posicaoJogada + 1] == -1) {
						System.out.println("\nCuidado: bomba próxima!");
					}
				}
			}
			
		}while(status != "morreu" && (tamanhoCaminho - qtdBombas) != pontos);
		
		if(status == "morreu") {
			System.out.println("\nGAME OVER");
			System.out.println("Você atingiu "+pontos+"/"+(tamanhoCaminho - qtdBombas)+" acertos");
		} else {
			System.out.println("\n\nParabéns, você ganhou o jogo!");
			System.out.println("Você atingiu "+pontos+"/"+(tamanhoCaminho - qtdBombas)+" acertos");
		}
	}
}
