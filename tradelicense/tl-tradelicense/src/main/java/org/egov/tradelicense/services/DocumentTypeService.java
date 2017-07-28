package org.egov.tradelicense.services;

import org.egov.models.CategoryResponse;
import org.egov.models.DocumentTypeRequest;
import org.egov.models.DocumentTypeResponse;
import org.egov.models.RequestInfo;

public interface DocumentTypeService {

	/**
	 * Description : service method for creating documentType 
	 * 
	 * @param DocumentTypeRequest
	 * @return DocumentTypeResponse
	 */
	public DocumentTypeResponse createDocumentType( DocumentTypeRequest documentTypeRequest);
	
	
	/**
	 * Description : service method for updating documentType
	 * 
	 * 
	 * @param CategoryRequest
	 * @return CategoryResponse
	 * @throws Exception
	 */
	public DocumentTypeResponse updateDocumentType(DocumentTypeRequest documentTypeRequest);
	
	
	/**
	 * Description : service method for searching DocumentType master
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param ids
	 * @param name
	 * @param applicationType
	 * @param pageSize
	 * @param offSet
	 * @return DocumentTypeResponse
	 * @throws Exception
	 */
	public DocumentTypeResponse getDocumentType(RequestInfo requestInfo, String tenantId, Integer[] ids, String name,
			Boolean enabled, String applicationType, Integer pageSize, Integer offSet);
}