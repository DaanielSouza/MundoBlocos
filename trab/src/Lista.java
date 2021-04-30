public class Lista {
    public No inicio = null;// ESPACO PARA ARMAZENAMENTO DA PRIMEIRA INFORMACAO

    public Integer tamanho = 0;// CONTROLE DE TAMANHO DO ARRAY
    private Integer limite = 5;// CONTROLE DE LIMITE DE TAMANHO DO ARRAY

    public void addBegin(Integer info) {
        if (tamanho < limite) {
            No no = new No(info);
            no.proximo = this.inicio;
            this.inicio = no;
            this.tamanho++;//INCREMENTADOR DE TAMANHO DA LISTA
        } else {
            System.out.println("Array cheio!");
        }
    }//INCLUI UM ITEM NO INICIO DA LISTA

    //NAO CONSEGUI PENSAR EM UMA MANEIRA MELHOR DE INCLUIR UM ITEM NO FINAL DA LISTA
    public void addEnd(Integer info) {//FUNÇÃO QUE INCLUI UM ITEM NO FINAL DA LISTA
        if (this.tamanho < limite) {
            No no = new No(info);
            if (tamanho == 0) {
                this.inicio = no;
                this.tamanho++;
            } else if (tamanho == 1) {
                this.inicio.proximo = no;
                this.tamanho++;
            } else if (tamanho == 2) {
                this.inicio.proximo.proximo = no;
                this.tamanho++;
            } else if (tamanho == 3) {
                this.inicio.proximo.proximo.proximo = no;
                this.tamanho++;
            } else if (tamanho == 4) {
                this.inicio.proximo.proximo.proximo.proximo = no;
                this.tamanho++;
            }
        }
    }

    //REMOVE O NÓ DE ACORDO COM O DADO PASSADO
    public void removerNo(Integer dados) {
        if (this.inicio.getConteudo() == dados) {//VERIFICA A EXISTENCIA DO DADO NA PRIMEIRA POSIÇÃO
            if(this.tamanho == 1){//POSICAO 1, NO VIRA NULO.
                this.inicio = null;
            } else {//CASO EXISTAM OUTROS ITENS NA LISTA
                this.inicio = this.inicio.proximo;
            }
            this.tamanho--;  //DECREMENTA O TAMANHO DA LISTA
        } else {//TRATA O RESTANTE DOS TAMANHOS DE LISTA
            if(this.inicio.proximo.getConteudo() == dados){
                this.inicio.proximo = this.inicio.proximo.proximo;
                this.tamanho--;
            } else {
                if (this.inicio.proximo.proximo.getConteudo() == dados) {
                    this.inicio.proximo = this.inicio.proximo.proximo;
                    this.tamanho--;
                } else {
                    if (this.inicio.proximo.proximo.proximo.getConteudo() == dados) {
                        this.inicio.proximo.proximo = this.inicio.proximo.proximo.proximo;
                        this.tamanho--;
                    }else {
                        if (this.inicio.proximo.proximo.proximo.proximo.getConteudo() == dados) {
                            this.inicio.proximo.proximo.proximo = this.inicio.proximo.proximo.proximo.proximo;
                            this.tamanho--;
                        } else {
                            if(this.inicio.proximo.proximo.proximo.proximo.getConteudo() == dados){
                                this.inicio.proximo.proximo.proximo.proximo = null;
                                this.tamanho--;
                            } else {
                                System.out.println("Não encontrado!");
                            }
                            
                        }
                    }
                } 
            }
        }

    }
    public void removeAll(){//REMOVE TODOS OS ITENS E ZERA O ARRAY
        this.inicio = null;
        this.tamanho = 0;
    }
    public void removeAll(Integer dados){//REINICIA OS ITENS DA LISTA
        this.inicio = new No(dados);
        this.tamanho = 1;
    }

    public Integer getTamanho() {//RETORNA O TAMANHO DA LISTA
        return this.tamanho;
    }

    public String retornarLista(){//RETORNA A LISTA PARA IMPRESSAO
        String resposta = "";

        if (this.tamanho > 0){//GERA A IMPRESSAO DE ACORDO COM O TAMANHO DA LISTA
            resposta = this.inicio.getConteudo().toString() + " ";
            if (this.tamanho > 1){
                String segundo = this.inicio.proximo.getConteudo().toString()+ " ";
                resposta =  resposta + segundo;
                if (this.tamanho > 2){
                    String terceiro = this.inicio.proximo.proximo.getConteudo().toString()+ " ";
                    resposta =  resposta + terceiro;
                    if (this.tamanho > 3){
                        String quarto = this.inicio.proximo.proximo.proximo.getConteudo().toString()+ " ";
                        resposta =  resposta + quarto;
                        if (this.tamanho > 4){
                            String cinco = this.inicio.proximo.proximo.proximo.proximo.getConteudo().toString()+ " ";
                            resposta =  resposta + cinco;
                        }
                    }
                } 
            }
        }
         
         
        return resposta;
    }

    public Lista(Integer info) {//CONSTRUTOR COM PARAMETRO INFORMANDO OS DADOS;
        if (tamanho == 0) {
            No no = new No(info);
            this.inicio = no;
            this.tamanho++;//INCREMENTAR O VALOR DO TAMANHO
        }
    }
}
