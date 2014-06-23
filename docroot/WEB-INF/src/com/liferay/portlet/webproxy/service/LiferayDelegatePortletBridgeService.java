/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

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
