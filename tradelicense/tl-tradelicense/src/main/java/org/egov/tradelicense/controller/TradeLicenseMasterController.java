package org.egov.tradelicense.controller;

import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.DocumentTypeRequest;
import org.egov.models.DocumentTypeResponse;
import org.egov.models.PenaltyRateRequest;
import org.egov.models.PenaltyRateResponse;
import org.egov.models.RequestInfoWrapper;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.tradelicense.services.CategoryService;
import org.egov.tradelicense.services.DocumentTypeService;
import org.egov.tradelicense.services.PenaltyRateService;
import org.egov.tradelicense.services.UOMService;
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
	CategoryService categoryService;

	@Autowired
	UOMService uomService;

	@Autowired
	PenaltyRateService penaltyRateService;
	
	@Autowired
	DocumentTypeService documentTypeService;

	/**
	 * Description : This api for creating category master
	 * 
	 * @param CategoryRequest
	 * @return CategoryResponse
	 */

	@RequestMapping(path = "/category/_create", method = RequestMethod.POST)
	public CategoryResponse createCategoryMaster(@RequestBody CategoryRequest categoryRequest) {

		return categoryService.createCategoryMaster(categoryRequest);

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

		return categoryService.updateCategoryMaster(categoryRequest);

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
	 public CategoryResponse getCategoryMaster(@RequestBody RequestInfoWrapper
	 requestInfo,
	 @RequestParam(required = true) String tenantId, @RequestParam(required =
	 false) Integer[] ids,
	 @RequestParam(required = false) String name, @RequestParam(required =
	 false) String code,
	 @RequestParam(required = false) Integer pageSize, @RequestParam(required
	 = false) Integer offSet)
	 throws Exception {
	
	 return categoryService.getCategoryMaster(requestInfo.getRequestInfo(),
	 tenantId, ids, name, code, pageSize,
	 offSet);
	
	 }

	/**
	 * Description : This api for creating UOM master
	 * 
	 * @param UOMRequest
	 * @return UOMResponse
	 */
	 @RequestMapping(path = "/uom/_create", method = RequestMethod.POST)
		public UOMResponse createUomMaster(@RequestBody UOMRequest uomRequest) {

			return uomService.createUomMaster( uomRequest );

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

		return uomService.updateUomMaster(uomRequest);

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
			@RequestParam(required = true) String tenantId,
			@RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String code,
			@RequestParam(required = false) Boolean active,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer offSet) throws Exception {

		return uomService.getUomMaster(requestInfo.getRequestInfo(), tenantId, ids, name, code, active, pageSize,
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
	
	
	/**
	 * Description : This api for creating documentType master
	 * 
	 * @param tenantId
	 * @param DocumentTypeRequest
	 * @return DocumentTypeResponse
	 */

	@RequestMapping(path = "/documenttype/_create", method = RequestMethod.POST)
	public DocumentTypeResponse createDocumentTypeMaster(
			@RequestBody DocumentTypeRequest documentTypeRequest) {

		return documentTypeService.createDocumentType( documentTypeRequest);

	}



	/**
	 * Description : This api for updating DocumentType master
	 * 
	 * @param DocumentTypeRequest
	 * @return DocumentTypeResponse
	 */
	@RequestMapping(path = "/documenttype/_update", method = RequestMethod.POST)
	public DocumentTypeResponse updateDocumentTypeMaster(@RequestBody DocumentTypeRequest documentTypeRequest) {

		return documentTypeService.updateDocumentType( documentTypeRequest );

	}




	/**
	 * Description : This api for searching DocumentType master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param applicationType
	 * @param pageSize
	 * @param offSet
	 * @return PenaltyRateResponse
	 * @throws Exception
	 */
	@RequestMapping(path = "/documenttype/_search", method = RequestMethod.POST)
	public DocumentTypeResponse getDocumentTypeMaster(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(required = true) String tenantId,
			@RequestParam(required = false) Integer[] ids,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Boolean enabled,
			@RequestParam(required = false) String applicationType,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer offSet) throws Exception {

		return documentTypeService.getDocumentType(requestInfo.getRequestInfo(), tenantId, ids, name, enabled, applicationType, pageSize, offSet);
	}
}