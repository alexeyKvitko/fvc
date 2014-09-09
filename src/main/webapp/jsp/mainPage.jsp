<%@page contentType="text/html"  pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html">
<html>
	<spring:message code="application.title" var="appTitle"/>
  <head>    
	<meta charset=utf-8>
		<meta http-equiv="Cache-Control" content="no-store" />
		<title>${appTitle}</title>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/app.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/project.css"  />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/msg.css"  />
    <script type="text/javascript">
      var CONTEXT_PATH ="<%=request.getContextPath()%>/";
    </script>
   
   <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all-debug.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/messages.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/msg.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/app.js"></script>
    
 </head>
 
<body style="background-color: #f0f4fb ">
	<div id='mainDiv' class='centerDiv'>
	</div>
</body>
