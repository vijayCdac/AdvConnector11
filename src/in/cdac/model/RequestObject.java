package in.cdac.model;

import javax.xml.datatype.XMLGregorianCalendar;

import in.cdac.cryptoservice.Idtype;
import in.cdac.cryptoservice.KeyType;
import in.cdac.cryptoservice.Operations;
import in.cdac.cryptoservice.TokenType;

public class RequestObject
{
    private String ac;
    private Operations opr;
    private TokenType tkntype;
    private KeyType keytype;
    private String keyidentifier;
    private String lk;
    private String txn;
    private String aadhaarNum;
    private String refNum;
    private XMLGregorianCalendar ts;
    private String sa;
    private String pin;
    private Idtype idType;
    private String type;
    private byte[] value;
    private String val;
    private String data;
    private String idData;
    private String mac;
    private String url;
    private String fileParam;
    private String ip;
    
    public String getFileParam() {
        return this.fileParam;
    }
    
    public void setFileParam(final String fileParam) {
        this.fileParam = fileParam;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public String getAadhaarNum() {
        return this.aadhaarNum;
    }
    
    public void setAadhaarNum(final String aadhaarNum) {
        this.aadhaarNum = aadhaarNum;
    }
    
    public String getMac() {
        return this.mac;
    }
    
    public void setMac(final String mac) {
        this.mac = mac;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public String getIdData() {
        return this.idData;
    }
    
    public void setIdData(final String idData) {
        this.idData = idData;
    }
    
    public String getNumber() {
        return this.aadhaarNum;
    }
    
    public void setNumber(final String number) {
        this.aadhaarNum = number;
    }
    
    public XMLGregorianCalendar getTs() {
        return this.ts;
    }
    
    public void setTs(final XMLGregorianCalendar ts) {
        this.ts = ts;
    }
    
    public String getAc() {
        return this.ac;
    }
    
    public void setAc(final String ac) {
        this.ac = ac;
    }
    
    public Operations getOpr() {
        return this.opr;
    }
    
    public void setOpr(final Operations opr) {
        this.opr = opr;
    }
    
    public TokenType getTkntype() {
        return this.tkntype;
    }
    
    public void setTkntype(final TokenType tkntype) {
        this.tkntype = tkntype;
    }
    
    public KeyType getKeytype() {
        return this.keytype;
    }
    
    public void setKeytype(final KeyType keytype) {
        this.keytype = keytype;
    }
    
    public String getKeyidentifier() {
        return this.keyidentifier;
    }
    
    public void setKeyidentifier(final String keyidentifier) {
        this.keyidentifier = keyidentifier;
    }
    
    public String getLk() {
        return this.lk;
    }
    
    public void setLk(final String lk) {
        this.lk = lk;
    }
    
    public String getTxn() {
        return this.txn;
    }
    
    public void setTxn(final String txn) {
        this.txn = txn;
    }
    
    public String getRefNum() {
        return this.refNum;
    }
    
    public void setRefNum(final String refNum) {
        this.refNum = refNum;
    }
    
    public String getSa() {
        return this.sa;
    }
    
    public void setSa(final String sa) {
        this.sa = sa;
    }
    
    public String getPin() {
        return this.pin;
    }
    
    public void setPin(final String pin) {
        this.pin = pin;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public byte[] getValue() {
        return this.value;
    }
    
    public void setValue(final byte[] value) {
        this.value = value;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public String getVal() {
        return this.val;
    }
    
    public void setVal(final String val) {
        this.val = val;
    }
    
    public Idtype getIdType() {
        return this.idType;
    }
    
    public void setIdType(final Idtype idType) {
        this.idType = idType;
    }
}