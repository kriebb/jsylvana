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
<%@taglib uri="http://sourceforge.net/projects/jsf-comp/clientvalidators" prefix="cv"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../MasterPage/top.html" %>
    <h1>ProjectOpgave</h1>
    <f:view>
        <h:form id="hoofd">
            <!--immidiate="true" -->
            <h:selectOneMenu 
                id="ddlProjecten" 
                onchange="this.form.submit();" 
                valueChangeListener="#{viewProjectOpgave.ddlProject.ddlProjectChanged}" 
                value="#{viewProjectOpgave.ddlProject.selectedProjectID}" >
                <f:selectItems value="#{viewProjectOpgave.ddlProject.projecten}"/>
            </h:selectOneMenu>
            <br/><br/>
            <h:outputText styleClass="Validation" rendered="#{viewProjectOpgave.beperkteEditModus}" value="U bevindt zich in beperkte Editmodus: Inschrijvingen zijn begonnen"/>
            <br/>
            <h:outputLabel styleClass="message" rendered="#{viewProjectOpgave.ddlProject.selectedProjectID>-1}" value="<div align='center'> ...Overzicht projectopgaven...</div>"/>
            <h:dataTable value="#{viewProjectOpgave.projectOpgaveBySelectedProjectID}" 
                         var="po" rendered="#{viewProjectOpgave.ddlProject.selectedProjectID>-1}" 
                         title="...ProjectOpgaven...">        
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
                    <h:commandLink value="<img alt='edit' src='../Images/View.gif' border='0'/>"  rendered="#{po.opgaveId != viewProjectOpgave.selectRow}" actionListener="#{viewProjectOpgave.editCurrentRow}"> <!--mag geen immediate=true zijn! Omdat anders de pagina niet goed gerenderd wordt!-->
                        <f:param  name="selectRow" value="#{po.opgaveId}"></f:param>     
                    </h:commandLink>
                    <h:graphicImage  value="../Images/View.gif" rendered="#{po.opgaveId == viewProjectOpgave.selectRow}"></h:graphicImage>
                </h:column>
                <h:column>
                    <h:commandButton onclick="if (confirm('Ben je zeker deze projectopgave te verwijderen?') == false) return false;" actionListener="#{viewProjectOpgave.checkDeleteCurrentRow}" image="../Images/icon-delete.gif">
                        <f:param  name="deleteRow" value="#{po.opgaveId}"></f:param>     
                    </h:commandButton>
                    
                </h:column>
            </h:dataTable>
            <h:commandButton styleClass="button" rendered="#{viewProjectOpgave.insertMode &&  viewProjectOpgave.ddlProject.selectedProjectID>-1}" actionListener="#{viewProjectOpgave.toggleInsertMode}"  value="Maak projectOpgave"/>
            <h:commandButton styleClass="button" rendered="#{!viewProjectOpgave.insertMode &&  viewProjectOpgave.ddlProject.selectedProjectID>-1 && viewProjectOpgave.hardDeleteEnabled}" actionListener="#{viewProjectOpgave.hardDelete}"  value="Hard delete"/>

        <br/>

        <br/>
        <f:subview id="drilldown" rendered="#{viewProjectOpgave.drilldownTableVisible &&  viewProjectOpgave.ddlProject.selectedProjectID>-1 && !viewProjectOpgave.projectVeranderd}">
            <h:outputLabel value="<div align='center'> ...Maak een projectopgave aan...</div>" styleClass="message" rendered="#{viewProjectOpgave.insertMode}"/>                             
            <h:outputLabel styleClass="message" rendered="#{viewProjectOpgave.editMode}" value="<div align='center'> ...Wijzig deze projectopgave aan...</div>"/>
            <table>
                    <tr>
                        <td><h:outputText value="Titel:"/></td>
                        <td><h:inputText  id="inputTitel"   value="#{viewProjectOpgave.selectedProjectOpgave.opgaveTitel}" ></h:inputText>
                        <cv:requiredFieldValidator componentToValidate="inputTitel" highlight="true" display="dynamic" errorMessage="Verplicht veld!"/>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Korte Omschrijving:"/></td>
                        <td><h:inputText id="inputOmschrijving" value="#{viewProjectOpgave.selectedProjectOpgave.korteOmschrijving}"></h:inputText>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Aantal Groepen:"/></td>
                        <td><h:inputText id="inputAantalGroepen" value="#{viewProjectOpgave.selectedProjectOpgave.aantalGroepen}"></h:inputText>
                            <cv:regularExpressionValidator componentToValidate="inputAantalGroepen" pattern="[0-9]" highlight="true" errorMessage="Enkel cijfers!"/>
 
                    </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Aantal Studenten Per Groep:"/></td>
                        <td><h:inputText id="inputStudenten" value="#{viewProjectOpgave.selectedProjectOpgave.aantalStudentenPerGroep}"></h:inputText></td>
                    </tr>
            </table>    
            <h:commandButton onclick="return validate();" actionListener="#{viewProjectOpgave.checkEdit}" rendered="#{viewProjectOpgave.editMode}"  image="../Images/icon-save.gif"/>
            <h:commandButton onclick="return validate();" actionListener="#{viewProjectOpgave.checkInsert}" rendered="#{viewProjectOpgave.insertMode}"  image="../Images/icon-save.gif" />          
            <h:commandLink actionListener="#{viewProjectOpgave.cancel}"  value="<img alt='cancel' src='../Images/icon-cancel.gif' border='0'/>"/>             
        
       
        </f:subview>
         <br/>
            <h:messages styleClass="Validation" id="fout"></h:messages>
        <br/>
            <cv:scriptGenerator form="hoofd" popup="false" />    
        </h:form>
             
    <br/><br/>
        
    </f:view>    
<%@ include file="../MasterPage/bottom.html" %>