package Domein.ProjectPackage;

import Domein.StudentPackage.Student;
import Utils.ApplicationException;
import java.util.Collection;
import java.util.Map;

import Domein.Cache;

import Persistentie.SqlProjectProvider;


public class ProjectBeheerder
{
    public ProjectBeheerder()
    {
    }
    
    public Map<Integer, Project> GetProjectenDictionary()
    {
        Map<Integer, Project> project_alle;
        try
        {
            project_alle = (Map<Integer, Project>)Cache.getInstance().getFromCache("project_alle");
        }
        catch(NullPointerException ex)
        {
            project_alle = SqlProjectProvider.getInstance().GetProjecten();
            Cache.getInstance().putInCache("project_alle",project_alle);
        }
        return project_alle;
    }
    
    public Collection<Project> GetProjecten()
    {
        return GetProjectenDictionary().values();
    }
    
    public Map<Integer, Project> GetProjectenDictionaryByStudent(String studentnr)
    {   Map<Integer, Project> project_student ;
        try
        {
            project_student = (Map<Integer, Project>)Cache.getInstance().getFromCache("project_"+studentnr);
        }
        catch(NullPointerException ex)
        {
            Student student = null;
            try
            {
                student = (Student) Cache.getInstance().getFromCache("student_" + studentnr);
            }
            catch(NullPointerException ex1)
            {
                //student = SqlStudentProvider.getInstance().GetStudentByID(studentnr);
                Cache.getInstance().putInCache("student_" + studentnr,student);
            }
            project_student = SqlProjectProvider.getInstance().GetProjectenByStudent(student);
            Cache.getInstance().putInCache("project_" + studentnr,project_student);
        }
        
        return project_student;
    }
    
    public Collection<Project> GetProjectenByStudent(String studentnr)
    {
        return GetProjectenDictionaryByStudent(studentnr).values();
    }
    
    public Map<Integer, ProjectOpgave> GetOpgavenByProjectIdDictionary(int projectid)
    {
        Map<Integer, ProjectOpgave> projectopgave_projectid;
        try
        {
            projectopgave_projectid = (Map<Integer, ProjectOpgave>)Cache.getInstance().getFromCache("projectopgave_projectid="+projectid);
        }
        catch(NullPointerException ex)
        {
            projectopgave_projectid = SqlProjectProvider.getInstance().GetProjectOpgavenByProjectID(projectid);
            Cache.getInstance().putInCache("projectopgave_projectid=" + projectid,projectopgave_projectid);
        }
        return projectopgave_projectid;
    }
    public Collection<ProjectOpgave> GetOpgavenByProjectId(int projectid)
    {
        return GetOpgavenByProjectIdDictionary(projectid).values();
    }
    
    public Map<Integer, ProjectOpgave> GetOpgavenByProjectIdDictionaryForStudent(int projectid)
    {
        int userid = 0;//Integer.Parse(HttpContext.Current.Session["username"].ToString());
        Map<Integer, ProjectOpgave> projectopgave_projectid;
        try
        {
            projectopgave_projectid = (Map<Integer, ProjectOpgave>)Cache.getInstance().getFromCache("opgaveprojectstudent=" + projectid);
        }
        catch(NullPointerException ex)
        {
            projectopgave_projectid = SqlProjectProvider.getInstance().GetProjectOpgavenByProjectIDForStudent(projectid, userid);
            Cache.getInstance().putInCache("opgaveprojectstudent=" + projectid,projectopgave_projectid);
        }
        return projectopgave_projectid;
    }
    public Collection<ProjectOpgave> GetOpgavenByProjectIdForStudent(int projectid)
    {
        return GetOpgavenByProjectIdDictionaryForStudent(projectid).values();
    }
    
    public ProjectOpgave GetOpgaveByProjectID_OpgaveID(int projectid,int opgaveid)
    {
        Map<Integer,ProjectOpgave> op =  GetOpgavenByProjectIdDictionary(projectid);
        if (op.containsKey(opgaveid))
        {
            return op.get(opgaveid);
        }
        else
        {
            return SqlProjectProvider.getInstance().GetProjectOpgaveByID(opgaveid);
        }
    }
    
    /** KRS: CODE AANGEPAST*/
    public Boolean MakeProjectOpgave(ProjectOpgave opgave) throws ApplicationException
    {
        Cache.getInstance().purgeCache("projectopgave_projectid=" + opgave.getProjectID());
        if (normalValidation(opgave))
        {
            return SqlProjectProvider.getInstance().InsertProjectOpgave(opgave);
        }
        else
        {
            throw new ApplicationException("de validatie van de opgave is niet correct verlopen");
        }
    }
    
