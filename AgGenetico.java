package AlgoritmosGeneticos;


import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class AgGenetico {

    public void configurar(int genes) {

    // Crea una configuración predeterminada para el algoritmo genético
    Configuration config = new DefaultConfiguration();

    // Crea una instancia de la función de aptitud personalizada
    FuncionAptitud fa = new FuncionAptitud();

    try {
        // Establece la función de aptitud en la configuración
        config.setFitnessFunction(fa);

        // Crea un arreglo de genes para el cromosoma, con tamaño igual al número de genes especificado
        Gene[] sampleGenes = new Gene[genes];

        // Inicializa cada gen con un rango de valores (en este caso, entre 0 y 1)
        for (int i = 0; i < genes; i++) {
         sampleGenes[i] = new IntegerGene(config, 0, 1); // Cambia el rango según tus necesidades
        }

        // Crea un cromosoma con los genes configurados
        Chromosome ic = new Chromosome(config, sampleGenes);

        // Establece el cromosoma de muestra en la configuración
        config.setSampleChromosome(ic);


        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }


    
}