package org.egov.models;

import javax.validation.constraints.NotNull;

import org.egov.enums.FeeTypeEnum;
import org.egov.enums.RateTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in SubCategoryDetail
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDetail {

	private Long id = null;

	@NotNull
	private String subCategoryId = null;

	@NotNull
	private FeeTypeEnum feeTypeId = null;

	@NotNull
	private RateTypeEnum rateTypeId = null;
	
	@NotNull
	private String uomId = null;
}