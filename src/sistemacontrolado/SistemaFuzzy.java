/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontrolado;

import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.defuzzifier.Defuzzifier;
import net.sourceforge.jFuzzyLogic.rule.Variable;
/**
 *
 * @author User-PC
 */
public class SistemaFuzzy {
    
    public double dA,dV;
    public boolean done;
    
    public SistemaFuzzy(){
        done = false;
    }

    public void setdA(double dA) {
        this.dA = dA;
    }

    public void setdV(double dV) {
        this.dV = dV;
    }

    public void setDone(boolean plot) {
        this.done = plot;
    }
    
    public void run(double dx, double dy){
    
    String fileName = "fcl/tipper_trapezio.fcl";
    FIS fis = FIS.load(fileName);
    
    if(fis==null){
        System.err.println("Can't load file file "+fileName);
        return;
    }
    
    fis.setVariable("x_diff", dx);
    fis.setVariable("y_diff", dy);
    
    fis.evaluate();
    
    Variable out_velocidade = fis.getVariable("speed");
    Variable out_angulo = fis.getVariable("angle");
    
    
    if(done){
        plot(fis, out_angulo, out_velocidade);
        System.out.println("pronto!");
    }
    
    System.out.println(out_angulo);//printa no concole
    System.out.println(out_velocidade);//printa no console
    
    dA = out_angulo.getValue();
    dV = out_velocidade.getValue();
   
    }
    
    //ideia, inicialmente, tentar acertar só mudando a velocidade, e mantendo o angulo intacto.

    public void plot(FIS fis, Variable out_angulo, Variable out_velocidade){
        JFuzzyChart.get().chart(fis); //plota tudo eu acho
        JFuzzyChart.get().chart(out_angulo, out_angulo.getDefuzzifier(), true);//plota somente o output angulo
        JFuzzyChart.get().chart(out_velocidade, out_velocidade.getDefuzzifier(), true);//plota somente o output velocidade
    }
    
    public double getdA() {
        return dA;
    }

    public double getdV() {
        return dV;
    }
}
