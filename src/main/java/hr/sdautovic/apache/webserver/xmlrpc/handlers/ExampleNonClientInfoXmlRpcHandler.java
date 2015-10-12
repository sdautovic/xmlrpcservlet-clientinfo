package hr.sdautovic.apache.webserver.xmlrpc.handlers;

public class ExampleNonClientInfoXmlRpcHandler {
	public String ping(String ping) {
		System.out.println("ping() ping=" + ping);
		return "PONG: " + ping;
	}
}
