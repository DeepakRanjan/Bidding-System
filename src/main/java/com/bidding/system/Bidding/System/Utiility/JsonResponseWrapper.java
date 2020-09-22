package com.bidding.system.Bidding.System.Utiility;

import com.bidding.system.Bidding.System.beans.JsonResponse;

/**
 * It is wrapper class which will be used to format output json response
 * @author deepakranjan
 *
 */
public class JsonResponseWrapper {

	public static JsonResponse getSuccessResponse(final Object result, final String message, int code) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCode(code);
		jsonResponse.setMessage(message);
		jsonResponse.setResult(result);
		jsonResponse.setStatus(JsonResponse.SUCCESS);
		return jsonResponse;
	}
	
	public static JsonResponse getFailureResponse(final Object result, final String message, int code) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCode(code);
		jsonResponse.setMessage(message);
		jsonResponse.setResult(result);
		jsonResponse.setStatus(JsonResponse.FAILURE);
		return jsonResponse;
	}
}
