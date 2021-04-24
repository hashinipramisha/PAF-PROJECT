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

import model.project;


@Path("/project")
public class projectService {


	project projectdbObj = new project();


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readproject()
	{
		projectdbObj = new project();
		return projectdbObj.readproject();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertproject(@FormParam("projectName") String projectName,@FormParam("category") String category,@FormParam("budget") String budget, @FormParam("description") String description)
	{
		projectdbObj = new project();
		String output = projectdbObj.insertproject(projectName, budget, description, category);
		return output;
	}


	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateproject(String projectData)
	{

		//Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		//Read the values from the JSON object
		int projectID = projectObject.get("projectID").getAsInt();
		String projectName = projectObject.get("projectName").getAsString();
		String category = projectObject.get("category").getAsString();
		String budget = projectObject.get("budget").getAsString();
		String description = projectObject.get("description").getAsString();
		String output = projectdbObj.updateproject(projectName, category, budget, description,projectID);
		return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteproject(String projectdbData)
	{
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(projectdbData, "", Parser.xmlParser());

		//Read the value from the element 
		 String projectID = doc.select("projectID").text();
		 String output = projectdbObj.deleteproject(projectID);
   		return output;
		
	}


}