package corso.ListaPlayer.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Database {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public Database(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Map<String, Object>> eseguiQuery(String query, Object... params) {
		try {
			return jdbcTemplate.queryForList(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public boolean eseguiUpdate(String query, Object... params) {
		try {
			jdbcTemplate.update(query, params);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
