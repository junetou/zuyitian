package org.andy.work.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class IllegalRequestException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3310473806106654833L;
	
	private BindingResult result;
	
	public IllegalRequestException(BindingResult result)
	{
		this.result = result;
	}
	
	public IllegalRequestException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage()
	{
		if (this.result != null)
		{
			StringBuffer sb = new StringBuffer();
			for (ObjectError error : this.result.getAllErrors())
			{
				sb.append(error.getDefaultMessage() + " - ");
			}
			
			return sb.toString();
		}
		
		return super.getMessage();
	}
}
