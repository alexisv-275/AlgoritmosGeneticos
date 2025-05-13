package AlgoritmosGeneticos.Mensajes;

import java.io.Serializable;

public class Contenido implements  Serializable {
    private int poblacionInicial;
    private int iteracionGeneracion;
    private int evolucion;


    public Contenido(int poblacionInicial, int iteracionGeneracion, int evolucion)  {
        this.poblacionInicial = poblacionInicial;
        this.iteracionGeneracion = iteracionGeneracion;
        this.evolucion = evolucion;
    }

    public int getPoblacionInicial() {
        return poblacionInicial;
    }

    public void setPoblacionInicial(int poblacionInicial) {
        this.poblacionInicial = poblacionInicial;
    }

    public int getIteracionGeneracion() {
        return iteracionGeneracion;
    }

    public void setIteracionGeneracion(int iteracionGeneracion) {
        this.iteracionGeneracion = iteracionGeneracion;
    }

    public int getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(int evolucion) {
        this.evolucion = evolucion;
    }
    
}
