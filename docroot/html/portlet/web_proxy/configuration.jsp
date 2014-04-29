<%--
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
--%>

<%@ include file="/html/portlet/web_proxy/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<aui:input autoFocus="<%= (windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP)) %>" cssClass="lfr-input-text-container" label="url" name="preferences--initUrl--" value="<%= initUrl %>" />

		<aui:input cssClass="lfr-input-text-container" label='<%= LanguageUtil.get(pageContext, "scope") + " (" + LanguageUtil.get(pageContext, "regex") + ")" %>' name="preferences--scope--" value="<%= scope %>" />

		<div class="alert alert-info" id="<portlet:namespace />currentLoginMsg">
			<c:choose>
				<c:when test="<%= true %>">
					<liferay-ui:message key="you-may-use-the-tokens-email-address-screen-name-userid-and-password" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="you-may-use-the-tokens-email-address-screen-name-userid" />
				</c:otherwise>
			</c:choose>
		</div>

		<aui:select name="preferences--authentication--">
			<aui:option label="none" selected='<%= authentication.equals("none") %>' />
			<aui:option label="basic" selected='<%= authentication.equals("basic") %>' />
			<aui:option label="ntlm" selected='<%= authentication.equals("ntlm") %>' />
		</aui:select>

		<aui:input cssClass="lfr-input-text-container" name="preferences--authenticationUsername--" value="<%= authenticationUsername %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--authenticationPassword--" value="<%= authenticationPassword %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--authenticationHost--" value="<%= authenticationHost %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--authenticationDomain--" value="<%= authenticationDomain %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyHost--" value="<%= proxyHost %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyPort--" value="<%= proxyPort %>" />

		<aui:select name="preferences--proxyAuthentication--">
			<aui:option label="none" selected='<%= proxyAuthentication.equals("none") %>' />
			<aui:option label="basic" selected='<%= proxyAuthentication.equals("basic") %>' />
			<aui:option label="ntlm" selected='<%= proxyAuthentication.equals("ntlm") %>' />
		</aui:select>

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationUsername--" value="<%= proxyAuthenticationUsername %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationPassword--" value="<%= proxyAuthenticationPassword %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationHost--" value="<%= proxyAuthenticationHost %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationDomain--" value="<%= proxyAuthenticationDomain %>" />

		<aui:input cssClass="lfr-textarea-container" name="preferences--stylesheet--" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= stylesheet %>" wrap="soft" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>