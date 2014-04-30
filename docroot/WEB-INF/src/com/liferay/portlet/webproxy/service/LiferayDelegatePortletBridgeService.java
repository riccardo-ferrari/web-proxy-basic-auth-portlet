package com.liferay.portlet.webproxy.service;

import org.portletbridge.portlet.PortletBridgeService;

public class LiferayDelegatePortletBridgeService implements PortletBridgeService {

	private static final String DELEGATE = "/delegate";

	@Override
	public String getIdFromRequestUri(String contextPath, String requestUri) {
        String path = contextPath.length() > 0 ? requestUri.substring(contextPath.length() - 1) : requestUri;
        if (path.startsWith(DELEGATE)) {
        	path = path.substring(DELEGATE.length());
        }
        int secondIndexOfSlash = path.indexOf('/', 2);
        int thirdIndexOfSlash = path.indexOf('/', secondIndexOfSlash + 1);
        if(secondIndexOfSlash >= 0 && thirdIndexOfSlash >= 0) {
            return path.substring(secondIndexOfSlash + 1, thirdIndexOfSlash);
        }
        return null;
	}

}
