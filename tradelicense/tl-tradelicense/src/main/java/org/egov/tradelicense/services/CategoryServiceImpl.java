package org.egov.tradelicense.services;

import java.util.Date;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.Category;
import org.egov.models.CategoryDetail;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
import org.egov.tradelicense.config.PropertiesManager;
import org.egov.tradelicense.exception.DuplicateIdException;
import org.egov.tradelicense.exception.InvalidInputException;
import org.egov.tradelicense.repository.CategoryRepository;
import org.egov.tradelicense.repository.helper.UtilityHelper;
import org.egov.tradelicense.utility.ConstantUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UtilityHelper utilityHelper;
	
	@Autowired
	private PropertiesManager propertiesManager;

	@Override
	@Transactional
	public CategoryResponse createCategoryMaster(CategoryRequest categoryRequest) {

		RequestInfo requestInfo = categoryRequest.getRequestInfo();
		AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
		for (Category category : categoryRequest.getCategories()) {

			Long ParentId = category.getParentId();
			Boolean isExists = utilityHelper.checkWhetherDuplicateRecordExits(category.getTenantId(),
					category.getCode(), ConstantUtility.CATEGORY_TABLE_NAME, null);
			if (isExists) {
				throw new DuplicateIdException(propertiesManager.getCategoryCustomMsg(),requestInfo);
			}

			if (ParentId != null) {
				Boolean isParentExists = utilityHelper.checkWhetherParentRecordExits(category.getParentId(),
						ConstantUtility.CATEGORY_TABLE_NAME);
				if (isParentExists) {
					for (CategoryDetail categoryDetail : category.getDetails()) {
						Boolean isCategoryDetailExists = utilityHelper.checkWhetherDuplicateCategoryDetailRecordExits(
								categoryDetail, ConstantUtility.CATEGORY_DETAIL_TABLE_NAME, null);
						if (isCategoryDetailExists) {
							throw new DuplicateIdException(propertiesManager.getCategoryCustomMsg(), requestInfo);
						}
						Boolean isUomExists = utilityHelper.checkWhetherUomExists(categoryDetail);
						if (isUomExists) {
							try {
								category.setAuditDetails(auditDetails);
								Long id = categoryRepository.createCategory(category.getTenantId(), category);
								category.setId(id);
							} catch (Exception e) {
								throw new InvalidInputException(requestInfo);
							}
						} else {
							throw new InvalidInputException(requestInfo);
						}
					}
				} else {
					throw new InvalidInputException(requestInfo);
				}
			} else {
				try {
					category.setAuditDetails(auditDetails);
					Long id = categoryRepository.createCategory(category.getTenantId(), category);
					category.setId(id);
				} catch (Exception e) {
					throw new InvalidInputException(requestInfo);
				}
			}
		}

		CategoryResponse categoryResponse = new CategoryResponse();
		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
		categoryResponse.setCategories(categoryRequest.getCategories());
		categoryResponse.setResponseInfo(responseInfo);

		return categoryResponse;
	}

	@Override
	@Transactional
	public CategoryResponse updateCategoryMaster(CategoryRequest categoryRequest) {

		RequestInfo requestInfo = categoryRequest.getRequestInfo();
		for (Category category : categoryRequest.getCategories()) {

			Long ParentId = category.getParentId();
			Boolean isExists = utilityHelper.checkWhetherDuplicateRecordExits(category.getTenantId(),
					category.getCode(), ConstantUtility.CATEGORY_TABLE_NAME, category.getId());
			if (isExists) {
				throw new DuplicateIdException(propertiesManager.getCategoryCustomMsg(),requestInfo);
			}
			if (ParentId != null) {
				Boolean isParentExists = utilityHelper.checkWhetherParentRecordExits(category.getParentId(),
						ConstantUtility.CATEGORY_TABLE_NAME);
				if (isParentExists) {
					for (CategoryDetail categoryDetail : category.getDetails()) {
						Boolean isCategoryDetailExists = utilityHelper.checkWhetherDuplicateCategoryDetailRecordExits(
								categoryDetail, ConstantUtility.CATEGORY_DETAIL_TABLE_NAME, categoryDetail.getId());
						if (isCategoryDetailExists) {
							throw new DuplicateIdException(propertiesManager.getCategoryCustomMsg(),requestInfo);
						}
						Boolean isUomExists = utilityHelper.checkWhetherUomExists(categoryDetail);
						if (isUomExists) {
							try {
								Long updatedTime = new Date().getTime();
								category.getAuditDetails().setLastModifiedTime(updatedTime);
								category.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
								categoryDetail = categoryRepository.updateCategoryDetail(categoryDetail);
								category = categoryRepository.updateCategory(category);
							} catch (Exception e) {
								throw new InvalidInputException(categoryRequest.getRequestInfo());
							}
						} else {
							throw new InvalidInputException(requestInfo);
						}
					}
				} else {
					throw new InvalidInputException(requestInfo);
				}
			} else {
				try {
					Long updatedTime = new Date().getTime();
					category.getAuditDetails().setLastModifiedTime(updatedTime);
					category.getAuditDetails().setLastModifiedBy(requestInfo.getUserInfo().getUsername());
					category = categoryRepository.updateCategory(category);
				} catch (Exception e) {
					throw new InvalidInputException(categoryRequest.getRequestInfo());
				}
			}
		}

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setCategories(categoryRequest.getCategories());
		ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
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
}