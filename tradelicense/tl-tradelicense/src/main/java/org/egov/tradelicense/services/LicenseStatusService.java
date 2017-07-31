package org.egov.tradelicense.services;

import org.egov.models.LicenseStatusRequest;
import org.egov.models.LicenseStatusResponse;
import org.egov.models.RequestInfo;

/**
 * Service class for LicenseStatus master
 * 
 * @author Shubham pratap singh
 */

public interface LicenseStatusService {

	/**
	 * Description : service method for creating LicenseStatus master
	 * 
	 * @param LicenseStatusRequest
	 * @return LicenseStatusResponse
	 */
	public LicenseStatusResponse createLicenseStatusMaster(LicenseStatusRequest licenseStatusRequest);

	/**
	 * Description : service method for updating LicenseStatus master
	 * 
	 * @param LicenseStatusRequest
	 * @return LicenseStatusResponse
	 */
	public LicenseStatusResponse updateLicenseStatusMaster(LicenseStatusRequest licenseStatusRequest);


	/**
	 * Description : service method for searching LicenseStatus master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param active
	 * @param pageSize
	 * @param offSet
	 * @return LicenseStatusResponse
	 * @throws Exception
	 */
	public LicenseStatusResponse getLicenseStatusMaster(RequestInfo requestInfo, String tenantId, Integer[] ids, String name, String code,
			Boolean active, Integer pageSize, Integer offSet);

}
