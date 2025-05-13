package AlgoritmosGeneticos.AlgoritmoGenetico;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class FuncionAptitud extends FitnessFunction {

    //Clase que se encarga de evaluar el individuo

    @Override
    protected double evaluate(IChromosome ic) {
        //crear la funcion
        return individuo(ic);
    }

    private double individuo(IChromosome ic){
        int signoX = (int)ic.getGene(0).getAllele();
        int x1 = (int)ic.getGene(1).getAllele();
        int x2 = (int)ic.getGene(2).getAllele();
        int x3 = (int)ic.getGene(3).getAllele();
        int x4 = (int)ic.getGene(4).getAllele();
        
        int signoY = (int)ic.getGene(5).getAllele();
        int y1 = (int)ic.getGene(6).getAllele();
        int y2 = (int)ic.getGene(7).getAllele();
        int y3 = (int)ic.getGene(8).getAllele();
        int y4 = (int)ic.getGene(9).getAllele();
        
        int x_valor = Integer.parseInt(x1+""+x2+""+x3+""+x4, 2);
        int y_valor = Integer.parseInt(y1+""+y2+""+y3+""+y4, 2);


        if (signoX==0){
            x_valor = -x_valor;
        }
        if (signoY==0){
            y_valor = -y_valor;
        }

        if (x_valor == y_valor) {
            return 0; // Penalización máxima
        }

        
        //Al restarle estoy obteniendo el valor mínimo global 
        //También se puede sacar la inversa
        // double score = 352-(x_valor*x_valor + y_valor*y_valor);
        double resultado1 = x_valor * x_valor - 8 * x_valor + 7;
        double resultado2 = y_valor * y_valor - 8 * y_valor + 7;

        resultado1 = Math.abs(resultado1);
        resultado2 = Math.abs(resultado2);

        // if (resultado1 < 0) {
        //     resultado1 = resultado1 * -1; 
        // }
        
        // if (resultado2 < 0) {
        //     resultado2 = resultado2 * -1; 
        // }

        // if ((x_valor == 7 && y_valor == 1) || (x_valor == 1 && y_valor == 7)) {
        //     return 704; // Máximo score
        // }

        // double score = 352-(x_valor*x_valor -8*x_valor+7);
        // double score = 352-(resultado);
        double score = 704-(resultado1 + resultado2);
        return score;
    }
}
