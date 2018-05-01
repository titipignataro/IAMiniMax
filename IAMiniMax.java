package iaminimax;

import static iaminimax.Jogador.*;


public class IAMiniMax {

    public static void main(String[] args) {
     Tabuleiro t = new Tabuleiro();
     t.iniciaTabuleiro();
     
     No no_inicial = new No(t,null,Ninguem);
     
        System.out.println(no_inicial.Max());
     

    }
}
