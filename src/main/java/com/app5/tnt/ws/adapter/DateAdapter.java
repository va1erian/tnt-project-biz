package com.app5.tnt.ws.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	
	private SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public Date unmarshal(String dateToParse) throws Exception {
		return inputDateFormat.parse(dateToParse);
	}

	@Override
	public String marshal(Date dateToFormat) throws Exception {
		return outputDateFormat.format(dateToFormat);
	}
}
