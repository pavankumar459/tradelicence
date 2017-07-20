package org.egov.models;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDetail {

	private Long id = null;

	@NotNull
	private String subCategoryId = null;

	@NotNull
	private String feeTypeId = null;

	@NotNull
	private String rateTypeId = null;
	
	@NotNull
	private String uomId = null;
}