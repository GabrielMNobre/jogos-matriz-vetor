package jogos;
import java.util.Random;
import java.util.Scanner;

public class JogoPerguntas {
	
	static Scanner sc = new Scanner(System.in);
	static Random sort = new Random();
	
	public static void main(String args[]) {
		String perguntas[][] = {};//[0]nivel, [1]sala, [2]id da pergunta e [3]pergunta.
		String respostas[][] = {};//[0]id pergunta, [1]resposta e [2]correta
		String alternativas[] = {"a) ", "b) ", "c) ", "d) ", "e) "};
		int perguntasSala[] = new int[5];
		String posicaoRespostaCorreta[] = new String[5];
		String respostaUser[] = new String[5];
		String respostaUsuario = "";
		int posicaoResposta[] = new int[5];
		int nivel = 0,
			x = 0,
			posicaoRespostaUsuario = 0,
			correta = 0,
			sala = 1;
		boolean fim = false;
		
		perguntas = montaPerguntas(perguntas);
		respostas = montaRespostas(respostas);
		nivel = selecionaNivelJogo();
		posicaoResposta = sorteiaResposta();

		do {
			correta = 0;
			if(sala == 1) {
				System.out.println("Bem-vindo  a sala 1!\n");
			} else if(sala == 2) {
				System.out.println("Bem-vindo  a sala 2!\n");
			} else {
				System.out.println("Bem-vindo  a sala 3!\n");
			}
			perguntasSala = escolhePerguntaSala(nivel, perguntas, sala);
			for(int i = 0; i < perguntasSala.length; i++) {
				System.out.println(perguntasSala[i]);
			}
			for(int i = 0; i < 5; i++) {
				respostaUsuario = "";
				System.out.println((i+1)+") "+imprimePergunta(perguntas,perguntasSala[i]));
				System.out.println("\n"+imprimeResposta(respostas, alternativas, perguntasSala[i], posicaoResposta));
				posicaoRespostaCorreta = respostaCorreta(posicaoResposta, respostas, perguntasSala[i]);
				do {
					System.out.print("Entre com a alternativa correta: ");
					respostaUsuario = sc.next().toLowerCase();
					switch(respostaUsuario) {
						case "a":
						case "b":
						case "c":
						case "d":
						case "e":
							x = 1;
							break;
						default :
							System.out.println("\nAlternativa inexistente! Tente novamente!");
							x = 0;
							break;
					}
				}while(x != 1);
				
				switch(respostaUsuario) {
				case "a":
					posicaoRespostaUsuario = 0;
					break;
				case "b":
					posicaoRespostaUsuario = 1;
					break;
				case "c":
					posicaoRespostaUsuario = 2;
					break;
				case "d":
					posicaoRespostaUsuario = 3;
					break;
				case "e":
					posicaoRespostaUsuario = 4;
					break;
				}
				if(posicaoRespostaCorreta[posicaoRespostaUsuario] == "1") {
					System.out.println("\nResposta Correta!\n");
					respostaUser[i] = "correta";
					correta++;
				} else {
					System.out.println("\nResposta Incorreta!\n");
					respostaUser[i] = "incorreta";
				}
			}
			System.out.println(feedbackResposta(respostaUser));
			if(correta >= 3) {
				if(sala == 3) {
					System.out.println("\t\t\t\t██╗    ██╗██╗███╗   ██╗     ██████╗  █████╗ ███╗   ███╗███████╗\n" + 
									   "\t\t\t\t██║    ██║██║████╗  ██║    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝\n" + 
									   "\t\t\t\t██║ █╗ ██║██║██╔██╗ ██║    ██║  ███╗███████║██╔████╔██║█████╗  \n" + 
									   "\t\t\t\t██║███╗██║██║██║╚██╗██║    ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  \n" + 
									   "\t\t\t\t╚███╔███╔╝██║██║ ╚████║    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗\n" + 
									   "\t\t\t\t ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝     ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");
					System.exit(0);
				} else {
					System.out.println("\t\t\t\t\t\t\tPróxima sala...\n");
				}
				sala++;
				fim = false;
			} else {
				System.out.println("\t\t\t\t ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n" + 
								   "\t\t\t\t██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n" + 
								   "\t\t\t\t██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n" + 
								   "\t\t\t\t██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n" + 
								   "\t\t\t\t╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n" + 
								   "\t\t\t\t ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝");
				System.exit(0);
				fim = true;
			}
		} while(sala <= 3 && !fim);
	}
	
