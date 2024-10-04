//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.11.06 at 09:51:41 AM IST 
//


package in.cdac.cryptoservice;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Operations.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Operations">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="sign"/>
 *     &lt;enumeration value="encrypt"/>
 *     &lt;enumeration value="decrypt"/>
 *     &lt;enumeration value="getrefnum"/>
 *     &lt;enumeration value="getuid"/>
 *     &lt;enumeration value="struid"/>
 *     &lt;enumeration value="activate"/>
 *     &lt;enumeration value="deactivate"/>
 *     &lt;enumeration value="delete"/>
 *     &lt;enumeration value="userschemes"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Operations")
@XmlEnum
public enum Operations {

    @XmlEnumValue("sign")
    SIGN("sign"),
    @XmlEnumValue("encrypt")
    ENCRYPT("encrypt"),
    @XmlEnumValue("decrypt")
    DECRYPT("decrypt"),
    @XmlEnumValue("getrefnum")
    GETREFNUM("getrefnum"),
    @XmlEnumValue("getuid")
    GETUID("getuid"),
    @XmlEnumValue("struid")
    STRUID("struid"),
    @XmlEnumValue("activate")
    ACTIVATE("activate"),
    @XmlEnumValue("deactivate")
    DEACTIVATE("deactivate"),
    @XmlEnumValue("delete")
    DELETE("delete"),
    @XmlEnumValue("userschemes")
    USERSCHEMES("userschemes");
    private final String value;

    Operations(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Operations fromValue(String v) {
        for (Operations c: Operations.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
