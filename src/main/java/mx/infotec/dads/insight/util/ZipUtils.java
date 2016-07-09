package mx.infotec.dads.insight.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase util para llevar a cabo operaciones con archivos Zip
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ZipUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    private static final int BUFFER_SIZE = 4096;

    private ZipUtils() {
    }

    private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException {
	byte[] buffer = new byte[BUFFER_SIZE];
	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
	int count;
	while ((count = in.read(buffer)) != -1) {
	    out.write(buffer, 0, count);
	}
	out.close();
    }

    private static void mkdirs(File outdir, String path) {
	File d = new File(outdir, path);
	if (!d.exists())
	    d.mkdirs();
    }

    private static String dirpart(String name) {
	int s = name.lastIndexOf(File.separatorChar);
	return s == -1 ? null : name.substring(0, s);
    }

    /***
     * Extract zipfile to outdir with complete directory structure
     * 
     * @param zipfile
     *            Input .zip file
     * @param outdir
     *            Output directory
     */
    public static void extract(String file, File outdir) {
	try {
	    InputStream is = ZipUtils.class.getResourceAsStream(file);
	    ZipInputStream zin = new ZipInputStream(is);
	    ZipEntry entry;
	    String name;
	    String dir;
	    while ((entry = zin.getNextEntry()) != null) {
		name = entry.getName();
		if (entry.isDirectory()) {
		    mkdirs(outdir, name);
		    continue;
		}
		dir = dirpart(name);
		if (dir != null)
		    mkdirs(outdir, dir);

		extractFile(zin, outdir, name);
	    }
	    zin.close();
	} catch (IOException e) {
	    LOGGER.error("extract", e);
	}
    }
}
