package com.appsfreedom.utils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
	// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
	private static String dbUrl = "jdbc:mysql://sealinnoqa1.ciilauvvkh9m.us-east-1.rds.amazonaws.com/afmdb";
	private static String username = "qm2dev";// "readonlydev" 
	private static String password = "afmdb20150429";// "readonly123"

	public static void cleanUpConnections() {
		String query1 = "DELETE FROM afmdb.connection WHERE name in('db_dev','rest_dev');";

		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt1 = con.prepareStatement(query1);


			// Execute the SQL Query. Store results in ResultSet
			stmt1.executeUpdate();

			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return Integer.valueOf(log_count);
	}
	public static void cleanUpPlugin(String pluginName) {
		String query1 = "DELETE FROM afmdb.connection where plugin_id=(SELECT id FROM afmdb.plugin where name='"+pluginName+"');";
		String query2 = "DELETE FROM afmdb.plugin where name = '"+pluginName+"';";

		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt1 = con.prepareStatement(query1);
			java.sql.PreparedStatement stmt2 = con.prepareStatement(query2);
			//java.sql.PreparedStatement stmt3 = con.prepareStatement(query3);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			stmt1.executeUpdate();
			stmt2.executeUpdate();
			//stmt3.executeUpdate();

			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return Integer.valueOf(log_count);
	}

	
	public static void cleanUpUser(String loginId) {
		String query1 = "DELETE FROM afmdb.group WHERE afmdb.group.id=(SELECT afmdb.user_group.group_id FROM afmdb.user_group WHERE afmdb.user_group.user_id =(SELECT afmdb.user.id FROM afmdb.user WHERE afmdb.user.login_name='"
				+ loginId + "'));";
		String query2 = "DELETE FROM afmdb.user_group WHERE afmdb.user_group.user_id=(SELECT afmdb.user.id FROM afmdb.user WHERE afmdb.user.login_name ='"
				+ loginId + "');";
		String query3 = "DELETE FROM afmdb.user WHERE afmdb.user.login_name ='" + loginId + "';";

		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt1 = con.prepareStatement(query1);
			java.sql.PreparedStatement stmt2 = con.prepareStatement(query2);
			java.sql.PreparedStatement stmt3 = con.prepareStatement(query3);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			stmt2.executeUpdate();
			stmt1.executeUpdate();
			stmt3.executeUpdate();

			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return Integer.valueOf(log_count);
	}

	public static void cleanUpRole(String tenantId, String roleName) {
		String query1 = "delete FROM afmdb.role_permission where company_id=(select id from afmdb.company where afmdb.company.tenant_id='"
				+ tenantId
				+ "') and role_id = (select id from role where company_id=(select id from afmdb.company where afmdb.company.tenant_id='"
				+ tenantId + "') and name ='" + roleName + "');";
		String query2 = "delete FROM afmdb.group_role where company_id=(select id from afmdb.company where afmdb.company.tenant_id='"
				+ tenantId
				+ "') and role_id = (select id from role where company_id=(select id from afmdb.company where afmdb.company.tenant_id='"
				+ tenantId + "') and name ='" + roleName + "');";
		String query3 = "delete FROM afmdb.role where company_id=(select id from afmdb.company where afmdb.company.tenant_id='"
				+ tenantId + "') and name ='" + roleName + "';";

		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt1 = con.prepareStatement(query1);
			java.sql.PreparedStatement stmt2 = con.prepareStatement(query2);
			java.sql.PreparedStatement stmt3 = con.prepareStatement(query3);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			stmt1.executeUpdate();
			stmt2.executeUpdate();
			stmt3.executeUpdate();

			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return Integer.valueOf(log_count);
	}

	public static Integer getLogsCount(String tenantId) {
		String query = "SELECT COUNT(*) FROM afmdb.api_call_log where company_id IN (SELECT id FROM afmdb.company where tenant_id='"
				+ tenantId + "');";
		// String query = "SELECT COUNT(*) FROM afmdb.api_call_log where
		// company_id IN (SELECT id FROM afmdb.company where
		// tenant_id='"+tenantId+"' and details= "admin Logout");";

		String log_count = "";
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				log_count = rs.getString(1);
				// proj_list.add(projectname);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Integer.valueOf(log_count);
	}

	public static Integer getsearchLogsCount(String tenantId) {
		String query = "SELECT COUNT(*) FROM afmdb.api_call_log where company_id IN (SELECT id FROM afmdb.company where tenant_id='"
				+ tenantId + "' and details= 'admin Logout');";

		String log_count = "";
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				log_count = rs.getString(1);
				// proj_list.add(projectname);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Integer.valueOf(log_count);
	}

	public static Hashtable<String, Float> getCompanyRestrictions(String tenantId) {

		Hashtable<String, Float> restrictions = new Hashtable();
		String query = "SELECT " + "afmdb.company_license.tenant_count," + "afmdb.company_license.storage_limit,"
				+ "afmdb.company_license.data_transfer," + "afmdb.company_license.action_count,"
				+ "afmdb.company_license.bos_created," + "afmdb.company_license.bos_access_count,"
				+ "afmdb.company_license.app_count," + "afmdb.company_license.table_count,"
				+ "afmdb.company_license.platform_user_count," + "afmdb.company_license.business_user_count,"
				+ "afmdb.company_license.maxFileSize," + "afmdb.company_license.maxStorageSize,"
				+ "afmdb.company_license.allow_business_user " + "FROM afmdb.company_license "
				+ "where company_id=(SELECT id FROM afmdb.company WHERE tenant_id= ?);";

		String restriction = "";
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				restriction = rs.getString(1);
				restrictions.put("Tenants", Float.parseFloat(rs.getString(1)));
				restrictions.put("Storage", Float.parseFloat(rs.getString(2)));
				restrictions.put("DataTransfer", Float.parseFloat(rs.getString(3)));
				restrictions.put("Actions", Float.parseFloat(rs.getString(4)));
				restrictions.put("BOSCreated", Float.parseFloat(rs.getString(5)));
				restrictions.put("BOSCalled", Float.parseFloat(rs.getString(6)));
				restrictions.put("AppsAllowed", Float.parseFloat(rs.getString(7)));
				restrictions.put("DBTablesCreated", Float.parseFloat(rs.getString(8)));
				restrictions.put("PlatformUsersCreated", Float.parseFloat(rs.getString(9)));
				restrictions.put("BusinessUsersCreated", Float.parseFloat(rs.getString(10)));
				restrictions.put("DriveUploadFileLimit", Float.parseFloat(rs.getString(11)));
				restrictions.put("DriveStorageLimit", Float.parseFloat(rs.getString(12)));
				restrictions.put("AllowToAddBusinessUsers", Float.parseFloat(rs.getString(13)));
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restrictions;
	}

	public static float getDriveUsage(String tenantId) {
		String query = "SELECT ROUND(SUM(file_size)/(1024*1024),2) as utilization FROM afmdb.file_meta_data where "
				+ "company_id=(SELECT id FROM afmdb.company WHERE tenant_id= ?);";
		String utilization = "";
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				utilization = rs.getString(1);
				// String myAge = rs.getString(2);
				// System.out.println(utilization);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Float.valueOf(utilization);
	}

	public static float getAvailableSpace(String tenantId) {
		String query = "SELECT maxStorageSize FROM afmdb.company_license where "
				+ "company_id=(SELECT id FROM afmdb.company WHERE tenant_id= ?);";
		String available = "";
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				available = rs.getString(1);
				// String myAge = rs.getString(2);
				// System.out.println(utilization);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Float.valueOf(available) - getDriveUsage(tenantId);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// System.out.println(getDriveUsage("WAT110173"));
		// System.out.println(getAvailableSpace("OFT110155"));
		cleanUpConnections();

	}

	public static List<String> getTopUsers(String tenantId) {
		String query = "SELECT a_c_l.user_id AS userId,count(*) AS count,(SELECT CONCAT(first_name, ' ', last_name) FROM user WHERE user.id = a_c_l.user_id) AS userName FROM afmdb.api_call_log a_c_l WHERE company_id = (SELECT id FROM afmdb.company WHERE tenant_id= ?) GROUP BY user_id LIMIT 5;";
		List<String> user_list = new ArrayList<String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				String user = rs.getString(3) + " (" + rs.getString(2) + ")";
				user_list.add(user);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user_list;
	}

	public static List<String> getTopApps(String tenantId) {
		String query = "select a.name,b.count from apps a,(SELECT SUBSTR(details, 8, (SELECT LOCATE(';', details)-8)) appId,count(*) AS count FROM api_call_log	WHERE company_id = (SELECT id FROM afmdb.company WHERE tenant_id= ?) and request_type ='App' and details like 'Appkey%' GROUP BY appId ORDER BY count DESC LIMIT 5)b where a.app_key = b.appid ORDER BY b.count DESC;";
		List<String> app_list = new ArrayList<String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				String app = rs.getString(1) + " (" + rs.getString(2) + ")";
				app_list.add(app);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return app_list;
	}

	public static Map<String, String> getAccessreport(String tenantId, String selectedType) {

		String userQuery = "";
		String groupBy = "";
		String queryByUser = "";

		if (!selectedType.equalsIgnoreCase("users")) {
			groupBy = "group by tab.user_id,tab.appkey ";
		} else if (!selectedType.equalsIgnoreCase("apps")) {
			groupBy = "group by tab.appkey,tab.user_id ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select tab.login_name AS loginName,tab.user_id AS userId,tab.user_name AS userName,app.name AS appName,tab.createdTS AS date,tab.days AS count,tab.appkey AS appId from (select a.login_name,a.user_id,a.company_name,a.user_name,a.createdTS,datediff(CURRENT_DATE(),a.createdTS) as days, ");
		sb.append(
				"SUBSTRING(a.details,LOCATE('Appkey',a.details)+length('Appkey')+1,16) as appkey from api_call_log a,company c where a.company_id=c.id and c.tenant_id='"
						+ tenantId + "' " + userQuery + " and a.request_type='App' ");
		sb.append("order by a.createdTS desc) as tab,apps app where tab.appkey=app.app_key " + groupBy + " union ");
		sb.append(
				"Select u.login_name,u.id,concat(u.first_name,' ',u.last_name),'-',null,'0','-'  from `user` u,company c where u.company_id=c.id and u.isActive=1 and c.tenant_id='"
						+ tenantId + "' " + queryByUser + " and u.id not in ");
		sb.append("(select distinct a.user_id from api_call_log a,company c where a.company_id=c.id and c.tenant_id='"
				+ tenantId + "' " + userQuery + " and a.request_type='App')");

		Map<String, String> app_list = new HashMap<String, String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(sb.toString());

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				String user_app_name = rs.getString(1) + "-" + rs.getString(4);
				String lastAccessed_daysElapsed = rs.getString(5) + "-" + rs.getString(6);
				System.out.println(
						"user_app_name:" + user_app_name + " lastAccessed_daysElapsed:" + lastAccessed_daysElapsed);
				app_list.put(user_app_name, lastAccessed_daysElapsed);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return app_list;

	}

	public static Map<String, String> getMainTable(String tenantId) {

		StringBuffer sb = new StringBuffer();
		sb.append("select bb.*,acl.device_type as deviceType from(SELECT a.app_key as appId,a.name AS appName, ");
		sb.append("(SELECT versioned_datetime FROM project_components WHERE project_id = a.id ORDER BY ");
		sb.append(
				"versioned_datetime LIMIT 1) AS date,(SELECT count(apps_id) FROM assigned_apps WHERE apps_id = a.id) AS count, ");
		sb.append(
				"(SELECT CONCAT(first_name, ' ', last_name) FROM user WHERE id = p.owner) AS userName,a.description ");
		sb.append(
				"	FROM apps a JOIN project p ON p.id = a.project_id WHERE p.company_id =(SELECT id FROM afmdb.company WHERE tenant_id= '"
						+ tenantId + "') and a.is_published=1 ");
		sb.append("	and a.company_id=(SELECT id FROM afmdb.company WHERE tenant_id= '" + tenantId
				+ "'))bb left join api_call_log acl on SUBSTR(acl.details, 8, ");
		sb.append(
				"	(SELECT LOCATE(';', acl.details)-8))=bb.appId and acl.company_id=(SELECT id FROM afmdb.company WHERE tenant_id= '"
						+ tenantId + "') and acl.request_type='App' ");
		sb.append(" group by bb.appName,acl.device_type");

		Map<String, String> app_list = new HashMap<String, String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(sb.toString());

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				String user_app_name = rs.getString("appId") + "-" + rs.getString("deviceType");
				String lastAccessed_daysElapsed = rs.getString("count");
				System.out.println(
						"user_app_name:" + user_app_name + " lastAccessed_daysElapsed:" + lastAccessed_daysElapsed);
				app_list.put(user_app_name, lastAccessed_daysElapsed);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return app_list;

	}

	public static List<String> getAllProjects(String tenantId) {
		String query = "select name from project where company_id =(SELECT id FROM afmdb.company WHERE tenant_id= '"
				+ tenantId + "') and status=1;";
		List<String> proj_list = new ArrayList<String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				String projectname = rs.getString(1);
				proj_list.add(projectname);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return proj_list;
	}

	public static boolean doesBosExists(String tenantId, String projectname, String bosname) {
		String query = "select a.name,max(a.version) as version,a.type,b.name from project_components a,project b,company c where a.project_id = b.id and b.company_id=c.id and c.tenant_id='"
				+ tenantId + "' and b.name='" + projectname + "' and a.name='" + bosname + "';";
		boolean exists = false;
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				exists = true;
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exists;
	}

	public static Map<String, String> getuserDetails(String tenantId, String loginid, String email) {
		String query = "select a.isActive,a.password from user a,company c where a.company_id=c.id and c.tenant_id='"
				+ tenantId + "' and a.login_name='" + loginid + "' and a.email='" + email + "';";
		Map<String, String> useretails = new HashMap<String, String>();
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				boolean isActive = rs.getBoolean(1);
				String pwd = rs.getString(2);
				useretails.put("isActive", isActive + "");
				useretails.put("password", pwd);
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return useretails;
	}
	
	public static boolean doesUserExists(String userId, String compID) {
		
		String qry = "SELECT * FROM afmdb.user where company_id = "+compID.split("\\.")[0]+" AND login_name = '"+userId+"'";
		
		boolean exists = false;
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);

			// Create Statement Object
			java.sql.PreparedStatement stmt = con.prepareStatement(qry);
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery();

			// While Loop to iterate through all data and print results
			while (rs.next()) {
				exists = true;
			}
			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exists;
	}
	
	public static boolean clearTenantSchedulerlogs(String teantid) {
		boolean exists = false;
		List<String> scheduler_list = new ArrayList<String>();
		String query1 = "delete from afmdb.scheduler where company_id='"+ teantid + "';";		
		String query2 = "delete FROM afmdb.scheduler_log where scheduler_id IN (Select id from afmdb.scheduler where company_id='"+ teantid + "';)";
		String query3 = "delete FROM afmdb.scheduler_log_finished where scheduler_id IN (Select id from afmdb.scheduler where company_id='"+ teantid + "';)";
				
		try {
			// Load mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			
			// Create Statement Object
			java.sql.PreparedStatement stmt3 = con.prepareStatement(query3);                                  
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs3 = stmt3.executeQuery();
			
			// Create Statement Object
			java.sql.PreparedStatement stmt2 = con.prepareStatement(query2);                                  
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs2 = stmt2.executeQuery();
			
			// Create Statement Object
			java.sql.PreparedStatement stmt1 = con.prepareStatement(query1);                                  
			// stmt.setString(1, tenantId);

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs1 = stmt1.executeQuery();
			
			exists = true;

			// closing DB Connection
			con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exists;
	}
}
