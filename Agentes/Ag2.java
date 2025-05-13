package AlgoritmosGeneticos.Agentes;

import java.util.Random;

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

    class Comportamiento extends Behaviour {

        @Override
        public void action() {

            jade.lang.acl.ACLMessage acl = blockingReceive();

            if (acl.getConversationId().equalsIgnoreCase("ValoresGenerados")) {

                try {
                    Contenido c1 = (Contenido)acl.getContentObject();

                    new AgGenetico().configurar(10, c1.getPoblacionInicial(), c1.getIteracionGeneracion(), c1.getEvolucion());

                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
                
            }
            

        }

        @Override
        public boolean done() {
            return false;
        }

    }
    
}
