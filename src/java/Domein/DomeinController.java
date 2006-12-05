package Domein;


import Domein.DocentPackage.DocentBeheerder;
import Domein.ProjectPackage.ProjectBeheerder;


public class DomeinController
{
    private DocentBeheerder docentBeheerder;
    private ProjectBeheerder projectBeheerder;
    //private StudentBeheerder studentBeheerder;

    private DomeinController()
    {           
    }

    private static DomeinController instance=null;

    public static DomeinController getInstance()
    {
            if (instance == null)
            {
                instance = new DomeinController();
            }
            return instance;
    }

    public ProjectBeheerder getProjectBeheerder()
    {
            if (projectBeheerder == null)
            {
                projectBeheerder = new ProjectBeheerder();
            }
            return projectBeheerder;
    }

   /* public StudentBeheerder getStudentBeheerder()
    {
            if (studentBeheerder == null)
            {
                studentBeheerder = new StudentBeheerder();
            }
            return studentBeheerder;
    }
*/
    public DocentBeheerder getDocentBeheerder()
    {
            if (docentBeheerder == null)
            {
                docentBeheerder = new DocentBeheerder();
            }
            return docentBeheerder;
    }
}
