<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.dianping.customer.report.biz.utils.LoginUtils" %>
<%@ page import="com.dianping.customer.report.biz.utils.ConfigUtils" %>
<%@ page import="com.dianping.customer.report.biz.utils.Beans" %>
<%@ page import="com.dianping.customer.report.biz.service.PermissionService" %>
<%@ page import="com.dianping.customer.report.biz.hr.dao.SFUserDao" %>
<%@ page import="com.dianping.customer.report.biz.entity.SFUser" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.collections.CollectionUtils" %>
<%@ page import="com.dianping.customer.report.biz.utils.StringUtils" %>
<!DOCTYPE html>
<html>

<%
    List<SFUser> userList = ((SFUserDao) Beans.getBean("sFUserDao")).getSFUserByLoginId(LoginUtils.getLoginId());
    Integer defaultCityId = null;
    String defaultCityName = null;
    if (CollectionUtils.isNotEmpty(userList)) {
        SFUser user = userList.get(0);
        defaultCityId = user.getCityId();
        defaultCityName = user.getCity();
    }
%>

<script>
    var ENV = {
        imageDomain: "<%=ConfigUtils.getImageDomain()   %>",
        path: "<%=ConfigUtils.getPath()   %>",
        logoutUrl: "<%=ConfigUtils.getLoginOutUrl() + "?service=" + URLEncoder.encode(ConfigUtils.getServerName(), "utf-8")%>",
        loginId: "<%=LoginUtils.getLoginId()   %>",
        loginName: "<%=LoginUtils.getLoginName()   %>",
        isSales: <%=((PermissionService) Beans.getBean("permissionService")).getRoleTreeNodeByLoginId(LoginUtils.getLoginId()) != null%>,
        defaultCityId: <%=(defaultCityId == null ? null :defaultCityId.intValue())%>,
        defaultCityName: <%=(StringUtils.isBlank(defaultCityName) ? null : "\"" + defaultCityName + "\"")%>
    }

</script>
<%

    //,"vendor/ckeditor/ckeditor","vendor/ckeditor/config"
    String[] jsArray = {"node_modules", "jquery-vendors", "services", "models", "modules", "dialogs", "index"};
    String[] cssArray = {"/vendor/bootstrap/css/bootstrap.min.css", "/vendor/bootstrap/css/bootstrap-theme.min.css", "/vendor/toastr/toastr.css", "/vendor/datepicker/css/datepicker3.css", "/asset/index.css", "/vendor/typeahead.js/typeahead.css"};

    String prefix = "";
    if (System.getProperty("isLocal") != null) {
        prefix = "http://localhost:3005/dist";
    } else {
        prefix = ConfigUtils.getJsPath() + ConfigUtils.getJsVersion();
    }
%>
<head>
    <meta charset="utf8">
    <%for (String css : cssArray) {%>
    <link href="<%=prefix+css%>" media="all" rel="stylesheet" type="text/css"/>
    <%}%>
    <%for (String js : jsArray) {%>
    <script src="<%=prefix+"/"+js+".js"%>"></script>
    <%}%>

</head>
<body>
<%
    if(System.getProperty("isLocal") == null) {
%>
<!-- feedback -->
<script src="/feedback/static/js/feedback.js"></script>
<script>
    require('backbone-history').on('change', function(fragment) {
        FB.update([9]);
    })
</script>
<!-- end -->
<%
    }
%>
</body>
</html>