	static String feedbackResposta(String respostas[]) {
		String retorno = "";
		for(int i = 0; i <= respostas.length-1; i++) {
			//checa a correspodencia das posições do arrya com as questões ex: posicao 0 do array == desafio1
			if (i == 0) {
				//verifica se o usuário acertou ou errou o desafio, adicionando a String retorno se foi acerto ou erro
				if(respostas[i].equals("correta")) {
					retorno += "\t\t\t\t\t\t ___________________________\n";
					retorno += "\t\t\t\t\t\t|                           |\n";
					retorno += "\t\t\t\t\t\t| Você acertou a pergunta "+(i+1)+" |\n";
				} else {
					retorno += "\t\t\t\t\t\t ___________________________\n";
					retorno += "\t\t\t\t\t\t|                           |\n";
					retorno += "\t\t\t\t\t\t| Você errou a pergunta "+(i+1)+"   |\n";
				}
			} else if (i == 1) {
				if(respostas[i].equals("correta")) {
					retorno += "\t\t\t\t\t\t| Você acertou a pergunta "+(i+1)+" |\n";
				} else {
					retorno += "\t\t\t\t\t\t| Você errou a pergunta "+(i+1)+"   |\n";
				}
			} else if (i == 2) {
				if(respostas[i].equals("correta")) {
					retorno += "\t\t\t\t\t\t| Você acertou a pergunta "+(i+1)+" |\n";
				} else {
					retorno += "\t\t\t\t\t\t| Você errou a pergunta "+(i+1)+"   |\n";
				}
			} else if (i == 3) {
				if(respostas[i].equals("correta")) {
					retorno += "\t\t\t\t\t\t| Você acertou a pergunta "+(i+1)+" |\n";
				} else {
					retorno += "\t\t\t\t\t\t| Você errou a pergunta "+(i+1)+"   |\n";
				}
			} else if (i == 4) {
				if(respostas[i].equals("correta")) {
					retorno += "\t\t\t\t\t\t| Você acertou a pergunta "+(i+1)+" |\n";
					retorno += "\t\t\t\t\t\t|___________________________|\n";
				} else {
					retorno += "\t\t\t\t\t\t| Você errou a pergunta "+(i+1)+"   |\n";
					retorno += "\t\t\t\t\t\t|___________________________|\n";
				}
			}
		}
		return retorno;
	}
	
	static int[] sorteiaResposta() {
		
		String possibilidades[] = {
				"01234", "01243", "01324", "01342", "01423", "01432", "02134", "02143", "02314", "02341", "02413", "02431", "03124",
				"03142", "03214", "03241", "03412", "03421", "04123", "04132", "04213", "04231", "04312", "04321", "10234", "10243",
				"10324", "10342", "10423", "10432", "12034", "12043", "12304", "12340", "12403", "12430", "13024", "13042", "13204", 
				"13240", "13402", "13420", "14023", "14032", "14203", "14230", "14302", "14320", "20134", "20143", "20314", "20341",
				"20413", "20431", "21034", "21043", "21304", "21340", "21403", "21430", "23014", "23041", "23104", "23140", "23401",
				"23410", "24013", "24031", "24103", "24130", "24301", "24310", "30124", "30142", "30214", "30241", "30412", "30421",
				"31024", "31042", "31204", "31240", "31402", "31420", "32014", "32041", "32104", "32140", "32401", "32410", "34012",
				"34021", "34102", "34120", "34201", "34210", "40123", "40132", "40213", "40231", "40312", "40321", "41023", "41032", 
				"41203", "41230", "41302", "41320", "42013", "42031", "42103", "42130", "42301", "42310", "43012", "43021", "43102", 
				"43120", "43201", "43210"
		};
		int nmr = sort.nextInt(120);
		int posicaoResposta[] = new int[5];
		
		for(int i = 0; i < posicaoResposta.length; i++) {
			if(i == 4) {
				posicaoResposta[i] = Integer.parseInt(possibilidades[nmr].substring(i));
			} else {
				posicaoResposta[i] = Integer.parseInt(possibilidades[nmr].substring(i, i+1));
			}
		}
		return posicaoResposta;
	}
	
	static String[] respostaCorreta(int posResposta[], String respostas[][], int questao) {
		String respostasCorretas[] = new String[5];
		String respostasPosicaoCorreta[] = new String[5];
		int posicao = 0;
		for(int res = 0; res < respostas.length; res++) {
			if(respostas[res][0].equals(String.valueOf(questao))) {
				for(int i = posicao; i < respostasCorretas.length;) {
					respostasCorretas[i] = respostas[res][2];
					posicao++;
					break;
				}
			}
		}
		
		for(int i = 0; i < respostasPosicaoCorreta.length; i++) {
			respostasPosicaoCorreta[i] = respostasCorretas[posResposta[i]];
		}
		return respostasPosicaoCorreta;
	}
	
