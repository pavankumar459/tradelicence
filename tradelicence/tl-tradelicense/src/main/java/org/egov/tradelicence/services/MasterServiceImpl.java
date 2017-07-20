package org.egov.tradelicence.services;

import java.util.Date;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.Category;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.models.UOM;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.tradelicence.exception.DuplicateIdException;
import org.egov.tradelicence.exception.InvalidInputException;
import org.egov.tradelicence.repository.CategoryRepository;
import org.egov.tradelicence.repository.UOMRepository;
import org.egov.tradelicence.repository.ValidatorRepository;
import org.egov.tradelicence.utility.ConstantUtility;
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

	@Override
	@Transactional
	public CategoryResponse craeateCategoryMaster(String tenantId, CategoryRequest categoryRequest) {

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

	private AuditDetails getCreateMasterAuditDetals(RequestInfo requestInfo) {

		AuditDetails auditDetails = new AuditDetails();
		Long createdTime = new Date().getTime();
		auditDetails.setCreatedTime(createdTime);
		auditDetails.setLastModifiedTime(createdTime);
		auditDetails.setCreatedBy(requestInfo.getUserInfo().getUsername());
		auditDetails.setCreatedBy(requestInfo.getUserInfo().getUsername());

		return auditDetails;
	}
}