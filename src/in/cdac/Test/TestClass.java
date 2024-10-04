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

public class TestClass {
	public static void main(String[] args) throws MalformedURLException, IOException {
		IConnector conn = new ConnectorImpl();
		RequestObject vltReq = new RequestObject();
		vltReq.setOpr(Operations.STRUID);
		vltReq.setTxn(UUID.randomUUID().toString());
		vltReq.setTs(Utility.generateTimeStamp());
		vltReq.setKeytype(KeyType.AES);
		// vltReq.setRefNum("938009319450238976");
		// vltReq.setNumber("534354163142");
		vltReq.setNumber("520872869150");// 999971658847
		vltReq.setRefNum("1234803686430928896");
//		 vltReq.setKeyidentifier("eb7bc1137a89d047006899dbf2b6abb41a86d95a999af74d9e55a1bf06b6ce75");
		vltReq.setTkntype(TokenType.SOFT);
		// Remove
		// Client sets in source code.
		vltReq.setAc("A100001");
		vltReq.setSa("A100001");
		vltReq.setLk("ddb81abd-23d7-4cf6-ab5e-29d01bcc2950");
		vltReq.setUrl("http://10.210.9.67:8080/vault/");
		//vltReq.setFileParam("config.properties");
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
		}
	}
}