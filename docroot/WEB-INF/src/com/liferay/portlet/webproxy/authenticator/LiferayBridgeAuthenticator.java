package com.liferay.portlet.webproxy.authenticator;

import java.io.Serializable;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.portletbridge.PortletBridgeException;
import org.portletbridge.ResourceException;
import org.portletbridge.portlet.BridgeAuthenticator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class LiferayBridgeAuthenticator implements BridgeAuthenticator, Serializable{

	@Override
	public Credentials getCredentials(RenderRequest request)
			throws ResourceException {
        PortletPreferences preferences = request.getPreferences();
        String configAuthentication = preferences.getValue("authentication",
                "none");
        String configAuthenticationUsername = getUserName(request, preferences.getValue(
                "authenticationUsername", null));
        String configAuthenticationPassword = getPassword(request, preferences.getValue(
                "authenticationPassword", null));
        String configAuthenticationHost = preferences.getValue(
                "authenticationHost", null);
        String configAuthenticationDomain = preferences.getValue(
                "authenticationDomain", null);
        if (configAuthentication != null
                && configAuthentication.trim().length() > 0) {
            if ("ntlm".equalsIgnoreCase(configAuthentication)) {
                return new NTCredentials(configAuthenticationUsername,
                        configAuthenticationPassword, configAuthenticationHost,
                        configAuthenticationDomain);
            } else if ("basic".equalsIgnoreCase(configAuthentication)) {
                return
                        new UsernamePasswordCredentials(
                                configAuthenticationUsername,
                                configAuthenticationPassword);
            } else if ("none".equalsIgnoreCase(configAuthentication)) {
                return null;
            } else {
                throw new PortletBridgeException("error.configAuthentication");
            }
        }
        return null;
	}

	protected String getPassword(
			PortletRequest portletRequest, String password) {

		if (Validator.isNull(password) || password.equals("@password@")) {
			password = PortalUtil.getUserPassword(portletRequest);

			if (password == null) {
				password = StringPool.BLANK;
			}
		}

		return password;
	}

	protected String getUserName(
			PortletRequest portletRequest, String userName) {

		User user = null;
		try {
			user = PortalUtil.getUser(portletRequest);
		} catch (PortalException e) {
			_log.warn(e);
		} catch (SystemException e) {
			_log.warn(e);
		}

		if (user == null) {
			return userName;
		}

		if (Validator.isNull(userName) || userName.equals("@user_id@")) {
			userName = portletRequest.getRemoteUser();
		}
		else if (userName.equals("@email_address@")) {
			userName = user.getEmailAddress();
		}
		else if (userName.equals("@screen_name@")) {
			userName = user.getScreenName();
		}

		return userName;
	}

	private static Log _log = LogFactoryUtil.getLog(LiferayBridgeAuthenticator.class);

}
