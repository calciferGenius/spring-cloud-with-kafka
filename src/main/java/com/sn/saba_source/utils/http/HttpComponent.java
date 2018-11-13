package com.sn.saba_source.utils.http;

import com.sn.saba_source.constants.Constants;
import com.sn.saba_source.exception.exception.HttpComponentException;
import com.sn.saba_source.exception.exception.ResponseFailedException;
import com.sn.saba_source.exception.http.*;
import com.sn.saba_source.utils.MySecureProtocolSocketFactory;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpComponent {

	private final static Log log = LogFactory.getLog(HttpComponent.class);

	public static HttpMethodBase sendAPIRequest(RequestMethod requestMethod, String action,
                                                Map<String, String> params, Map<String, String> headers, Integer timeout) throws HttpComponentException, GetFailedException, PostFailedException, ResponseFailedException, HeadFailedException, PutFailedException, DeleteFailedException, OptionsFailedException, TraceFailedException, UnsupportedEncodingException {
		if(requestMethod == null)
			throw new HttpComponentException("Request method is null.");

		long currentTimeMillis = System.currentTimeMillis();
		switch (requestMethod){
			case GET:
				return get(action, params, headers, timeout, currentTimeMillis);
			case HEAD:
				return head(action, params, headers, timeout, currentTimeMillis);
			case POST:
				return post(action, new HttpRequest(params), headers, timeout, false, currentTimeMillis);
			case PUT:
				return put(action, new HttpRequest(params), headers, timeout, false, currentTimeMillis);
			case DELETE:
				return delete(action, params, headers, timeout, currentTimeMillis);
			case OPTIONS:
				return options(action, params, headers, timeout, currentTimeMillis);
			case TRACE:
				return trace(action, timeout, headers, currentTimeMillis);
			default:
				throw new HttpComponentException("Request method not supported.");
		}

	}

	public static HttpMethodBase sendAPIRequest(RequestMethod requestMethod, String action, String contentType, String encoding, String body, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, PostFailedException, PutFailedException {
		if(requestMethod == null)
			throw new HttpComponentException("Request method is null");

		long currentTimeMIllis = System.currentTimeMillis();
		switch (requestMethod){
			case POST:
				return post(action, new HttpRequest(body, contentType, encoding), headers, timeout, true, currentTimeMIllis);
			case PUT:
				return put(action, new HttpRequest(body, contentType, encoding), headers, timeout, true, currentTimeMIllis);
			default:
				throw new HttpComponentException("Request method not supported with raw body.");
		}
	}

	/**
	 *	Retrieve a data
	 * @param action
	 * @param params
	 * @param headers
	 * @param timeoutSeconds
	 * @param tracker
	 * @return
	 * @throws GetFailedException
     */
	public static GetMethod get(String action, Map<String, String> params, Map<String, String> headers, Integer timeoutSeconds, long tracker) throws GetFailedException {

		GetMethod get = new GetMethod(generateGetURL(action, params));
//		GetMethod get = new GetMethod(action);
//		setHttpMethodBaseParams(get, params, tracker);
		setHttpMethodBaseHeaders(get, headers, tracker);
		try {
			log.info("Generated URL[" + get.getURI() + "], timeout[" + timeoutSeconds + "] at " + tracker);
			createHttpClient(timeoutSeconds).executeMethod(get);
			return get;
		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new GetFailedException();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new GetFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new GetFailedException(e.getMessage());
		}

	}

	/**
	 * HTTP HEAD request is checking if a given url is serviceable, a given file exists, etc..
	 *
	 * @param action
	 * @param params
	 * @param headers
	 * @param timeoutSeconds
	 * @param tracker
	 * @return
	 * @throws HeadFailedException
     */
	public static HeadMethod head(String action, Map<String, String> params, Map<String, String> headers, Integer timeoutSeconds, long tracker) throws HeadFailedException {

		HeadMethod head = new HeadMethod(action);

		setHttpMethodBaseParams(head, params, tracker);
		setHttpMethodBaseHeaders(head, headers, tracker);

		try {
			log.info("Head URL[" + head.getURI() + "], timeout[" + timeoutSeconds + "] at " + tracker);
			createHttpClient(timeoutSeconds).executeMethod(head);
			return head;
		} catch (HttpException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new HeadFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new HeadFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new HeadFailedException(e.getMessage());
		}
	}

	/**
	 * Save a record. For user generated
	 * @param action
	 * @param httpRequest
	 * @param headers
	 * @param timeoutSeconds
	 * @param isRaw
	 * @param tracker
	 * @return
	 * @throws PostFailedException
     */
	public static PostMethod post(String action, HttpRequest httpRequest, Map<String, String> headers, Integer timeoutSeconds, boolean isRaw, long tracker) throws PostFailedException, UnsupportedEncodingException {

		PostMethod post = new PostMethod(action);
		if(isRaw){
			setRawRequest(post, httpRequest.getRaw(), httpRequest.getContentType(), httpRequest.getEncoding(), tracker);
		}else{
			if (MapUtils.isNotEmpty(httpRequest.getParams())) {
				String paramLog = post.getName() + " params[";
				for (String key: httpRequest.getParams().keySet()) {
					String value = httpRequest.getParams().get(key);
					paramLog += "{key: " + key + ", value:" + value + "}";
					post.setParameter(key, value);
				}
				log.info(paramLog + "] at " + tracker);
			}
		}

		setHttpMethodBaseHeaders(post, headers, tracker);

		try {
			log.info("Post URL[" + post.getURI() + "], timeout[" + timeoutSeconds + "] at " + tracker);
			createHttpClient(timeoutSeconds).executeMethod(post);
			return post;
		} catch (HttpException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new PostFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new PostFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new PostFailedException(e.getMessage());
		}
	}

	/**
	 * PUT is used for ADD or UPDATE method
	 * @param action
	 * @param httpRequest
	 * @param headers
	 * @param timeout
	 * @param isRaw
	 * @param tracker
	 * @return
     * @throws PutFailedException
     */
	public static PutMethod put(String action, HttpRequest httpRequest, Map<String, String> headers, Integer timeout, boolean isRaw, long tracker) throws PutFailedException, UnsupportedEncodingException {
		PutMethod put = new PutMethod(action);

		if(isRaw){
			setRawRequest(put, httpRequest.getRaw(), httpRequest.getContentType(), httpRequest.getEncoding(), tracker);
		}else{
			setHttpMethodBaseParams(put, httpRequest.getParams(), tracker);
		}
		setHttpMethodBaseHeaders(put, headers, tracker);

		try {
			log.info("Put URL[" + put.getURI() + "], timeout[" + timeout + "] at " + tracker);
			createHttpClient(timeout).executeMethod(put);
			return put;
		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new PutFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new PutFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new PutFailedException(e.getMessage());
		}
	}

	/**
	 * Delete request that a resource be removed
	 * @param url
	 * @param params
	 * @param headers
	 * @param timeout
	 * @param tracker
	 * @return
	 * @throws DeleteFailedException
     */
	public static DeleteMethod delete(String url, Map<String, String> params, Map<String, String> headers, Integer timeout, long tracker) throws DeleteFailedException {
		DeleteMethod delete = new DeleteMethod(url);
		setHttpMethodBaseParams(delete, params, tracker);
		setHttpMethodBaseHeaders(delete, headers, tracker);

		try {
			log.info("Delete URL[" + delete.getURI() + "], timeout[" + timeout + "] at " + tracker);
			createHttpClient(timeout).executeMethod(delete);
			return delete;
		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new DeleteFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new DeleteFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new DeleteFailedException(e.getMessage());
		}
	}

	/**
	 * Options is used to determine server availability. used to describe the communication options for the target resource.
	 * @param url
	 * @param params
	 * @param headers
	 * @param timeout
	 * @param tracker
	 * @return
	 * @throws OptionsFailedException
     */
	public static OptionsMethod options(String url, Map<String, String> params, Map<String, String> headers, Integer timeout, long tracker) throws OptionsFailedException{
		OptionsMethod options = new OptionsMethod(url);
		setHttpMethodBaseParams(options, params, tracker);
		setHttpMethodBaseHeaders(options, headers, tracker);

		try {
			log.info("Options URL[" + options.getURI() + "], timeout[" + timeout + "] at " + tracker);
			createHttpClient(timeout).executeMethod(options);
			return options;
		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new OptionsFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new OptionsFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new OptionsFailedException(e.getMessage());
		}
	}

	/**
	 * Options is used to determine server availability. used to describe the communication options for the target resource.
	 * @param url
	 * @param headers
	 * @param timeout
	 * @param tracker
	 * @return
	 * @throws TraceFailedException
     */
	public static TraceMethod trace(String url,  Integer timeout, Map<String, String> headers, long tracker) throws TraceFailedException{
		TraceMethod trace = new TraceMethod(url);
		setHttpMethodBaseHeaders(trace, headers, tracker);

		try {
			log.info("Trace URL[" + trace.getURI() + "], timeout[" + timeout + "] at " + tracker);
			createHttpClient(timeout).executeMethod(trace);
			return trace;
		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new TraceFailedException(e.getMessage());
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
			throw new TraceFailedException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
			throw new TraceFailedException(e.getMessage());
		}
	}

	/**
	 * This method will be used to only get status code 200 and throw exception when other status was found.
	 * @param response
	 * @return
	 * @throws HttpComponentException
	 */
	public static String getSuccessResponse(HttpResponse response) throws HttpComponentException {
		if(response.getStatusCode().intValue() == Constants.HTTP_SUCCESS_STATUS.intValue()){
			return response.getResponse();
		}
		throw new HttpComponentException("The " + response.getMethodName() + " result status is not OK. code[" + response.getStatusCode()+ "], response[ " + response.getResponse()+ "]");
	}

	/**
	 * Will match the received http call to the expected code. Else will throw exception
	 * @param response
	 * @param code
	 * @return
	 * @throws HttpComponentException
	 */
	public static String getResponse(HttpResponse response, int code) throws HttpComponentException {
		if(response.getStatusCode() == code)
			return response.getResponse();

		throw new HttpComponentException("The " + response.getMethodName() + " result status is not OK. Expecting code[" + code + "] Received: code[" + response.getStatusCode()+ "], response[ " + response.getResponse()+ "]");
	}

	/**
	 * This will throw an exception if the status code is not in the validRange param
	 * @param validRange
	 * @return
	 */
	public static String getResponseValidRange(HttpResponse response, int[] validRange) throws HttpComponentException {
		if(response.getStatusCode() > validRange[0] && response.getStatusCode() < validRange[1]){
			return response.getResponse();
		}
		throw new HttpComponentException("The " + response.getMethodName() + " result status is not OK. Validrange[" + validRange[0] + ", " + validRange[1] + "] code[" + response.getStatusCode()+ "], response[ " + response.getResponse()+ "]");
	}

	/**
	 * Will match all the status codes to the received http call. else will throw exception
	 * @param response
	 * @param statusCodes
	 * @return
	 * @throws HttpComponentException
	 */
	public static String getResponseMatchCode(HttpResponse response, int[] statusCodes) throws HttpComponentException {
		if(statusCodes != null){
			for(int i: statusCodes){
				if(response.getStatusCode() == i)
					return response.getResponse();
			}
		}
		throw new HttpComponentException("The " + response.getMethodName() + " result status is not OK. Valid status codes[" + ArrayUtils.toString(statusCodes) + "] code[" + response.getStatusCode()+ "], response[ " + response.getResponse()+ "]");
	}

	public static String generateGetURL(String action, Map<String, String> params) {
		String url = "";
		if (StringUtils.isBlank(action))
			return url;
		if (MapUtils.isNotEmpty(params)) {
			url = action + "?";
			for(String key : params.keySet()) {
				String value = params.get(key);
				try {
					value = URLEncoder.encode(value, Constants.ENCODING_UTF8);
				} catch (Exception e) {
					log.error(e.getStackTrace());
					e.printStackTrace();
				}
				// 如果值为空，则无需传递
				if (StringUtils.isBlank(value))
					continue;
				if (url.endsWith("?"))
					url = url + key + "=" + value;
				else
					url = url + "&" + key + "=" + value;
			}
		} else {
			url = action;
		}
		return url;
	}

	public static String generateGetURLObj(String action, Map<String, Object> params) {
		return generateGetURL(action, convertMap(params));
	}

	public static String appendGetParams(String action, Map<String, String> params) {

		String url = "";
		if (action == null)
			return url;

		if (MapUtils.isNotEmpty(params)) {
			for (String key: params.keySet()) {
				String value = params.get(key);
				try {
					value = URLEncoder.encode(value, Constants.ENCODING_UTF8);
				} catch (Exception e) {
					log.error(e.getStackTrace());
					e.printStackTrace();
				}

				if (StringUtils.isBlank(value))
					continue;

				url = url + "&" + key + "=" + value;
			}
		} else {
			url = action;
		}
		return url;
	}

	private static Map<String, String> convertMap(Map<String, Object> param){
		Map<String, String> map = new HashMap<>();
		if(MapUtils.isNotEmpty(param)) {
			for (String key : param.keySet()) {
				Object value = param.get(key);
				if (value != null)
					map.put(key, value.toString());
			}
		}
		return map;
	}

	private static HttpClient createHttpClient(Integer seconds) {
		Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);

		HttpClient httpclient = new HttpClient();
		HttpClientParams params = new HttpClientParams();
		params.setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
		params.setParameter("http.protocol.content-charset", Constants.ENCODING_UTF8);

		//default is 30 seconds if [seconds] is empty
		params.setParameter("http.socket.timeout", seconds != null? seconds * 1000 : Constants.HTTP_TIMEOUT * 1000);
		httpclient.setParams(params);
		return httpclient;
	}

	private static void setRawRequest(EntityEnclosingMethod entityEnclosingMethod, String body, String contentType, String encoding, long tracking) throws UnsupportedEncodingException {
		log.info(entityEnclosingMethod.getName() + " request content[" + body + "], contentType[" + contentType + "], encoding[" + encoding + "] at " + tracking);
		entityEnclosingMethod.setRequestEntity(new StringRequestEntity(body, contentType, encoding));
	}

	private static void setHttpMethodBaseHeaders(HttpMethodBase httpMethodBase, Map<String, String> headers, long tracking){
		if (MapUtils.isNotEmpty(headers)) {
			String paramLog = httpMethodBase.getName() + " headers[";
			for(String key: headers.keySet()) {
				String value = headers.get(key);
				paramLog += "{key: " + key + ", value:" + value + "}";
				httpMethodBase.addRequestHeader(key, value);
			}
			log.info(paramLog + "] at " + tracking);
		}
	}

	private static void setHttpMethodBaseParams(HttpMethodBase httpMethodBase, Map<String, String> params, long tracking){
		if (MapUtils.isNotEmpty(params)) {
			HttpMethodParams httpParams = new HttpMethodParams();
			String paramLog = httpMethodBase.getName() + " params[";
			for (String key: params.keySet()) {
				String value = params.get(key);
				paramLog += "{key: " + key + ", value:" + value + "}";
				httpParams.setParameter(key, value);
			}
			log.info(paramLog + "] at " + tracking);
			httpMethodBase.setParams(httpParams);
		}
	}
}