public class No {
    private Integer info = null;//DADO DO BLOCO
    public No proximo;//PROXIMO OBJETO 

    public No(Integer conteudo) {
        this.info = conteudo;
    }//CONSTRUTOR

    public Integer getConteudo() {
        return this.info;
    }//METODO QUE RETORNA O CONTEUDO
}