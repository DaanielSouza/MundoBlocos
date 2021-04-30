import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    // INSTANCIAR AS LISTAS QUE SERAO UTILIZADAS INICIALMENTE
    public static Lista zero = new Lista(0);
    public static Lista um = new Lista(1);
    public static Lista dois = new Lista(2);
    public static Lista tres = new Lista(3);
    public static Lista quatro = new Lista(4);
    // INSTANCIAR AS LISTAS QUE SERAO UTILIZADAS INICIALMENTE

    //VETOR DE LISTAS
    public static Lista[] vetor = new Lista[5];

    public static void main(String[] args) throws Exception {

        //INCLUI AS LISTAS NO VETOR
        vetor[0] = zero;
        vetor[1] = um;
        vetor[2] = dois;
        vetor[3] = tres;
        vetor[4] = quatro;
        //INCLUI AS LISTAS NO VETOR

        //SCANNER PARA ARMAZENAS O ARQUIVO DE TEXTO
        Scanner in = new Scanner(new FileReader("trab/src/entrada.txt"));

        while (in.hasNextLine()) {
            String line = in.nextLine();//RECEBE A LINHA QUE O WHILE ESTA PERCORRENDO NO MOMENTO

            if (line.equals("quit")) {//CASO A LINHA SEJA "QUIT", SAIR DO PROGRAMA
                FileWriter arq = new FileWriter("trab/src/saida.txt");//GERA O ARQUIVO DE SAIDA
                PrintWriter gravarArq = new PrintWriter(arq);//GERA O ARQUIVO DE SAIDA

                //GERAR A IMPRESSAO DAS LISTAS//
                gravarArq.print("0: ");
                gravarArq.println(vetor[0].retornarLista());
                gravarArq.printf("1: ");
                gravarArq.println(vetor[1].retornarLista());
                gravarArq.printf("2: ");
                gravarArq.println(vetor[2].retornarLista());
                gravarArq.printf("3: ");
                gravarArq.println(vetor[3].retornarLista());
                gravarArq.printf("4: ");
                gravarArq.println(vetor[4].retornarLista());
                //GERAR A IMPRESSAO DAS LISTAS//

                gravarArq.close();//FECHA E SALVA O ARQUIVO
                break;
            } else {
                String[] lista = line.split(" ");
                if ((lista[0].equals("move") || lista[0].equals("pile"))
                        && (lista[2].equals("onto") || lista[2].equals("over"))) {
                    Integer valorPrimeiro = Integer.parseInt(lista[1]);// TRADUZ O VALOR PARA UMA POSIÇAO NA LISTA
                    Integer valorSegundo = Integer.parseInt(lista[3]);// TRADUZ O VALOR PARA UMA POSIÇAO NA LISTA

                    Integer primeiro = buscaIndice(valorPrimeiro);//BUSCA OS INDICES DO PRIMEIRO VALOR
                    Integer segundo = buscaIndice(valorSegundo);//BUSCA OS INDICES DO SEGUNDO VALOR

                    if (primeiro > -1 && segundo > -1) {//CASO OS INDICES SEJAM VALIDO
                        if (lista[0].equals("move")) {//SE O COMANDO FOR MOVE
                            if (lista[2].equals("over")) {//SE O COMANDO FOR OVER

                                returnAll(primeiro, vetor[primeiro].getTamanho());// RETORNA OS VALORES SUPERIORES PARA
                                                                                  // SUA
                                                                                  // POSIÇÃO ORIGINAL
                                vetor[segundo].addEnd(primeiro);// INSERIR O VALOR NA ULTIMA POSIÇÃO DA LISTA
                                vetor[primeiro].removerNo(valorPrimeiro);// REMOVE O NÓ DA PRIMEIRA LISTA

                            } else if (lista[2].equals("onto")) {//SE O COMANDO FOR ONTO

                                returnAll(segundo, vetor[segundo].getTamanho());// RETORNA OS VALORES SUPERIORES PARA
                                                                                // SUA
                                                                                // POSICAO ORIGINAL
                                returnAll(primeiro, vetor[primeiro].getTamanho());// RETORNA OS VALORES SUPERIORES PARA
                                                                                  // SUA
                                                                                  // POSICAO ORIGINAL
                                vetor[primeiro].removeAll(primeiro);// REMOVE O NO DA PRIMEIRA LISTA
                                vetor[segundo].removeAll(segundo);// REMOVE O NO DA SEGUNDA LISTA

                                vetor[segundo].addEnd(primeiro);// INSERIR O VALOR NA ÚLTIMA POSIÇÃO DA LISTA
                                vetor[primeiro].removerNo(valorPrimeiro);//REMOVE O NO DA PRIMEIRA LISTA

                            }
                        } else if (lista[0].equals("pile")) {//OPCAO PILE
                            if (lista[2].equals("over")) {//PILE OVER

                                moveAll(segundo, primeiro);//MOVE TUDO DO 2º PARAMETRO PARA A POSICAO ESCOLHIDA NO 1º
                                vetor[primeiro].removeAll();//REMOVE TODOS OS ITENS DA POSICAO PASSADA

                            } else if (lista[2].equals("onto")) {//PILE ONTO

                                returnAll(segundo, vetor[segundo].getTamanho());//RETORNA TODOS O INDICE PASSANDO O ITEM E SEU TAMANHO
                                moveAll(segundo, primeiro);//MOVE TODOS DO 2° PARAMETRO PARA O 1º PARÂMETRO
                                vetor[primeiro].removeAll();//REMOVE TODOS OS ITENS DO INDICE ORIGINAL

                            }
                        }
                    } else {
                        print("Erro de formato!");//CASO SEJA INFORMADO ARQUIVOS COM ERROS NO INICIO
                    }
                }

            }
        }
    }

    private static Integer buscaIndice(Integer info) {//RETORNA O INDICE DO DADO PESQUISADO
        Integer indice = -1;
        Boolean validador = false;

        for (Integer i = 0; i < 5; i++) {//FOR QUE PERCORRE O VETOR CRIADO
            for (Integer j = 0; j < vetor[i].getTamanho(); j++) {//FOR QUE PERCORRE AS POSICOES DENTRO DA LISTA ENCADEADA
                if (j == 0) {
                    if (info == vetor[i].inicio.getConteudo()) {//COMPARA O CONTEUDO COM O PARAMETRO INFO PASSADO
                        validador = true;
                        indice = i;//VALOR DO INDICE
                        break;//PARA O LOOP
                    }
                } else if (j == 1) {
                    if (info == vetor[i].inicio.proximo.getConteudo()) {
                        validador = true;
                        indice = i;
                        break;
                    }
                } else if (j == 2) {
                    if (info == vetor[i].inicio.proximo.proximo.getConteudo()) {
                        validador = true;
                        indice = i;
                        break;
                    }
                } else if (j == 3) {
                    if (info == vetor[i].inicio.proximo.proximo.proximo.getConteudo()) {
                        validador = true;
                        indice = i;
                        break;
                    }
                } else if (j == 4) {
                    if (info == vetor[i].inicio.proximo.proximo.proximo.proximo.getConteudo()) {
                        validador = true;
                        indice = i;
                        break;
                    }
                }
            }
            if (validador == true) {
                break;
            }
        }

        return indice;
    }

    private static void returnAll(Integer posicao, Integer tamanho) {//RETORNA TODOS OS ITENS PARA SEU DEVIDO LUGAR
        if (tamanho > 1) {
            vetor[posicao].tamanho = 1;
            returnDois(posicao);
            if (tamanho > 2) {
                returnTres(posicao);
                if (tamanho > 3) {
                    returnQuatro(posicao);
                    if (tamanho > 4) {
                        returnCinco(posicao);
                    }
                }
            }
        }
    }

    private static void moveAll(Integer posicao, Integer primeiro) {//MOVE TODOS OS ITENS PARA UM LOCAL ESCOLHIDO
        Integer tamanho = vetor[primeiro].getTamanho();
        if (tamanho > 0) {
            vetor[posicao].addEnd(vetor[primeiro].inicio.getConteudo());
            if (tamanho > 1) {
                moveDois(posicao, primeiro);
                if (tamanho > 2) {
                    moveTres(posicao, primeiro);
                    if (tamanho > 3) {
                        moveQuatro(posicao, primeiro);
                        if (tamanho > 4) {
                            moveCinco(posicao, primeiro);
                        }
                    }
                }
            }
        }
    }

    /*ALTERAÇÕES DE POSICOES NAS LISTAS - EXISTE ALGUMA MANEIRA MELHOR, MAS ESSA FOI A QUE EU CONSEGUI KKKK */

    private static void moveDois(Integer posicao, Integer primeiro) {
        Integer conteudo = vetor[primeiro].inicio.proximo.getConteudo();
        vetor[posicao].addEnd(conteudo);
    }

    private static void moveTres(Integer posicao, Integer primeiro) {
        Integer conteudo = vetor[primeiro].inicio.proximo.proximo.getConteudo();
        vetor[posicao].addEnd(conteudo);
    }

    private static void moveQuatro(Integer posicao, Integer primeiro) {
        Integer conteudo = vetor[primeiro].inicio.proximo.proximo.proximo.getConteudo();
        vetor[posicao].addEnd(conteudo);
    }

    private static void moveCinco(Integer posicao, Integer primeiro) {
        Integer conteudo = vetor[primeiro].inicio.proximo.proximo.proximo.proximo.getConteudo();
        vetor[posicao].addEnd(conteudo);
    }

    private static void returnDois(Integer posicao) {
        Integer novaPosicao = vetor[posicao].inicio.proximo.getConteudo();
        vetor[novaPosicao].addBegin(novaPosicao);
    }

    private static void returnTres(Integer posicao) {
        Integer novaPosicao = vetor[posicao].inicio.proximo.proximo.getConteudo();
        vetor[novaPosicao].addBegin(novaPosicao);
    }

    private static void returnQuatro(Integer posicao) {
        Integer novaPosicao = vetor[posicao].inicio.proximo.proximo.proximo.getConteudo();
        vetor[novaPosicao].addBegin(novaPosicao);
    }

    private static void returnCinco(Integer posicao) {
        Integer novaPosicao = vetor[posicao].inicio.proximo.proximo.proximo.proximo.getConteudo();
        vetor[novaPosicao].addBegin(novaPosicao);
    }

    /* FUNCAO GENERICA PARA SUBSTITUIR O SYSOUT*/

    private static void print(String msg) {
        System.out.println(msg);
    }

    private static void print(int msg) {
        System.out.println(msg);
    }

    private static void print(Boolean msg) {
        System.out.println(msg);
    }
}