	static String imprimeResposta(String respostas[][], String alternativas[], int questao, int posResposta[]) {
		String resposta = "";
		String possibilidadesResposta[] = new String[5];
		int posicao = 0;
		for(int res = 0; res < respostas.length; res++) {
			if(respostas[res][0].equals(String.valueOf(questao))) {
				for(int i = posicao; i < possibilidadesResposta.length;) {
					possibilidadesResposta[i] = respostas[res][1];
					posicao++;
					break;
				}
			}
		}
		for(int i = 0; i < possibilidadesResposta.length; i++) {
			resposta += alternativas[i]+""+possibilidadesResposta[posResposta[i]]+"\n";
		}
		return resposta;
	}
	
	static String imprimePergunta(String perguntas[][], int questao) {
		String pergunta = "";
		for(int per = 0; per < perguntas.length; per++) {
			if(perguntas[per][2].equals(String.valueOf(questao))) {
				pergunta = perguntas[per][3];
			}
		}
		return pergunta;
	}
	
	static int[] escolhePerguntaSala(int nivel, String perguntas[][], int sala) {
		int posicaoQuestao[] = new int[5];
		int primeiro = 0,
			nmrGerado = 0;
		String numerosAnteriores = "";
		int prim = 0;
		
		for(int p = 0; p < perguntas.length; p++) {
			while(perguntas[p][0].equals(String.valueOf(nivel)) && perguntas[p][1].equals(String.valueOf(sala))) {
				primeiro = p+1;
				break;
			}
			if(primeiro != 0) {
				break;
			}
		}
		prim = primeiro;
		for(int i = 0; i < posicaoQuestao.length; i++) {
			nmrGerado = sort.nextInt(((prim+6) - prim) + 1) + prim;
			if(i == 0) {
				posicaoQuestao[i] = nmrGerado;
				numerosAnteriores += "#"+nmrGerado;
			} else {
				for(int j = 0; j < i; j++) {
					if(numerosAnteriores.contains(String.valueOf(nmrGerado)) || nmrGerado == 0) {
						nmrGerado = sort.nextInt(((prim+6) - prim) + 1) + prim;
						j = 0;
					} else {
						if(nmrGerado == 0) {
							j = 0;
						} else {
							posicaoQuestao[i] = nmrGerado;
							numerosAnteriores += "#"+nmrGerado;
							break;
						}
						
					}
				}
			}
		}
		return posicaoQuestao;
	}
	
	static int selecionaNivelJogo() {
		int x = 0,
			nivel = 0;
		do {
			System.out.println("Entre com o nível de dificuldade:\n1 - Fácil\n2 - Intermediário \n3 - Difícil");
			nivel = sc.nextInt();
			if(nivel > 0 && nivel <= 3) {
				x = 1;
			} else {
				System.out.println("Opção de nível inválida! Entre com uma opção existente.");
			}
		}while(x != 1);
		return nivel;
	}
	
