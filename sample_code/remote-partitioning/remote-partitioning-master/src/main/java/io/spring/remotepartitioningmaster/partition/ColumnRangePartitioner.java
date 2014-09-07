package io.spring.remotepartitioningmaster.partition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * {@link org.springframework.batch.core.partition.support.Partitioner} implementation
 * to partition data based on the provided column.
 * </p>
 */
public class ColumnRangePartitioner implements Partitioner {
	private final String table;
	private final String column;
	private final JdbcOperations jdbcTemplate;

	public ColumnRangePartitioner(DataSource datasource, String table, String column) {
		this.table = table;
		this.column = column;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		int min = jdbcTemplate.queryForObject("SELECT MIN(" + column + ") from " + table, Integer.class);
		int max = jdbcTemplate.queryForObject("SELECT MAX(" + column + ") from " + table, Integer.class);

		int targetSize = (max - min) / gridSize;

		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

		int number = 0;
		int start = min;
		int end = start + targetSize - 1;

		while (start <= max) {
			ExecutionContext value = new ExecutionContext();
			result.put("partition" + number, value);

			if (end >= max) {
				end = max;
			}

			value.putInt("minValue", start);
			value.putInt("maxValue", end);

			start += targetSize;
			end += targetSize;
			number++;
		}

		return result;
	}
}
