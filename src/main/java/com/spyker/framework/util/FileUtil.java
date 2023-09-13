package com.spyker.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;

/**
 * @author ZZF on 2012-5-14
 */
public final class FileUtil {

	public static void write(InputStream inputStream, File toFile) throws IOException {

		OutputStream outputStream = new FileOutputStream(toFile);

		IOUtils.copy(inputStream, outputStream);

		IOUtils.closeQuietly(inputStream);
		IOUtils.closeQuietly(outputStream);
	}

	public static String getFileType(String fileName) {

		final String[] splits = fileName.split("\\.");

		if (splits.length > 1) {
			return "." + splits[splits.length - 1];
		}

		return "";
	}

	public static String getPathFromDate() {

		String format = "/" + "yyyy" + "/" + "MM" + "/" + "dd" + "/";

		final SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
		final String result = simpleFormat.format(new Date(System.currentTimeMillis()));

		return result;
	}

	public static void delete(String filePath) {
		final File file = new File(filePath);

		if (file.exists()) {
			file.delete();
		}
	}

	public static void delete(File deleteFile) {

		if (deleteFile.exists()) {
			deleteFile.delete();
		}
	}

	public static boolean exist(File file) {

		if (file.exists() && file.isFile()) {
			return true;
		}
		return false;
	}

	public static void createDir(String destDirName) {
		File dir = new File(destDirName);

		if (!dir.exists()) {

			if (!destDirName.endsWith(File.separator)) {
				destDirName = destDirName + File.separator;
			}

			dir.mkdirs();
		}
	}
}
