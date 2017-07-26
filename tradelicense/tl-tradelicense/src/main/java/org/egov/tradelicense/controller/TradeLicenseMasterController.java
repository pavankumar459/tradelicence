package org.egov.tradelicense.controller;

import org.egov.models.BusinessNatureRequest;
import org.egov.models.BusinessNatureResponse;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.PenaltyRateRequest;
import org.egov.models.PenaltyRateResponse;
import org.egov.models.RequestInfoWrapper;
import org.egov.models.SubCategoryRequest;
import org.egov.models.SubCategoryResponse;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.tradelicense.services.MasterService;
import org.egov.tradelicense.services.PenaltyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller have the all api's related to tradelicense master
 * 
 * @author Pavan Kumar Kamma
 *
 */

@RestController
@RequestMapping(path = "/tradelicense")
public class TradeLicenseMasterController {

	@Autowired
	MasterService masterService;

	@Autowired
	PenaltyRateService penaltyRateService;

	/**
	 * Description : This api for creating category master
	 * 
	 * @param tenantId
	 * @param CategoryRequest
	 * @return CategoryResponse
	 */

	@RequestMapping(path = "/category/_create", method = RequestMethod.POST)
	public CategoryResponse createCategoryMaster(@RequestParam(required = true) String tenantId,
			@RequestBody CategoryRequest categoryRequest) {

		return masterService.createCategoryMaster(tenantId, categoryRequest);

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

	/**
	 * Description : This api for creating subcategory master
	 * 
	 * @param tenantId
	 * @param SubCategoryRequest
	 * @return SubCategoryResponse
	 */
	@RequestMapping(path = "/subcategory/_create", method = RequestMethod.POST)
	public SubCategoryResponse createSubCategoryMaster(@RequestParam(required = true) String tenantId,
			@RequestBody SubCategoryRequest subCategoryRequest) {

		return masterService.createSubCategoryMaster(tenantId, subCategoryRequest);

	}

	/**
	 * Description : This api for updating subcategory master
	 * 
	 * 
	 * @param SubCategoryRequest
	 * @return SubCategoryResponse
	 */
	@RequestMapping(path = "/subcategory/_update", method = RequestMethod.POST)
	public SubCategoryResponse updateSubCategoryMaster(@RequestBody SubCategoryRequest subCategoryRequest) {

		return masterService.updateSubCategoryMaster(subCategoryRequest);

	}

	/**
	 * Description : This api for searching subcategory master
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
	 * @throws Exception
	 */
	@RequestMapping(path = "/subcategory/_search", method = RequestMethod.POST)
	public SubCategoryResponse getSubCategoryMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId, @RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name, @RequestParam(required = false) String code,
			@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Integer subCategoryId,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer offSet)
			throws Exception {

		return masterService.getSubCategoryMaster(requestInfo.getRequestInfo(), tenantId, ids, name, code, categoryId,
				subCategoryId, pageSize, offSet);

	}

	/**
	 * Description : This api for creating business nature master
	 * 
	 * @param tenantId
	 * @param BusinessNatureRequest
	 * @return BusinessNatureResponse
	 */
	@RequestMapping(path = "/businessnature/_create", method = RequestMethod.POST)
	public BusinessNatureResponse createBusinessNatureMaster(@RequestParam(required = true) String tenantId,
			@RequestBody BusinessNatureRequest businessNatureRequest) {

		return masterService.createBusinessNatureMaster(tenantId, businessNatureRequest);

	}

	/**
	 * Description : This api for updating business nature master
	 * 
	 * 
	 * @param BusinessNatureRequest
	 * @return BusinessNatureResponse
	 */
	@RequestMapping(path = "/businessnature/_update", method = RequestMethod.POST)
	public BusinessNatureResponse updateBusinessNatureMaster(@RequestBody BusinessNatureRequest businessNatureRequest) {

		return masterService.updateBusinessNatureMaster(businessNatureRequest);

	}

	/**
	 * Description : This api for searching business nature master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param code
	 * @param pageSize
	 * @param offSet
	 * @return BusinessNatureResponse
	 * @throws Exception
	 */
	@RequestMapping(path = "/businessnature/_search", method = RequestMethod.POST)
	public BusinessNatureResponse getBusinessNatureMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId, @RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name, @RequestParam(required = false) String code,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer offSet)
			throws Exception {

		return masterService.getBusinessNatureMaster(requestInfo.getRequestInfo(), tenantId, ids, name, code, pageSize,
				offSet);

	}

	/**
	 * Description : This api for creating penaltyRate master
	 * 
	 * @param tenantId
	 * @param PenaltyRateRequest
	 * @return PenaltyRateResponse
	 */

	@RequestMapping(path = "/penaltyrate/_create", method = RequestMethod.POST)
	public PenaltyRateResponse createPenaltyRateMaster(@RequestParam(required = true) String tenantId,
			@RequestBody PenaltyRateRequest penaltyRateRequest) {

		return penaltyRateService.createPenaltyRateMaster(tenantId, penaltyRateRequest);
	}

	/**
	 * Description : This api for updating penaltyRate master
	 * 
	 * @param PenaltyRateRequest
	 * @return PenaltyRateResponse
	 */

	@RequestMapping(path = "/penaltyrate/_update", method = RequestMethod.POST)
	public PenaltyRateResponse createPenaltyRateMaster(@RequestBody PenaltyRateRequest penaltyRateRequest) {

		return penaltyRateService.updatePenaltyRateMaster(penaltyRateRequest);
	}

	/**
	 * Description : This api for searching penaltyRate master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param applicationTypeId
	 * @param pageSize
	 * @param offSet
	 * @return PenaltyRateResponse
	 * @throws Exception
	 */
	@RequestMapping(path = "/penaltyrate/_search", method = RequestMethod.POST)
	public PenaltyRateResponse getPenaltyRateMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId, @RequestParam(required = false) Integer[] ids,
			@RequestParam(required = true) String applicationTypeId, @RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer offSet) throws Exception {

		return penaltyRateService.getPenaltyRateMaster(requestInfo.getRequestInfo(), tenantId, ids, applicationTypeId,
				pageSize, offSet);

	}
}