
package com.linkage.module.service.outer.exception;

import com.linkage.module.service.outer.mo.RtInfo;

public class XmlException extends Exception
{

	/**
	 * UID
	 */
	private static final long serialVersionUID = -1843678763193417295L;
	private String message = null;

	public XmlException()
	{
	}

	public XmlException(String message)
	{
		this.message = message;
	}

	@Override
	public String getMessage()
	{
		return new RtInfo("001", message == null ? "解析xmlData异常" : message).toXML();
	}
}