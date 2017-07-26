package org.egov.tradelicense.services;

import java.util.Date;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.BusinessNature;
import org.egov.models.BusinessNatureRequest;
import org.egov.models.BusinessNatureResponse;
import org.egov.models.Category;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.models.SubCategory;
import org.egov.models.SubCategoryDetail;
import org.egov.models.SubCategoryRequest;
import org.egov.models.SubCategoryResponse;
import org.egov.models.UOM;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.models.UserInfo;
import org.egov.tradelicense.exception.DuplicateIdException;
import org.egov.tradelicense.exception.InvalidInputException;
import org.egov.tradelicense.repository.BusinessNatureRepository;
import org.egov.tradelicense.repository.CategoryRepository;
import org.egov.tradelicense.repository.SubCategoryRepository;
import org.egov.tradelicense.repository.UOMRepository;
import org.egov.tradelicense.repository.ValidatorRepository;
import org.egov.tradelicense.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	private ValidatorRepository validatorRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UOMRepository uomRepository;

	@Autowired
	BusinessNatureRepository businessNatureRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	@Transactional
	public CategoryResponse createCategoryMaster(String tenantId, CategoryRequest categoryRequest) {

		for (Category category : categoryRequest.getCategories()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(category.getTenantId(), category.getCode(),
					ConstantUtility.CATEGORY_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(categoryRequest.getRequestInfo());

			RequestInfo requestInfo = categoryRequest.getRequestInfo();
			AuditDetails auditDetails = getCreateMasterAuditDetals(requestInfo);
			try {

				category.setAuditDetails(auditDetails);
				Long id = categoryRepository.createCategory(tenantId, category);
				category.setId(id);

			} catch (Exception e) {
				throw new InvalidInputException(categoryRequest.getRequestInfo());
			}
		}

		CategoryResponse categoryResponse = new CategoryResponse();

		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(categoryRequest.getRequestInfo(), true);

		categoryResponse.setCategories(categoryRequest.getCategories());
		categoryResponse.setResponseInfo(responseInfo);

		return categoryResponse;
	}

	@Override
	@Transactional
	public CategoryResponse updateCategoryMaster(CategoryRequest categoryRequest) {

		for (Category category : categoryRequest.getCategories()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(category.getTenantId(), category.getCode(),
					ConstantUtility.CATEGORY_TABLE_NAME, category.getId());

			if (isExists)
				throw new DuplicateIdException(categoryRequest.getRequestInfo());

			RequestInfo requestInfo = categoryRequest.getRequestInfo();
			try {

				Long updatedTime = new Date().getTime();
				category.getAuditDetails().setLastModifiedTime(updatedTime);
				category.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
				category = categoryRepository.updateCategory(category);

			} catch (Exception e) {

				throw new InvalidInputException(categoryRequest.getRequestInfo());

			}
		}

		CategoryResponse categoryResponse = new CategoryResponse();

		categoryResponse.setCategories(categoryRequest.getCategories());
		ResponseInfo responseInfo = responseInfoFactory
				.createResponseInfoFromRequestInfo(categoryRequest.getRequestInfo(), true);
		categoryResponse.setResponseInfo(responseInfo);
		return categoryResponse;
	}

	@Override
	public CategoryResponse getCategoryMaster(RequestInfo requestInfo, String tenantId, Integer[] ids, String name,
			String code, Integer pageSize, Integer offSet) {

		CategoryResponse categoryResponse = new CategoryResponse();
		try {
			List<Category> categories = categoryRepository.searchCategory(tenantId, ids, name, code, pageSize, offSet);
			ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
			categoryResponse.setCategories(categories);
			categoryResponse.setResponseInfo(responseInfo);

		} catch (Exception e) {
			throw new InvalidInputException(requestInfo);
		}

		return categoryResponse;

	}

	@Override
	@Transactional
	public UOMResponse createUomMaster(String tenantId, UOMRequest uomRequest) {

		for (UOM uom : uomRequest.getUoms()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(uom.getTenantId(), uom.getCode(),
					ConstantUtility.UOM_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(uomRequest.getRequestInfo());

			RequestInfo requestInfo = uomRequest.getRequestInfo();
			AuditDetails auditDetails = getCreateMasterAuditDetals(requestInfo);
			try {

				uom.setAuditDetails(auditDetails);
				Long id = uomRepository.createUom(tenantId, uom);
				uom.setId(id);

			} catch (Exception e) {
				throw new InvalidInputException(uomRequest.getRequestInfo());
			}
		}

		UOMResponse uomResponse = new UOMResponse();

		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(uomRequest.getRequestInfo(),
				true);

		uomResponse.setUoms(uomRequest.getUoms());
		uomResponse.setResponseInfo(responseInfo);

		return uomResponse;
	}

	@Override
	@Transactional
	public UOMResponse updateUomMaster(UOMRequest uomRequest) {

		for (UOM uom : uomRequest.getUoms()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(uom.getTenantId(), uom.getCode(),
					ConstantUtility.UOM_TABLE_NAME, uom.getId());

			if (isExists)
				throw new DuplicateIdException(uomRequest.getRequestInfo());

			RequestInfo requestInfo = uomRequest.getRequestInfo();
			try {

				Long updatedTime = new Date().getTime();
				uom.getAuditDetails().setLastModifiedTime(updatedTime);
				uom.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
				uom = uomRepository.updateUom(uom);

			} catch (Exception e) {

				throw new InvalidInputException(uomRequest.getRequestInfo());
			}
		}

		UOMResponse uomResponse = new UOMResponse();

		uomResponse.setUoms(uomRequest.getUoms());
		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(uomRequest.getRequestInfo(),
				true);
		uomResponse.setResponseInfo(responseInfo);
		return uomResponse;
	}

	@Override
	public UOMResponse getUomMaster(RequestInfo requestInfo, String tenantId, Integer[] ids, String name, String code,
			Boolean active, Integer pageSize, Integer offSet) {

		UOMResponse uomResponse = new UOMResponse();

		try {
			List<UOM> uoms = uomRepository.searchUom(tenantId, ids, name, code, active, pageSize, offSet);
			ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
			uomResponse.setUoms(uoms);
			uomResponse.setResponseInfo(responseInfo);

		} catch (Exception e) {
			throw new InvalidInputException(requestInfo);
		}

		return uomResponse;

	}

	@Override
	@Transactional
	public SubCategoryResponse createSubCategoryMaster(String tenantId, SubCategoryRequest subCategoryRequest) {

		for (SubCategory subCategory : subCategoryRequest.getSubCategories()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(subCategory.getTenantId(),
					subCategory.getCode(), ConstantUtility.SUB_CATEGORY_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(subCategoryRequest.getRequestInfo());

			Boolean isCategoryExists = validatorRepository.checkWhetherCategoryExists(subCategory);
			if (!isCategoryExists) {
				throw new InvalidInputException(subCategoryRequest.getRequestInfo());
			}

			for (SubCategoryDetail subCategoryDetail : subCategory.getSubCategoryDetails()) {

				Boolean isUomExists = validatorRepository.checkWhetherUomExists(subCategoryDetail);
				if (!isUomExists) {
					throw new InvalidInputException(subCategoryRequest.getRequestInfo());
				}
			}

			RequestInfo requestInfo = subCategoryRequest.getRequestInfo();
			AuditDetails auditDetails = getCreateMasterAuditDetals(requestInfo);
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

			Boolean isExists = validatorRepository.checkWhetherRecordExits(subCategory.getTenantId(),
					subCategory.getCode(), ConstantUtility.SUB_CATEGORY_TABLE_NAME, subCategory.getId());

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

	@Override
	@Transactional
	public BusinessNatureResponse createBusinessNatureMaster(String tenantId,
			BusinessNatureRequest businessNatureRequest) {

		for (BusinessNature businessNature : businessNatureRequest.getBusinessNatures()) {

			Boolean isExists = validatorRepository.checkWhetherRecordExits(businessNature.getTenantId(),
					businessNature.getCode(), ConstantUtility.BUSINESS_NATURE_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(businessNatureRequest.getRequestInfo());

			RequestInfo requestInfo = businessNatureRequest.getRequestInfo();
			AuditDetails auditDetails = getCreateMasterAuditDetals(requestInfo);
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

			Boolean isExists = validatorRepository.checkWhetherRecordExits(businessNature.getTenantId(),
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

	private AuditDetails getCreateMasterAuditDetals(RequestInfo requestInfo) {

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