package com.kemean.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class CreateBean {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir"); // 项目路径
		String tableName = "daiken_design";
		String dbuser = "daiken"; // 数据库用户名
		String dbpassword = "daiken@@"; // 数据库用户密码
		String dblinkstr = "jdbc:mysql://39.108.159.198:3306/" + tableName;

		String[] tables = new String[] { "daiken_goods_hot_treasure" };
		if (tables.length > 0) {
			for (int j = 0; j < tables.length; j++) {
				String dbdriver = "com.mysql.jdbc.Driver"; // 数据库驱动
				String schema = tableName;// 处理字段注释
				String tablename = tables[j]; // 需要创建Bean的表名
				String filepath = dir + "\\src\\main\\java\\com\\kemean\\bean\\" + javaname(tablename) + ".java"; // 创建文件路径

				CreateBean ta = new CreateBean(schema, dbdriver, dblinkstr, dbuser, dbpassword, tablename, filepath);
				ta.connect();
				// mysql数据库-查询字段comment
				ta.queryTableInfo_mysql();
				String[] s;
				System.out.println("开始获取表【" + tablename + "】字段");
				s = ta.getColumenName();
				System.out.println("获取表【" + tablename + "】" + s.length + "个字段");
				for (int i = 0; i < s.length; i++) {
					ta.generate(s[i]);
				}
				ta.coding.append("}");
				System.out.println("创建BEAN JAVA文件");
				ta.writeData(ta.getCoding(ta.coding), filepath);
				ta.destroy();
				System.out.println("创建BEAN JAVA文件成功，路径" + filepath);
			}
		} else {
			System.out.println("请输入表名");
		}
	}

	private Connection conn = null; // 保存链接路径
	private Statement stmt = null; // 建立连接
	private ResultSetMetaData meta = null; // 保存表属性信息
	private ResultSet rs = null; // 查询结果集
	private File f = null; // 建立的文件
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	private FileOutputStream fos = null;
	private StringBuffer coding = new StringBuffer(); // 字符串缓冲区
	private String packageName = null; // 数据库包名
	private String url = null; // 路径名
	private String table = null; // 表空间名
	private String password = null; // 密码
	private String tableName = null; // 表明
	private String schema = null;
	private static HashMap<String, String> dataTypeMap = null;
	// key:列名字 value:#号隔开 目前只放了comment
	private static HashMap<String, String> columnInfoMap = new HashMap<String, String>();

	public CreateBean(String schema, String packageName, String url, String table, String password, String tableName,
			String filepathAndName) {
		this.schema = schema;
		this.packageName = packageName;
		this.url = url;
		this.table = table;
		this.password = password;
		this.tableName = tableName;
		f = new File(filepathAndName);
		if (!f.exists()) { // 如果文件不存在则建立文件
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * jdbc type与java datatype 关系映射配置
	 * 
	 * @param code
	 * @return
	 */
	static {
		dataTypeMap = new HashMap<String, String>();
		dataTypeMap.put("INT", "Integer");
		dataTypeMap.put("FLOAT", "Float");
		dataTypeMap.put("VARCHAR", "String");
		dataTypeMap.put("DATETIME", "Date");
		dataTypeMap.put("DATE", "Date");
		dataTypeMap.put("DOUBLE", "Double");
		dataTypeMap.put("TINYINT", "Integer");
		dataTypeMap.put("FLOAT UNSIGNED", "Float");
		dataTypeMap.put("SMALLINT", "Integer");
		dataTypeMap.put("CHAR", "String");
		dataTypeMap.put("BIT", "Boolean");
		dataTypeMap.put("TINYINT", "Boolean");
	}

	private String getCoding(StringBuffer code) {
		return code.toString();
	}

	/**
	 * name#type
	 * 
	 * @param property
	 * @return
	 */
	private StringBuffer generate(String property) {
		String propertyName = property.split("#")[0];
		String propertyType = property.split("#")[1];
		boolean isAutoIncre = Boolean.parseBoolean(property.split("#")[2]);
		String prop = propertyName.toLowerCase();
		String propStr[] = prop.split("_");
		String beanname = "";
		for (int i = 0; i < propStr.length; i++) {
			if (propStr.length == 1) {
				beanname = prop;
			} else {
				String imp = "";
				if (i == 0) {
					imp = propStr[i].substring(0, 1);
				} else {
					imp = propStr[i].substring(0, 1).toUpperCase();
				}

				beanname += imp + propStr[i].substring(1, propStr[i].length());
			}
		}
		// 自动递增列
		if (isAutoIncre) {
			// 添加注解 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
			coding.append("		@Id\n");
			coding.append("		@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
		} else {
			// 添加注释
			if (columnInfoMap.get(propertyName) != null) {
				// 多行注释
				String[] commentLines = columnInfoMap.get(propertyName).split("\n");
				if (commentLines != null && commentLines.length > 0) {
					coding.append("		/**\n");
					for (int i = 0; i < commentLines.length; i++) {
						coding.append("   		 *" + commentLines[i] + "\n");
					}
					coding.append("		 */\n");
				} else {
					coding.append("		//" + columnInfoMap.get(propertyName) + "\n");
				}

			}
			// 添加注解@Column(name="work_time")
			coding.append("		@Column(name=\"" + propertyName + "\")\n");
		}
		coding.append("		private " + propertyType + " " + beanname + ";\n\n");
		return coding;
	}

	private void destroy() {
		// 关闭与数据库的所有链接
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (bw != null) {
				bw.close();
				bw = null;
			}
			if (fos != null) {
				fos.close();
				fos = null;
			}
			if (osw != null) {
				osw.close();
				osw = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String javaname(String name) {
		String propStr[] = name.split("_");
		String newname = "";
		for (int i = 0; i < propStr.length; i++) {
			if (propStr.length == 1) {
				newname = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			} else {
				newname += propStr[i].substring(0, 1).toUpperCase() + propStr[i].substring(1, propStr[i].length());
			}
		}
		return newname;
	}

	/*
	 * 数据库连接发生异常就关闭链接
	 */
	private void connect() {
		try {
			Class.forName(packageName);
			conn = DriverManager.getConnection(url, table, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select  * from " + tableName); // 查询下确定结果集是那个表
			meta = rs.getMetaData(); // 调用结果集的记录表信息的方法
			String prop = tableName.toLowerCase();
			String propStr[] = prop.split("_");
			String newname = "";
			for (int i = 0; i < propStr.length; i++) {
				if (propStr.length == 1) {
					// newname = prop;
					newname = javaname(prop);
				} else {
					newname += propStr[i].substring(0, 1).toUpperCase() + propStr[i].substring(1, propStr[i].length());
				}
			}
			// 默认导入的包
			coding.append("import javax.persistence.*;");
			coding.append("\n\n@Table(name = \"" + prop + "\")\n");
			coding.append("public class " + newname + "{\n\n\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}

			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 字段名#字段类型
	 * 
	 * @return
	 */
	private String[] getColumenName() {
		int count;
		String[] s = null;
		try {
			count = meta.getColumnCount();
			String[] strColumenName = new String[count];
			for (int i = 1; i <= count; i++) {
				// strColumenName[i-1] = meta.getColumnName(i);
				System.out.println("字段名：" + meta.getColumnName(i) + "|字段类型：" + meta.getColumnTypeName(i) + "|自动递增:"
						+ meta.isAutoIncrement(i));
				String cname = meta.getColumnName(i);
				String cdata = meta.getColumnTypeName(i);
				boolean isAutoIncre = meta.isAutoIncrement(i);
				System.out.println(meta.getColumnLabel(i));
				// 转换databasetype为javatype
				cdata = parseToJavaType(cdata);
				strColumenName[i - 1] = cname + "#" + cdata + "#" + isAutoIncre;
			}
			s = strColumenName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * @param cdata
	 * @return
	 */
	private String parseToJavaType(String cdata) {
		String type = dataTypeMap.get(cdata);
		if (type == null) {
			System.out.println(">>>>>error:" + cdata + "类型没有配置对应的java datatype！！！！！");
			type = "String";
		}
		return type;
	}

	private void writeData(String message, String filepath) {
		try {
			fos = new FileOutputStream(filepath);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			bw.write(message);
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询表结构相关数据
	 */
	public void queryTableInfo_mysql() {
		try {
			Class.forName(packageName);
			conn = DriverManager.getConnection(url, table, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select column_name,data_type,column_comment  from information_schema.COLUMNS where TABLE_SCHEMA='"
							+ schema + "' and TABLE_NAME = '" + tableName + "'"); // 查询下确定结果集是那个表
			while (rs.next()) {
				String key = rs.getString("column_name");
				String value = rs.getString("column_comment");
				columnInfoMap.put(key, value);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}

			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}
	}

}
