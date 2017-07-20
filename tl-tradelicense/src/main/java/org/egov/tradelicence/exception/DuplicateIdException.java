package org.egov.tradelicence.exception;

import org.egov.models.RequestInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Pavan Kumar Kamma 
 * Duplicate Tenant & code combination custom Exception class
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private RequestInfo requestInfo;
}