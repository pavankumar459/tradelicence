package org.egov.tradelicence.services;

import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.SubCategoryRequest;
import org.egov.models.SubCategoryResponse;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;

public interface MasterService {

	/**
	 * Description : service method for creating category master
	 * 
	 * @param tenantId
	 * @param CategoryRequest
	 * @return CategoryResponse
	 * @throws Exception
	 */
	public CategoryResponse craeateCategoryMaster(String tenantId, CategoryRequest categoryRequest);

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
			String code, Integer pageSize, Integer offSet);

	/**
	 * Description : service method for creating UOM master
	 * 
	 * @param tenantId
	 * @param UOMRequest
	 * @return UOMResponse
	 */
	public UOMResponse createUomMaster(String tenantId, UOMRequest uomRequest);

	/**
	 * Description : service method for updating UOM master
	 * 
	 * 
	 * @param UOMRequest
	 * @return UOMResponse
	 */
	public UOMResponse updateUomMaster(UOMRequest uomRequest);

	/**
	 * Description : service method for searching UOM master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param active
	 * @param pageSize
	 * @param offSet
	 * @return UOMResponse
	 * @throws Exception
	 */
	public UOMResponse getUomMaster(RequestInfo requestInfo, String tenantId, Integer[] ids, String name, String code,
			Boolean active, Integer pageSize, Integer offSet);

	/**
	 * Description : service method for creating subcategory master
	 * 
	 * @param tenantId
	 * @param SubCategoryRequest
	 * @return SubCategoryResponse
	 */
	public SubCategoryResponse craeateSubCategoryMaster(String tenantId, SubCategoryRequest subCategoryRequest);

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