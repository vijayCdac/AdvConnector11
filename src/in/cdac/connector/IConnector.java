package in.cdac.connector;

import java.io.IOException;
import java.net.MalformedURLException;

import in.cdac.model.RequestObject;
import in.cdac.model.ResponseObject;

/**
 * @author CDAC
 *  This class contains the major methods to call the requests
 *
 */
public interface IConnector 
{
	public ResponseObject requestVault(RequestObject requestObject) throws MalformedURLException, IOException;
	
}