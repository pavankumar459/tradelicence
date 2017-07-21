package org.egov.tradelicence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.egov.models.AuditDetails;
import org.egov.models.SubCategory;
import org.egov.tradelicence.repository.builder.SubCategoryQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SubCategoryRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Description : this method will creating subcategory
	 * 
	 * @param tenantId
	 * @param SubCategory
	 * @return subCategoryId
	 */
	public Long createSubCategory(String tenantId, SubCategory subCategory) {

		Long createdTime = new Date().getTime();

		String subCategoryInsertQuery = SubCategoryQueryBuilder.INSERT_SUB_CATEGORY_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(subCategoryInsertQuery, new String[] { "id" });

				ps.setString(1, subCategory.getTenantId());
				ps.setString(2, subCategory.getCode());
				ps.setString(3, subCategory.getName());
				ps.setString(4, subCategory.getBusinessNatureId().toString());
				ps.setLong(5, subCategory.getCategoryId());
				ps.setString(6, subCategory.getAuditDetails().getCreatedBy());
				ps.setString(7, subCategory.getAuditDetails().getLastModifiedBy());
				ps.setLong(8, createdTime);
				ps.setLong(9, createdTime);
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());

	}

	/**
	 * Description : this method for updating SubCategory
	 * 
	 * @param SubCategory
	 * @return SubCategory
	 */
	public SubCategory updateSubCategory(SubCategory subCategory) {

		Long updatedTime = new Date().getTime();

		String subCategoryUpdateSql = SubCategoryQueryBuilder.UPDATE_SUB_CATEGORY_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(subCategoryUpdateSql);

				ps.setString(1, subCategory.getTenantId());
				ps.setString(2, subCategory.getCode());
				ps.setString(3, subCategory.getName());
				ps.setString(4, subCategory.getBusinessNatureId().toString());
				ps.setLong(5, subCategory.getCategoryId());
				ps.setString(6, subCategory.getAuditDetails().getLastModifiedBy());
				ps.setLong(7, updatedTime);
				ps.setLong(8, subCategory.getId());

				return ps;
			}
		};

		jdbcTemplate.update(psc);
		return subCategory;
	}

	/**
	 * Description : this method for search subCategory
	 * 
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param businessNatureId
	 * @param categoryId
	 * @param pageSize
	 * @param offSet
	 * @return List<SubCategory>
	 * @throws Exception
	 */
	public List<SubCategory> searchSubCategory(String tenantId, Integer[] ids, String name, String code,
			Integer businessNatureId, Integer categoryId, Integer pageSize, Integer offSet) {

		List<Object> preparedStatementValues = new ArrayList<>();
		String subCategorySearchQuery = SubCategoryQueryBuilder.buildSearchQuery(tenantId, ids, name, code,
				businessNatureId, categoryId, pageSize, offSet, preparedStatementValues);
		List<SubCategory> subCategories = getSubCategories(subCategorySearchQuery.toString(), preparedStatementValues);

		return subCategories;

	}

	/**
	 * This method will execute the given query & will build the SubCategory
	 * object
	 * 
	 * @param query
	 *            String that need to be executed
	 * @return {@link SubCategory} List of Category
	 */
	private List<SubCategory> getSubCategories(String query, List<Object> preparedStatementValues) {

		List<SubCategory> subCategories = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, preparedStatementValues.toArray());

		for (Map<String, Object> row : rows) {
			SubCategory subCategory = new SubCategory();
			subCategory.setId(getLong(row.get("id")));
			subCategory.setTenantId(getString(row.get("tenantid")));
			subCategory.setCode(getString(row.get("code")));
			subCategory.setName(getString(row.get("name")));
			AuditDetails auditDetails = new AuditDetails();
			auditDetails.setCreatedBy(getString(row.get("createdby")));
			auditDetails.setLastModifiedBy(getString(row.get("lastmodifiedby")));
			auditDetails.setCreatedTime(getLong(row.get("createdtime")));
			auditDetails.setLastModifiedTime(getLong(row.get("lastmodifiedtime")));
			subCategory.setAuditDetails(auditDetails);

			subCategories.add(subCategory);

		}

		return subCategories;
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
