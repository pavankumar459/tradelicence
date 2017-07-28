package org.egov.tradelicense.services;

import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;

public interface CategoryService {

	/**
	 * Description : service method for creating category master
	 * 
	 * @param tenantId
	 * @param CategoryRequest
	 * @return CategoryResponse
	 * @throws Exception
	 */
	public CategoryResponse createCategoryMaster(CategoryRequest categoryRequest);

	/**
	 * Description : service method for updating category master
	 * 
	 * 
	 * @param CategoryRequest
	 * @return CategoryResponse
	 * @throws Exception
	 */
	public CategoryResponse updateCategoryMaster(CategoryRequest categoryRequest);

	/**
	 * Description : service method for searching category master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param pageSize
	 * @param offSet
	 * @return CategoryResponse
	 * @throws Exception
	 */
	public CategoryResponse getCategoryMaster(RequestInfo requestInfo, String tenantId, Integer[] ids, String name,
			String code, String type, Integer pageSize, Integer offSet);

}