    public Boolean UpdateProjectOpgave(ProjectOpgave opgave) throws ApplicationException
    {
        if (validatie(opgave))
        {
            Cache.getInstance().purgeCache("projectopgave_projectid=" + opgave.getProjectID());
            return SqlProjectProvider.getInstance().UpdateProjectOpgave(opgave);
        }
        else
        {
            throw new ApplicationException("de validatie van de opgave is niet correct verlopen");
        }
    }
    
    
    
    public Boolean DeleteProjectOpgave(ProjectOpgave opgave) throws ApplicationException
    {
        if (opgave.getProject().inschrijvingBegonnen()
        && !opgave.getProject().inschrijvingGedaan())
        {
            
            throw new ApplicationException("De applicatie weigert de opgave te verwijderen! Gelieve de inschrijvingsperiode aan te passen of geduld te oefenen tot deze verlopen is!");
            
        }
        else
        {
            if (validatie(opgave))
            {
                Cache.getInstance().purgeCache("projectopgave_projectid=" + opgave.getProjectID());
                return SqlProjectProvider.getInstance().DeleteProjectOpgave(opgave.getOpgaveId());
            }
            throw new ApplicationException("Fout in de de validatie van een opgave die wordt verwijderdt!");
        }
    }
    public Boolean HardDeleteProjectOpgave(ProjectOpgave opgave) throws ApplicationException
    {
        if (validatie(opgave))
        {
            Cache.getInstance().purgeCache("projectopgave_projectid=" + opgave.getProjectID());
            return SqlProjectProvider.getInstance().HardDeleteProjectOpgave(opgave.getOpgaveId());
        }
        else
        {
            throw new ApplicationException("de validatie van de opgave is niet correct verlopen");
        }
    }
    
    private Boolean validatie(ProjectOpgave opgave) throws ApplicationException
    {
        //Problement met cache. Bij het veranderen, verandert die alle nullpointers. Voorlopige oplossing, is het rechtstreeks ophalen.: Oplossing: bij het doorgeven van het object bij editmode, geef je een kopie door.
        ProjectOpgave vorigeOpgave = this.GetOpgaveByProjectID_OpgaveID(opgave.getProjectID(), opgave.getOpgaveId());//SqlProjectProvider.getInstance().GetProjectOpgaveByID(opgave.getOpgaveId());// 
        if (opgave.getProject().inschrijvingBegonnen())
        {
            if (opgave.getAantalGroepen() < vorigeOpgave.getAantalGroepen())
            { throw new ApplicationException("Je mag de aantal groepen(" + opgave.getAantalGroepen() + ") niet meer verkleinen. Uw vorige waarde was:" + vorigeOpgave.getAantalGroepen()); }
            if (opgave.getAantalStudentenPerGroep() != vorigeOpgave.getAantalStudentenPerGroep())
            { throw new ApplicationException("Je mag het aantal studenten per groep(" + opgave.getAantalStudentenPerGroep() + ") niet meer wijzigen. Uw vorige waarde was:" + vorigeOpgave.getAantalStudentenPerGroep()); }
        }
        return normalValidation(opgave);
        
    }
    private Boolean normalValidation(ProjectOpgave opgave) throws ApplicationException
    {
        if (opgave.getAantalGroepen() < 0)
        { throw new ApplicationException("Aantal groepen mogen niet < 0; Uw waarde was:" + opgave.getAantalGroepen()); }
        if (opgave.getAantalStudentenPerGroep() < 0)
        { throw new ApplicationException("Aantal studenten per groep mogen niet < 0;Uw waarde was:" + opgave.getAantalStudentenPerGroep()); }
        if (opgave.getOpgaveTitel().equals(""))
        { throw new ApplicationException("Er moet een opgavetitel bekend zijn. U heeft een lege titel gegeven"); }
        
        if (opgave.getProject() == null)
        {
            throw new ApplicationException("Er moet voor een project gekozen zijn! U koos niet voor een project!");
        }
        return true;
    }
    /** KRS CODE GESTOPT AANGEPAST*/
    public Map<Integer,ProjectLuik> GetProjectLuikByProjectID(int projectid)
    {
        Map<Integer,ProjectLuik> projectluik;
        try
        {
            projectluik = (Map<Integer,ProjectLuik>)Cache.getInstance().getFromCache("projectluik_projectid=" + projectid);
        }
        catch(NullPointerException ex)
        {
            projectluik = SqlProjectProvider.getInstance().GetProjectLuikenByProjectID(projectid);
            Cache.getInstance().putInCache("projectluik_projectid=" + projectid,projectluik);
        }
        return projectluik;
    }
}
