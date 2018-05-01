package iaminimax;

import static iaminimax.Jogador.*;
import static iaminimax.Simbolo.*;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;



public class No {
    private Tabuleiro tabuleiroAtual; 
    private No tabuleiroAnterior;
    private Jogador jogador; // jogador que faz a jogada no tabuleiro atual
    private int utilidade;
    
    
    public No(Tabuleiro t,No ta,Jogador j){
        this.tabuleiroAtual = t;
        this.tabuleiroAnterior = ta;
        this.jogador = j;
        this.utilidade = t.utilidadeInt();
    }
    public void imprimeNo(){
        this.tabuleiroAtual.imprimeTabuleiro();
        System.out.println("Jogador Anterior: " + this.jogador);   
        System.out.println("Utilidade: " + this.utilidade);
    }
    
    public int Max(){
        int v = max(this,-1000,1000);
        System.out.println("UTILIDADE A PROCURAR> " + v);
        System.out.println("INICIAL>");
        this.imprimeNo();
        System.out.println("");
        boolean[][] ff = new boolean[3][3];
        maxResposta(this,-1000,1000,v,ff);
        int ValorMaximo = -1000;
        int ijogada = 0, jjogada = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(ff[i][j] == true){
                System.out.println(i + " " + j);
                    int ij[] = new int[2];
                    ij[0] = i;
                    ij[1] = j;
                    Tabuleiro tab = this.tabuleiroAtual.fazerMovimento(X, ij);
                    int k = tab.utilidadeInt();
                    System.out.println(k);
                    if(ValorMaximo < k){
                        ijogada = i;
                        jjogada = j;
                        ValorMaximo = k;
                    }
                }
            }
        }
        
        System.out.println("É melhor jogar no " + ijogada + " " + jjogada);
        

        return v;   
    }
    
