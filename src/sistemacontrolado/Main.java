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
public class Main {

    
    //lembrar: não usar DX negativo e Dy na mesma regra, porque, quando DX é negativo,
    public static void main(String[] args) {
        // TODO code application logic here
        double rangeMin = 0;
        double rangeMax = 90;
        
        double tempo  = 0;
        double limiteDeTempo = 10;
        double Fmax = 20;//força/vel max
    
        double Ao = 45;
        double Vo = Fmax/2;
        //                                           
        
        executarBernardo(Ao,Vo,10,100,50);
        
        }
    
        private static void executarBernardo(double Ao, double Vo, double initialHeight,
                double targetX, double targetY){
            
            Canhao sys = new Canhao();
            double[] metrics = sys.calcMetrics(Ao, Vo, initialHeight , targetX, targetY);
            
            double angleRate = 0.2;
            double speedRate = 0.1;
            int totalAdjusts = 0;
            
            //MUDAR ESSE && PARA ||
            while( ((metrics[0] > 2) || (metrics[0] < -2)) && ((metrics[1] > 2) || (metrics[1] < -2))) {
                sys.printParameters();
                //double[] metricas = sys.calcMetrics();
                sys.printOutput();
                
                sys.adjustInput(angleRate, speedRate);
                totalAdjusts++;
                
                
                metrics = sys.calcMetrics(sys.getAo(), sys.getVo(), initialHeight, targetX, targetY);
                
                double DX = metrics[0];
                double DY = metrics[1];
                
                
                SistemaFuzzy sf = new SistemaFuzzy();
                sf.run(DX, DY);
                
                
                if(totalAdjusts > 100) {
                    sf.setPlot(true);
                    System.out.println("Max executions exceeded");
                    break;
                }
                
                if(sys.isDone()){
                    sf.setPlot(true);
                    System.out.println("finished in "+totalAdjusts +"iterations");
                    break;
                }
                
                System.out.println("execuções: "+totalAdjusts);
                    
                
                Vo = Vo + sf.getdV();//nova velocidade
                Ao = Ao + sf.getdA();//novo angulo

                metrics = sys.calcMetrics(Ao, Vo, initialHeight, targetX, targetY);
                
                
                
            }
            
            
        }
        
        private static void executarPedro(){
            double rangeMin = 0;
            double rangeMax = 90;

            double tempo  = 0;
            double limiteDeTempo = 10;
            double Fmax = 20;//força/vel max

            double Ao = 45;
            double Vo = Fmax/2;
            //                                           
            SistemaControlado sc = new SistemaControlado();
            //Ao, Vo, Yc, Xa, Ya
            sc.run(Ao, Vo, 10, 100, 100);


            for (int i = 0; i < 50; i++){
                //executa o fuzzy
                SistemaFuzzy sf = new SistemaFuzzy();
                sf.run(sc.getDX(), sc.getDY());

                //att as entradas
                Vo = Vo + sf.getdV();
                Ao = Ao + sf.getdA();

                //executa dnv a simulação
                sc.run(Ao, Vo, 10, 100, 100);
            }
        }
        
    
    
}
