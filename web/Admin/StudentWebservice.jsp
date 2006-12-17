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
<f:view>
    <h:form>
                    <h:commandButton  styleClass="button" actionListener="#{studentInfo.studentenFromWebservice}" rendered="#{viewProjectOpgave.insertMode}"  value="Haal gegevens op" />          
                    <h:dataTable value="#{studentInfo.list}" 
                                 var="po">        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="naam"></h:outputText>
                            </f:facet>
                                <h:outputText value="#{po.naam}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="studentNr"></h:outputText>
                            </f:facet>
                                <h:outputText value="#{po.studentnr}"/>
                        </h:column>
                    </h:dataTable>
                    <h:messages styleClass="Validation" id="fout"></h:messages>
    </h:form>
</f:view>

<%@ include file="../MasterPage/bottom.html" %>