package AlgoritmosGeneticos.Agentes;

import java.util.Random;

import AlgoritmosGeneticos.Mensajes.Contenido;
import AlgoritmosGeneticos.Mensajes.Mensaje;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.UnreadableException;

public class Ag1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        System.out.println("Chau, AG1");
    }

    class Comportamiento extends Behaviour {

        
            private boolean done = false; 

            @Override
            public void action() {
                // Generar y enviar la configuración inicial
                Contenido contenido = generarNuevaConfiguracion();
                enviarConfiguracion(contenido);

                // Esperar respuesta de Ag2
                jade.lang.acl.ACLMessage acl = blockingReceive();

                if (acl != null && acl.getConversationId().equalsIgnoreCase("ValoresGenerados")) {
                    try {
                        String respuesta = acl.getContent();
                        if ("Configuración aceptada".equalsIgnoreCase(respuesta)) {
                            System.out.println("Ag1: Configuración aceptada por Ag2.");
                            done = true; 
                            doDelete();
                        } else if ("Configuración rechazada".equalsIgnoreCase(respuesta)) {
                            System.out.println("Ag1: Configuración rechazada. Generando nueva configuración...");
                            // Generar y enviar una nueva configuración
                            contenido = generarNuevaConfiguracion();
                            enviarConfiguracion(contenido);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public boolean done() {
                return done; // Finalizar cuando se encuentre la configuración correcta
            }

            // Método para generar una nueva configuración aleatoria
            private Contenido generarNuevaConfiguracion() {

                int poblacionInicial = generarAleatorio(1, 10);
                int iteracionGeneracion = generarAleatorio(1, 20);
                int evolucion = generarAleatorio(1, 20);

                // Imprimir los valores generados
                System.out.println("NUEVA CONFIG");
                System.out.println("Ag1: Generando configuración...");
                System.out.println("Población Inicial: " + poblacionInicial);
                System.out.println("Iteración Generación: " + iteracionGeneracion);
                System.out.println("Evolución: " + evolucion);

                return new Contenido(poblacionInicial, iteracionGeneracion, evolucion);
            }

            // Método para enviar una configuración a Ag2
            private void enviarConfiguracion(Contenido contenido) {
                Mensaje.enviarMensajeObj(myAgent, "Ag2", contenido, 0, "ValoresGenerados");
            }

            // Método para generar un número aleatorio entre un rango
            private int generarAleatorio(int min, int max) {
                Random random = new Random();
                return random.nextInt((max - min) + 1) + min;
            }
        }

    
}
