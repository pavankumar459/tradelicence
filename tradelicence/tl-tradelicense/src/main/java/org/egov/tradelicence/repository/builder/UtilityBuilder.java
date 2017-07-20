package org.egov.tradelicence.repository.builder;

/**
 * 
 * @author PavanKumar Kamma
 * 	 This class will have all the common queries 
 *   which will be used in the tradelicence master 
 *
 */
public class UtilityBuilder {

	public static String getUniqueTenantCodeQuery(String tableName, String code, String tenantId, Long id) {

		StringBuffer uniqueQuery = new StringBuffer("select count(*) from " + tableName);
		uniqueQuery.append(" where code = '" + code + "'");
		uniqueQuery.append(" AND tenantId = '" + tenantId + "'");
		if (id != null) {
			uniqueQuery.append(" AND id !=" + id);
		}

		return uniqueQuery.toString();

	}

}
