package org.egov.tradelicense.services;

import org.egov.models.AuditDetails;
import org.egov.models.FeeMatrix;
import org.egov.models.FeeMatrixRequest;
import org.egov.models.FeeMatrixResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.tradelicense.config.PropertiesManager;
import org.egov.tradelicense.exception.DuplicateIdException;
import org.egov.tradelicense.repository.FeeMatrixRepository;
import org.egov.tradelicense.repository.helper.UtilityHelper;
import org.egov.tradelicense.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeMatrixServiceImpl implements FeeMatrixService {

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	FeeMatrixRepository feeMatrixRepository;

	@Autowired
	UtilityHelper utilityHelper;

	@Autowired
	private PropertiesManager propertiesManager;

	@Override
	@Transactional
	public FeeMatrixResponse createFeeMatrixMaster(String tenantId, FeeMatrixRequest feeMatrixRequest) {

		RequestInfo requestInfo = feeMatrixRequest.getRequestInfo();
		AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
		for (FeeMatrix feeMatrix : feeMatrixRequest.getFeeMatrices()) {
			
			tenantId = feeMatrix.getTenantId();
			String applicationType = feeMatrix.getApplicationType().toString();
			Long categoryId = feeMatrix.getCategoryId();
			Long subCategoryId = feeMatrix.getSubCategoryId();
			String financialYear = feeMatrix.getFinancialYear();
//			Boolean isExists = utilityHelper.checkWhetherDuplicateFeeMatrixRecordExits(feeMatrix.getTenantId(),
//					feeMatrix.getApplicationType().toString(), feeMatrix.getCategoryId(), feeMatrix.getSubCategoryId(),
//					feeMatrix.getFinancialYear(), ConstantUtility.CATEGORY_TABLE_NAME, null);
//			if (isExists) {
//				throw new DuplicateIdException(propertiesManager.getCategoryCustomMsg(), requestInfo);
//			}
		}

		FeeMatrixResponse feeMatrixResponse = new FeeMatrixResponse();
		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
		feeMatrixResponse.setFeeMatrices(feeMatrixRequest.getFeeMatrices());
		feeMatrixResponse.setResponseInfo(responseInfo);

		return feeMatrixResponse;
	}

	public FeeMatrixResponse updateFeeMatrixMaster(FeeMatrixRequest feeMatrixRequest) {

		RequestInfo requestInfo = feeMatrixRequest.getRequestInfo();
		AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
		for (FeeMatrix feeMatrix : feeMatrixRequest.getFeeMatrices()) {

		}

		FeeMatrixResponse feeMatrixResponse = new FeeMatrixResponse();
		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
		feeMatrixResponse.setFeeMatrices(feeMatrixRequest.getFeeMatrices());
		feeMatrixResponse.setResponseInfo(responseInfo);

		return feeMatrixResponse;
	}

	public FeeMatrixResponse getFeeMatrixMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			Integer categoryId, Integer subcategoryId, Integer financialYear, String applicationType,
			String businessNature, Integer pageSize, Integer offSet) {

		return null;
	}
}