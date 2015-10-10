package hr.sdautovic.apache.webserver.xmlrpc.adt;

public class ClientInfo {
	private String m_clientIP;
	private int m_clientPort;
	
	public ClientInfo(String clientIP, int clientPort) {
		this.m_clientIP = clientIP;
		this.m_clientPort = clientPort;
	}
	
	public String getClientIP() {
		return this.m_clientIP;
	}
	
	public int getClientPort() {
		return this.m_clientPort;
	}
}
