package Persistentie;

import Persistentie.SimpleConnectionPool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Domein.ProjectPackage.Project;
import Domein.ProjectPackage.ProjectLuik;
import Domein.ProjectPackage.ProjectOpgave;
import Domein.StudentPackage.Student;


public class SqlProjectProvider
{
	private SimpleConnectionPool pool;
	private SqlProjectProvider()
        {           
		pool = SimpleConnectionPool.getInstance("jdbc:mysql:///projecten?zeroDateTimeBehavior=convertToNull", "kristof", "riebbels1","com.mysql.jdbc.Driver", 30);             
        }

    private static SqlProjectProvider instance=null;

    public static SqlProjectProvider getInstance()
    {
            if (instance == null)
            {
                instance = new SqlProjectProvider();
            }
            return instance;
    }
    
	public Project GetProjectByID(int projectid)
    {
		Connection con = pool.getConnection();
		Project p = null;
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Project_SelectByProjectID(?)}");
			stmt.setInt("p_projectID",projectid);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				p = getProject(rs);				
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return p;
    }

	private Project getProject(ResultSet rs) throws SQLException
	{
		return new Project(rs.getInt("projectID"),rs.getString("projectTitel"),rs.getInt("studiejaar"),rs.getDate("inschrijvingVan"),rs.getDate("inschrijvingTot"));
	}
    public Map<Integer,Project> GetProjecten()
    {
		Map<Integer,Project> projecten = new HashMap<Integer,Project>();
    	Connection con = pool.getConnection();
		Project p = null;
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Project_SelectAll()}");			
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				p = getProject(rs);
				projecten.put(p.getProjectId(),p);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return projecten;    
		}

    public Map<Integer, Project> GetProjectenByStudent(Student student)
    {
		Map<Integer,Project> projecten = new HashMap<Integer,Project>();
    	Connection con = pool.getConnection();
		Project p = null;
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Project_SelectByStudent(?)}");
			stmt.setInt("p_studentID",student.getStudentNr());
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				p = getProject(rs);
				projecten.put(p.getProjectId(),p);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return projecten;    
    }

    /* Methodes voor de klasse ProjectLuik */
    private ProjectLuik getProjectLuik(ResultSet rs) throws SQLException
	{
		return new ProjectLuik(rs.getInt("luikid"),rs.getString("luiktitel"),rs.getInt("percentage"),rs.getInt("projectid"));
	}
    public ProjectLuik GetProjectLuikByProjectluikID(int projectluikid)
    {
		Connection con = pool.getConnection();
		ProjectLuik pl = null;
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Projectluik_SelectByID(?)}");
			stmt.setInt("p_luikid",projectluikid);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				pl = getProjectLuik(rs);				
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return pl;
    }

   /* Overbodige methode?? public Map<Integer,ProjectLuik> GetProjectLuiken()
    {
    	int projectid = 0;
    	Connection con = pool.getConnection();
		Map<Integer,ProjectLuik> mapPl = new HashMap<Integer,ProjectLuik>();
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Projectluik_SelectAll(?)}"); //is raar,die vraagt hier een standaard parameter??
			stmt.setInt("p_projectid",projectid);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				ProjectLuik pl = getProjectLuik(rs);
				mapPl.put(pl.getLuikId(),pl);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return mapPl;
    }*/

    /* Methodes voor de klasse ProjectOpgave */

    public Boolean InsertProjectOpgave(ProjectOpgave projectopgave)
    {
    	Connection con =  pool.getConnection();
    	boolean res = true;

		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{ call usp_ProjectOpgave_Insert(?,?,?,?,?,?) }");
            stmt.setString("p_opgaveTitel",projectopgave.getOpgaveTitel());
            stmt.setString("p_korteOmschrijving",(projectopgave.getKorteOmschrijving()==""?null:projectopgave.getKorteOmschrijving()));
            stmt.setInt("p_aantalStudentenPerGroep", projectopgave.getAantalStudentenPerGroep());
            stmt.setInt("p_aantalGroepen", projectopgave.getAantalGroepen());
            stmt.setInt("p_projectID", projectopgave.getProject().getProjectId());
			stmt.execute();
			projectopgave.setOpgaveId(stmt.getInt("p_opgaveID"));
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

    public Boolean UpdateProjectOpgave(ProjectOpgave projectopgave)
    {
    	Connection con =  pool.getConnection();
    	boolean res = true;

		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{ call usp_ProjectOpgave_Update(?,?,?,?,?,?) }");
            stmt.setInt("p_opgaveID",projectopgave.getOpgaveId());
            stmt.setString("p_opgaveTitel",projectopgave.getOpgaveTitel());
            stmt.setString("p_korteOmschrijving", projectopgave.getKorteOmschrijving());
            stmt.setInt("p_aantalStudentenPerGroep", projectopgave.getAantalStudentenPerGroep());
            stmt.setInt("p_aantalGroepen", projectopgave.getAantalGroepen());
            stmt.setInt("p_projectID", projectopgave.getProject().getProjectId());
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

    public Boolean DeleteProjectOpgave(int projectopgaveid)
    {
    	Connection con =  pool.getConnection();
    	boolean res = true;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{ call usp_ProjectOpgave_Delete(?,?) }");
            stmt.setInt("p_opgaveID",projectopgaveid);
			stmt.execute();
			res = (stmt.getInt("p_gelukt")==0?false:true);
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
    
    public Boolean HardDeleteProjectOpgave(int projectopgaveid)
    {
    	Connection con =  pool.getConnection();
    	boolean res = true;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{ call usp_ProjectOpgave_DeleteAll(?) }");
            stmt.setInt("p_opgaveID",projectopgaveid);
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


    private ProjectOpgave getProjectOpgave(ResultSet rs) throws SQLException
    {
        return new ProjectOpgave
        (
           rs.getInt("opgaveID"),
           rs.getString("opgaveTitel"),
           rs.getString("korteOmschrijving"),
           rs.getInt("aantalGroepen"),
           rs.getInt("aantalStudentenPerGroep"),
           rs.getInt("projectId")
        );
    }
    public ProjectOpgave GetProjectOpgaveByID(int projectopgaveid)
    {
    	Connection con = pool.getConnection();
		ProjectOpgave po = null;
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{ call usp_ProjectOpgave_SelectByOpgaveID(?) }");
			stmt.setInt("p_opgaveID",projectopgaveid);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				po = getProjectOpgave(rs);				
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return po;
    }

    public Map<Integer,ProjectOpgave> GetProjectOpgaven()
    {
    	Connection con = pool.getConnection();
		Map<Integer,ProjectOpgave> mapPo = new HashMap<Integer,ProjectOpgave>();
		ResultSet rs = null;
		ProjectOpgave po= null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_ProjectOpgave_SelectAll()}");
			rs = stmt.executeQuery();
			while(rs.next()) 
			{
				po = getProjectOpgave(rs);
				mapPo.put(po.getOpgaveId(),po);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return mapPo;    
	}

    public Map<Integer,ProjectOpgave> GetProjectOpgavenByProjectID(int projectID)
    {
    	Connection con = pool.getConnection();
		Map<Integer,ProjectOpgave> mapPo = new HashMap<Integer,ProjectOpgave>();
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_ProjectOpgave_SelectByProjectID(?)}");
			stmt.setInt("p_projectID",projectID);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				ProjectOpgave po = getProjectOpgave(rs);
				mapPo.put(po.getOpgaveId(),po);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return mapPo;    
    }

    public Map<Integer, ProjectOpgave> GetProjectOpgavenByProjectIDForStudent(int projectID, int studentnr)
    {
    	Connection con = pool.getConnection();
		Map<Integer,ProjectOpgave> mapPo = new HashMap<Integer,ProjectOpgave>();
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_ProjectOpgave_SelectByProjectIDForStudent(?,?)}");
			stmt.setInt("p_studentnr",studentnr);
			stmt.setInt("p_projectID",projectID);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				ProjectOpgave po = getProjectOpgave(rs);
				mapPo.put(po.getProjectID(),po);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return mapPo; 
    }

    public Map<Integer,ProjectLuik> GetProjectLuikenByProjectID(int projectid)
    {
    	Connection con = pool.getConnection();
		Map<Integer,ProjectLuik> mapPl = new HashMap<Integer,ProjectLuik>();
		ResultSet rs = null;
		CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
		try 
		{
			con.setAutoCommit(true);
			stmt = con.prepareCall("{call usp_Projectluik_SelectAll(?)}");
			stmt.setInt("p_projectid",projectid);
			rs = stmt.executeQuery();			
			while(rs.next()) 
			{
				ProjectLuik pl = getProjectLuik(rs);
				mapPl.put(pl.getLuikId(),pl);
			}
			stmt.close();
			rs.close();
		} 
		catch (SQLException ex) 
		{
			System.err.println(ex.getMessage());
		}
		pool.returnConnection(con);
		return mapPl;    
		}
}
