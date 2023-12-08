package corso.ListaPlayer.database;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import corso.ListaPlayer.modelli.Player;
import corso.ListaPlayer.modelli.Team;

@Service
public class PlayersDAO {

	private DataSource dataSource = initDriver();

	@Autowired
	private Database database = new Database(dataSource);

	private HashMap<Integer, Player> readAll(String query, String... params) {

		List<Map<String, Object>> listaMappe = database.eseguiQuery(query, params);
		Player n = null;
		HashMap<Integer, Player> ris = new HashMap<Integer, Player>();
		for (Map<String, Object> record : listaMappe) {
			n = new Player();
			n.setId((int) record.get("id"));
			n.setNickname((String) record.get("nickname"));
			n.setNome((String) record.get("nome"));
			n.setCognome((String) record.get("cognome"));
			n.setTeam(new Team());
			n.getTeam().setId((int) record.get("id_team"));
			n.getTeam().setNome((String) record.get("nome_team"));
			ris.put(n.getId(), n);
		}
		return ris;
	}

	private DataSource initDriver() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/player?useSSL=false&serverTimezone=UTC");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return driverManagerDataSource;
	}

	private boolean update(String query, String... params) {
		return database.eseguiUpdate(query, params);
	}

	public HashMap<Integer, Player> read() {
		String query = "select P.id, nickname, P.nome, cognome, T.id as id_team, T.nome as nome_team from player P left join teams T on P.id_team = T.id";
		return readAll(query);
	}

	public boolean create(HashMap<String, String> parametri) {
		String query = "insert into PLAYER(nickname, nome, cognome, id_team) values(?,?,?,?)";
		return update(query, parametri.get("nickname"), parametri.get("nome"), parametri.get("cognome"),
				parametri.get("idteam"));
	}

	public boolean update(HashMap<String, String> parametri) {
		String query = "update PLAYER set nickname = ?, nome = ?, cognome = ?, id_team = ? where id = ?";
		return update(query, parametri.get("nickname"), parametri.get("nome"), parametri.get("cognome"),
				parametri.get("idteam"), parametri.get("id"));
	}

	public boolean delete(HashMap<String, String> parametri) {
		String query = "delete from PLAYER where id = ?";
		return update(query, parametri.get("id"));
	}

}
