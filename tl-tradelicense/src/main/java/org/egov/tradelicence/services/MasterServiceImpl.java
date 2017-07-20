package org.egov.tradelicence.services;

import java.util.Date;
import java.util.List;

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
import org.egov.tradelicence.repository.TradeLicenceMasterRepository;
import org.egov.tradelicence.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	private TradeLicenceMasterRepository tradeLicenceMasterRepository;

	@Override
	@Transactional
	public CategoryResponse craeateCategoryMaster(String tenantId, CategoryRequest categoryRequest) {

		for (Category category : categoryRequest.getCategories()) {

			Boolean isExists = tradeLicenceMasterRepository.checkWhetherRecordExits(category.getTenantId(),
					category.getCode(), ConstantUtility.CATEGORY_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(categoryRequest.getRequestInfo());
			try {

				Long createdTime = new Date().getTime();
				Long id = tradeLicenceMasterRepository.createCategory(tenantId, category);
				category.setId(id);
				category.getAuditDetails().setCreatedTime(createdTime);
				category.getAuditDetails().setLastModifiedTime(createdTime);

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

			Boolean isExists = tradeLicenceMasterRepository.checkWhetherRecordExits(category.getTenantId(),
					category.getCode(), ConstantUtility.CATEGORY_TABLE_NAME, category.getId());

			if (isExists)
				throw new DuplicateIdException(categoryRequest.getRequestInfo());

			try {
				Long updatedTime = new Date().getTime();

				category = tradeLicenceMasterRepository.updateCategory(category);

				category.getAuditDetails().setLastModifiedTime(updatedTime);

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
			List<Category> categories = tradeLicenceMasterRepository.searchCategory(tenantId, ids, name, code, pageSize,
					offSet);
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

			Boolean isExists = tradeLicenceMasterRepository.checkWhetherRecordExits(uom.getTenantId(), uom.getCode(),
					ConstantUtility.UOM_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(uomRequest.getRequestInfo());
			try {

				Long createdTime = new Date().getTime();
				Long id = tradeLicenceMasterRepository.createUom(tenantId, uom);
				uom.setId(id);
				uom.getAuditDetails().setCreatedTime(createdTime);
				uom.getAuditDetails().setLastModifiedTime(createdTime);

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

			Boolean isExists = tradeLicenceMasterRepository.checkWhetherRecordExits(uom.getTenantId(), uom.getCode(),
					ConstantUtility.UOM_TABLE_NAME, uom.getId());

			if (isExists)
				throw new DuplicateIdException(uomRequest.getRequestInfo());

			try {
				Long updatedTime = new Date().getTime();

				uom = tradeLicenceMasterRepository.updateUom(uom);

				uom.getAuditDetails().setLastModifiedTime(updatedTime);

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
			List<UOM> uoms = tradeLicenceMasterRepository.searchUom(tenantId, ids, name, code, active, pageSize,
					offSet);
			ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
			uomResponse.setUoms(uoms);
			uomResponse.setResponseInfo(responseInfo);

		} catch (Exception e) {
			throw new InvalidInputException(requestInfo);
		}

		return uomResponse;

	}
}