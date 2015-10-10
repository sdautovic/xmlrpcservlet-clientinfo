package hr.sdautovic.apache.webserver.xmlrpc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import hr.sdautovic.apache.webserver.xmlrpc.adt.ClientInfo;

public class XmlRpcServletClientInfo extends XmlRpcServlet {
	private static final long serialVersionUID = 6597985487839748908L;
	
	PropertyHandlerMapping m_propertyHandlerMapping;
	private static ThreadLocal<ClientInfo> ms_clientInfo = new ThreadLocal<ClientInfo>();
	
    public static ClientInfo getClientInfo() {
        return ms_clientInfo.get();
    }
    
	public XmlRpcServletClientInfo(PropertyHandlerMapping propertyHandlerMapping) {
		this.m_propertyHandlerMapping = propertyHandlerMapping;
	}
	
	@Override
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		ClientInfo ci = new ClientInfo(pRequest.getRemoteAddr(), pRequest.getRemotePort());
		ms_clientInfo.set(ci);
		super.doPost(pRequest, pResponse);
	}
	
	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
		return m_propertyHandlerMapping;
	}
}
