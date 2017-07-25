package org.egov.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in a Trade license
 * ApplicationTypeResponse
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationTypeResponse {

	private ResponseInfo responseInfo;

	private List<ApplicationType> ApplicationTypes;
}