<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%> 
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAS: DocentTeams</title>
    </head>
    <body>

    <h1>DocentTeams</h1>
    <f:view>
        <h:form>
   <!--Moet die immediate daar staan?-->
            <h:selectOneMenu 
       id="ddlProjecten" 
       immediate="true" 
       onchange="this.form.submit();" 
       valueChangeListener="#{docentTeam.ddlProjectOpgave.ddlProjectChanged}" 
       value="#{docentTeam.ddlProjectOpgave.selectedProjectID}" >
            <f:selectItems value="#{docentTeam.ddlProjectOpgave.projecten}"/>
    </h:selectOneMenu>
    <br /><br />
    <!--Moet die immediate daar staan?-->      
        <h:selectOneMenu id="ddlProjectOpgaven" 
        immediate="true" 
        rendered="#{docentTeam.ddlProjectOpgave.selectedProjectID > -1}"
        onchange="this.form.submit();" 
        valueChangeListener="#{docentTeam.ddlProjectOpgave.ddlProjectOpgaveChanged}" 
        value="#{docentTeam.ddlProjectOpgave.selectedProjectOpgaveID}" >
            <f:selectItems value="#{docentTeam.ddlProjectOpgave.projectOpgaven}"/>
        </h:selectOneMenu>
    </c:if>
            
            
          
        </h:form>
    </f:view>    
    </body>
</html>
