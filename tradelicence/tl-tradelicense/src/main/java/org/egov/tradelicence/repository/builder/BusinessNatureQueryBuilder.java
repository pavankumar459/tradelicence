package org.egov.tradelicence.repository.builder;

import java.util.List;

/**
 * This Class contains INSERT, UPDATE and SELECT queries for BusinessNature API's
 * 
 * @author Pavan Kumar Kamma
 */
public class BusinessNatureQueryBuilder {

	public static final String INSERT_BUSINESS_NATURE_QUERY = "INSERT INTO egtl_mstr_business_nature"
			+ " (tenantId, code, name, createdBy, lastModifiedBy, createdTime, lastModifiedTime)"
			+ " VALUES(?,?,?,?,?,?,?)";

	public static final String UPDATE_BUSINESS_NATURE_QUERY = "UPDATE egtl_mstr_business_nature"
			+ " SET tenantId = ?, code = ?, name = ?," + " lastModifiedBy = ?, lastModifiedTime = ?" + " WHERE id = ?";

	public static String buildSearchQuery(String tenantId, Integer[] ids, String name, String code, Integer pageSize,
			Integer offSet, List<Object> preparedStatementValues) {

		StringBuffer searchSql = new StringBuffer();

		searchSql.append("select * from egtl_mstr_business_nature where ");
		searchSql.append(" tenantId = ? ");
		preparedStatementValues.add(tenantId);

		if (ids != null && ids.length > 0) {

			String searchIds = "";
			int count = 1;
			for (Integer id : ids) {

				if (count < ids.length)
					searchIds = searchIds + id + ",";
				else
					searchIds = searchIds + id;

				count++;
			}
			searchSql.append(" AND id IN (" + searchIds + ") ");
		}

		if (code != null && !code.isEmpty()) {
			searchSql.append(" AND code =? ");
			preparedStatementValues.add(code);
		}

		if (name != null && !name.isEmpty()) {
			searchSql.append(" AND name =? ");
			preparedStatementValues.add(name);
		}

		if (pageSize == null)
			pageSize = 30;

		searchSql.append(" limit ? ");
		preparedStatementValues.add(pageSize);

		if (offSet == null)
			offSet = 0;

		searchSql.append(" offset ? ");
		preparedStatementValues.add(offSet);

		return searchSql.toString();
	}
}