package sistemac1;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Path("receive")
public class SyncEndpoint {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void receive(String data) {
		try {
			
			System.out.println("-----------------------------------");
			JSONArray jsonarray = new JSONArray(data);
			System.out.println("Ordenes finales: "+jsonarray.length());
			
			JSONObject primeraOrden = jsonarray.getJSONObject(0);
			System.out.println(primeraOrden.toString());
			System.out.println("Ordenes totales: "+primeraOrden.getInt("orderset"));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		
			
			Date orderCreationDate = dateFormat.parse(primeraOrden.getString("date"));
			Date now = new Date();
			
			long timelapsed = (now.getTime() - orderCreationDate.getTime())/1000;
			System.out.println("Tiempo de flujo de SI: "+timelapsed+ "ms");
			
			long timeRateSubtotal = jsonarray.length()/timelapsed;
			System.out.println("Ordenes finales/Tiempo: "+timeRateSubtotal+ " ordenes/s");

			long timeRateTotal = primeraOrden.getInt("orderset")/timelapsed;
			System.out.println("Ordenes totales/Tiempo: "+timeRateTotal+ " ordenes/s");
			
			
		} catch (JSONException | ParseException e) {			
			e.printStackTrace();
		}
		
		
		System.out.println("-----------------------------------");
		
		consolePrint(data);
		
	}
	
	public void consolePrint(String s){
		if (s.length() > 100)
		    System.out.println(s.substring(0, 100) + "...");
		else
			System.out.println(s);
	}

}
