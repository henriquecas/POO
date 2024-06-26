import java.util.Random;

public class Personagem{

  String nome;
  int energia;
  private int fome;
  private int sono;
  private VetorDinamico estoque;



  Personagem(String string){
    nome = null;
    energia = 10;
    fome = 0;
    sono = 0;
    estoque = new VetorDinamico();
  }


  Personagem(int energia, int fome, int sono){
    if (energia >= 0 && energia <= 10)
      this.energia = energia;
    if (fome >= 0 && fome <= 10)
      this.fome = fome;
    if (sono >= 0 && sono <= 10)
      this.sono = sono;
  }

  void cacar(String[] ITENS){
    if(energia >= 2){
      Random gerador = new Random();
      int oQueCacar = gerador.nextInt(ITENS.length);
      System.out.printf(" %s Foi cacar\n", nome);
      estoque.adicionar(ITENS[oQueCacar]);
      System.out.printf("%s cacou %s\n", nome, ITENS[oQueCacar]);
      System.out.printf("%s foi adicionado ao estoque\n", ITENS[oQueCacar]);
    }
    else{
          System.out.printf("%s esta sem energia para cacar\n", nome);
    }
      
    
    energia -= 2;
    fome = Math.min(fome + 1, 10);
    sono = sono < 10 ? sono + 1 : sono;
  }

  void comer() {

      if(energia>=1 && estoque.qtde >=1){
        String ultimoItem = estoque.ultimoItem();
        System.out.printf("%s comeu %s\n", nome, ultimoItem);
        energia = Math.min(energia + 1, 10);
        estoque.removerUltimoItem();
          fome --;
      }
      else if(estoque.qtde == 0){
        System.out.printf("%s não tem itens no estoque...\n", nome);  
      }
      else{
      System.out.printf("%s esta sem fome...\n", nome);
      }
  }

  void dormir(){
    if(sono >= 1){
      System.out.printf("%s esta dormindo...\n", nome);
      sono -= 1;
      energia = Math.min(energia + 1, 10);
    }
    else{
      System.out.printf("%s sem sono...\n", nome);
    }
  }

  void morrer(Personagem outroPersonagem) {
    if (energia <= 0) {
        System.out.printf("%s morreu\n", nome);
        energia = 0;
    }
    if (outroPersonagem.energia <= 0) {
      System.out.printf("Ambos os personagens morreram. Fim do jogo.\n");
      System.out.printf("%s é o grande vencedor!!!\n", nome);
      System.exit(0);
  }
    
 }

  void atacar(Personagem alvo) {
    int dano = 1; 
    alvo.energia -= dano;
    System.out.printf("%s atacou %s\n", nome, alvo.nome);
    if (alvo.energia <= 0) {
        System.out.printf("%s derrotou %s!\n", nome, alvo.nome);
        System.out.printf("%s e o grande campeao!!!!!!\n", nome);
    }
}

  public String toString(){
    return String.format(
      "%s:\nEnergia: %d\nFome: %d\nSono: %d\nEstoque: %s\n",
      nome, energia, fome, sono, estoque
    );
  }

}