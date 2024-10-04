package in.cdac.model;

import javax.xml.datatype.XMLGregorianCalendar;

import in.cdac.cryptoservice.KeyType;

public class ResponseObject 
{
	private String status;
	private String error;
	private String txn;
	private String number;
	private XMLGregorianCalendar ts;
	private String keyidentifier;
	private KeyType keytype;
	private String refNum;
	private String idType;
	private String idData;
	private String prn ;
	private String userScheme;
	
	public String getUserScheme() {
		return userScheme;
	}

	public void setUserScheme(String userScheme) {
		this.userScheme = userScheme;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getIdType() 
	{
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdData() {
		return idData;
	}

	public void setIdData(String idData) {
		this.idData = idData;
	}

	public KeyType getKeytype() {
		return keytype;
	}

	public void setKeytype(KeyType keytype) {
		this.keytype = keytype;
	}

	public String getKeyidentifier() {
		return keyidentifier;
	}

	public void setKeyidentifier(String keyidentifier) {
		this.keyidentifier = keyidentifier;
	}

	public ResponseObject()
	{
		
	}
	/*
	 * public ResponseObject(String status) { this.status = status; }
	 * 
	 * public ResponseObject(String status, String error) { this.error = error;
	 * this.status = status; }
	 */
	
	
	public XMLGregorianCalendar getTs() {
		return ts;
	}
	public void setTs(XMLGregorianCalendar ts) {
		this.ts = ts;
	}
	public String getTxn() {
		return txn;
	}
	public void setTxn(String txn) {
		this.txn = txn;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public String getError() 
	{
		return error;
	}
	public void setError(String error) 
	{
		this.error = error;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	
}