package org.egov.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.enums.BusinessNatureEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in SubCategory
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

	private Long id = null;

	@JsonProperty("tenantId")
	@NotNull
	@Size(min = 4, max = 128)
	private String tenantId = null;

	@NotNull
	private String name = null;

	@NotNull
	private String code = null;

	@NotNull
	private BusinessNatureEnum businessNatureId = null;

	@NotNull
	private Long categoryId = null;

	@Valid
	private List<SubCategoryDetail> subCategoryDetails;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;
}