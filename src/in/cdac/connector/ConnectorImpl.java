package in.cdac.connector;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import in.cdac.cryptoservice.Request;
import in.cdac.cryptoservice.Response;
import in.cdac.cryptoservice.Status;
import in.cdac.env.EnvironmentReader;
import in.cdac.model.RequestObject;
import in.cdac.model.ResponseObject;
import in.cdac.pii.PII;
import in.cdac.util.Debugger;
import in.cdac.util.Utility;
import in.cdac.validator.RequestValidator;
import in.cdac.xmlcreator.RequestXMLCreator;

public class ConnectorImpl implements IConnector
{
    private static EnvironmentReader envreader;
    private boolean debug;
    private static Debugger debugger;
    
    public ConnectorImpl() {
        this.debug = false;
    }
    
    public static void loadFileStatic(final String file) {
        ConnectorImpl.envreader = new EnvironmentReader(file);
    }
    
    public ResponseObject requestVault(final RequestObject reqObj) throws IOException {
        boolean readProperty = false;
        if (reqObj.getFileParam() != null) {
            loadFileStatic(reqObj.getFileParam());
            readProperty = true;
        }
        this.setParameters(reqObj, readProperty);
        final RequestValidator requestValidator = new RequestValidator(ConnectorImpl.debugger);
        final String validate = requestValidator.commonValidate(reqObj);
        if (validate != null) {
            return this.libErrorResp(reqObj, validate);
        }
        final RequestXMLCreator xmlCreator = new RequestXMLCreator();
        Request request = null;
        try {
            request = xmlCreator.prepareRequestObject(reqObj);
        }
        catch (Exception e) {
            ConnectorImpl.debugger.printStackTrace(e, this.debug);
            return this.libErrorResp(reqObj, "REQUEST_CREATION_ERROR");
        }
        HashMap<String, String> requestXml = this.marshallReq(request);
       // System.out.println("request xml=====>"+requestXml);
        if (requestXml.get("status").equalsIgnoreCase(Status.N.value().trim())) {
            return this.libErrorResp(reqObj, requestXml.get("error"));
        }
		/*
		 * final Proxy webProxy = new Proxy(Proxy.Type.HTTP, new
		 * InetSocketAddress(reqObj.getProxyURL(),
		 * Integer.valueOf(reqObj.getProxyPort()))); final
		 * SimpleClientHttpRequestFactory requestFactory = new
		 * SimpleClientHttpRequestFactory(); requestFactory.setProxy(webProxy); String
		 * encodedValue = null; if (reqObj.getProxyAuthRequired().equals("N")) {
		 * encodedValue =
		 * Base64.getEncoder().encodeToString(String.valueOf(reqObj.getProxyUser() + ":"
		 * + reqObj.getProxyPassword()).getBytes()); } final HashMap<String, String>
		 * callRestService = this.callRestService(String.valueOf(reqObj.getUrl().trim())
		 * + request.getOpr().value().trim().toLowerCase(), requestXml.get("data"),
		 * requestFactory, encodedValue);
		 */
        //API call
        HashMap<String, String> callRestService = this.callRestService(String.valueOf(reqObj.getUrl().trim())
		+ request.getOpr().value().trim().toLowerCase(), requestXml.get("data"));
    //    System.out.println("response connector::: "+callRestService);
        if (callRestService.get("status").equalsIgnoreCase(Status.N.value().trim())) {
            return this.libErrorResp(reqObj, callRestService.get("error"));
        }
        final HashMap<String, Object> response = this.unMarshResp(callRestService.get("data"));
        if (((String) response.get("status")).equalsIgnoreCase(Status.N.value().trim())) {
            return this.libErrorResp(reqObj, (String)response.get("error"));
        }
       
        
        ResponseObject respObj = new ResponseObject();
        try {
            respObj = this.vaultResponse((Response)response.get("data"), reqObj.getOpr().toString());
        }
        catch (Exception e2) {
            ConnectorImpl.debugger.printStackTrace(e2, this.debug);
            return this.libErrorResp(reqObj, "PII_ERROR");
        }
        return respObj;
    }
    
