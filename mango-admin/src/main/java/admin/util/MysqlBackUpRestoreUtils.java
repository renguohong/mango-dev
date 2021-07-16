package admin.util;

import java.io.File;
import java.io.IOException;

/* * @ClassName :
 * @Description :数据备份
 * @Author: gino
 * @Date: 2021/7/014
 * @Time: 23:01

 */
public class MysqlBackUpRestoreUtils {
	public static boolean backup(String host,String userName,String password
	,String backupFolderPath,String fileName,String database) throws IOException, InterruptedException {
		File backupFolderFile= new File(backupFolderPath);
		if (!backupFolderFile.exists()){
			backupFolderFile.mkdirs();
		}
		if (!backupFolderPath.endsWith("/")){
			backupFolderPath=backupFolderPath+File.separator;
		}
		String backupFilePath=backupFolderPath+fileName;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("mysqldump --opt").append("--add-drop-database").append("--add-drop-table");
		stringBuilder.append("-h").append(host).append("-u").append(userName).append("-p").append(password);
		stringBuilder.append("--result-file").append(backupFilePath).append("--default-character-set=utf8").append(database);
//		调用外部执行exe文件java api
		Process process= Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
		if (process.waitFor() == 0) {
			// 0 表示线程正常终止
			System.out.println("数据已经备份到 " + backupFilePath + " 文件中");
			return true;
		}
		return false;

	}
	private static String[] getCommand(String command) {
		String os = System.getProperty("os.name");
		String shell = "/bin/bash";
		String c = "-c";
		if(os.toLowerCase().startsWith("win")){
			shell = "cmd";
			c = "/c";
		}
		String[] cmd = { shell, c, command };
		return cmd;
	}

	/**
	 * 还原数据库
	 * @param restoreFilePath 数据库备份的脚本路径
	 * @param host IP地址
	 * @param database 数据库名称
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 */
	public static boolean restore(String restoreFilePath, String host, String userName, String password, String database)
			throws Exception {
		File restoreFile = new File(restoreFilePath);
		if (restoreFile.isDirectory()) {
			for (File file : restoreFile.listFiles()) {
				if (file.exists() && file.getPath().endsWith(".sql")) {
					restoreFilePath = file.getAbsolutePath();
					break;
				}
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
		stringBuilder.append(" ").append(database).append(" < ").append(restoreFilePath);
		try {
			Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
			if (process.waitFor() == 0) {
				System.out.println("数据已从 " + restoreFilePath + " 导入到数据库中");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		String userName = "root";
		String password = "123456";
		String database = "mango";

		System.out.println("开始备份");
		String backupFolderPath = "c:/dev/";
		String fileName = "mango.sql";
		backup(host, userName, password, backupFolderPath, fileName, database);
		System.out.println("备份成功");

		System.out.println("开始还原");
		String restoreFilePath = "c:/dev/mango.sql";
		restore(restoreFilePath, host, userName, password, database);
		System.out.println("还原成功");

	}

}
