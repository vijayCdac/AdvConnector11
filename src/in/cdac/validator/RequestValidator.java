package in.cdac.validator;
import in.cdac.cryptoservice.Operations;
import in.cdac.env.EnvironmentReader;
import in.cdac.env.ErrorConstants;
import in.cdac.model.RequestObject;
import in.cdac.util.Debugger;

public class RequestValidator 
{
	//Debugger debugger;
	//EnvironmentReader env;

	/**
	 * 
	 * @param debugger
	 */
	public RequestValidator( Debugger debugger )
	{
		//this.debugger = debugger;
		//this.env = new EnvironmentReader();
	}

	/**
	 * This method validates the input data provided in the request object
	 * @param reqObj
	 * @return
	 */
	public String commonValidate(RequestObject reqObj) 
	{
		if ( reqObj.getAc() == null || reqObj.getAc() == "" )
		{
			return ErrorConstants.AC_NOT_SET;
		}

		if ( reqObj.getSa() == null || reqObj.getSa() == "")
		{
			return ErrorConstants.SA_NOT_SET;
		}

		if ( !reqObj.getSa().equals(reqObj.getAc()))
		{
			return ErrorConstants.MISMATCH_AC_SA;
		}

		if ( reqObj.getOpr() == null)
		{
			return ErrorConstants.OPR_NOT_SET;
		}

		if ( reqObj.getLk() == null || reqObj.getLk() == "")
		{
			if ( reqObj.getPin() == null )
			{
				return ErrorConstants.LK_OR_PIN_NOT_SET;
			}
		}

		if ( reqObj.getTxn() == null || reqObj.getTxn()=="")
		{
			return ErrorConstants.TXN_NOT_SET;
		}

		if(reqObj.getTkntype()==null)
		{
			return ErrorConstants.TKN_NOT_SET;
		}

		if ( reqObj.getTs() == null)
		{
			return ErrorConstants.TS_NOT_SET;
		}
		
		if ( reqObj.getUrl() == null)
		{
			return ErrorConstants.URL_NOT_SET_OR_INCORRECT_URL;
		}
		
		if ( reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.STRUID.value().trim()))
		{
			if(reqObj.getKeytype() == null)
			{
				return ErrorConstants.KEY_TYPE_NOT_SET;
			}
		}
		
		
		
		if ( reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.GETUID.value().trim()) || reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.ACTIVATE.value().trim()) || reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.DEACTIVATE.value().trim())   )
		{
			if(reqObj.getRefNum()==null|| reqObj.getRefNum()=="")
			{
				return ErrorConstants.REF_NUM_NOT_SET;
			}
		}

		if (reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.STRUID.value().trim()) || reqObj.getOpr().value().trim().equalsIgnoreCase(Operations.GETREFNUM.value().trim())  )
		{
			if ( reqObj.getNumber() == null || reqObj.getNumber()=="")
			{
				return ErrorConstants.DATA_NOT_SET;
			}
			if(!AadhaarValidator.ValidateVerhoeff(reqObj.getNumber()))
			{
				return ErrorConstants.INVALID_AADHAAR;
			}
		}
		return null;
	}
}