    private ResponseObject vaultResponse(final Response resp, final String opr) throws Exception {
        final ResponseObject respObj = new ResponseObject();
        respObj.setTxn(resp.getTxn());
        respObj.setTs(resp.getTs());
        
        if (resp.getStatus().value().equalsIgnoreCase(Status.Y.value())) {
            final String str1 = opr;
            switch (opr) {
                case "STRUID": {
                    respObj.setRefNum(resp.getData().getRefno());
                    respObj.setPrn(resp.getData().getPrn());
                    break;
                }
                case "DEACTIVATE": {
                    respObj.setRefNum((String)null);
                    break;
                }
                case "ACTIVATE": {
                    respObj.setRefNum((String)null);
                    break;
                }
                case "GETREFNUM": {
                    respObj.setRefNum(resp.getData().getRefno());
                    break;
                }
                case "GETUID": {
                    final String data = new String(resp.getData().getValue());
                    final PII piiData = (PII)Utility.unmarshallXml((Class)PII.class, data);
                    respObj.setNumber(piiData.getId().getPid());
                    break;
                }
                case "USERSCHEMES": {
    				respObj.setRefNum(resp.getData().getRefno());
    				respObj.setPrn(resp.getData().getPrn());
    				respObj.setUserScheme(resp.getUserscheme());
    				break;
    			}
                default:
                    break;
            }
        }
        if (resp.getKeytype() != null) {
            respObj.setKeytype(resp.getKeytype());
        }
        if (resp.getKeyidentifier() != null) {
            respObj.setKeyidentifier(resp.getKeyidentifier());
        }
        respObj.setStatus(resp.getStatus().value());
        respObj.setError(resp.getError());
        return respObj;
    }
    
    private ResponseObject libErrorResp(final RequestObject request, final String error) {
        final ResponseObject respObject = new ResponseObject();
        respObject.setStatus(Status.N.value());
        respObject.setError(error);
        if (request.getTxn() != null) {
            respObject.setTxn(request.getTxn());
        }
        respObject.setTs(Utility.generateTimeStamp());
        return respObject;
    }
    
    private HashMap<String, Object> unMarshResp(final String respXml) {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            final Response resp = (Response)Utility.unmarshallXml((Class)Response.class, respXml);
            if (resp == null) {
                map.put("status", Status.N.value().trim());
                map.put("error", "RESPONSE_UNMARSHALLING_ERROR");
            }
            else {
                map.put("status", Status.Y.value().trim());
                map.put("data", resp);
            }
        }
        catch (Exception e) {
            ConnectorImpl.debugger.printStackTrace(e, this.debug);
            map.put("status", Status.N.value().trim());
            map.put("error", "RESPONSE_UNMARSHALLING_ERROR");
        }
        return map;
    }
    
    private HashMap<String, String> marshallReq(final Request req) {
        String reqXml = null;
        final HashMap<String, String> map = new HashMap<String, String>();
        try {
            reqXml = Utility.marshallObj((Class)Request.class, (Object)req);
            if (reqXml == null) {
                map.put("status", Status.N.value().trim());
                map.put("error", "REQ_MARSHALLING_ERROR");
            }
            else {
                map.put("status", Status.Y.value().trim());
                map.put("data", reqXml);
            }
        }
        catch (Exception e) {
            ConnectorImpl.debugger.printStackTrace(e, this.debug);
            map.put("error", "REQ_MARSHALLING_ERROR");
            map.put("status", Status.N.value().trim());
        }
        return map;
    }
    
    private HashMap<String, String> callRestService(final String vaultUrl, final String reqxml) {
         HashMap<String, String> map = new HashMap<String, String>();
        try {
             RestTemplate restTemplate = new RestTemplate();
             HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<String> request =  new HttpEntity<String> (reqxml,headers);
            String respXML = restTemplate.postForObject(vaultUrl, request,String.class);
            if (respXML == null) {
                map.put("status", Status.N.value().trim());
                map.put("error", "NULL_RESPONSE");
            }
            else {
                map.put("status", Status.Y.value().trim());
                map.put("data", respXML);
            }
        }
        catch (Exception excp) {
            ConnectorImpl.debugger.printStackTrace(excp, this.debug);
            map.put("status", Status.N.value().trim());
            map.put("error", "NETWORK_ISSUE");
        }
        return map;
    }
    
    private void setParameters(final RequestObject requestObject, final boolean useProperty) {
        if (useProperty) {
            requestObject.setAc(ConnectorImpl.envreader.getAC());
            requestObject.setSa(ConnectorImpl.envreader.getSA());
            requestObject.setLk(ConnectorImpl.envreader.getLK());
            requestObject.setUrl(ConnectorImpl.envreader.getVaultURL());
            this.debug = Boolean.parseBoolean(ConnectorImpl.envreader.getDebugFlag());
        }
    }
    
    static {
        ConnectorImpl.envreader = null;
        ConnectorImpl.debugger = new Debugger();
    }
}