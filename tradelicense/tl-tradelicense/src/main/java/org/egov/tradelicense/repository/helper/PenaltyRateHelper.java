package org.egov.tradelicense.repository.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egov.enums.ApplicationTypeEnum;
import org.egov.models.AuditDetails;
import org.egov.models.PenaltyRate;
import org.egov.models.PenaltyRateRequest;
import org.egov.models.RequestInfo;
import org.egov.tradelicense.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PenaltyRateHelper {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void validatePenaltyRange(String tenantId, PenaltyRateRequest penaltyRateRequest) {

		RequestInfo requestInfo = penaltyRateRequest.getRequestInfo();
		String applicationType = null;
		String oldApplicationType = null;
		Long oldToRange = null;
		Long fromRange = null;
		int count = 0;
		for (PenaltyRate penaltyRate : penaltyRateRequest.getPenaltyRates()) {
			if (count > 0) {
				applicationType = penaltyRate.getApplicationTypeId().toString();
				fromRange = penaltyRate.getFromRange();
				if (applicationType.equalsIgnoreCase(oldApplicationType)) {
					if (!fromRange.equals(oldToRange)) {
						throw new InvalidInputException(requestInfo);
					}
				} else {
					throw new InvalidInputException(requestInfo);
				}
			}
			oldApplicationType = penaltyRate.getApplicationTypeId().toString();
			oldToRange = penaltyRate.getToRange();
			count++;
		}
	}

	public void validateUpdatePenaltyRange(PenaltyRateRequest penaltyRateRequest) {

	}

	/**
	 * This method will execute the given query & will build the PenaltyRate
	 * object
	 * 
	 * @param query
	 * @return {@link PenaltyRate} List of Category
	 */
	public List<PenaltyRate> getPenaltyRates(String query, List<Object> preparedStatementValues) {

		List<PenaltyRate> penaltyRates = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, preparedStatementValues.toArray());

		for (Map<String, Object> row : rows) {
			PenaltyRate penaltyRate = new PenaltyRate();
			penaltyRate.setId(getLong(row.get("id")));
			penaltyRate.setTenantId(getString(row.get("tenantid")));
			penaltyRate.setApplicationTypeId(ApplicationTypeEnum.valueOf(getString(row.get("applicationTypeId"))));
			penaltyRate.setFromRange(getLong(row.get("fromRange")));
			penaltyRate.setToRange(getLong(row.get("toRange")));
			penaltyRate.setRate(getDouble(row.get("rate")));
			AuditDetails auditDetails = new AuditDetails();
			auditDetails.setCreatedBy(getString(row.get("createdby")));
			auditDetails.setLastModifiedBy(getString(row.get("lastmodifiedby")));
			auditDetails.setCreatedTime(getLong(row.get("createdtime")));
			auditDetails.setLastModifiedTime(getLong(row.get("lastmodifiedtime")));
			penaltyRate.setAuditDetails(auditDetails);

			penaltyRates.add(penaltyRate);

		}

		return penaltyRates;
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