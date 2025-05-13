package AlgoritmosGeneticos.Mensajes;

import java.io.IOException;
import java.io.Serializable;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class Mensaje {

    //Para no instanciar objeto Mensaje. 
    public static void enviarMensaje(Agent emisor, String receptor, String msj, int tipo, String idConversation){

        //LENGUAJE COMUNICACION AGENTE -> ACL

        //Parámetro -> int (tipo de mensaje)
        ACLMessage acl = new ACLMessage(tipo); 

        //Definir receptor
        AID aid = new AID();
        
        aid.setLocalName(receptor);

        //Add -> definir comunicacion en Broadcast

        acl.addReceiver(aid);

        //Definir emisor
        acl.setSender(emisor.getAID());

        // Serializable -> conversion de cadena de datos a bites para enviar por los buses del sistema (String -> Bites)

        //Mensaje
        acl.setContent(msj);

        //Lenguaje 
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        //Codigo entre agentes para decidir si recibir o no mensajes
        acl.setConversationId(idConversation);

        //Enviar mensaje 
        emisor.send(acl);
    }

   //Enviar objeto
    
    public static void enviarMensajeObj(Agent emisor, String receptor, Serializable obj, int tipo, String idConversation){

        //LENGUAJE COMUNICACION AGENTE -> ACL

        //Parámetro -> int (tipo de mensaje)
        ACLMessage acl = new ACLMessage(tipo); 

        //Definir receptor
        AID aid = new AID();
        
        aid.setLocalName(receptor);

        //Add -> definir comunicacion en Broadcast

        acl.addReceiver(aid);

        //Definir emisor
        acl.setSender(emisor.getAID());

        // Serializable -> conversion de cadena de datos a bites para enviar por los buses del sistema (String -> Bites)

        //Mensaje
        try {
            acl.setContentObject(obj);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Lenguaje 
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        //Codigo entre agentes para decidir si recibir o no mensajes
        acl.setConversationId(idConversation);

        //Enviar mensaje 
        emisor.send(acl);
    }
    }