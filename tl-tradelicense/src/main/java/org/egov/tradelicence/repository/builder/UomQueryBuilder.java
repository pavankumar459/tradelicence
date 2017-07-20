package org.egov.tradelicence.repository.builder;

/**
 * This Class contains INSERT, UPDATE and SELECT queries for UOM API's
 * 
 * @author Pavan Kumar Kamma
 */
public class UomQueryBuilder {

	public static final String INSERT_UOM_QUERY = "INSERT INTO egtl_mstr_uom"
			+ " (tenantId, code, name, active, createdBy, lastModifiedBy, createdTime, lastModifiedTime)"
			+ " VALUES(?,?,?,?,?,?,?,?)";

	public static final String UPDATE_UOM_QUERY = "UPDATE egtl_mstr_uom"
			+ " SET tenantId = ?, code = ?, name = ?, active = ?," + " lastModifiedBy = ?, lastModifiedTime = ?"
			+ " WHERE id = ?";

	public static String buildSearchQuery(String tenantId, Integer[] ids, String name, String code, Boolean active,
			Integer pageSize, Integer offSet) {

		StringBuffer searchSql = new StringBuffer();

		searchSql.append("select * from egtl_mstr_uom where tenantId = '" + tenantId + "'");

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
			searchSql.append(" AND id IN (" + searchIds + ")");
		}

		if (code != null && !code.isEmpty())
			searchSql.append(" AND code = '" + code + "'");

		if (name != null && !name.isEmpty())
			searchSql.append(" AND name = '" + name + "'");

		if (active != null)
			searchSql.append(" AND name = '" + active + "'");

		if (pageSize == null)
			pageSize = 30;

		if (offSet == null)
			offSet = 0;

		searchSql.append("offset " + offSet + " limit " + pageSize);

		return searchSql.toString();

	}
}