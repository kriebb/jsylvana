package Domein;

import Domein.DocentPackage.Docent;
import Domein.ProjectPackage.ProjectOpgave;
import Persistentie.SimpleConnectionPool;
import Persistentie.SqlDocentProvider;
import Persistentie.SqlProjectProvider;

import java.sql.*;
import java.util.Map;

public class TestOurConnectionPool{
	
        public TestOurConnectionPool()
        {
    		pool = SimpleConnectionPool.getInstance("jdbc:mysql:///projecten?zeroDateTimeBehavior=convertToNull", "frederik", "bracke","com.mysql.jdbc.Driver", 30);            
    		Connection con = pool.getConnection();
    		if (con == null) {
    			System.out.println("Can't receive connection!");
    			return;
    		}
    		
    		ResultSet rs = null;
    		CallableStatement stmt = null;
    		ResultSetMetaData rsmd = null;

    		try {
    			con.setAutoCommit(false);
    			stmt = con.prepareCall("{call usp_Project_SelectAll()}");
    			
    			rs = stmt.executeQuery();
    			rsmd = rs.getMetaData();

    			while(rs.next()) 
    			{
    				for (int i = 1; i < rsmd.getColumnCount(); i++) {
    					System.out.print(rs.getObject(i)+ ", ");
    				}
    				System.out.println("");
    			}
    			stmt.close();
    			rs.close();
    		} catch (SQLException ex) {
    			System.err.println(ex.getMessage());
    		}
    		pool.returnConnection(con);

        
        }
	private static SimpleConnectionPool pool;

        public static void main(String args[])
        {
        	/*ProjectOpgave po = new ProjectOpgave(1,"titeltjesfUpdate",null,0,0,4);
        /*	System.out.println(SqlProjectProvider.getInstance().UpdateProjectOpgave(po));
        	System.out.println(SqlProjectProvider.getInstance().GetProjecten());
        	System.out.println(SqlProjectProvider.getInstance().GetProjectLuikByProjectluikID(8));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectLuikByProjectluikID(8).getProject().toString());
        	System.out.println(SqlProjectProvider.getInstance().GetProjectLuikenByProjectID(4));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaven());
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaveByID(1));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgavenByProjectID(4));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgavenByProjectIDForStudent(4,1));
        	po = new ProjectOpgave(1,"titeltjeUpdatejen",null,0,0,4);
        	System.out.println(SqlProjectProvider.getInstance().UpdateProjectOpgave(po));
     
        	po = new ProjectOpgave(3,"titeltjeUpdatejenNIEUW",null,0,0,4);
        	System.out.println(SqlProjectProvider.getInstance().InsertProjectOpgave(po));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaven());
        	System.out.println(SqlProjectProvider.getInstance().DeleteProjectOpgave(po.getOpgaveId()));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaven());
        	System.out.println(SqlProjectProvider.getInstance().DeleteProjectOpgave(28));
        	po = new ProjectOpgave(3,"titeltjeUpdatejenNIEUW",null,0,0,4);
        	System.out.println(SqlProjectProvider.getInstance().InsertProjectOpgave(po));
        	System.out.println(SqlProjectProvider.getInstance().HardDeleteProjectOpgave(po.getOpgaveId()));
        	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaven());
        	System.out.println(SqlProjectProvider.getInstance().GetProjectenByStudent(new Student(200600001,"","","","","",false) ));
            	System.out.println(SqlProjectProvider.getInstance().GetProjectOpgavenByProjectID(4));
                System.out.println(SqlProjectProvider.getInstance().GetProjectOpgaven());
          */
                //werken wel
                //Map<String,Docent> docenten = SqlDocentProvider.getInstance().GetDocenten();
                //System.out.print(docenten);
                //System.out.print(SqlDocentProvider.getInstance().GetDocentTeamsByProjectOpgave(38));
                //System.out.print(SqlDocentProvider.getInstance().GetDocentInDocentTeam_ByDocentTeamIDEnProjectID(1,4,9));
                /*ProjectOpgave p = new ProjectOpgave();
                p.setOpgaveId(29);
                DocentTeam dt = new DocentTeam(2,p);
                System.out.print(SqlDocentProvider.getInstance().InsertDocentTeam(dt));
                System.out.print(SqlDocentProvider.getInstance().DeleteDocentTeam(3));
                 System.out.print(SqlDocentProvider.getInstance().GetDocentTeamsByProjectOpgave(38));*/
            
                //werkt niet
                System.out.print(SqlDocentProvider.getInstance().UpdateDocentInDocentTeam(1,8,"AVA"));
                
                
                
        }
}