	static String[][] montaPerguntas(String matriz[][]) {
		String perguntas[][] = {
				{"1", "1", "1", "Normalmente, quantos litros de sangue uma pessoa tem? Em média, quantos são retirados numa doação de sangue?"},
				{"1", "1", "2", "De quem é a famosa frase 'Penso, logo existo'?"},
				{"1", "1", "3", "De onde é a invenção do chuveiro elétrico?"},
				{"1", "1", "4", "Quais o menor e o maior país do mundo?"},
				{"1", "1", "5", "Qual o nome do presidente do Brasil que ficou conhecido como Jango?"},
				{"1", "1", "6", "Qual o grupo em que todas as palavras foram escritas corretamente?"},
				{"1", "1", "7", "Qual o livro mais vendido no mundo a seguir à Bíblia?"},
				{"1", "2", "8", "Quantas casas decimais tem o número pi?"},
				{"1", "2", "9", "Atualmente, quantos elementos químicos a tabela periódica possui?"},
				{"1", "2", "10", "Quais os países que têm a maior e a menor expectativa de vida do mundo?"},
				{"1", "2", "11", "O que a palavra legend significa em português?"},
				{"1", "2", "12", "Qual o número mínimo de jogadores numa partida de futebol?"},
				{"1", "2", "13", "Quais os principais autores do Barroco no Brasil?"},
				{"1", "2", "14", "Quais as duas datas que são comemoradas em novembro?"},
				{"1", "3", "15", "Quem pintou 'Guernica'?"},
				{"1", "3", "16", "Quanto tempo a luz do Sol demora para chegar à Terra?"},
				{"1", "3", "17", "Qual a tradução da frase 'Fabiano cogió su saco antes de salir'?"},
				{"1", "3", "18", "Qual a nacionalidade de Che Guevara?"},
				{"1", "3", "19", "Quais são os três predadores do reino animal reconhecidos pela habilidade de caçar em grupo, se camuflar para surpreender as presas e possuir sentidos apurados, respectivamente:"},
				{"1", "3", "20", "Qual a altura da rede de vôlei nos jogos masculino e feminino?"},
				{"1", "3", "21", "Em que ordem surgiram os modelos atômicos?"},
				{"2", "1", "22", "Qual personagem folclórico costuma ser agradado pelos caçadores com a oferta de fumo?"},
				{"2", "1", "23", "Em que período da pré-história o fogo foi descoberto?"},
				{"2", "1", "24", "Qual das alternativas abaixo apenas contêm classes de palavras?"},
				{"2", "1", "25", "Qual a montanha mais alta do Brasil?"},
				{"2", "1", "26", "Qual a velocidade da luz?"},
				{"2", "1", "27", "Em qual local da Ásia o português é língua oficial?"},
				{"2", "1", "28", "'It is six twenty ou twenty past six'. Que horas são em inglês?"},
				{"2", "2", "29", "Quem é o autor de 'O Príncipe'?"},
				{"2", "2", "30", "Como é a conjugação do verbo caber na 1.ª pessoa do singular do presente do indicativo?"},
				{"2", "2", "31", "Quais destas construções famosas ficam nos Estados Unidos?"},
				{"2", "2", "32", "Quais destas doenças são sexualmente transmissíveis?"},
				{"2", "2", "33", "Qual destes países é transcontinental?"},
				{"2", "2", "34", "Em qual das orações abaixo a palavra foi empregada incorretamente?"},
				{"2", "2", "35", "Qual foi o recurso utilizado inicialmente pelo homem para explicar a origem das coisas?"},
				{"2", "3", "36", "Qual das alternativas menciona apenas símbolos nacionais?"},
				{"2", "3", "37", "Quais os planetas do sistema solar?"},
				{"2", "3", "38", "Qual era o nome de Aleijadinho?"},
				{"2", "3", "39", "Júpiter e Plutão são os correlatos romanos de quais deuses gregos?"},
				{"2", "3", "40", "Qual o maior animal terrestre?"},
				{"2", "3", "41", "Qual o tema do famoso discurso Eu Tenho um Sonho, de Martin Luther King?"},
				{"2", "3", "42", "Que líder mundial ficou conhecida como 'Dama de Ferro'?"},
				{"3", "1", "43", "O que são Acordo de Paris e Tríplice Aliança respectivamente?"},
				{"3", "1", "44", "Quais os nomes dos três Reis Magos?"},
				{"3", "1", "45", "Quais os principais heterônimos de Fernando Pessoa?"},
				{"3", "1", "46", "Qual a religião monoteísta que conta com o maior número de adeptos no mundo?"},
				{"3", "1", "47", "Qual desses filmes foi baseado na obra de Shakespeare?"},
				{"3", "1", "48", "Quem foi o primeiro homem a pisar na Lua? Em que ano aconteceu?"},
				{"3", "1", "49", "Qual o nome do cientista que descobriu o processo de pasteurização e a vacina contra a raiva?"},
				{"3", "2", "50", "As pessoas de qual tipo sanguíneo são consideradas doadores universais?"},
				{"3", "2", "51", "Quais são os cromossomos que determinam o sexo masculino?"},
				{"3", "2", "52", "Em que estado australiano fica situada a cidade de Sydney?"},
				{"3", "2", "53", "Que organização juvenil foi fundado por Baden-Powell?"},
				{"3", "2", "54", "Quem amamentou os gêmeos Rômulo e Remo?"},
				{"3", "2", "55", "No exterior de que famoso edifício francês foi construída uma enorme pirâmide de vidro em 1989?"},
				{"3", "2", "56", "Como se chamam os vasos que transportam sangue do coração para a periferia do corpo?"},
				{"3", "3", "57", "Com que dois países faz fronteira o Equador?"},
				{"3", "3", "58", "Que animal gruguleja?"},
				{"3", "3", "59", "Qual é o maior arquipélago da Terra?"},
				{"3", "3", "60", "Que substância é absorvida pelas plantas e expirada por todos os seres vivos?"},
				{"3", "3", "61", "Em que oceano fica Madagascar?"},
				{"3", "3", "62", "Que artista é conhecido como um dos expoentes máximos do Ready-Mades?"},
				{"3", "3", "63", "Qual o metal cujo símbolo químico é o Au?"}
		};
		return perguntas;
	}
	
