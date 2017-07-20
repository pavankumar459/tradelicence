package org.egov.tradelicence.controller;

import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfoWrapper;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.tradelicence.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller have the all api's related to tradelicence master
 * 
 * @author Pavan Kumar Kamma
 *
 */

@RestController
@RequestMapping(path = "/tradelicence")
public class TradeLicenceMasterController {

	@Autowired
	MasterService masterService;

	/**
	 * Description : This api for creating category master
	 * 
	 * @param tenantId
	 * @param CategoryRequest
	 * @return CategoryResponse
	 */

	@RequestMapping(path = "/category/_create", method = RequestMethod.POST)
	public CategoryResponse craeateCategoryMaster(@RequestParam(required = true) String tenantId,
			@RequestBody CategoryRequest categoryRequest) {

		return masterService.craeateCategoryMaster(tenantId, categoryRequest);

	}

	/**
	 * Description : This api for updating category master
	 * 
	 * 
	 * @param CategoryRequest
	 * @return CategoryResponse
	 */
	@RequestMapping(path = "/category/_update", method = RequestMethod.POST)
	public CategoryResponse updateCategoryMaster(@RequestBody CategoryRequest categoryRequest) {

		return masterService.updateCategoryMaster(categoryRequest);

	}

	/**
	 * Description : This api for searching category master
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
	@RequestMapping(path = "/category/_search", method = RequestMethod.POST)
	public CategoryResponse getCategoryMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId, @RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name, @RequestParam(required = false) String code,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer offSet)
			throws Exception {

		return masterService.getCategoryMaster(requestInfo.getRequestInfo(), tenantId, ids, name, code, pageSize,
				offSet);

	}

	/**
	 * Description : This api for creating UOM master
	 * 
	 * @param tenantId
	 * @param UOMRequest
	 * @return UOMResponse
	 */
	@RequestMapping(path = "/uom/_create", method = RequestMethod.POST)
	public UOMResponse createUomMaster(@RequestParam(required = true) String tenantId,
			@RequestBody UOMRequest uomRequest) {

		return masterService.createUomMaster(tenantId, uomRequest);

	}

	/**
	 * Description : This api for updating UOM master
	 * 
	 * 
	 * @param UOMRequest
	 * @return UOMResponse
	 */
	@RequestMapping(path = "/uom/_update", method = RequestMethod.POST)
	public UOMResponse updateUomMaster(@RequestBody UOMRequest uomRequest) {

		return masterService.updateUomMaster(uomRequest);

	}

	/**
	 * Description : This api for searching UOM master
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
	@RequestMapping(path = "/uom/_search", method = RequestMethod.POST)
	public UOMResponse getUomMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId, @RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name, @RequestParam(required = false) String code,
			@RequestParam(required = false) Boolean active, @RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer offSet) throws Exception {

		return masterService.getUomMaster(requestInfo.getRequestInfo(), tenantId, ids, name, code, active, pageSize,
				offSet);

	}
}