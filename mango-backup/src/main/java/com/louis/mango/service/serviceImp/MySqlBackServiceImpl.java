package com.louis.mango.service.serviceImp;

import com.louis.mango.service.MySqlBackService;
import org.springframework.stereotype.Service;

@Service
public class MySqlBackServiceImpl implements MySqlBackService {

	@Override
	public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
		return false;
	}

	@Override
	public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
		return false;
	}
}
