package org.egov.tradelicense.services;

import org.egov.models.PenaltyRateRequest;
import org.egov.models.PenaltyRateResponse;
import org.egov.models.RequestInfo;

public interface PenaltyRateService {

	/**
	 * Description : service method for creating penaltyRate master
	 * 
	 * @param tenantId
	 * @param PenaltyRateRequest
	 * @return PenaltyRateResponse
	 * @throws Exception
	 */
	public PenaltyRateResponse createPenaltyRateMaster(String tenantId, PenaltyRateRequest penaltyRateRequest);

	/**
	 * Description : service method for updating penaltyRate master
	 * 
	 * @param PenaltyRateRequest
	 * @return PenaltyRateResponse
	 * @throws Exception
	 */
	public PenaltyRateResponse updatePenaltyRateMaster(PenaltyRateRequest penaltyRateRequest);

	/**
	 * Description : service method for searching penaltyRate master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param applicationTypeId
	 * @param pageSize
	 * @param offSet
	 * @return PenaltyRateResponse
	 * @throws Exception
	 */
	public PenaltyRateResponse getPenaltyRateMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			String applicationTypeId, Integer pageSize, Integer offSet);

}