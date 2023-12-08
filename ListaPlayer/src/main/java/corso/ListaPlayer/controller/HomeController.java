package corso.ListaPlayer.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import corso.ListaPlayer.database.PlayersDAO;
import corso.ListaPlayer.database.TeamDAO;

/*
 * Front Controller - Design Pattern
 * questi tipi di classe serviranno a gestire
 * REQUEST - richieste 
 * RESPONSE - risposte
 * quindi parliamo di architettura client server
 * ed andremo ad utilizzare il protocollo HTTP
 * HTTP ha dei VERBI o METODI - Get, Post, Put, Delete
 * 
 * nel front controller andremo a preparare le possibili
 * request a cui il server dovra' rispondere.
 * 
 */

@Controller
public class HomeController {

	@Autowired
	private PlayersDAO playersDAO;
	@Autowired
	private TeamDAO teamDAO;

	@GetMapping("/someEndpoint")
	public String someEndppint() {
		return "yourView";
	}

	/*
	 * Sto creando un primo metodo home() che apre la pagina home.html per far in
	 * modo che il nostro server riponda ingaggiando questo metodo facciamo in modo
	 * che la request debba essere "/home" oltre a questo diciamo al metodo che deve
	 * rispondere nel caso in cui il metodo con cui e' arrivata la request sia un
	 * metodo GET
	 * 
	 * 
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home() {
		return "home.html";
	}

	@RequestMapping(path = "/players", method = RequestMethod.GET)
	public String players(Model model) {
		model.addAttribute("listaPlayers", playersDAO.read());
		return "players.html";
	}

	@RequestMapping(path = "/addPlayer", method = RequestMethod.POST)
	public String addPlayer(@RequestParam HashMap<String, String> parametri) {
		playersDAO.create(parametri);
		return "redirect:/players";
	}

	@RequestMapping(path = "/modPlayer", method = RequestMethod.POST)
	public String modPlayer(@RequestParam HashMap<String, String> parametri) {
		playersDAO.update(parametri);
		return "redirect:/players";
	}

	@RequestMapping(path = "/delPlayer", method = RequestMethod.POST)
	public String delPlayer(@RequestParam HashMap<String, String> parametri) {
		playersDAO.delete(parametri);
		return "redirect:/players";
	}

	@RequestMapping(path = "/team", method = RequestMethod.GET)
	public String team(Model model) {
		model.addAttribute("listaTeam", teamDAO.read());
		return "team.html";
	}

	@RequestMapping(path = "/addTeam", method = RequestMethod.POST)
	public String addTeam(@RequestParam HashMap<String, String> parametri) {
		teamDAO.create(parametri);
		return "redirect:/players";
	}

}
