<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page import="javax.servlet.jsp.jstl.core.Config"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld"  prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<%@ taglib uri="/WEB-INF/jflow.tld" prefix="jflow" %>

<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %>
<%   
	//request.setAttribute("mLocale", request.getLocale());
	//request.setAttribute("project_name","jflow");
%>
<fmt:setLocale value="${Config.FMT_LOCALE}" />
<fmt:setBundle basename="ApplicationResources" />
