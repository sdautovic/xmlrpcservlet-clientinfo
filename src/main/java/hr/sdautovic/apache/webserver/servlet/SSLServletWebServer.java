package hr.sdautovic.apache.webserver.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.xmlrpc.webserver.ServletWebServer;

public class SSLServletWebServer extends ServletWebServer {
	private SSLServerSocketFactory m_sslSocketFactory = null;
	
	public SSLServletWebServer(HttpServlet pServlet, int pPort, String privateKeyStorePassphrase, String privateKeyStoreFile, 
			String trustedCrtKeyStorePassphrase, String trustedCrtKeyStoreFile) throws ServletException, NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, KeyManagementException, UnrecoverableKeyException {
		super(pServlet, pPort);
		this.m_sslSocketFactory = createSSLServerSocketFactory(privateKeyStorePassphrase, privateKeyStoreFile, trustedCrtKeyStorePassphrase, trustedCrtKeyStoreFile);
	}

	@Override
	protected ServerSocket createServerSocket(int pPort, int backlog, InetAddress addr) throws IOException {
		ServerSocket socket = this.m_sslSocketFactory.createServerSocket(pPort, backlog, addr);
		return socket;
	}
	
	private SSLServerSocketFactory createSSLServerSocketFactory(String privateKeyStorePassphrase, String privateKeyStoreFile,
			String trustedCrtKeyStorePassphrase, String trustedCrtKeyStoreFile) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, KeyManagementException, UnrecoverableKeyException {
		
		KeyStore keyStorePrivate = KeyStore.getInstance("PKCS12");
		keyStorePrivate.load(new FileInputStream(privateKeyStoreFile), privateKeyStorePassphrase.toCharArray());
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keyStorePrivate, privateKeyStorePassphrase.toCharArray());
		
		KeyStore keyStoreTrusted = KeyStore.getInstance("JKS");
		keyStoreTrusted.load(new FileInputStream(trustedCrtKeyStoreFile), trustedCrtKeyStorePassphrase.toCharArray());
		
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(keyStoreTrusted);
		
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		return ctx.getServerSocketFactory();
	}
}
