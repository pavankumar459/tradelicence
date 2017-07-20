package org.egov.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UOM {

	private Long id = null;

	@JsonProperty("tenantId")
	@NotNull
	@Size(min = 4, max = 128)
	private String tenantId = null;

	@NotNull
	@Max(value=256)
	private String name = null;

	@NotNull
	@Max(value=256)
	private String code = null;

	private Boolean active = true;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;
}