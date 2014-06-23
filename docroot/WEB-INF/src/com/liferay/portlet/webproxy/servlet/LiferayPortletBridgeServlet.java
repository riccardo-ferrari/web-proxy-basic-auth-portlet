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

package com.liferay.portlet.webproxy.servlet;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.portletbridge.portlet.PortletBridgeServlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.webproxy.service.LiferayDelegatePortletBridgeService;

public class LiferayPortletBridgeServlet extends PortletBridgeServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Field _portletBridgeService = getClass().getSuperclass().getDeclaredField("portletBridgeService");
			_portletBridgeService.setAccessible(true);
			_portletBridgeService.set(this, new LiferayDelegatePortletBridgeService());
		} catch (NoSuchFieldException e) {
			_log.error(e);
		} catch (SecurityException e) {
			_log.error(e);
		} catch (IllegalArgumentException e) {
			_log.error(e);
		} catch (IllegalAccessException e) {
			_log.error(e);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	Log _log = LogFactoryUtil.getLog(getClass());
}
