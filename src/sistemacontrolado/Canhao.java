/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontrolado;


import javax.sound.midi.Soundbank;


/**
 *
 * @author User-PC
 */
public class Canhao {
  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */    
    private boolean done;
    private double[] metrics;
    private final double g =  9.8;//gravidade
    private double Ao;//Angulo inicial
    private double Vo;//vel inicial
    private double Xa, Ya;//posição do alvo
    private double Yc;//altura do canhão em relação ao solo
    private double Vox, Voy; //Velocidades iniciais em x e y

    
    public Canhao(){
        done = false;
    }
    
    public void printParameters() {
    	System.out.println("Parâmetros iniciais do Canhão:");
    	System.out.println("Velocidade inicial: " + this.Vo);
    	System.out.println("Angulo inicial: " + this.Ao);
    	System.out.println("Altura inicial: " + this.Yc);
    	System.out.println("Parâmetros do Alvo:");
    	System.out.println("Altura do alvo: " + this.Ya);
    	System.out.println("Distancia do alvo: " + this.Xa);
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
    }
    
    public double[] calcMetrics(double Ao, double Vo, double Yc, double Xa, double Ya) {
    	
        
        this.Ao = Ao;
        this.Vo = Vo;
        this.Yc = Yc;
        this.Xa = Xa;
        this.Ya = Ya;
        this.Voy = calcVy();
        this.Vox = calcVx();
   
        done = hit(2);
        
        metrics = new double[2];
    	
    	//Tempo de subida
    	double ts = calcTs(this.Voy);
    	//Altura máxima
    	double hMax = calcHmax(ts, this.Voy);
    	//Tempo de descida
    	double td = calcTd(hMax);
    	//Tempo total
    	double tTotal = td + ts;
    	//Alcance horizontal
    	double xMax = xReach(tTotal, this.Vox);
    	//Alcance horizontal quando passa pelo alvo
    	double yTarget = calcTargetY(this.Ya, this.Vox, this.Voy);
    	metrics[0] = (this.Xa - xMax);
    	metrics[1] = (this.Ya - yTarget);
    	
        return metrics;
    }

    public boolean isDone() {
        return done;
    }
    
    public void printOutput() {//se der ruim poe devolta
    	//double[] metrics = this.calcMetrics(this.Ao,this.Vo,this.Yc,this.Xa,this.Ya);
    	System.out.println("Diferença no eixo x: " + metrics[0]);
    	System.out.println("Diferença no eixo y: " + metrics[1]);
    }
    
    public void adjustInput(double angle, double speed) {
    	this.Ao = this.Ao + angle;
    	this.Vo = this.Vo + speed;
    	this.Voy = this.calcVy();
    	this.Vox = this.calcVx();
    }
    
    /***
     * Calcula tempo de subida
     * @return
     */
    public double calcTs(double voy) {
    	return voy/this.g;
    }
    
    /***
     * Calculo da altura máxima alcançada pelo projétil
     * @return
     */
    public double calcHmax(double ts, double voy) {
    	return (this.Yc - (this.g * Math.pow(ts, 2) * 0.5) + voy*ts);
    }
    
    /***
     * Cálculo do tempo de descida do projétil
     * @return
     */
    public double calcTd(double hMax) {
    	return Math.sqrt(2 * hMax/this.g);
    }
    
    /***
     * Cálcula o alcance máximo do projétil no eixo x
     * @return
     */
    public double xReach(double t, double vox) {
    	return vox*t;
    }
    
    public double calcTargetY(double targetX, double vox, double voy) {
    	double targetT = targetX / vox;
    	return this.Yc - (this.g * Math.pow(targetT, 2) *0.5) + voy*targetT;
    }

    public double getG() {
        return g;
    }

    public double getAo() {
        return Ao;
    }

    public double getVo() {
        return Vo;
    }

    public double getXa() {
        return Xa;
    }

    public double getYa() {
        return Ya;
    }

    public double getYc() {
        return Yc;
    }

    public double getVox() {
        return Vox;
    }

    public double getVoy() {
        return Voy;
    }

    
    public double calcT(){
        return Xa/Vox;
   }
    
    public double calcVy(){
    	double aRad = Math.toRadians(this.Ao);
        return Vo*Math.sin(aRad);
    }
    
    public double calcVx(){
    	double aRad = Math.toRadians(this.Ao);
        return Vo*Math.cos(aRad);
    }
     
    public double[] getMetrics(){
        return metrics;
    }
    
    public boolean hit(double r){
        
        double t = calcT();
        double x = Vox*t;
        double y = Yc + Voy*t - g*Math.pow(t, 2)/2;
        
        return ( ((x-r)>=Xa && (x+r)<=Xa) && ((y-r)>=Ya && (y+r)<=Ya) );
    }
}
