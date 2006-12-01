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
        <title>PAS: ProjectOpgave</title>
    </head>
    <body>

    <h1>ProjectOpgave</h1>
    <f:view>
        <h:form>
   <h:selectOneMenu 
       id="ddlProjecten" 
       immediate="true" 
       onchange="this.form.submit();" 
       valueChangeListener="#{viewProjectOpgave.ddlProject.ddlProjectChanged}" 
       value="#{viewProjectOpgave.ddlProject.selectedProjectID}" >
            <f:selectItems value="#{viewProjectOpgave.ddlProject.projecten}"/>
    </h:selectOneMenu>
    <br/><br/>
          
        <div align="center">...ProjectOpgaven...</div> 
        <h:dataTable value="#{viewProjectOpgave.projectOpgaveBySelectedProjectID}" 
                      var="po" rendered="#{viewProjectOpgave.ddlProject.selectedProjectID>-1}" >        
           <h:column>
                <f:facet name="header">
                    <h:outputText value="Titel"></h:outputText>
                </f:facet>
                <h:outputText value="#{po.opgaveTitel}"/>                  
            </h:column>
            <h:column>
                <f:facet name="header" >
                    <h:outputText value="Omschrijving"></h:outputText>
                </f:facet>
                <h:outputText value="#{po.korteOmschrijving}"/>                  
            </h:column>
            <h:column>
                <f:facet name="header" >
                    <h:outputText value="Aantal Groepen"></h:outputText>
                </f:facet>
                <h:outputText value="#{po.aantalGroepen}"/>                  
            </h:column>
            <h:column>
                <f:facet name="header" >
                    <h:outputText value="Aantal studenten / groep"></h:outputText>
                </f:facet>
                <h:outputText value="#{po.aantalStudentenPerGroep}"/>                  
            </h:column>
           <h:column>
              <h:commandLink value="Edit" actionListener="#{viewProjectOpgave.editCurrentRow}"> <!--mag geen immediate=true zijn! Omdat anders de pagina niet goed gerenderd wordt!-->
                      <f:param  name="selectRow" value="#{po.opgaveId}"></f:param>     
                  </h:commandLink>
           </h:column>
           <h:column>
                <h:commandLink actionListener="#{viewProjectOpgave.checkDeleteCurrentRow}" value="Delete">
                  <f:param  name="deleteRow" value="#{po.opgaveId}"></f:param>     
                </h:commandLink>
            </h:column>
        </h:dataTable>
        <br/>
            <h:commandButton rendered="#{viewProjectOpgave.insertMode &&  viewProjectOpgave.ddlProject.selectedProjectID>-1}" actionListener="#{viewProjectOpgave.toggleInsertMode}"  value="Maak projectOpgave"/>
        <br/>
            <f:subview id="drilldown" rendered="#{viewProjectOpgave.drilldownTableVisible}">
            <h:outputText value="Titel:"/> <h:inputText  id="inputTitel"  value="#{viewProjectOpgave.selectedProjectOpgave.opgaveTitel}" ></h:inputText><br/>
            <h:outputText value="Korte Omschrijving:"/> <h:inputText id="inputOmschrijving" value="#{viewProjectOpgave.selectedProjectOpgave.korteOmschrijving}"></h:inputText><br/>
            <h:outputText value="Aantal Groepen:"/>  <h:inputText id="inputAantalGroepen" value="#{viewProjectOpgave.selectedProjectOpgave.aantalGroepen}"></h:inputText><br/>
            <h:outputText value="Aantal Studenten Per Groep:"/> <h:inputText id="inputStudenten" value="#{viewProjectOpgave.selectedProjectOpgave.aantalStudentenPerGroep}"></h:inputText><br/>
            <h:commandLink actionListener="#{viewProjectOpgave.checkEdit}" rendered="#{viewProjectOpgave.editMode}"  value="Edit"/>
            <h:commandLink actionListener="#{viewProjectOpgave.checkInsert}" rendered="#{viewProjectOpgave.insertMode}"  value="Insert" />
          
            <h:commandLink actionListener="#{viewProjectOpgave.cancel}"  value="Cancel"/>             
            </f:subview>
         <br/>
            <h:messages id="fout"></h:messages>
        <br/>
        
        
    <br/><br/>

    
        </h:form>
        
    </f:view>    
    </body>
</html>
