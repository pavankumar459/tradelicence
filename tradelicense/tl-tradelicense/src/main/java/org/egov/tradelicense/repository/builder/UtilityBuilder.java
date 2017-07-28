package org.egov.tradelicense.repository.builder;

/**
 * 
 * @author PavanKumar Kamma This class will have all the common queries which
 *         will be used in the tradelicense master
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

	public static String getCategoryParentValidationQuery(String tableName, Long parentId) {

		StringBuffer categoryParentValidationQuery = new StringBuffer("select count(*) from " + tableName);
		categoryParentValidationQuery.append(" where id = '" + parentId + "'");

		return categoryParentValidationQuery.toString();
	}

	public static String getCategoryDetailValidationQuery(String tableName, Long categoryId, String feeType,
			String rateType, Long id) {

		StringBuffer categoryDetailValidationQuery = new StringBuffer("select count(*) from " + tableName);
		categoryDetailValidationQuery.append(" where id = '" + categoryId + "'");
		if (feeType != null && !feeType.isEmpty()) {
			categoryDetailValidationQuery.append(" where feeType = '" + feeType + "'");
		}
		if (rateType != null && !rateType.isEmpty()) {
			categoryDetailValidationQuery.append(" where rateType = '" + rateType + "'");
		}
		if (id != null) {
			categoryDetailValidationQuery.append(" AND id !=" + id);
		}

		return categoryDetailValidationQuery.toString();
	}

	public static String getUomValidationQuery(String tableName, Long uomId) {

		StringBuffer uomValidationQuery = new StringBuffer("select count(*) from " + tableName);
		uomValidationQuery.append(" where id = '" + uomId + "'");

		return uomValidationQuery.toString();
	}
	
	public static String getUniqueTenantCodeQuerywithName(String tableName, String name, String tenantId, String applicationType, Long id) {

		StringBuffer uniqueQuery = new StringBuffer("select count(*) from " + tableName);
		uniqueQuery.append(" where name = '" + name + "'");
		uniqueQuery.append(" AND tenantId = '" + tenantId + "'");
		uniqueQuery.append(" AND applicationType = '" + applicationType + "'");
		if (id != null) {
			uniqueQuery.append(" AND id !=" + id);
		}

		return uniqueQuery.toString();

	}
	
	
	public static String getDocumentTypeValidationQuery(String tanentId, String name, String applicationName, String tableName) {

		StringBuffer documentTypeValidationQuery = new StringBuffer("select count(*) from " + tableName);
		documentTypeValidationQuery.append(" where tenantId = '" + tanentId + "'");
		documentTypeValidationQuery.append(" AND name = '" + name + "'");
		documentTypeValidationQuery.append(" AND applicationType = '" + applicationName + "'");

		return documentTypeValidationQuery.toString();
	}

}
