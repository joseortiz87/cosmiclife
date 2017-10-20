package mx.com.cosmiclife.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CosmicLifeController {
	
	   private static Logger log = Logger.getLogger(CosmicLifeController.class);
	   private static String URL_STARTS = "http://skymaponline.net/DataService.asmx/GetStarData";
	   private static String URL_CONSTE = "http://skymaponline.net/DataService.asmx/GetConstData";
	   
	   @RequestMapping(value = "/getStarts",method = RequestMethod.POST,produces = "application/json")
	    public @ResponseBody String getStarts(@RequestParam("lat") String lat,@RequestParam("lng") String lng,@RequestParam("time") String time){
		   return consultHttpServer(URL_STARTS, "{ \"qs\" : \"r=4500&x=4500&y=4500&lat= " + lat + "&long=" + lng + "&time=" + time + "&rotation=90&w=9920&h=14030\" }"); //2250 w=4960&h=7015
	    }
	   
	   @RequestMapping(value = "/getConst",method = RequestMethod.POST,produces = "application/json")
	    public @ResponseBody String getConst(@RequestParam("lat") String lat,@RequestParam("lng") String lng,@RequestParam("time") String time){
		   return consultHttpServer(URL_CONSTE, "{ \"qs\" : \"r=3000&x=3000&y=3000&lat= " + lat + "&long=" + lng + "&time=" + time + "&rotation=90&w=9920&h=14030\" }"); //1500 w=4960&h=7015
	    }
	   
	   private String consultHttpServer(String url,String json) {
		   try {
			   HttpClient client = HttpClientBuilder.create().build();
			   HttpPost post = new HttpPost(url);
	
			   // add header
			   post.setHeader("Content-Type", "application/json; charset=utf-8");
			   StringEntity input = new StringEntity(json);
			   input.setContentType("application/json");
			   post.setEntity(input);
	
			   HttpResponse response = client.execute(post);
			   log.info("Response Code : "
			                   + response.getStatusLine().getStatusCode());
	
			   BufferedReader rd = new BufferedReader(
			           new InputStreamReader(response.getEntity().getContent()));
	
			   StringBuffer result = new StringBuffer();
			   String line = "";
			   while ((line = rd.readLine()) != null) {
				   result.append(line);
			   }
			   return result.toString();
		   }catch(Exception e) {
			   log.error("",e);
		   }
		   return "";
	   }
}
