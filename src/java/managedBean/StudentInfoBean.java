/*
 * StudentInfo.java
 *
 * Created on 13 december 2006, 13:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Kristof
 */
public class StudentInfoBean
{   
    private List<managedBean.StudentInfo> list;
    /** Creates a new instance of StudentInfo */
    public StudentInfoBean()
    {
        list = new ArrayList<StudentInfo>();
    }
    public void studentenFromWebservice(ActionEvent e)
    {

        try { // Call Web Service Operation
            StudentService service = new StudentService();            
            StudentServiceSoap port = service.getStudentServiceSoap12();
            list = port.getStudenten().getStudentInfo();
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }         FacesContext.getCurrentInstance().renderResponse();
    }
    public List<StudentInfo> getList()
    {
        return list;
    }
    public static void main(String args[])
    {
        try { // Call Web Service Operation
            StudentService service = new StudentService();
            StudentServiceSoap port = service.getStudentServiceSoap12();
            Iterator<managedBean.StudentInfo> inf = (port.getStudenten().getStudentInfo().iterator());
            while(inf.hasNext())
            {
                StudentInfo infje = inf.next();
                System.out.println(infje.getNaam());
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    }
}
