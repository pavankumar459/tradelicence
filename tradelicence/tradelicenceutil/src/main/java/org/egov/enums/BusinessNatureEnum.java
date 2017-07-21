package org.egov.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * FeeTypeEnum
 * 
 * Author : Pavan Kumar kamma
 */
public enum BusinessNatureEnum {

	PERMANENT("Permanent"),

	TEMPORARY("Temporary");

	private String value;

	BusinessNatureEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static BusinessNatureEnum fromValue(String text) {
		for (BusinessNatureEnum businessNature : BusinessNatureEnum.values()) {
			if (String.valueOf(businessNature.value).equals(text)) {
				return businessNature;
			}
		}
		return null;
	}
}