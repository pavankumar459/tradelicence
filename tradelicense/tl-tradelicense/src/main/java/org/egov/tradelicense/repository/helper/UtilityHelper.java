package org.egov.tradelicense.repository.helper;

import java.util.Date;

import org.egov.models.AuditDetails;
import org.egov.models.RequestInfo;
import org.egov.models.UserInfo;
import org.egov.tradelicense.repository.builder.UtilityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UtilityHelper {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

	/*public Boolean checkWhetherCategoryExists(SubCategory subCategory) {

		Boolean isExists = Boolean.FALSE;
		String tableName = ConstantUtility.CATEGORY_TABLE_NAME;
		Long categoryId = subCategory.getCategoryId();
		String query = UtilityBuilder.getCategoryValidationQuery(tableName, categoryId);
		int count = 0;

		try {

			count = (Integer) jdbcTemplate.queryForObject(query, Integer.class);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		if (count > 0)
			isExists = Boolean.TRUE;

		return isExists;
	}

	public Boolean checkWhetherUomExists(SubCategoryDetail subCategoryDetail) {

		Boolean isExists = Boolean.FALSE;
		String tableName = ConstantUtility.UOM_TABLE_NAME;
		String uomId = subCategoryDetail.getUomId();
		String query = UtilityBuilder.getUomValidationQuery(tableName, uomId);
		int count = 0;

		try {

			count = (Integer) jdbcTemplate.queryForObject(query, Integer.class);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		if (count > 0)
			isExists = Boolean.TRUE;

		return isExists;
	}*/

	public AuditDetails getCreateMasterAuditDetals(RequestInfo requestInfo) {

		AuditDetails auditDetails = new AuditDetails();
		Long createdTime = new Date().getTime();
		auditDetails.setCreatedTime(createdTime);
		auditDetails.setLastModifiedTime(createdTime);
		UserInfo userInfo = requestInfo.getUserInfo();
		if (userInfo != null) {
			auditDetails.setCreatedBy(userInfo.getUsername());
			auditDetails.setLastModifiedBy(requestInfo.getUserInfo().getUsername());
		}

		return auditDetails;
	}
}