package com.louis.mango.service;

public interface MySqlBackService {
	boolean backup(String host, String userName, String password,
				   String backupFolderPath, String fileName, String database) throws Exception;

	boolean restore(String restoreFilePath,String host,String userName,String password,String database) throws Exception;
}