	static String[][] montaRespostas(String matriz[][]){
		String respostas[][] = {
				{"1", "Tem entre 2 a 4 litros. São retirados 450 mililitros", "0"},
				{"1", "Tem entre 4 a 6 litros. São retirados 450 mililitros", "1"},
				{"1", "Tem 10 litros. São retirados 2 litros", "0"},
				{"1", "Tem 7 litros. São retirados 1,5 litros", "0"},
				{"1", "Tem 0,5 litros. São retirados 0,5 litros", "0"},
				{"2", "Platão", "0"},
				{"2", "Galileu Galilei", "0"},
				{"2", "Descartes", "1"},
				{"2", "Sócrates", "0"},
				{"2", "Francis Bacon", "0"},
				{"3", "França", "0"},
				{"3", "Inglaterra", "0"},
				{"3", "Brasil", "1"},
				{"3", "Austrália", "0"},
				{"3", "Itália", "0"},
				{"4", "Vaticano e Rússia", "1"},
				{"4", "Nauru e China", "0"},
				{"4", "Mônaco e Canadá", "0"},
				{"4", "Malta e Estados Unidos", "0"},
				{"4", "São Marino e Índia", "0"},
				{"5", "Jânio Quadros", "0"},
				{"5", "Jacinto Anjos", "0"},
				{"5", "Getúlio Vargas", "0"},
				{"5", "João Figueiredo", "0"},
				{"5", "João Goulart", "1"},
				{"6", "Asterístico, beneficiente, meteorologia, entertido", "0"},
				{"6", "Asterisco, beneficente, meteorologia, entretido", "1"},
				{"6", "Asterisco, beneficente, metereologia, entretido", "0"},
				{"6", "Asterístico, beneficiente, metereologia, entretido", "0"},
				{"6", "Asterisco, beneficiente, metereologia, entretido", "0"},
				{"7", "O Senhor dos Anéis", "0"},
				{"7", "Dom Quixote", "1"},
				{"7", "O Pequeno Príncipe", "0"},
				{"7", "Ela, a Feiticeira", "0"},
				{"7", "Um Conto de Duas Cidades", "0"},
				{"8", "Duas", "0"},
				{"8", "Centenas", "0"},
				{"8", "Infinitas", "1"},
				{"8", "Vinte", "0"},
				{"8", "Milhares", "0"},
				{"9", "113", "0"},
				{"9", "109", "0"},
				{"9", "108", "0"},
				{"9", "118", "1"},
				{"9", "92", "0"},
				{"10", "Japão e Serra Leoa", "1"},
				{"10", "Austrália e Afeganistão", "0"},
				{"10", "Itália e Chade", "0"},
				{"10", "Brasil e Congo", "0"},
				{"10", "Estados Unidos e Angola", "0"},
				{"11", "Legenda", "0"},
				{"11", "Conto", "0"},
				{"11", "História", "0"},
				{"11", "Lenda", "1"},
				{"11", "Legendário", "0"},
				{"12", "8", "0"},
				{"12", "10", "0"},
				{"12", "9", "0"},
				{"12", "5", "0"},
				{"12", "7", "1"},
				{"13", "Gregório de Matos, Bento Teixeira e Manuel Botelho de Oliveira", "1"},
				{"13", "Miguel de Cervantes, Gregório de Matos e Danthe Alighieri", "0"},
				{"13", "Padre Antônio Vieira, Padre Manuel de Melo e Gregório de Matos", "0"},
				{"13", "Castro Alves, Bento Teixeira e Manuel Botelho de Oliveira", "0"},
				{"13", "Álvares de Azevedo, Gregório de Matos e Bento Teixeira", "0"},
				{"14", "Independência do Brasil e Dia da Bandeira", "0"},
				{"14", "Proclamação da República e Dia Nacional da Consciência Negra", "1"},
				{"14", "Dia do Médico e Dia de São Lucas", "0"},
				{"14", "Dia de Finados e Dia Nacional do Livro", "0"},
				{"14", "Black Friday e Dia da Árvore", "0"},
				{"15", "Paul Cézanne", "0"},
				{"15", "Pablo Picasso", "1"},
				{"15", "Diego Rivera", "0"},
				{"15", "Tarsila do Amaral", "0"},
				{"15", "Salvador Dalí", "0"},
				{"16", "12 minutos", "0"},
				{"16", "1 dia", "0"},
				{"16", "12 horas", "0"},
				{"16", "8 minutos", "1"},
				{"16", "segundos", "0"},
				{"17", "Fabiano coseu seu paletó antes de sair", "0"},
				{"17", "Fabiano fechou o saco antes de sair", "0"},
				{"17", "Fabiano pegou seu paletó antes de sair", "1"},
				{"17", "Fabiano cortou o saco antes de cair", "0"},
				{"17", "Fabiano rasgou seu paletó antes de cair", "0"},
				{"18", "Cubana", "0"},
				{"18", "Peruana", "0"},
				{"18", "Panamenha", "0"},
				{"18", "Boliviana", "0"},
				{"18", "Argentina", "1"},
				{"19", "Tubarão branco, crocodilo e sucuri", "0"},
				{"19", "Tigre, gavião e orca", "0"},
				{"19", "Hiena, urso branco e lobo cinzento", "1"},
				{"19", "Orca, onça e tarântula", "0"},
				{"19", "Leão, tubarão branco e urso cinzento", "0"},
				{"20", "2,4 para ambos", "0"},
				{"20", "2,5 m e 2,0 m", "0"},
				{"20", "1,8 m e 1,5 m", "0"},
				{"20", "2,45 m e 2,15 m", "0"},
				{"20", "2,43 m e 2,24 m", "1"},
				{"21", "Thomson, Dalton, Rutherford, Rutherford-Bohr", "0"},
				{"21", "Rutherford-Bohr, Rutherford, Thomson, Dalton", "0"},
				{"21", "Dalton, Rutherford-Bohr, Thomson, Rutherford", "0"},
				{"21", "Dalton, Thomson, Rutherford-Bohr, Rutherford", "0"},
				{"21", "Dalton, Thomson, Rutherford, Rutherford-Bohr", "1"},
				{"22", "Caipora", "1"},
				{"22", "Saci", "0"},
				{"22", "Lobisomem", "0"},
				{"22", "Boitatá", "0"},
				{"22", "Negrinho do Pastoreio", "0"},
				{"23", "Neolítico", "0"},
				{"23", "Paleolítico", "1"},
				{"23", "Idade dos Metais", "0"},
				{"23", "Período da Pedra Polida", "0"},
				{"23", "Idade Média", "0"},
				{"24", "Vogais, semivogais e consoantes", "0"},
				{"24", "Artigo, verbo transitivo e verbo intransitivo", "0"},
				{"24", "Fonologia, Morfologia e Sintaxe", "0"},
				{"24", "Hiatos, ditongos e tritongos", "1"},
				{"24", "Substantivo, verbo e preposição", "0"},
				{"25", "Pico da Neblina", "1"},
				{"25", "Pico Paraná", "0"},
				{"25", "Monte Roraima", "0"},
				{"25", "Pico Maior de Friburgo", "0"},
				{"25", "Pico da Bandeira", "0"},
				{"26", "300 000 000 metros por segundo (m/s)", "0"},
				{"26", "150 000 000 metros por segundo (m/s)", "0"},
				{"26", "199 792 458 metros por segundo (m/s)", "0"},
				{"26", "299 792 458 metros por segundo (m/s)", "1"},
				{"26", "30 000 000 metros por segundo (m/s)", "0"},
				{"27", "Índia", "0"},
				{"27", "Filipinas", "0"},
				{"27", "Moçambique", "0"},
				{"27", "Macau", "1"},
				{"27", "Portugal", "0"},
				{"28", "12:06", "0"},
				{"28", "6:20", "1"},
				{"28", "2:20", "0"},
				{"28", "6:02", "0"},
				{"28", "12:20", "0"},
				{"29", "Maquiavel", "1"},
				{"29", "Antoine de Saint-Exupéry", "0"},
				{"29", "Montesquieu", "0"},
				{"29", "Thomas Hobbes", "0"},
				{"29", "Rousseau", "0"},
				{"30", "Eu caibo", "1"},
				{"30", "Ele cabe", "0"},
				{"30", "Que eu caiba", "0"},
				{"30", "Eu cabo", "0"},
				{"30", "Nenhuma das alternativas", "0"},
				{"31", "Estátua da Liberdade, Golden Gate Bridge e Empire State Building", "1"},
				{"31", "Estátua da Liberdade, Big Ben e The High Line", "0"},
				{"31", "Angkor Wat, Taj Mahal e Skywalk no Grand Canyon", "0"},
				{"31", "Lincoln Memorial, Sidney Opera House e Burj Khalifa", "0"},
				{"31", "30 St Mary Axe, The High Line e Residencial 148 Spruce Street", "0"},
				{"32", "Aids, tricomoníase e ebola", "0"},
				{"32", "Chikungunya, aids e herpes genital", "0"},
				{"32", "Gonorreia, clamídia e sífilis", "1"},
				{"32", "Botulismo, cistite e gonorreia", "0"},
				{"32", "Hepatite B, febre tifoide e hanseníase", "0"},
				{"33", "Rússia", "1"},
				{"33", "Filipinas", "0"},
				{"33", "Istambul", "0"},
				{"33", "Groenlândia", "0"},
				{"33", "Tanzânia", "0"},
				{"34", "Mais uma vez, portou-se mal.", "0"},
				{"34", "É um homem mal.", "1"},
				{"34", "Esse é o mal de todos.", "0"},
				{"34", "Mal falou nele, o fulano apareceu.", "0"},
				{"34", "É um mau vendedor.", "0"},
				{"35", "A Filosofia", "0"},
				{"35", "A Biologia", "0"},
				{"35", "A Matemática", "0"},
				{"35", "A Astronomia", "0"},
				{"35", "A Mitologia", "1"},
				{"36", "Bandeira insígnia da presidência, bandeira nacional, brasão, hinos e selo", "0"},
				{"36", "Bandeira nacional, armas nacionais, hino nacional e selo nacional", "1"},
				{"36", "Bandeira nacional, brasão, hino nacional e hino da independência", "0"},
				{"36", "Bandeira nacional, cores nacionais, hino nacional e hino da independência", "0"},
				{"36", "Bandeira insígnia da presidência, brasão flora e fauna e hinos", "0"},
				{"37", "Terra, Vênus, Saturno, Urano, Júpiter, Marte, Netuno, Mercúrio", "1"},
				{"37", "Júpiter, Marte, Mercúrio, Netuno, Plutão, Saturno, Terra, Urano, Vênus", "0"},
				{"37", "Vênus, Saturno, Urano, Júpiter, Marte, Netuno, Mercúrio", "0"},
				{"37", "Júpiter, Marte, Mercúrio, Netuno, Plutão, Saturno, Sol, Terra, Urano, Vênus", "0"},
				{"37", "Terra, Vênus, Saturno, Júpiter, Marte, Netuno, Mercúrio", "0"},
				{"38", "Alexandrino Francisco Lisboa", "0"},
				{"38", "Manuel Francisco Lisboa", "0"},
				{"38", "Alex Francisco Lisboa", "0"},
				{"38", "Francisco Antônio Lisboa", "0"},
				{"38", "Antônio Francisco Lisboa", "1"},
				{"39", "Ares e Hermes", "0"},
				{"39", "Cronos e Apolo", "0"},
				{"39", "Zeus e Hades", "1"},
				{"39", "Dionísio e Deméter", "0"},
				{"39", "Zeus e Ares", "0"},
				{"40", "Baleia Azul", "0"},
				{"40", "Dinossauro", "0"},
				{"40", "Elefante africano", "1"},
				{"40", "Tubarão Branco", "0"},
				{"40", "Girafa", "0"},
				{"41", "Igualdade das raças", "1"},
				{"41", "Justiça para os menos favorecidos", "0"},
				{"41", "Intolerância religiosa", "0"},
				{"41", "Prêmio Nobel da Paz", "0"},
				{"41", "Luta contra o Apartheid", "0"},
				{"42", "Dilma Rousseff", "0"},
				{"42", "Angela Merkel", "0"},
				{"42", "Margaret Thatcher", "1"},
				{"42", "Hillary Clinton", "0"},
				{"42", "Christine Lagarde", "0"},
				{"43", "Acordo ortográfico entre países cuja língua oficial é o francês e Acordo de cooperação financeira internacional entre as três maiores potências mundiais", "0"},
				{"43", "Acordo entre países europeus acerca da imigração e Acordo econômico entre Inglaterra, Rússia a França", "0"},
				{"43", "Acordo entre vários países acerca das consequências do aquecimento global e Acordo de cooperação financeira internacional entre as três maiores potências mundiais", "0"},
				{"43", "Acordo de cooperação financeira internacional entre as três maiores potências mundiais e Acordo entre vários países acerca das consequências do aquecimento global", "0"},
				{"43", "Acordo entre vários países acerca das consequências do aquecimento global e Acordo entre Alemanha, império Austro-Húngaro e Itália acerca de apoio em caso de guerra", "1"},
				{"44", "Gaspar, Nicolau e Natanael", "0"},
				{"44", "Belchior, Gaspar e Baltazar", "1"},
				{"44", "Belchior, Gaspar e Nataniel", "0"},
				{"44", "Gabriel, Benjamim e Melchior", "0"},
				{"44", "Melchior, Noé e Galileu", "0"},
				{"45", "Alberto Caeiro, Ricardo Reis e Álvaro de Campos", "1"},
				{"45", "Ariano Suassuna, Raul Bopp e Quincas Borba", "0"},
				{"45", "Bento Teixeira, Ricardo Reis e Haroldo de Campos", "0"},
				{"45", "Alberto Caeiro, Ricardo Leite e Augusto de Campos", "0"},
				{"45", "Bento Teixeira, Ricardo Reis e Augusto de Campos", "0"},
				{"46", "Judaísmo", "0"},
				{"46", "Zoroastrismo", "0"},
				{"46", "Islamismo", "0"},
				{"46", "Cristianismo", "1"},
				{"46", "Hinduísmo", "0"},
				{"47", "Muito Barulho por Nada (2012)", "1"},
				{"47", "Capitães de Areia (2011)", "0"},
				{"47", "A Dama das Camélias (1936)", "0"},
				{"47", "A Revolução dos Bichos (1954)", "0"},
				{"47", "Excalibur (1981)", "0"},
				{"48", "Yuri Gagarin, em 1961", "0"},
				{"48", "Buzz Aldrin, em 1969", "0"},
				{"48", "Charles Conrad, em 1969", "0"},
				{"48", "Charles Duke, em 1971", "0"},
				{"48", "Neil Armstrong, em 1969.", "1"},
				{"49", "Marie Curie", "0"},
				{"49", "Blaise Pascal", "0"},
				{"49", "Louis Pasteur", "1"},
				{"49", "Antoine Lavoisier", "0"},
				{"49", "Charles Darwin", "0"},
				{"50", "Tipo A", "0"},
				{"50", "Tipo B", "0"},
				{"50", "Tipo O", "1"},
				{"50", "Tipo AB", "0"},
				{"50", "Tipo ABO", "0"},
				{"51", "Os V", "0"},
				{"51", "Os X", "0"},
				{"51", "Os Y", "1"},
				{"51", "Os W", "0"},
				{"51", "Os Z", "0"},
				{"52", "Nova Gales do Sul", "1"},
				{"52", "Victoria", "0"},
				{"52", "Tasmânia", "0"},
				{"52", "Queensland", "0"},
				{"52", "Norfolk", "0"},
				{"53", "A juventude socialista", "0"},
				{"53", "O escotismo", "1"},
				{"53", "O clube dos aventureiros", "0"},
				{"53", "A associação juvenil", "0"},
				{"53", "A Organização mundial da juventude", "0"},
				{"54", "uma cabra", "0"},
				{"54", "uma vaca", "0"},
				{"54", "uma ovelha", "0"},
				{"54", "uma gata", "0"},
				{"54", "uma loba", "1"},
				{"55", "Torre Eiffel", "0"},
				{"55", "Petit Palais", "0"},
				{"55", "Grand Palais", "0"},
				{"55", "Museu do Louvre", "1"},
				{"55", "Arco do Triunfo", "0"},
				{"56", "veias", "0"},
				{"56", "átrios", "0"},
				{"56", "ventrículos", "0"},
				{"56", "artérias", "1"},
				{"56", "aurículos", "0"},
				{"57", "com o Brasil e com a Colômbia", "0"},
				{"57", "com a Colômbia e com a Venezuela", "0"},
				{"57", "com a Colômbia e com o Peru", "1"},
				{"57", "com o Peru e com o Equador", "0"},
				{"57", "com o Equador e o Brasil", "0"},
				{"58", "o pavão", "0"},
				{"58", "a garça", "0"},
				{"58", "o papagaio", "0"},
				{"58", "a cacatua", "0"},
				{"58", "o peru", "1"},
				{"59", "a Filipinas", "0"},
				{"59", "a Indonésia", "1"},
				{"59", "as Bahamas", "0"},
				{"59", "a Finlândia", "0"},
				{"59", "as Maldivas", "0"},
				{"60", "o oxigênio", "0"},
				{"60", "o nitrogênio", "0"},
				{"60", "o nitrato de sódio", "0"},
				{"60", "o dióxido de ferro", "0"},
				{"60", "o dióxido de carbono", "1"},
				{"61", "Oceano Índico", "1"},
				{"61", "Oceano Antártico", "0"},
				{"61", "Oceano Atlântico", "0"},
				{"61", "Oceano Pacífico", "0"},
				{"61", "Oceano Ártico", "0"},
				{"62", "Pablo Picasso", "0"},
				{"62", "Salvador Dalí", "0"},
				{"62", "Marcel Duchamp", "1"},
				{"62", "Van Gogh", "0"},
				{"62", "Leonardo da Vinci", "0"},
				{"63", "Cobre", "0"},
				{"63", "Prata", "0"},
				{"63", "Mercúrio", "0"},
				{"63", "Ouro", "1"},
				{"63", "Manganês", "0"}
		};
		return respostas;
	}
}
