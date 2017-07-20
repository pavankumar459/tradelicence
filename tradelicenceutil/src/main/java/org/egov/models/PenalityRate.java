package org.egov.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenalityRate {

	private Long id = null;

	@JsonProperty("tenantId")
	@NotNull
	@Size(min = 4, max = 128)
	private String tenantId = null;

	@NotNull
	private Long applicationTypeId = null;

	@NotNull
	private Long fromRange = null;
	
	@NotNull
	private Long toRange = null;
	
	@NotNull
	private Long rate = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;
}