package in.cdac.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

import in.cdac.connector.ConnectorImpl;
import in.cdac.connector.IConnector;
import in.cdac.cryptoservice.KeyType;
import in.cdac.cryptoservice.Operations;
import in.cdac.cryptoservice.Status;
import in.cdac.cryptoservice.TokenType;
import in.cdac.model.RequestObject;
import in.cdac.model.ResponseObject;
import in.cdac.util.Utility;

public class JMeterTest {
			
	public void jMeterTestClass() throws MalformedURLException, IOException
	{
		IConnector conn = new ConnectorImpl();
		RequestObject vltReq = new RequestObject();
		vltReq.setOpr(Operations.GETUID);
		vltReq.setTxn(UUID.randomUUID().toString());
		vltReq.setTs(Utility.generateTimeStamp());
		// vltReq.setRefNum("938009319450238976");
		// vltReq.setNumber("534354163142");
		vltReq.setNumber("154067682643");// 999971658847
		vltReq.setRefNum("1100675670965436416");
		// vltReq.setKeyidentifier("eb7bc1137a89d047006899dbf2b6abb41a86d95a999af74d9e55a1bf06b6ce75");
		vltReq.setKeytype(KeyType.AES);
		vltReq.setTkntype(TokenType.SOFT);
		// Remove
		// Client sets in source code.
		vltReq.setAc("A100020");
		//vltReq.setAc("A100019");
		
		vltReq.setSa("A100020");
		//vltReq.setSa("A100019");
		vltReq.setLk("834081be-20d0-4c90-aaa5-5fa4f5627aa2");
		//vltReq.setLk("834081be-20d0-4c90-aaa5-5fa4f5627aa1");
		
		//vltReq.setLk("503e94c0-d8c0-4c8d-af77-85ab45248e9c");
		
		vltReq.setUrl("http://localhost:8080/vault/");
		// vltReq.setFileParam("config.properties");
		// vltReq.setIdType(Idtype.UID);
		// vltReq.setIdData("N");
		ResponseObject vltResp = conn.requestVault(vltReq);
		// System.out.println("Status : " + vltResp.getStatus());

		if (vltResp.getStatus().equals(Status.N.value().trim())) {
			System.out.println("ERROR : " + vltResp.getError() + "   Status : " + vltResp.getStatus());
		} else {
			System.out.println("REFNUM : " + vltResp.getRefNum() + "    AadhaarNumber : " + vltResp.getNumber()
					+ "    Status : " + vltResp.getStatus() + "    Prn : " + vltResp.getPrn() + "    userScheme : "
					+ vltResp.getUserScheme());
			// System.out.println("AadhaarNumber : " + vltResp.getNumber());
		}
		
		
		
	}
}
