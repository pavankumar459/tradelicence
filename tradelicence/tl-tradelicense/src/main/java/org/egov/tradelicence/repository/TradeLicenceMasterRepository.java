package org.egov.tradelicence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.egov.models.Category;
import org.egov.models.UOM;
import org.egov.tradelicence.repository.builder.CategoryQueryBuilder;
import org.egov.tradelicence.repository.builder.UomQueryBuilder;
import org.egov.tradelicence.repository.builder.UtilityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TradeLicenceMasterRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long createCategory(String tenantId, Category category) {

		Long createdTime = new Date().getTime();

		String categoryInsert = CategoryQueryBuilder.INSERT_CATEGORY_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(categoryInsert, new String[] { "id" });

				ps.setString(1, category.getTenantId());
				ps.setString(2, category.getCode());
				ps.setString(3, category.getName());
				ps.setString(4, category.getAuditDetails().getCreatedBy());
				ps.setString(5, category.getAuditDetails().getLastModifiedBy());
				ps.setLong(6, createdTime);
				ps.setLong(7, createdTime);
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());

	}

	public Category updateCategory(Category category) {

		Long updatedTime = new Date().getTime();

		String categoryUpdateSql = CategoryQueryBuilder.UPDATE_CATEGORY_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(categoryUpdateSql);

				ps.setString(1, category.getTenantId());
				ps.setString(2, category.getCode());
				ps.setString(3, category.getName());
				ps.setString(4, category.getAuditDetails().getLastModifiedBy());
				ps.setLong(5, updatedTime);
				ps.setLong(6, category.getId());

				return ps;
			}
		};

		jdbcTemplate.update(psc);
		return category;
	}

	public List<Category> searchCategory(String tenantId, Integer[] ids, String name, String code, Integer pageSize,
			Integer offSet) {

		String categorySearchQuery = CategoryQueryBuilder.buildSearchQuery(tenantId, ids, name, code, pageSize, offSet);

		List<Category> categories = jdbcTemplate.query(categorySearchQuery, new BeanPropertyRowMapper(Category.class));

		return categories;

	}

	public Long createUom(String tenantId, UOM uom) {

		Long createdTime = new Date().getTime();

		String uomInsert = UomQueryBuilder.INSERT_UOM_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(uomInsert, new String[] { "id" });

				ps.setString(1, uom.getTenantId());
				ps.setString(2, uom.getCode());
				ps.setString(3, uom.getName());
				ps.setBoolean(4, uom.getActive());
				ps.setString(5, uom.getAuditDetails().getCreatedBy());
				ps.setString(6, uom.getAuditDetails().getLastModifiedBy());
				ps.setLong(7, createdTime);
				ps.setLong(8, createdTime);
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());

	}

	public UOM updateUom(UOM uom) {

		Long updatedTime = new Date().getTime();

		String uomUpdateSql = UomQueryBuilder.UPDATE_UOM_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(uomUpdateSql);

				ps.setString(1, uom.getTenantId());
				ps.setString(2, uom.getCode());
				ps.setString(3, uom.getName());
				ps.setBoolean(4, uom.getActive());
				ps.setString(5, uom.getAuditDetails().getLastModifiedBy());
				ps.setLong(6, updatedTime);
				ps.setLong(7, uom.getId());

				return ps;
			}
		};

		jdbcTemplate.update(psc);
		return uom;
	}

	public List<UOM> searchUom(String tenantId, Integer[] ids, String name, String code, Boolean active,
			Integer pageSize, Integer offSet) {

		String uomSearchQuery = UomQueryBuilder.buildSearchQuery(tenantId, ids, name, code, active, pageSize, offSet);

		List<UOM> uoms = jdbcTemplate.query(uomSearchQuery, new BeanPropertyRowMapper(UOM.class));

		return uoms;

	}

	/**
	 * This will check whether any record exists with the given tenantId & code
	 * in database or not
	 * 
	 * @param tenantId
	 * @param code
	 * @return True / false if record exists / record does n't exists
	 */
	public Boolean checkWhetherRecordExits(String tenantId, String code, String tableName, Long id) {

		Boolean isExists = Boolean.TRUE;

		String query = UtilityBuilder.getUniqueTenantCodeQuery(tableName, code, tenantId, id);

		int count = 0;

		try {

			count = (Integer) jdbcTemplate.queryForObject(query, Integer.class);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		if (count == 0)
			isExists = Boolean.FALSE;

		return isExists;

	}
}