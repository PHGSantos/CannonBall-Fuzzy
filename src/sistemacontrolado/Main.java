/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontrolado;

import java.util.Random;

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
        double Fmax = 200;//força/vel max
    
        //                                           Ao, Vo, Yc, Xa, Ya
        SistemaControlado sc = new SistemaControlado(45, Fmax/2, 2, 120, 0);
        //sc.run();
        //sc.run2();
        sc.run3();
        
    }
    
}
