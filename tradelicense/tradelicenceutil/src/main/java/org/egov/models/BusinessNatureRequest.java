package org.egov.models;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in a Trade license
 * BusinessNatureRequest
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessNatureRequest {

	@JsonProperty("RequestInfo")
	private RequestInfo requestInfo;

	@Valid
	private List<BusinessNature> BusinessNatures;
}