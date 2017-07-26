package org.egov.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in a Trade license
 * BusinessNatureResponse
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessNatureResponse {

	private ResponseInfo responseInfo;

	private List<BusinessNature> BusinessNatures;
}