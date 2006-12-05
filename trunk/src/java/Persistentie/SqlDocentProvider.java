
package Persistentie;

import Domein.DocentPackage.Docent;
import Domein.DocentPackage.DocentTeam;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class SqlDocentProvider {
    private SimpleConnectionPool pool;
    private static SqlDocentProvider instance = null;
    
    public SqlDocentProvider() {
        pool = SimpleConnectionPool.getInstance("jdbc:mysql:///projecten?zeroDateTimeBehavior=convertToNull", "frederik", "bracke","com.mysql.jdbc.Driver", 30);
    }
    
    public static SqlDocentProvider getInstance()
    {
        if(instance==null)
        {
            instance=new SqlDocentProvider();
        }
        return instance;
    }
    public Docent getDocent(ResultSet rs) throws SQLException
    {
        return new Docent(rs.getString("docentID"),rs.getString("naam"),rs.getString("voornaam"),rs.getString("email"),rs.getString("paswoord"),rs.getBoolean("admin"));
    }
    public Map<String,Docent> GetDocenten()
    {
        Map<String,Docent> docenten = new HashMap<String,Docent>();
        Connection con = pool.getConnection();
        Docent d = null;
        ResultSet rs = null;
	CallableStatement stmt = null;
        try 
        {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{call usp_Docent_SelectAll()}");			
                rs = stmt.executeQuery();			
                while(rs.next()) 
                {
                        d = getDocent(rs);
                        docenten.put(d.getDocentId(),d);
                }
                stmt.close();
                rs.close();
        } 
        catch (SQLException ex) 
        {
                System.err.println(ex.getMessage());
        }
        
        pool.returnConnection(con);        
        return docenten;
    }


    /* Methodes voor de klasse DocentTeam */
    public boolean InsertDocentTeam(DocentTeam dt)
    {
    	Connection con =  pool.getConnection();
    	boolean res = true;

	CallableStatement stmt = null;
            try 
            {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{ call usp_Docentteam_Insert(?) }");
                stmt.setInt("p_opgaveid", dt.getProjectOpgave().getOpgaveId());
                stmt.execute();
                //projectopgave.setOpgaveId(stmt.getInt("p_opgaveID"));
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
    /*overbodig (?)
    public Boolean UpdateDocentTeam(DocentTeam docentteam)
    {
        Connection con =  pool.getConnection();
    	boolean res = true;

	CallableStatement stmt = null;
            try 
            {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{ call usp_Docentteam_Update(?,?) }");
                stmt.setInt("p_docentteamid", docentteam.getDocentTeamId());
                stmt.setInt("p_opgaveid", docentteam.getProjectOpgave().getOpgaveId());
                stmt.execute();
                //projectopgave.setOpgaveId(stmt.getInt("p_opgaveID"));
                stmt.close();

            } 
            catch (SQLException ex) 
            {
                    System.err.println(ex.getMessage());
                    res = false;
            }
            pool.returnConnection(con);
            return res;
    }*/

    public Boolean DeleteDocentTeam(int docentteamid)
    {
        Connection con =  pool.getConnection();
    	boolean res = true;

	CallableStatement stmt = null; //zoveel mogelijk gebruik maken. Is zware methode, vreet performance. :(
            try 
            {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{ call usp_Docentteam_Delete(?) }");
                stmt.setInt("p_docentteamid", docentteamid);
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
    public DocentTeam GetDocentTeam(ResultSet rs) throws SQLException
    {
        return new DocentTeam(rs.getInt("docentTeamID"),rs.getInt("opgaveID"),rs.getInt("projectID"));
    }
    public Map<Integer,DocentTeam> GetDocentTeamsByProjectOpgave(int projectopgaveid)
    {
        Connection con = pool.getConnection();
        Map<Integer,DocentTeam> docentTeams = new HashMap<Integer,DocentTeam>();
        ResultSet rs = null;
        CallableStatement stmt = null;
        try 
        {
            con.setAutoCommit(true);
            stmt = con.prepareCall("{call usp_Docentteam_By_Opgave(?)}");
            stmt.setInt("p_opgaveid",projectopgaveid);
            rs = stmt.executeQuery();			
            while(rs.next()) 
            {
                    DocentTeam dt = GetDocentTeam(rs);
                    docentTeams.put(dt.getDocentTeamId(),dt);
            }
            stmt.close();
            rs.close();
        } 
        catch (SQLException ex) 
        {
                System.err.println(ex.getMessage());
        }
        pool.returnConnection(con);
        return docentTeams;
    }
    /* overbodig (?)
    
    public Map<Integer,DocentTeam> GetDocentTeams()
    {
    	Map<Integer,DocentTeam> docentTeam = new HashMap<Integer,DocentTeam>();
        Connection con = pool.getConnection();
        DocentTeam dt = null;
        ResultSet rs = null;
	CallableStatement stmt = null;
        try 
        {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{call usp_DocentTeam_SelectAll()}");			
                rs = stmt.executeQuery();			
                while(rs.next()) 
                {
                        dt = GetDocentTeam(rs);
                        docentTeam.put(dt.getDocentTeamId(),dt);
                }
                stmt.close();
                rs.close();
        } 
        catch (SQLException ex) 
        {
                System.err.println(ex.getMessage());
        }
        
        pool.returnConnection(con);        
        return docentTeam;
    }/*

    /* Methodes voor de klasse DocentInDocentTeam */

    

    public Boolean UpdateDocentInDocentTeam(int docentteamid,int projectluikid, String docentid)
    {
        Connection con =  pool.getConnection();
    	boolean res = true;

	CallableStatement stmt = null;
            try 
            {
                con.setAutoCommit(true);
                stmt = con.prepareCall("{ call usp_DocentInDocentteam_Update(?,?,?) }");
                stmt.setInt("p_docentteamid", docentteamid);
                stmt.setInt("p_projectluikid", projectluikid);
                stmt.setString("p_docentid", docentid);
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
    
    public Map<Integer, String> GetDocentInDocentTeam_ByDocentTeamIDEnProjectID(int docentTeamID, int ProjectID, int OpgaveId)
    {
        Connection con = pool.getConnection();
        Map<Integer,String> docentenInDocentteam = new HashMap<Integer,String>();
        ResultSet rs = null;
        CallableStatement stmt = null;
        try 
        {
            con.setAutoCommit(true);
            stmt = con.prepareCall("{call usp_DocentInDocentteam_Select_By_DocentteamidEnProjectid(?,?)}");
            stmt.setInt("p_docentteamid",docentTeamID);
            stmt.setInt("p_projectid",ProjectID);
            rs = stmt.executeQuery();			
            while(rs.next()) 
            {
                    docentenInDocentteam.put(rs.getInt("docentteamid"),rs.getString("docentid"));
            }
            stmt.close();
            rs.close();
        } 
        catch (SQLException ex) 
        {
                System.err.println(ex.getMessage());
        }
        pool.returnConnection(con);
        return docentenInDocentteam;
    }

    public Boolean controleerLogin(String email, String paswoord)
    {
        return false;
    }
}
