package asw.instagnam.connessioni.rest;

import asw.instagnam.connessioni.domain.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.logging.Logger; 
import java.util.*; 

@RestController
public class ConnessioniController {

	@Autowired 
	private ConnessioniService connessioniService;

	@Autowired 
	private ConnessioniRepository connessioniRepository;


	private final Logger logger = Logger.getLogger(ConnessioniController.class.toString()); 

	/* Crea una nuova connessione. 
	 * la richiesta contiene nel corpo una stringa della forma follower:followed */ 
	@PostMapping("/connessioni")
	public Connessione createConnessione(@RequestBody CreateConnessioneRequest request) {
		Connessione  connessione = null;
		String follower = request.getFollower();
		String followed = request.getFollowed();
		Collection<Connessione> connessioni = connessioniRepository.findAll();
		int test = 0;
		for (Connessione conn : connessioni) {
			if (follower.equals(conn.getFollower()) && followed.equals(conn.getFollowed())) {
				test = test+1;
			}
		}
		if (test == 0) {
			logger.info("REST CALL: createConnessione " + follower + ", " + followed); 
			connessione = connessioniService.createConnessione(follower, followed);
		}

		return connessione; 

	}	

	/* Trova la connessione con connessioneId. */ 
	@GetMapping("/connessioni/{connessioneId}")
	public Connessione getConnessione(@PathVariable Long connessioneId) {
		logger.info("REST CALL: getConnessione " + connessioneId); 
		Connessione connessione = connessioniService.getConnessione(connessioneId);
		if (connessione!=null) {
			return connessione;
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Connessione not found"
					);
		}
	}

	/* Trova tutte le connessioni (o tutte le connessioni di follower). */ 
	@GetMapping("/connessioni")
	public Collection<Connessione> getConnessioni(@RequestParam(value="follower", required=false) String follower) {
		Collection<Connessione> connessioni = null; 
		if (follower==null) {
			logger.info("REST CALL: getConnessioni"); 
			connessioni = connessioniService.getConnessioni();
		} else {
			logger.info("REST CALL: getConnessioni " + follower); 
			connessioni = connessioniService.getConnessioniByFollower(follower);
		}
		return connessioni;
	}

}
