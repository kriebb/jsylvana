/*
 * StudentInfo.java
 *
 * Created on 13 december 2006, 13:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import Domein.Cache;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Kristof
 */
public class ViewWebservice
{   
    private List<managedBean.StudentInfo> list;
    /** Creates a new instance of StudentInfo */
    public ViewWebservice()
    {
        list = new ArrayList<managedBean.StudentInfo>();
    }
    public void studentenFromWebservice(ActionEvent e)
    {
        try { // Call Web Service Operation

                try
                {
                    list = (List<managedBean.StudentInfo>)Cache.getInstance().getFromCache("list_xml");
                }
                catch(NullPointerException ex)
                {
                    list = new ArrayList<managedBean.StudentInfo>();
                    managedBean.StudentService service = new managedBean.StudentService();
                    managedBean.StudentServiceSoap port = service.getStudentServiceSoap12();
                    list = port.getStudenten().getStudentInfo();
                    Cache.getInstance().putInCache("list_xml",list);
                }           
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("gelukt!"));            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));            
            ex.printStackTrace();
        }         FacesContext.getCurrentInstance().renderResponse();
    }
    public List<managedBean.StudentInfo> getList()
    {
        return list;
    }
}
