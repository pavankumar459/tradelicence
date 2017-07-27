package org.egov.tradelicense.services;

import java.util.Date;

import org.egov.models.AuditDetails;
import org.egov.models.BusinessNature;
import org.egov.models.BusinessNatureRequest;
import org.egov.models.BusinessNatureResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.tradelicense.exception.DuplicateIdException;
import org.egov.tradelicense.exception.InvalidInputException;
import org.egov.tradelicense.repository.BusinessNatureRepository;
import org.egov.tradelicense.repository.SubCategoryRepository;
import org.egov.tradelicense.repository.helper.UtilityHelper;
import org.egov.tradelicense.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessNatureServiceImpl implements BusinessNatureService{

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	private UtilityHelper utilityHelper;
	@Autowired
	BusinessNatureRepository businessNatureRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	
	
	@Override
	@Transactional
	public BusinessNatureResponse createBusinessNatureMaster(String tenantId,
			BusinessNatureRequest businessNatureRequest) {

		for (BusinessNature businessNature : businessNatureRequest.getBusinessNatures()) {

			Boolean isExists = utilityHelper.checkWhetherRecordExits(businessNature.getTenantId(),
					businessNature.getCode(), ConstantUtility.BUSINESS_NATURE_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(businessNatureRequest.getRequestInfo());

			RequestInfo requestInfo = businessNatureRequest.getRequestInfo();
			AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
			try {

				businessNature.setAuditDetails(auditDetails);
				Long id = businessNatureRepository.createBusinessNature(tenantId, businessNature);
				businessNature.setId(id);

			} catch (Exception e) {
				throw new InvalidInputException(businessNatureRequest.getRequestInfo());
			}
		}

		BusinessNatureResponse businessNatureResponse = new BusinessNatureResponse();

		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(businessNatureRequest.getRequestInfo(), true);

		businessNatureResponse.setBusinessNatures(businessNatureRequest.getBusinessNatures());
		businessNatureResponse.setResponseInfo(responseInfo);

		return businessNatureResponse;
	}

	@Override
	@Transactional
	public BusinessNatureResponse updateBusinessNatureMaster(BusinessNatureRequest businessNatureRequest) {

		for (BusinessNature businessNature : businessNatureRequest.getBusinessNatures()) {

			Boolean isExists = utilityHelper.checkWhetherRecordExits(businessNature.getTenantId(),
					businessNature.getCode(), ConstantUtility.BUSINESS_NATURE_TABLE_NAME, businessNature.getId());

			if (isExists)
				throw new DuplicateIdException(businessNatureRequest.getRequestInfo());

			RequestInfo requestInfo = businessNatureRequest.getRequestInfo();
			try {

				Long updatedTime = new Date().getTime();
				businessNature.getAuditDetails().setLastModifiedTime(updatedTime);
				businessNature.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
				businessNature = businessNatureRepository.updateBusinessNature(businessNature);

			} catch (Exception e) {

				throw new InvalidInputException(businessNatureRequest.getRequestInfo());

			}
		}

		BusinessNatureResponse businessNatureResponse = new BusinessNatureResponse();

		businessNatureResponse.setBusinessNatures(businessNatureRequest.getBusinessNatures());
		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(businessNatureRequest.getRequestInfo(), true);
		businessNatureResponse.setResponseInfo(responseInfo);
		return businessNatureResponse;
	}

	@Override
	public BusinessNatureResponse getBusinessNatureMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			String name, String code, Integer pageSize, Integer offSet) {

		BusinessNatureResponse businessNatureResponse = new BusinessNatureResponse();
		/*
		 * try { List<BusinessNature> businessNatures =
		 * businessNatureRepository.searchBusinessNature(tenantId, ids, name,
		 * code, pageSize, offSet); ResponseInfo responseInfo =
		 * responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo,
		 * true); businessNatureResponse.setBusinessNatures(businessNatures);
		 * businessNatureResponse.setResponseInfo(responseInfo);
		 * 
		 * } catch (Exception e) { throw new InvalidInputException(requestInfo);
		 * }
		 */

		return businessNatureResponse;

	}
}