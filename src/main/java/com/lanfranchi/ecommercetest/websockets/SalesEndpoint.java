package com.lanfranchi.ecommercetest.websockets;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/channel/sales")
public class SalesEndpoint {
	
	@Inject
	private ConnectedUsers connectedUsers;
	
	@OnOpen
	public void onNewUser(Session session){
		System.out.println("Novo usuario conectado: "+session);
		connectedUsers.add(session);
	}
	
	@OnClose
	public void onClose(Session session,CloseReason closeReason) {
		System.out.println("Removendo sessao WS: "+session+" - "+connectedUsers.remove(session));
		System.out.println("Motivo: "+closeReason.getCloseCode());
	}
}
