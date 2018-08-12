/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontrolado;

import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
/**
 *
 * @author User-PC
 */
public class SistemaFuzzy {
    
    public double dA,dV;
    public boolean plot;
    
    public SistemaFuzzy(){
        plot = false;
    }

    public void setdA(double dA) {
        this.dA = dA;
    }

    public void setdV(double dV) {
        this.dV = dV;
    }

    public void setPlot(boolean plot) {
        this.plot = plot;
    }
    
    public void run(double dx, double dy){
    
    String fileName = "fcl/tipper_trapezio.fcl";
    FIS fis = FIS.load(fileName);
    
    if(fis==null){
        System.err.println("Can't load file file "+fileName);
        return;
    }
    
    if(plot){
        JFuzzyChart.get().chart(fis);
    }

    fis.setVariable("x_diff", dx);
    fis.setVariable("y_diff", dy);
    
    fis.evaluate();
    
    //Variable out_angulo = fis.getVariable("out_angle");
    //JFuzzyChart.get().chart(out_angulo, out_angulo.getDefuzzifier(), true);
    
    Variable out_velocidade = fis.getVariable("speed");
    Variable out_angulo = fis.getVariable("angle");
    
    if(plot){
        JFuzzyChart.get().chart(out_velocidade, out_velocidade.getDefuzzifier(), true);
        System.out.println(out_angulo);
        System.out.println(out_velocidade);
    }
    dA = out_angulo.getValue();
    dV = out_velocidade.getValue();
   
    }
    
    
    //ideia, inicialmente, tentar acertar só mudando a velocidade, e mantendo o angulo intacto.

    public double getdA() {
        return dA;
    }

    public double getdV() {
        return dV;
    }
}
