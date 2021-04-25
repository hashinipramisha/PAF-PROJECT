package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.paymentM;


@Path("/paymentM")
public class paymentService {


	paymentM paymentdbObj = new paymentM();


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpaymentM()
	{
		paymentdbObj = new paymentM();
		return paymentdbObj.readpaymentM();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertpaymentM(@FormParam("PaymentID") String PaymentID,@FormParam("Username") String Username,@FormParam("Amount") String Amount, @FormParam("Type") String Type,
			@FormParam("CrdType") String CrdType)
	{
		paymentdbObj = new paymentM();
		String output = paymentdbObj.insertpaymentM(Username, Amount, Type, CrdType);
		return output;
	}


	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatepaymentM(String paymentMData)
	{

		//Convert the input string to a JSON object
		JsonObject paymentMObject = new JsonParser().parse(paymentMData).getAsJsonObject();
		//Read the values from the JSON object
		int PaymentID = paymentMObject.get("PaymentID").getAsInt();
		String Username = paymentMObject.get("Username").getAsString();
		String Amount = paymentMObject.get("Amount").getAsString();
		String Type = paymentMObject.get("Type").getAsString();
		String CrdType = paymentMObject.get("CrdType").getAsString();
		String output = paymentdbObj.updatepaymentM(CrdType, Username, Amount, Type, PaymentID);
		return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepaymentM(String paymentdbData)
	{
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentdbData, "", Parser.xmlParser());

		//Read the value from the element 
		 String PaymentID = doc.select("PaymentID").text();
		 String output = paymentdbObj.deletepaymentM(PaymentID);
   		return output;
		
	}


}