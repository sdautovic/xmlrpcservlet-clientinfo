package hr.sdautovic.apache.webserver.xmlrpc.handlers;

import hr.sdautovic.apache.webserver.xmlrpc.servlet.XmlRpcServletClientInfo;

public class ExampleXmlRpcHandler {
	public String ping(String ping) {
		System.out.println("ping() [client=" + XmlRpcServletClientInfo.getClientInfo().getClientIP() + ":" 
				+ XmlRpcServletClientInfo.getClientInfo().getClientPort() + "] ping=" + ping);
		return "PONG: " + ping;
	}
}