public int maxResposta(No nn,int alfa, int beta, int vr,boolean[][] ff){
        Tabuleiro ta = nn.getTabuleiroAtual();
        //nn.imprimeNo();
        if(Tabuleiro.verificaTerminal(ta)){
            return ta.utilidadeInt();
        }
        int v = -1000;
        ArrayList<int[]> posicoesLivres = new ArrayList<int[]>();
        posicoesLivres = ta.movimentosPossiveis();
        ArrayList<No> nos_gerados = new ArrayList<No>();
        
        Tabuleiro novo_tabuleiro;
        No novo_no;
        int injn[] = new int[2];
        for(int i = 0; i<posicoesLivres.size();i++){
        injn = new int[2];
                injn = posicoesLivres.get(i);
        novo_tabuleiro = ta.fazerMovimento(X, injn);
        novo_no = new No(novo_tabuleiro,nn,JMIN);
        nos_gerados.add(novo_no);
        v = Math.max(v, minResposta(novo_no,alfa,beta,vr, ff));
        if(v >= beta){
            return v;
        }
        alfa = Math.max(alfa,v);
        }
        return v;
    }
    
   public int minResposta(No nn,int alfa, int beta,int vr, boolean[][] ff){
       Tabuleiro ta = nn.getTabuleiroAtual();
       //nn.imprimeNo();
        if(Tabuleiro.verificaTerminal(ta)){
            if(nn.utilidade == vr){
                ArrayList<No> caminho = new ArrayList<No>();
                No no_percorrer = nn;
//                System.out.println("CAMINHO POSSIVEL======");
                while(no_percorrer.jogador != Ninguem ){
                    caminho.add(no_percorrer);
                    no_percorrer = no_percorrer.tabuleiroAnterior;
                }
                int[] jogada = 
                No.retornaJogada(no_percorrer, caminho.get(caminho.size() - 1));
                int in = jogada[0];
                int jn = jogada[1];
                
                ff[in][jn] = true;
//                caminho.add(no_percorrer);
//                for(int i=caminho.size() - 1;i>=0;i--){
//                    caminho.get(i).imprimeNo();
//                }
//                System.out.println("====================\n\n");
            }
            return ta.utilidadeInt();
        }
        int v = 1000;
        
        ArrayList<int[]> posicoesLivres = new ArrayList<int[]>();
        posicoesLivres = ta.movimentosPossiveis();
                Tabuleiro novo_tabuleiro;
        No novo_no;
        int injn[] = new int[2];
        ArrayList<No> nos_gerados = new ArrayList<No>();
        
        for(int i = 0; i<posicoesLivres.size();i++){
        injn = new int[2];
                injn = posicoesLivres.get(i);
        novo_tabuleiro = ta.fazerMovimento(O, injn);
        novo_no = new No(novo_tabuleiro,nn,JMAX);
        nos_gerados.add(novo_no);
        v = Math.min(v, maxResposta(novo_no,alfa,beta,vr,ff));
        if(v <= alfa){
            return v;
        }
        beta = Math.min(beta,v);
        }
       return v;
    }    
    
   
   public static int[] retornaJogada(No a, No b){
       int ij[] = new int[2];
       for(int i = 0;i<3;i++){
           for(int j = 0; j<3;j++){
               if(a.tabuleiroAtual.posicoes[i][j] 
                       != b.tabuleiroAtual.posicoes[i][j]){
                   ij[0] = i;
                   ij[1] = j;
                 return ij;  
               }
           }
       }
       return ij;
   }
   
   
    
    
    
    public int max(No nn,int alfa, int beta){
        Tabuleiro ta = nn.getTabuleiroAtual();
        //RECURSAO MIN
        //nn.imprimeNo();
        if(Tabuleiro.verificaTerminal(ta)){
            return ta.utilidadeInt();
        }
        int v = -1000;
        
        ArrayList<int[]> posicoesLivres = new ArrayList<int[]>();
        posicoesLivres = ta.movimentosPossiveis();
        ArrayList<No> nos_gerados = new ArrayList<No>();
        
        Tabuleiro novo_tabuleiro;
        No novo_no;
        int injn[] = new int[2];
        for(int i = 0; i<posicoesLivres.size();i++){
        injn = new int[2];
                injn = posicoesLivres.get(i);
        novo_tabuleiro = ta.fazerMovimento(X, injn);
        novo_no = new No(novo_tabuleiro,nn,JMAX);
        nos_gerados.add(novo_no);
        v = Math.max(v, min(novo_no,alfa,beta));
        if(v >= beta){
            return v;
        }
        alfa = Math.max(alfa,v);
        }
        
       return v;
    }
    
   public int min(No nn,int alfa, int beta){
       Tabuleiro ta = nn.getTabuleiroAtual();
       //RECURSAO MIN
        //nn.imprimeNo();
       
        if(Tabuleiro.verificaTerminal(ta)){
            return ta.utilidadeInt();
        }
        int v = 1000;
        
        ArrayList<int[]> posicoesLivres = new ArrayList<int[]>();
        posicoesLivres = ta.movimentosPossiveis();
                Tabuleiro novo_tabuleiro;
        No novo_no;
        int injn[] = new int[2];
        ArrayList<No> nos_gerados = new ArrayList<No>();
        
        for(int i = 0; i<posicoesLivres.size();i++){
        injn = new int[2];
                injn = posicoesLivres.get(i);
        novo_tabuleiro = ta.fazerMovimento(O, injn);
        novo_no = new No(novo_tabuleiro,nn,JMIN);
        nos_gerados.add(novo_no);
        v = Math.min(v, max(novo_no,alfa,beta));
        if(v <= alfa){
            return v;
        }
        beta = Math.min(beta,v);
        }
        
       return v;
    }
    
   
    
    public Tabuleiro getTabuleiroAtual() {
        return tabuleiroAtual;
    }

    public void setTabuleiroAtual(Tabuleiro tabuleiroAtual) {
        this.tabuleiroAtual = tabuleiroAtual;
    }

    public No getTabuleiroAnterior() {
        return tabuleiroAnterior;
    }

    public void setTabuleiroAnterior(No tabuleiroAnterior) {
        this.tabuleiroAnterior = tabuleiroAnterior;
    }

    public Jogador getJogadorAtual() {
        return jogador;
    }

    public void setJogadorAtual(Jogador jogadorAtual) {
        this.jogador = jogadorAtual;
    }
    
    
    



}
