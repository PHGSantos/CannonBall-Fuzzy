/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontrolado;

/**
 *
 * @author aluno
 */
public class SistemaControlado {
    
    private final double g =  9.8;//gravidade
    private double Ao;//Angulo inicial
    private double Vo;//vel inicial
    private double Xa, Ya;//posição do alvo
    private double Yc;//altura do canhão em relação ao solo
    private double Vx, Vy;
    private boolean done;
    private double DX, DY;
    /*
    Obs: canhão está em X=0
         altura do solo Y=0
    */
    
    public SistemaControlado(){
        this.done = false;
    }
    
    public void run(double Ao, double Vo, double Yc, double Xa, double Ya){
        
        this.Ao = Math.toRadians(Ao);
        this.Vo = Vo;
        this.Yc = Yc;
        this.Xa = Xa;
        this.Ya = Ya;
        
        
        Vy = calcVy();
        Vx = calcVx();
        
        //Tempo para chegar na mesma posição horizontal (X) que o alvo, independente da altura
        double t = calcT();
        
        double Xt = Vx*t;
        double Yt = Yc + Vy*t - g*Math.pow(t, 2)/2;
        
        if(!hit(10, Xt, Yt)){
           Xt = (Math.pow(Vo, 2)*Math.sin(2*Ao))/g;//usar a posição do projétil quando atinge o solo
            System.out.println("O alvo não foi atingido.");
        }else{
            System.out.println("O alvo foi atingido.");
            done = true;
        }
        
        DX = Xt - Xa;
        DY = Yt - Ya;
        
        
        
        System.out.println("Saída da Simulação do canhão:");
        System.out.println("t = "+t);
        System.out.println("Vx = "+Vx);
        System.out.println("Vy = "+Vy);
        System.out.println("X(t)= "+Xt);
        System.out.println("Y(t) = "+Yt);
        System.out.println("DX = "+DX);
        System.out.println("DY = "+DY);  
    }
   
    public boolean hit(double r, double x, double y){
        return ( ((x-r)>=Xa && (x+r)<=Xa) && ((y-r)>=Ya && (y+r)<=Ya) );
    }
    
    public double calcT(){
        return Xa/Vx;
   }
    
    public double calcVy(){
        return Vo*Math.sin(Math.toRadians(this.Ao));
    }
    
    public double calcVx(){
        return Vo*Math.cos(Math.toRadians(this.Ao));
    }    

    public double tempoDoAlcanceMax(){
        return Vo*Math.sin(Ao)/g;
    }

    public double getDX() {
        return DX;
    }

    public double getDY() {
        return DY;
    }
    
}
