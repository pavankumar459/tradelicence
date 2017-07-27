package org.egov.tradelicense.services;

import org.egov.models.RequestInfo;
import org.egov.models.SubCategoryRequest;
import org.egov.models.SubCategoryResponse;

public interface SubCategoryService {

	/**
	 * Description : service method for creating subcategory master
	 * 
	 * @param tenantId
	 * @param SubCategoryRequest
	 * @return SubCategoryResponse
	 */
	public SubCategoryResponse createSubCategoryMaster(String tenantId, SubCategoryRequest subCategoryRequest);

	/**
	 * Description : service method for updating subcategory master
	 * 
	 * 
	 * @param SubCategoryRequest
	 * @return SubCategoryResponse
	 */
	SubCategoryResponse updateSubCategoryMaster(SubCategoryRequest subCategoryRequest);

	/**
	 * Description : service method for searching subcategory master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param categoryId
	 * @param subCategoryId
	 * @param pageSize
	 * @param offSet
	 * @return SubCategoryResponse
	 */
	public SubCategoryResponse getSubCategoryMaster(RequestInfo requestInfo, String tenantId, Integer[] ids,
			String name, String code, Integer categoryId, Integer subCategoryId, Integer pageSize, Integer offSet);

}