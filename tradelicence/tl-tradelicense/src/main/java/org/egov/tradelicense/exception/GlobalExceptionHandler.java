package org.egov.tradelicense.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.models.Error;
import org.egov.models.ErrorRes;
import org.egov.models.ResponseInfo;
import org.egov.tradelicense.config.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Description : Global exception handler for property module
 * 
 * @author Pavan Kumar Kamma
 *
 */
@Service
public class GlobalExceptionHandler {

	@Autowired
	private PropertiesManager propertiesManager;

	/**
	 * Description : MethodArgumentNotValidException type exception handler
	 * 
	 * @param ex
	 * @return ErrorRes
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorRes processValidationError(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		Error error = new Error(HttpStatus.BAD_REQUEST.toString(), propertiesManager.getInvalidInput(), null, errors);
		List<Error> errorList = new ArrayList<Error>();
		errorList.add(error);
		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setStatus(propertiesManager.getFailedStatus());
		return new ErrorRes(responseInfo, errorList);
	}

	/**
	 * Description : General exception handler method
	 * 
	 * @param ex
	 * @param req
	 * @return
	 */

	@ExceptionHandler(value = { Exception.class })

	public ErrorRes unknownException(Exception ex, WebRequest req) {
		if (ex instanceof InvalidInputException) {
			Error error = new Error(HttpStatus.BAD_REQUEST.toString(), propertiesManager.getInvalidInput(), null, null);
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.setApiId(((InvalidInputException) ex).getRequestInfo().getApiId());
			responseInfo.setVer(((InvalidInputException) ex).getRequestInfo().getVer());
			responseInfo.setMsgId(((InvalidInputException) ex).getRequestInfo().getMsgId());
			responseInfo.setTs(new Date().getTime());
			responseInfo.setStatus(propertiesManager.getFailedStatus());
			List<Error> errorList = new ArrayList<Error>();
			errorList.add(error);
			return new ErrorRes(responseInfo, errorList);
		} else if (ex instanceof DuplicateIdException) {
			Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), propertiesManager.getDuplicateCode(),
					null, null);
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.setApiId(((DuplicateIdException) ex).getRequestInfo().getApiId());
			responseInfo.setVer(((DuplicateIdException) ex).getRequestInfo().getVer());
			responseInfo.setMsgId(((DuplicateIdException) ex).getRequestInfo().getMsgId());
			responseInfo.setTs(new Date().getTime());
			responseInfo.setStatus(propertiesManager.getFailedStatus());
			List<Error> errorList = new ArrayList<Error>();
			errorList.add(error);
			return new ErrorRes(responseInfo, errorList);
		} else {
			Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), null,
					new HashMap<String, String>());
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.setStatus(propertiesManager.getFailedStatus());
			List<Error> errorList = new ArrayList<Error>();
			errorList.add(error);
			return new ErrorRes(responseInfo, errorList);
		}

	}

}