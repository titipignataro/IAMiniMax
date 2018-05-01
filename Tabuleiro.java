package iaminimax;

import static iaminimax.Simbolo.*;
import java.util.ArrayList;

public class Tabuleiro {

    public Simbolo posicoes[][];

    public Tabuleiro() {
        this.posicoes = new Simbolo[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.posicoes[i][j] = Nenhum;
            }
        }
    }
    
    public void iniciaTabuleiro(){
    posicoes[0][0] = X;
    posicoes[0][1] = X;
    posicoes[0][2] = O;
    posicoes[1][0] = Nenhum;
    posicoes[1][1] = Nenhum;
    posicoes[1][2] = Nenhum;
    posicoes[2][0] = O;
    posicoes[2][1] = Nenhum;
    posicoes[2][2] = Nenhum;
    }

    public static boolean verificaTerminal(Tabuleiro t){
        Simbolo simbolo = X;
        int l = 0;
         while (l < 2) {
            for (int i = 0; i < 3; i++) {

//              S   S   S
                if (t.posicoes[i][0] == simbolo && t.posicoes[i][1] == simbolo
                        && t.posicoes[i][2] == simbolo) {
                    return true;
                }
/*
                S
                S
                S
*/
                if (t.posicoes[0][i] == simbolo && t.posicoes[1][i] == simbolo
                        && t.posicoes[2][i] == simbolo) {
                    return true;
                }
            }
/*
                S
                  S
                     S
*/
            if (t.posicoes[0][0] == simbolo && t.posicoes[1][1] == simbolo
                     && t.posicoes[2][2] == simbolo) {
                 return true;
             }
/*
                    S
                  S
                S
*/
            if (t.posicoes[0][2] == simbolo && t.posicoes[1][1] == simbolo
                     && t.posicoes[2][0] == simbolo) {
                 return true;
             }
            
            l++;
            simbolo = O;
        }

//      Tabuleiro ainda nao cheio
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t.posicoes[i][j] == Nenhum) {
                    return false;
                }
            }
        }
        
        //System.out.println("ATENCAO> debug esta parte");
        return true;
    }
    
    public int FavorecimentoDe(){
        int c = 0;
        Simbolo simbolo = X;
        int l = 0;
//==========================FINAL ============================================= 
       while (l < 2) {
            for (int i = 0; i < 3; i++) {
                if (posicoes[i][0] == simbolo && posicoes[i][1] == simbolo
                        && posicoes[i][2] == simbolo) {
                    if(simbolo == X){ return 10;}
                    if(simbolo == O){ return -10;}
                }
                if (posicoes[0][i] == simbolo && posicoes[1][i] == simbolo
                        && posicoes[2][i] == simbolo) {
                    if(simbolo == X){ return 10;}
                    if(simbolo == O){ return -10;}
                }
            }
            if (posicoes[0][0] == simbolo && posicoes[1][1] == simbolo
                     && posicoes[2][2] == simbolo) {
                if(simbolo == X){ return 10;}
                if(simbolo == O){ return -10;}
             }
            if (posicoes[0][2] == simbolo && posicoes[1][1] == simbolo
                     && posicoes[2][0] == simbolo) {
                    if(simbolo == X){ return 10;}
                    if(simbolo == O){ return -10;}
             }   
          l++;
          simbolo = O;
        }
//==========================DOIS A DOIS=======================================        
        simbolo = X;
        Simbolo simboloOponente = O;
         l = 0;
        while(l < 2){
            for (int i = 0; i < 3; i++) {
//===========================================================================
//              Horizontal e Vertical dois a dois : Ganhar pontos    
                if (posicoes[i][0] == simbolo && posicoes[i][1] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
                    if(posicoes[i][1] == simbolo && posicoes[i][2] == simbolo){
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
                    // TESTES AQUI 
                    if(posicoes[i][0] == simbolo && posicoes[i][2] == simbolo){
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
                    if (posicoes[0][i] == simbolo && posicoes[2][i] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
                    ///

                    if (posicoes[0][i] == simbolo && posicoes[1][i] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
                    if(posicoes[1][i] == simbolo && posicoes[2][i] == simbolo){
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
                    }
//===========================================================================
//              Horizontal e Vertical S S SO : Perder pontos  
                    if (posicoes[i][0] == simbolo && posicoes[i][1] == simbolo
                           && posicoes[i][2] == simboloOponente) {
                       if(simbolo == X){ c = c - 2;}
                        if(simbolo == O){ c = c + 2;}
                    }
                    if (posicoes[i][0] == simboloOponente 
                            && posicoes[i][1] == simbolo 
                           && posicoes[i][2] == simbolo) {
                       if(simbolo == X){ c = c - 2;}
                        if(simbolo == O){ c = c + 2;}
                    }
                    
                    if (posicoes[0][i] == simbolo && posicoes[1][i] == simbolo
                    && posicoes[2][i] == simboloOponente) {
                    if(simbolo == X){ c = c - 2;}
                    if(simbolo == O){ c = c + 2;}
                    }
                    if (posicoes[0][i] == simboloOponente
                            && posicoes[1][i] == simbolo
                            && posicoes[2][i] == simbolo) {
                    if(simbolo == X){ c = c - 2;}
                    if(simbolo == O){ c = c + 2;}
                    }
                    
                    //TESTES AQUI
                    if (posicoes[i][0] == simbolo && posicoes[i][2] == simbolo
                           && posicoes[i][1] == simboloOponente) {
                       if(simbolo == X){ c = c - 2;}
                        if(simbolo == O){ c = c + 2;}
                    }
                    if (posicoes[0][i] == simbolo && posicoes[2][i] == simbolo
                    && posicoes[1][i] == simboloOponente) {
                    if(simbolo == X){ c = c - 2;}
                    if(simbolo == O){ c = c + 2;}
                    }   
                    ///
                }
            
            
//========================================================================
//          Diagonais dois a dois : Ganhar pontos
            if (posicoes[0][0] == simbolo && posicoes[1][1] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
             }
            if (posicoes[1][1] == simbolo && posicoes[2][2] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
             }
            if (posicoes[0][2] == simbolo && posicoes[1][1] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
             }
            if(posicoes[1][1] == simbolo && posicoes[2][0] == simbolo){
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
            }
            //TESTES AQUI
            if (posicoes[0][0] == simbolo && posicoes[2][2] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
             }
            if (posicoes[0][2] == simbolo && posicoes[2][0] == simbolo) {
                        if(simbolo == X){ c++;}
                        if(simbolo == O){ c--;}
             }
//===========================================================================
//          Diagonais : Perder Pontos
            if (posicoes[0][0] == simbolo && posicoes[1][1] == simbolo
                    && posicoes[2][2] == simboloOponente) {
                if (simbolo == X) {
                    c = c - 2;
                }
                if (simbolo == O) {
                    c = c + 2;
                }
            }
            if (posicoes[0][0] == simbolo && posicoes[1][1] == simbolo
                    && posicoes[2][2] == simboloOponente) {
                if (simbolo == X) {
                    c = c - 2;
                }
                if (simbolo == O) {
                    c = c + 2;
                }
            }

            if (posicoes[0][2] == simboloOponente && posicoes[1][1] == simbolo
                     && posicoes[2][0] == simbolo) {
                if(simbolo == X){ c = c - 2;}
                if(simbolo == O){ c = c + 2;}
             }
            
            if (posicoes[0][2] == simboloOponente && posicoes[1][1] == simbolo
                     && posicoes[2][0] == simbolo) {
                if(simbolo == X){ c = c - 2;}
                if(simbolo == O){ c = c + 2;}
             }
            
            //TESTES AQUI
            if (posicoes[0][0] == simbolo && posicoes[2][2] == simbolo
                    && posicoes[1][1] == simboloOponente) {
                if (simbolo == X) {
                    c = c - 2;
                }
                if (simbolo == O) {
                    c = c + 2;
                }
            }
            if (posicoes[0][2] == simboloOponente && posicoes[2][0] == simbolo
                     && posicoes[1][1] == simbolo) {
                if(simbolo == X){ c = c - 2;}
                if(simbolo == O){ c = c + 2;}
             }
            
            
            
            simbolo = O;
            simboloOponente = X;
            l++;
        }
        return c;
    }
    
    
    public int utilidadeInt(){
        int utilidade = 0;
        utilidade = FavorecimentoDe();
        return utilidade;
    }
    
    
    public ArrayList<int[]> movimentosPossiveis(){
        int pos[];
        ArrayList<int[]> posicoesLivres = new ArrayList<int[]>();
        for(int i = 0; i<3 ;i++){
            for (int j = 0; j<3 ; j++){
                if(posicoes[i][j] == Nenhum){
                pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                posicoesLivres.add(pos);
                }
            }
        }
        return posicoesLivres;
    }

    public Tabuleiro fazerMovimento(Simbolo s, int[] ij){
        Tabuleiro nt = new Tabuleiro();
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                Simbolo[] x = this.posicoes[i];
                nt.posicoes[i][j] = x[j];
            }   
        }
        nt.posicoes[ij[0]][ij[1]] = s;
        
        return nt;
    }
    
    
    
    public void imprimeTabuleiro() {
        System.out.println("Tabuleiro> \n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(j == 0){
                    System.out.print(" ");
                }
                if (this.posicoes[i][j] == Nenhum) {
                    System.out.print(" ");
                } else {
                    System.out.print(this.posicoes[i][j]);
                }
                if (j < 2) {
                        System.out.print(" │ ");
                    }
            }
            if (i != 2) {
                System.out.println("");
                System.out.println("-------------");
            }
        }
        System.out.println("\n");
    }
    
    
    
}
