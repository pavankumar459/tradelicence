package org.egov.tradelicense.services;

import org.egov.models.FeeMatrixRequest;
import org.egov.models.FeeMatrixResponse;
import org.egov.models.RequestInfo;
import org.springframework.stereotype.Service;

@Service
public class FeeMatrixServiceImpl implements FeeMatrixService {

	public FeeMatrixResponse createFeeMatrixMaster(String tenantId, FeeMatrixRequest feeMatrixRequest) {

		return null;
	}

	public FeeMatrixResponse updateFeeMatrixMaster(FeeMatrixRequest feeMatrixRequest) {

		return null;
	}

	public FeeMatrixResponse getFeeMatrixMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			Integer categoryId, Integer subcategoryId, Integer financialYear, String applicationType,
			String businessNature, Integer pageSize, Integer offSet) {

		return null;
	}
}