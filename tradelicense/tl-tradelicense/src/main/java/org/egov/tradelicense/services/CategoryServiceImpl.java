package org.egov.tradelicense.services;

import java.util.Date;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.Category;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.ResponseInfoFactory;
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

	@Override
	@Transactional
	public CategoryResponse createCategoryMaster(String tenantId, CategoryRequest categoryRequest) {

		for (Category category : categoryRequest.getCategories()) {

			Boolean isExists = utilityHelper.checkWhetherRecordExits(category.getTenantId(), category.getCode(),
					ConstantUtility.CATEGORY_TABLE_NAME, null);
			if (isExists)
				throw new DuplicateIdException(categoryRequest.getRequestInfo());

			RequestInfo requestInfo = categoryRequest.getRequestInfo();
			AuditDetails auditDetails = utilityHelper.getCreateMasterAuditDetals(requestInfo);
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

			Boolean isExists = utilityHelper.checkWhetherRecordExits(category.getTenantId(), category.getCode(),
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
}