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

<%@ include file="../MasterPage/top.html" %>
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
    <br />
    <br />
    <!--Moet die immediate daar staan?-->      
        <h:selectOneMenu id="ddlProjectOpgaven" 
        immediate="true" 
        rendered="#{docentTeam.ddlProjectOpgave.selectedProjectID > -1}"
        onchange="this.form.submit();" 
        valueChangeListener="#{docentTeam.ddlProjectOpgave.ddlProjectOpgaveChanged}" 
        value="#{docentTeam.ddlProjectOpgave.selectedProjectOpgaveID}" >
            <f:selectItems value="#{docentTeam.ddlProjectOpgave.projectOpgaven}"/>
        </h:selectOneMenu>
        <br />
        <br />
        <h:selectOneMenu id="ddlDocentTeams"
                         immediate="true"
                         rendered="#{docentTeam.selectDocentTeamID> -1}"
                         onchange="this.form.submit();"
                         valueChangeListener="#{docentTeam.ddlDocentTeamsChanged}"
                         value="#{docentTeam.selectDocentTeamID}" >
                         <f:selectItems value="#{docentTeam.docentTeams}"/>
                          
        </h:selectOneMenu>
        <br/>
        <h:commandButton styleClass="button" value="Nieuw docentteam" action="nieuw" />
        <h:commandButton styleClass="button" value="verwijder docentteam" action="verwijder" />
        <br/>
         <%--   <h:dataTable>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Projectluik"/>
                    </f:facet>
                    <f:selectItems />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:selectOneMenu id="ddlDocenten">
                            <f:selectItems />
                        </h:selectOneMenu>
                    </f:facet>
                </h:column>
            </h:dataTable> --%>
        <br/>
        <h:commandButton styleClass="button" value="Update" action="update" />
        </h:form>
    </f:view>
<%@ include file="../MasterPage/bottom.html" %>