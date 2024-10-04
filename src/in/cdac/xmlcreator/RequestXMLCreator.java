package in.cdac.xmlcreator;

import java.io.UnsupportedEncodingException;



import in.cdac.cryptoservice.CustOpts;
import in.cdac.cryptoservice.DataElement;
import in.cdac.cryptoservice.Operations;
import in.cdac.cryptoservice.ParaName;
import in.cdac.cryptoservice.Request;
import in.cdac.model.RequestObject;
import in.cdac.pii.IdTag;
import in.cdac.pii.PII;
import in.cdac.util.MacAddressCall;
import in.cdac.util.MacObj;
import in.cdac.util.Utility;

/**
 * This class populates all values in the request object for request XML
 * creation
 * 
 * @author CDAC
 *
 */
public class RequestXMLCreator 
{
	/**
	 * This method sets the required values in the request
	 * 
	 * @param reqObj
	 * @return
	 * @throws Exception 
	 * @throws  
	 * @throws UnsupportedEncodingException
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	static String macAddress;
	static String currentIp;

	static
	{
		MacAddressCall.getMacAddress();
		currentIp=MacObj.getCurrentIp();
		macAddress=MacObj.getMacAddress();
	}
	
	/**
	 * 
	 * @param reqObj
	 * @return
	 * @throws Exception
	 */
	public Request prepareRequestObject(RequestObject reqObj) throws Exception
	{
		Request request = new Request();
		request.setTxn(reqObj.getTxn());
		request.setAc(reqObj.getAc());
		request.setSa(reqObj.getSa());
		if (reqObj.getKeyidentifier() != null) 
		{
			request.setKeyidentifier(reqObj.getKeyidentifier());
		}
		if (reqObj.getKeytype() != null) 
		{
			request.setKeytype(reqObj.getKeytype());
		}
		if (reqObj.getLk() != null) 
		{
			request.setLk(reqObj.getLk());
		}
		request.setOpr(reqObj.getOpr());
		request.setTkntype(reqObj.getTkntype());
		request.setTs(reqObj.getTs());
		request.setMac(macAddress);
		request.setIp(currentIp);
		DataElement dle = new DataElement();
		if (reqObj.getPin() != null) 
		{
			CustOpts custOpts = new CustOpts();
			ParaName pname = new ParaName();
			pname.setName("pin");
			pname.setValue(reqObj.getPin());
			custOpts.getParaNames().add(pname);
			request.setCustOpts(custOpts);
		}
		if (reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.GETUID.value().trim())
				|| reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.ACTIVATE.value().trim())
				|| reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.DEACTIVATE.value().trim())
				|| reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.DELETE.value().trim())) 
		{
			dle.setRefno(reqObj.getRefNum());
			//dle.setType(reqObj.getIdType());
		} 
		else if (reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.STRUID.value().trim())
				|| reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.GETREFNUM.value().trim())
				|| reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.USERSCHEMES.value().trim())) 
		{
			PII pii = new PII();
			IdTag uid = new IdTag();
			uid.setPid(reqObj.getNumber());
			pii.setId(uid);
			String resultid = Utility.marshallObj(pii.getClass(), pii);
			dle.setValue(resultid.getBytes("UTF-8"));
			//dle.setType(reqObj.getIdType());
		}
		request.setData(dle);
		return request;
	}
}
