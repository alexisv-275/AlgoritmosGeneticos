package AlgoritmosGeneticos;

public class Ejecutable {
    public static void main(String[] args){
        //Una población de 4 cromosomas, cada uno con 10 genes, que experimentarán 6 eventos y evolucionarán 8 veces
        // new AgGenetico().configurar(10, 4, 10, 15);
        // new AgGenetico().configurar(10, 8, 15, 15);
        new AgGenetico().configurar(10, 8, 10, 15);
    }
    
}
