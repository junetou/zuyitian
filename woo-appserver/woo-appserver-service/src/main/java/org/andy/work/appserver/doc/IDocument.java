package org.andy.work.appserver.doc;

import java.io.Serializable;

public interface IDocument extends Serializable
{
	void setId(String id);
	
	String getId();
}
