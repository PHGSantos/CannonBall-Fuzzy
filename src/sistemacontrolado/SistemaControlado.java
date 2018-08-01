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
    /*
    Obs: canhão está em X=0
         altura do solo Y=0
    */
    
    public SistemaControlado(double Ao, double Vo, double Yc, double Xa, double Ya){
        this.Ao = Ao;
        this.Vo = Vo;
        this.Yc = Yc;
        this.Xa = Xa;
        this.Ya = Ya;
    }
    
    
    public void run(){
        double t;
        Vy = calcVy();
        Vx = calcVx();
        
        double t1 = (Vy + Math.sqrt(Math.pow(Vy, 2) + 2*g*Yc))/g;
        double t2 = (Vy - Math.sqrt(Math.pow(Vy, 2) + 2*g*Yc))/g;
        
        System.out.println((g/2)+"t²-"+Vy+"t-"+Yc);
        System.out.println("t1 = "+t1);
        System.out.println("t2 = "+t2);
        
        if(t1 > t2)
            t = t1;
        else
            t = t2;
        
        if(t < 0){
            System.out.println("erro: tempo negativo");
        }else{
        
        double Xt = Vx*t;
        double Yt = Yc + Vy*t - g*Math.pow(t, 2)/2;
        
        //alcance máximo em X
        double Xr = (Math.pow(Vo, 2)*Math.sin(2*Ao))/g;
        double Yr =0;
        
        double dx = Xr - Xa;
        double dy = Yr - Ya;
        
        System.out.println("Saída da Simulação do canhão:");
        System.out.println("t = "+t);
        System.out.println("Vx = "+Vx);
        System.out.println("Vy = "+Vy);
        System.out.println("X(t)= "+Xt);
        System.out.println("Y(t) = "+Yt);
        System.out.println("Xr = "+Xr);
        System.out.println("Yr = "+Yr);
        System.out.println("DX = "+dx);
        System.out.println("DY = "+dy);  
        }
    }
    
    
    public void run2(){
        
         Vy = calcVy();
         Vx = calcVx();
        
        double t = 2*(Vo*Math.sin(Ao))/g;
        
        double Xt = Vx*t;
        double Yt = Yc + Vy*t - g*Math.pow(t, 2)/2;
        
        //alcance máximo em X
        double Xr = (Math.pow(Vo, 2)*Math.sin(2*Ao))/g;
        double Yr =0;
        
        double dx = Xr - Xa;
        double dy = Yr - Ya;
        
        System.out.println("Saída da Simulação do canhão:");
        System.out.println("t = "+t);
        System.out.println("Vx = "+Vx);
        System.out.println("Vy = "+Vy);
        System.out.println("X(t)= "+Xt);
        System.out.println("Y(t) = "+Yt);
        System.out.println("Xr = "+Xr);
        System.out.println("Yr = "+Yr);
        System.out.println("DX = "+dx);
        System.out.println("DY = "+dy);  
    }
    
    public void run3(){
        
        Vy = calcVy();
        Vx = calcVx();
        
        //Tempo para chegar na mesma posição horizontal (X) que o alvo, independente da altura
        double t = calcT();
        
        double Xt = Vx*t;
        double Yt = Yc + Vy*t - g*Math.pow(t, 2)/2;
        
        
        double Xr = (Math.pow(Vo, 2)*Math.sin(2*Ao))/g;//posição do projétil quando atinge o solo
        double Yr = Yt;//altura do projetil quando passa na posição X do alvo
        
        double dx = Xr - Xa;
        double dy = Yr - Ya;
        
        System.out.println("Saída da Simulação do canhão:");
        System.out.println("t = "+t);
        System.out.println("Vx = "+Vx);
        System.out.println("Vy = "+Vy);
        System.out.println("X(t)= "+Xt);
        System.out.println("Y(t) = "+Yt);
        System.out.println("Xr = "+Xr);
        System.out.println("Yr = "+Yr);
        System.out.println("DX = "+dx);
        System.out.println("DY = "+dy);  
    }
    
    
    public double calcT(){
        return Xa/Vx;
   }
    
    public double calcVy(){
        return Vo*Math.sin(this.Ao);
    }
    
    public double calcVx(){
        return Vo*Math.cos(this.Ao);
    }
    
    
}
