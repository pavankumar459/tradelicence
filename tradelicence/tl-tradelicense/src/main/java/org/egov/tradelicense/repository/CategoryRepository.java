package org.egov.tradelicense.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.egov.models.AuditDetails;
import org.egov.models.Category;
import org.egov.tradelicense.repository.builder.CategoryQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Description : this method will creating category
	 * 
	 * @param tenantId
	 * @param Category
	 * @return categoryId
	 */
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

	/**
	 * Description : this method for updating category
	 * 
	 * @param Category
	 * @return Category
	 */
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

	/**
	 * Description : this method for search category
	 * 
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param pageSize
	 * @param offSet
	 * @return List<Category>
	 * @throws Exception
	 */
	public List<Category> searchCategory(String tenantId, Integer[] ids, String name, String code, Integer pageSize,
			Integer offSet) {

		List<Object> preparedStatementValues = new ArrayList<>();
		String categorySearchQuery = CategoryQueryBuilder.buildSearchQuery(tenantId, ids, name, code, pageSize, offSet,
				preparedStatementValues);
		List<Category> categories = getCategories(categorySearchQuery.toString(), preparedStatementValues);

		return categories;

	}

	/**
	 * This method will execute the given query & will build the Category object
	 * 
	 * @param query
	 *            String that need to be executed
	 * @return {@link Category} List of Category
	 */
	private List<Category> getCategories(String query, List<Object> preparedStatementValues) {

		List<Category> categories = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, preparedStatementValues.toArray());

		for (Map<String, Object> row : rows) {
			Category category = new Category();
			category.setId(getLong(row.get("id")));
			category.setTenantId(getString(row.get("tenantid")));
			category.setCode(getString(row.get("code")));
			category.setName(getString(row.get("name")));
			AuditDetails auditDetails = new AuditDetails();
			auditDetails.setCreatedBy(getString(row.get("createdby")));
			auditDetails.setLastModifiedBy(getString(row.get("lastmodifiedby")));
			auditDetails.setCreatedTime(getLong(row.get("createdtime")));
			auditDetails.setLastModifiedTime(getLong(row.get("lastmodifiedtime")));
			category.setAuditDetails(auditDetails);

			categories.add(category);

		}

		return categories;
	}

	/**
	 * This method will cast the given object to String
	 * 
	 * @param object
	 *            that need to be cast to string
	 * @return {@link String}
	 */
	private String getString(Object object) {
		return object == null ? "" : object.toString();
	}

	/**
	 * This method will cast the given object to double
	 * 
	 * @param object
	 *            that need to be cast to Double
	 * @return {@link Double}
	 */
	private Double getDouble(Object object) {
		return object == null ? 0.0 : Double.parseDouble(object.toString());
	}

	/**
	 * This method will cast the given object to Long
	 * 
	 * @param object
	 *            that need to be cast to Long
	 * @return {@link Long}
	 */
	private Long getLong(Object object) {
		return object == null ? 0 : Long.parseLong(object.toString());
	}

}
