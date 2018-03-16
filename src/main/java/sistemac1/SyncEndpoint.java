package sistemac1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


@Path("receive")
public class SyncEndpoint {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void receive(String data) {
		
		System.out.println(data);
		
	}

}
