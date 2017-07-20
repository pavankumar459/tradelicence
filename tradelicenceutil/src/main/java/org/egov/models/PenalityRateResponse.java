package org.egov.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenalityRateResponse {

	private ResponseInfo responseInfo;

	private List<PenalityRate> penalityRates;
}