package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import orderPaf.Order;
import orderPaf.Product;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

public class orderService
{
		Order orderObj = new Order();
		
		@GET
		@Path("/orders")
		@Produces(MediaType.TEXT_HTML)
		public String readOrders()
		{
			return "<span>Order List</span>";
			//return orderObj.readUser();
		}
		
		
		@POST
		@Path("/add_order")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertOrder(@FormParam("price") Integer price,
							@FormParam("quantity") Integer quantity,
							@FormParam("phone") String phone,
							@FormParam("delivery_Name") String delivery_Name,
							@FormParam("delivery_Address") String delivery_Address,
							@FormParam("total_Amount") Integer total_Amount)
		{
			Gson gson = new Gson(); 
			 
			//Product[] productArray = gson.fromJson(products, Product[].class);
			
				//String output = orderObj.insertUser(price, quantity, phone, delivery_Name, delivery_Address, total_Amount );
				//return output;
			return "add";
		}

//		
//		@PUT
//		@Path("/update_order")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.TEXT_PLAIN)
//		public String updateOrder(String orderData)
//		{
//			//Convert the input string to a JSON object
//			JsonObject orderObj = new JsonParser().parse(orderData).getAsJsonObject();
//			//Read the values from the JSON object
//			Integer order_ID = orderObj.get("order_ID").getAsInt();
//			Integer price = orderObj.get("price").getAsInt();
//			Integer quantity = orderObj.get("quantity").getAsInt();
//			String phone = orderObj.get("phone").getAsString();
//			String delivery_Name = orderObj.get("delivery_Name").getAsString();
//			String delivery_Address = orderObj.get("delivery_Address").getAsString();
//			Integer total_Amount = orderObj.get("total_Amount").getAsInt();
//			String output = orderObj.updateOrder(order_ID, price, quantity, phone, delivery_Name,delivery_Address, total_Amount );
//			return output;
//		}
		
		
//		@DELETE
//		@Path("/delete_order")
//		@Consumes(MediaType.APPLICATION_XML)
//		@Produces(MediaType.TEXT_PLAIN)
//		public String deleteOrder(String orderData)
//		{
//		//Convert the input string to an XML document
//		 Document doc = Jsoup.parse(orderData, "", Parser.xmlParser());
//
//		//Read the value from the element <itemID>
//		 String order_ID = doc.select("order_ID").text();
//		 String output = orderObj.delete_order(order_ID);
//		return output;
//		}
}