package org.egov.tradelicense.services;

import java.util.Date;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.models.SubCategory;
import org.egov.models.SubCategoryDetail;
import org.egov.models.SubCategoryRequest;
import org.egov.models.SubCategoryResponse;
import org.egov.tradelicense.exception.DuplicateIdException;
import org.egov.tradelicense.exception.InvalidInputException;
import org.egov.tradelicense.repository.SubCategoryRepository;
import org.egov.tradelicense.repository.helper.UtilityHelper;
import org.egov.tradelicense.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	private UtilityHelper utilityHelper;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	@Transactional
	public SubCategoryResponse createSubCategoryMaster(String tenantId, SubCategoryRequest subCategoryRequest) {

		for (SubCategory subCategory : subCategoryRequest.getSubCategories()) {

			Boolean isExists = utilityHelper.checkWhetherRecordExits(subCategory.getTenantId(), subCategory.getCode(),
					ConstantUtility.SUB_CATEGORY_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(subCategoryRequest.getRequestInfo());

			Boolean isCategoryExists = utilityHelper.checkWhetherCategoryExists(subCategory);
			if (!isCategoryExists) {
				throw new InvalidInputException(subCategoryRequest.getRequestInfo());
			}

			for (SubCategoryDetail subCategoryDetail : subCategory.getSubCategoryDetails()) {

				Boolean isUomExists = utilityHelper.checkWhetherUomExists(subCategoryDetail);
				if (!isUomExists) {
					throw new InvalidInputException(subCategoryRequest.getRequestInfo());
				}
			}

			RequestInfo requestInfo = subCategoryRequest.getRequestInfo();
			AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
			try {

				subCategory.setAuditDetails(auditDetails);
				Long id = subCategoryRepository.createSubCategory(tenantId, subCategory);
				subCategory.setId(id);
				for (SubCategoryDetail subCategoryDetail : subCategory.getSubCategoryDetails()) {

					subCategoryDetail.setSubCategoryId(id.toString());
				}

			} catch (Exception e) {
				throw new InvalidInputException(subCategoryRequest.getRequestInfo());
			}
		}

		SubCategoryResponse subCategoryResponse = new SubCategoryResponse();

		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(subCategoryRequest.getRequestInfo(), true);

		subCategoryResponse.setSubCategories(subCategoryRequest.getSubCategories());
		subCategoryResponse.setResponseInfo(responseInfo);

		return subCategoryResponse;
	}

	@Override
	@Transactional
	public SubCategoryResponse updateSubCategoryMaster(SubCategoryRequest subCategoryRequest) {

		for (SubCategory subCategory : subCategoryRequest.getSubCategories()) {

			Boolean isExists = utilityHelper.checkWhetherRecordExits(subCategory.getTenantId(), subCategory.getCode(),
					ConstantUtility.SUB_CATEGORY_TABLE_NAME, subCategory.getId());

			if (isExists)
				throw new DuplicateIdException(subCategoryRequest.getRequestInfo());

			RequestInfo requestInfo = subCategoryRequest.getRequestInfo();
			try {

				Long updatedTime = new Date().getTime();
				subCategory.getAuditDetails().setLastModifiedTime(updatedTime);
				subCategory.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
				subCategory = subCategoryRepository.updateSubCategory(subCategory);

			} catch (Exception e) {

				throw new InvalidInputException(subCategoryRequest.getRequestInfo());

			}
		}

		SubCategoryResponse subCategoryResponse = new SubCategoryResponse();

		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(subCategoryRequest.getRequestInfo(), true);

		subCategoryResponse.setSubCategories(subCategoryRequest.getSubCategories());
		subCategoryResponse.setResponseInfo(responseInfo);

		return subCategoryResponse;
	}

	@Override
	public SubCategoryResponse getSubCategoryMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			String name, String code, Integer businessNatureId, Integer categoryId, Integer pageSize, Integer offSet) {

		SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
		try {
			List<SubCategory> subCategories = subCategoryRepository.searchSubCategory(tenantId, ids, name, code,
					businessNatureId, categoryId, pageSize, offSet);
			ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
			subCategoryResponse.setSubCategories(subCategories);
			subCategoryResponse.setResponseInfo(responseInfo);

		} catch (Exception e) {
			throw new InvalidInputException(requestInfo);
		}

		return subCategoryResponse;

	}
}