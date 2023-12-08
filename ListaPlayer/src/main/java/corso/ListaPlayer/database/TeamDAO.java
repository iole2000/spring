package corso.ListaPlayer.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import corso.ListaPlayer.modelli.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@Service
public class TeamDAO {

	private DataSource dataSource = initDriver();

	@Autowired
	private Database database = new Database(dataSource);

	private HashMap<Integer, Team> readAll(String query, String... params) {

		List<Map<String, Object>> listaMappe = database.eseguiQuery(query, params);
		Team n = null;
		HashMap<Integer, Team> ris = new HashMap<Integer, Team>();
		for (Map<String, Object> record : listaMappe) {
			n = new Team();
			n.setId((int) record.get("id"));
			n.setNome((String) record.get("nome"));
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

	public HashMap<Integer, Team> read() {
		String query = "select T.id, T.nome from teams T";
		return readAll(query);
	}

	public boolean create(HashMap<String, String> parametri) {
		String query = "insert into TEAMS(nome) values(?)";
		return update(query, parametri.get("nome"));
	}

	public boolean update(HashMap<String, String> parametri) {
		String query = "update TEAMS set nome = ? where id = ?";
		return update(query, parametri.get("nome"), parametri.get("id"));
	}

	public boolean delete(HashMap<String, String> parametri) {
		String query = "delete from TEAMS where id = ?";
		return update(query, parametri.get("id"));
	}

}
