/*
 * SimpleConnectionPool.java
 *
 * Created on 25 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Persistentie;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class SimpleConnectionPool {
    
    /** Creates a new instance of SimpleConnectionPool */
    
    private String URI;
    private String dbuser;
    private String dbpass;
    private String drivername;
    private int maxconn;

    private static SimpleConnectionPool ref;

    private static int clients;
    
    private SimpleConnectionPool(String URI,
            String dbuser,
            String dbpass,
            String drivername,
            int maxconn) {
        this.URI = URI;
        this.dbuser = dbuser;
        this.dbpass = dbpass;
        this.drivername = drivername;
        this.maxconn = maxconn;
        
        loadJDBCDriver();
    }
    private void loadJDBCDriver() {
        try {
            Driver driver = (Driver)Class.forName(this.drivername).newInstance();
            DriverManager.registerDriver(driver);

            
        } catch (Exception e) {
            System.out.println("Can't load/register JDBC driver ");
        }
    }
    
    public static synchronized  SimpleConnectionPool getInstance(String URI,
            String dbuser,
            String dbpass,
            String drivername,
            int maxconn) {
        if (ref == null) {
            ref = new SimpleConnectionPool(URI, dbuser, dbpass,drivername, maxconn);
        }
        clients++;
        return ref;
    }
    
    private List<Connection> freeConnections = new ArrayList<Connection>();
    
    public synchronized Connection getConnection() {
        Connection rescon = null;
        if (!this.freeConnections.isEmpty()) {
            rescon = this.freeConnections.get(this.freeConnections.size()-1);
            this.freeConnections.remove(rescon);
            try {
                if (rescon.isClosed()) {
                    System.out.println("Removed closed connection!");
                    // Try again recursively
                    rescon = getConnection();
                }
            } catch (SQLException e) {
                System.out.println("Removed closed connection!");
                // Try again recursively
                rescon = getConnection();
            } catch (Exception e) {
                System.out.println("Removed closed connection!");
                // Try again recursively
                rescon = getConnection();
            }
        } else {
            rescon = createConnection();
        }
        return rescon;
    }
    
    private Connection createConnection() {
	Connection rescon = null;
	try {
		if (this.dbuser == null) {
			rescon = DriverManager.getConnection(this.URI);
		} else {
			rescon = DriverManager.getConnection(this.URI, 			this.dbuser, this.dbpass);
		}
		// new connection in connection pool created
	} catch (SQLException e) {
		System.err.println("Cannot create a new connection!\n"+e.getMessage());
		rescon = null;
	}
	return rescon;
}
    public synchronized void returnConnection(Connection con) 
    {
	if ((con != null) && (this.freeConnections.size() <= 	this.maxconn)) 
        {
		this.freeConnections.add(con);
	}
    }
    
    public synchronized void release() {
	Iterator<Connection> allc = this.freeConnections.iterator();
	while (allc.hasNext()) {
		Connection con = allc.next();
		try {
			con.close();
		} catch (SQLException e) {
 			System.err.println("Cannot close connection!(Probably already closed?)");
		}
	}
	this.freeConnections.clear();
}



    
}
