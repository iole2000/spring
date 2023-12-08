package corso.ListaPlayer.modelli;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class Player extends Entity{
	@Autowired
	private String nickname;
	private String nome;
	private String cognome;
	private Team team;
	
	@Override
	public String toString() {
		return "Player [" + super.toString() + ", nickname=" + nickname + ", nome=" + nome + ", cognome="
				+ cognome + ", Team=" + team + "]";
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public void setMaps(Set<Map> loadMapsForPlayer) {
		// TODO Auto-generated method stub
		
	}
	
	

}
