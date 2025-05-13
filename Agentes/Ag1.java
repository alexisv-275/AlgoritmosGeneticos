package AlgoritmosGeneticos.Agentes;

import java.util.Random;

import AlgoritmosGeneticos.Mensajes.Contenido;
import AlgoritmosGeneticos.Mensajes.Mensaje;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

public class Ag1 extends Agent{

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }
    
    
    class Comportamiento extends Behaviour {

        @Override
        public void action() {
            // Generar valores aleatorios
            int poblacionInicial = generarAleatorio(4, 10);
            int iteracionGeneracion = generarAleatorio(10, 20);
            int evolucion = generarAleatorio(10, 20);

            // Imprimir los valores generados (puedes enviar estos valores a otro agente si es necesario)
            System.out.println("Población Inicial: " + poblacionInicial);
            System.out.println("Iteración Generación: " + iteracionGeneracion);
            System.out.println("Evolución: " + evolucion);

            
            Contenido contenido = new Contenido(poblacionInicial, iteracionGeneracion, evolucion);

            Mensaje.enviarMensajeObj(myAgent, "Ag2", contenido, 0, "ValoresGenerados");

            jade.lang.acl.ACLMessage acl = blockingReceive();

        }

        @Override
        public boolean done() {
            return false;
        }

        // Método para generar un número aleatorio entre un rango
        private int generarAleatorio(int min, int max) {
            Random random = new Random();
            return random.nextInt((max - min) + 1) + min;
        }
    }


    
}
