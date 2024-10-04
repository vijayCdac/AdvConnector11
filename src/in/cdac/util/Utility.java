package in.cdac.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.springframework.http.codec.xml.Jaxb2XmlEncoder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

/* * 
 * @author root
 *
 */
public class Utility 
{
	/**
	 * @return XMLGregorianCalendar
	 * @description generates timestamp without timezone
	 */
	public static XMLGregorianCalendar generateTimeStamp()
	{
		GregorianCalendar gc = new GregorianCalendar();
		XMLGregorianCalendar currServTime = null;
		try 
		{
			currServTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			currServTime.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		} 
		catch (DatatypeConfigurationException e) 
		{
			e.printStackTrace();
		}
		return currServTime;
	}

	/**
	 * @param clazz
	 * @param obj
	 * @return String
	 * @description marshalling the object.
	 */
////	StringWriter xml = new StringWriter();
//	//
//	   XmlMapper mapper1 = new XmlMapper();
//	    //ObjectMapper mapper2 = new ObjectMapper();
//
////	    mapper1.setVisibility(PropertyAccessor.FIELD, Visibility.DEFAULT);
//	    //mapper2.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//
//	//    mapper1.enable(SerializationFeature.INDENT_OUTPUT);
//	   // mapper2.enable(SerializationFeature.INDENT_OUTPUT);
//
////	    MyPojo mypojo = new MyPojo();
////	    mypojo.setName("Dhani");
////	    mypojo.setId("18082013");
////	    mypojo.setAge(5);
//
//	    String jsonStringXML = mapper1.writeValueAsString(obj);
//	//JAXBContext.newInstance(clazz).createMarshaller().marshal(obj, xml);
//	return jsonStringXML.toString();
	@SuppressWarnings("rawtypes")
	public static String marshallObj(Class clazz, Object obj) throws XMLStreamException, Exception
	{
		StringWriter xml = new StringWriter();
	//	System.out.println("b4");
		try {
		JAXBContext.newInstance(clazz).createMarshaller().marshal(obj, xml);
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//System.out.println(xml.toString());
		return xml.toString();
	}

	/**
	 * @param clazz
	 * @param xmlToParse
	 * @return Object
	 * @throws XMLStreamException 
	 * @throws JAXBException 
	 * @description unmarshallstringXml to the object.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Object unmarshallXml(Class clazz, String xmlToParse) throws Exception 
	{
		{
			Object obj = null;
			jakarta.xml.bind.JAXBContext jc=null;
			jc = 	jakarta.xml.bind.JAXBContext.newInstance(clazz);
			XMLInputFactory xif = XMLInputFactory.newFactory();
			XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(new StringReader(xmlToParse)));
			//Unma
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			obj =  unmarshaller.unmarshal(xsr);
			return obj;
		}
	}
}
