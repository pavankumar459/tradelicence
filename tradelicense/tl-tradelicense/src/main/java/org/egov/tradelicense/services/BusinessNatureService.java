package org.egov.tradelicense.services;

import org.egov.models.BusinessNatureRequest;
import org.egov.models.BusinessNatureResponse;
import org.egov.models.RequestInfo;

public interface BusinessNatureService {

	/**
	 * Description : service method for creating business nature master
	 * 
	 * @param tenantId
	 * @param BusinessNatureRequest
	 * @return BusinessNatureResponse
	 */
	public BusinessNatureResponse createBusinessNatureMaster(String tenantId,
			BusinessNatureRequest businessNatureRequest);

	/**
	 * Description : service method for updating business nature master
	 * 
	 * 
	 * @param BusinessNatureRequest
	 * @return BusinessNatureResponse
	 */
	public BusinessNatureResponse updateBusinessNatureMaster(BusinessNatureRequest businessNatureRequest);

	/**
	 * Description : service method for searching business nature master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param pageSize
	 * @param offSet
	 * @return BusinessNatureResponse
	 * @throws Exception
	 */
	public BusinessNatureResponse getBusinessNatureMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			String name, String code, Integer pageSize, Integer offSet);
}