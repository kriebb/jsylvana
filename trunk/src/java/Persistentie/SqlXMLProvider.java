/*
 * NewClass.java
 *
 * Created on 17 december 2006, 20:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Persistentie;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class SqlXMLProvider
{
    
    /** Creates a new instance of NewClass */
    private SqlXMLProvider()
    {
       pool = SimpleConnectionPool.getInstance("jdbc:mysql:///projecten?zeroDateTimeBehavior=convertToNull", "kristof", "riebbels1","com.mysql.jdbc.Driver", 30);             
    }
    private static SqlXMLProvider thisje;
    private SimpleConnectionPool pool;
    
    public static SqlXMLProvider getInstance()
    {
        if(thisje==null)
        {
            thisje = new SqlXMLProvider();
        }
        return thisje;
    }

    public Boolean insertStudent(managedBean.StudentInfo student)
    {
            	Connection con =  pool.getConnection();
                boolean res = true;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
                    con.setAutoCommit(true);
                    stmt = con.prepareCall("{ call usp_Student_Insert(?,?,?,?,?,?,?) }");
                    stmt.setInt("p_studentnr",student.getStudentnr());
                    stmt.setString("p_naam",student.getNaam());
                    stmt.setString("p_voornaam", student.getVoornaam());
                    stmt.setString("p_email", student.getEmail());
                    stmt.setString("p_paswoord", student.getPaswoord());
                    stmt.setString("p_studiejaar", student.getStudiejaar());
                    stmt.setBoolean("p_traject", student.isGeindividualiseerdTraject());
                    stmt.execute();
                    if(student.isGeindividualiseerdTraject())
                    {
                        List<Integer> projects = student.getProjecten().getInt();
                        this.deleteStudieTraject(student.getStudentnr());
                        for(Integer projectid: projects)
                        {
                         this.insertStudieTraject(student.getStudentnr(), projectid);    
                        }
                    }
                    stmt.close();
                    
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
			res = false;
		}
		pool.returnConnection(con);
		return res;
    }

    public Boolean insertStudieTraject(int studentnr, Integer projectid)
    {
            	Connection con =  pool.getConnection();
                boolean res = true;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
                    con.setAutoCommit(true);
                    stmt = con.prepareCall("{ call usp_Student_InsertStudieTraject(?,?) }");
                    stmt.setInt("p_studentnr",studentnr);
                    stmt.setInt("p_projectid", projectid);
                    stmt.execute();
                    stmt.close();                    
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
			res = false;
		}
		pool.returnConnection(con);
		return res;
    }

    public Boolean deleteStudieTraject(int i)
    {
            	Connection con =  pool.getConnection();
                boolean res = true;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
                    con.setAutoCommit(true);
                    stmt = con.prepareCall("{ call usp_Student_DeleteStudieTrajecten(?) }");
                    stmt.setInt("p_studentnr",i);
                    stmt.execute();
                    stmt.close();                    
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
			res = false;
		}
		pool.returnConnection(con);
		return res;
    }
    
    
}
