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
    private final double Fmax = 100;//força/vel max
    private double Xa, Ya;//posição do alvo
    private double Yc;//altura do canhão em relação ao solo
    
    /*
    Obs: canhão está em X=0
         altura do solo Y=0
    */
    
    public SistemaControlado(double Ao, double Vo, double Yc){
        this.Ao = Ao;
        this.Vo = Vo;
        this.Yc = Yc;
    }
    
    
    public void run(){
        double t = 0;
        
        double Vy = calcVy();
        double Vx = calcVx();
        
        double Xt = Vx*t;
        double Yt = Yc + Vy*t - g*Math.pow(t, 2)/2;
        
        //alcance máximo em X
        double Xr = (Math.pow(Vo, 2)*Math.sin(2*Ao))/g;
        double Yr =0;
        
        double dx = Xr - Xa;
        double dy = Yr - Ya;
    }
    
    public double calcVy(){
        return Vo*Math.sin(this.Ao);
    }
    
    public double calcVx(){
        return Vo*Math.cos(this.Ao);
    }
    
    
}
