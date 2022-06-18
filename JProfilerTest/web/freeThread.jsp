<%@ page language="java" import="cn.edu.zjut.jprofiler.test.TestCPU" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>free</title>
	</head>
	<body>
        <%
        for(int i=0; i<cn.edu.zjut.jprofiler.test.TestCPU.threadList.size(); i++){
			cn.edu.zjut.jprofiler.test.TestCPU.free(cn.edu.zjut.jprofiler.test.TestCPU.threadList.get(i));
		}
		cn.edu.zjut.jprofiler.test.TestCPU.threadList.clear();
        %>
        All threads killed! <br />
        Thread Counter:<%=cn.edu.zjut.jprofiler.test.TestCPU.threadList.size()%>
    </body>
</html>