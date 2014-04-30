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
