package AlgoritmosGeneticos.Agentes;

import java.util.ArrayList;
import java.util.List;

import org.jgap.IChromosome;

import AlgoritmosGeneticos.AlgoritmoGenetico.AgGenetico;
import AlgoritmosGeneticos.Mensajes.Contenido;
import AlgoritmosGeneticos.Mensajes.Mensaje;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.UnreadableException;

public class Ag2 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        System.out.println("Chau, AG2");
    }

    class Comportamiento extends Behaviour {
        private boolean done = false;

        @Override
        public void action() {

            // Recibir mensaje de Ag1
            jade.lang.acl.ACLMessage acl = blockingReceive();

            if (acl != null && acl.getConversationId().equalsIgnoreCase("ValoresGenerados")) {
                try {
                    Contenido c1 = (Contenido) acl.getContentObject();
                    System.out.println("Ag2: Configuración recibida de Ag1.");
                    System.out.println("Validando configuración...");

                    // Validar la configuración
                    boolean esValido = validarConfiguracion(c1);

                    // Responder a Ag1
                    if (esValido) {
                        System.out.println("Ag2: Configuración aceptada.");
                        Mensaje.enviarMensaje(myAgent, "Ag1", "Configuración aceptada", 0, "ValoresGenerados");

                        System.out.println("Ag2: Enviando configuración aceptada a Ag3...");
                        Mensaje.enviarMensajeObj(myAgent, "Ag3", c1, 0, "ValoresGeneradosFinales");
                        System.out.println("Ag2: Mensaje enviado a Ag3.");
                        done = true;
                        doDelete();

                    } else {
                        System.out.println("Ag2: Configuración rechazada.");
                        Mensaje.enviarMensaje(myAgent, "Ag1", "Configuración rechazada", 0, "ValoresGenerados");
                    }

                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }

        }

        private boolean validarConfiguracion(Contenido c1) {
            List<IChromosome> mejoresCromosomas = new ArrayList<>();

            // Ejecutar la configuración 5 veces
            AgGenetico ag = new AgGenetico();

            for (int i = 0; i < 5; i++) {

                ag = new AgGenetico(); // Reiniciar la configuración creando una nueva instancia
                System.out.println("Ejecución #" + (i + 1));

                // ag.configurar(10, 4, 3, 4);
                ag.configurar(10, c1.getPoblacionInicial(), c1.getIteracionGeneracion(),
                c1.getEvolucion());
                // ag.configurar(10, 8, 10, 15);

                // Obtener el mejor cromosoma y almacenarlo en la lista
                IChromosome mejor = ag.getMejorIndividuo();
                System.out.println("PILAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS");
                ag.ver_individuo(mejor);

                mejoresCromosomas.add(mejor);

            }
            // Validar que todos los cromosomas en la lista sean consistentes
            for (int j = 1; j < mejoresCromosomas.size(); j++) {
                System.out.println("Validando ejecución #" + j);
                if (!sonResultadosEquivalentes(mejoresCromosomas.get(0), mejoresCromosomas.get(j))) {
                    System.out.println("Inconsistencia detectada en los cromosomas.");
                    return false; // Si algún cromosoma es diferente, no es consistente
                }
            }

            System.out.println("Todos los resultados son consistentes.");
            return true; // Si todos los resultados son iguales, es consistente

        }

        private boolean sonResultadosEquivalentes(IChromosome c1, IChromosome c2) {
            // Extraer los valores de x e y de ambos cromosomas
            int[] resultado1 = obtenerValoresXY(c1);
            int[] resultado2 = obtenerValoresXY(c2);

            // Validar que las combinaciones de x e y sean equivalentes (considerando ambas
            // permutaciones)
            return (resultado1[0] == resultado2[0] && resultado1[1] == resultado2[1]) ||
                    (resultado1[0] == resultado2[1] && resultado1[1] == resultado2[0]);
        }

        private int[] obtenerValoresXY(IChromosome ic) {
            int signoX = (int) ic.getGene(0).getAllele();
            int x1 = (int) ic.getGene(1).getAllele();
            int x2 = (int) ic.getGene(2).getAllele();
            int x3 = (int) ic.getGene(3).getAllele();
            int x4 = (int) ic.getGene(4).getAllele();

            int signoY = (int) ic.getGene(5).getAllele();
            int y1 = (int) ic.getGene(6).getAllele();
            int y2 = (int) ic.getGene(7).getAllele();
            int y3 = (int) ic.getGene(8).getAllele();
            int y4 = (int) ic.getGene(9).getAllele();

            int x_valor = Integer.parseInt(x1 + "" + x2 + "" + x3 + "" + x4, 2);
            int y_valor = Integer.parseInt(y1 + "" + y2 + "" + y3 + "" + y4, 2);

            System.out.println("x:" + x_valor + " y:" + y_valor);

            if (signoX == 0) {
                x_valor = -x_valor;
            }
            if (signoY == 0) {
                y_valor = -y_valor;
            }

            return new int[] { x_valor, y_valor };
        }

        @Override
        public boolean done() {
            return done;
        }

    }

}
