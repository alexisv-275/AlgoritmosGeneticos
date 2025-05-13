package AlgoritmosGeneticos.Agentes;

import AlgoritmosGeneticos.Mensajes.Contenido;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.UnreadableException;

public class Ag3 extends Agent {

    @Override
    protected void setup() {
        // Aquí puedes agregar el comportamiento del agente Ag3
        System.out.println("Agente Ag3 iniciado.");
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        System.out.println("Chau, AG3");
    }

    class Comportamiento extends Behaviour {

        private boolean done = false; // Variable para controlar cuándo finalizar

        @Override
        public void action() {

            // Esperar respuesta de Ag2
            jade.lang.acl.ACLMessage acl = blockingReceive();

            if (acl != null && acl.getConversationId().equalsIgnoreCase("ValoresGeneradosFinales")) {
                try {
                    Contenido c1 = (Contenido) acl.getContentObject();

                    System.out.println("-------------Configuración adecuada---------------");
                    System.out.println("Población: " + c1.getPoblacionInicial());
                    System.out.println("Iteraciones: " + c1.getIteracionGeneracion());
                    System.out.println("Evolución: " + c1.getEvolucion());

                    done = true;
                    doDelete();

                } catch (UnreadableException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public boolean done() {
            return done; // Finalizar cuando se encuentre la configuración correcta
        }
    }
}
