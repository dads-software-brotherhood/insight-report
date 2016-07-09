package mx.infotec.dads.insight.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.insight.pdes.exceptions.ReportException;
import mx.infotec.dads.insight.pdes.service.context.ReportContext;
import com.google.gson.Gson;

/**
 * Clase util para manejar el contexto del sistema
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ContextUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextUtil.class);

    private ContextUtil() {

    }

    public static void saveToDisk(StringBuilder sb, String dataFile, File outputFile) throws IOException {
	if (!outputFile.exists()) {
	    outputFile.mkdirs();
	}
	File file = new File(outputFile, dataFile);
	if (!file.exists()) {
	    file.createNewFile();
	}
	String content = sb.toString();
	try (FileOutputStream fop = new FileOutputStream(file);) {
	    byte[] contentInBytes = content.getBytes();
	    fop.write(contentInBytes);
	    fop.flush();
	    fop.close();

	} catch (IOException e) {
	    LOGGER.error("saveToDisk", e);
	}
    }

    public static void saveImageToDisk(BufferedImage image, File outputFile, String fileName) throws IOException {
	File imageFolder = new File(outputFile, Constants.REPORT_IMG_FOLDER);
	if (!imageFolder.exists()) {
	    imageFolder.mkdirs();
	}
	File imageFile = new File(imageFolder, fileName + "." + Constants.EXTENSION_PNG);
	ImageIO.write(image, Constants.EXTENSION_PNG, imageFile);
    }

    public static void saveExceptionToDisk(Throwable e, String fileName, File outputFile) {
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    saveToDisk(new StringBuilder("Please send this log to: daniel.cortes@infotec.mx").append("\n\n")
		    .append(sw.toString()), fileName, outputFile);
	} catch (IOException ioException) {
	    LOGGER.error("saveExceptionToDisk", ioException);
	}
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName
     *            ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws URISyntaxException
     * @throws Exception
     */
    public static String exportResource(String resourceName) throws ReportException {
	String jarFolder = locateJarFolder();
	try (InputStream stream = ContextUtil.class.getResourceAsStream(resourceName);
		OutputStream resStreamOut = new FileOutputStream(jarFolder + resourceName);) {
	    if (stream == null) {
		throw new ReportException("Cannot get resource \"" + resourceName + "\" from Jar file.");
	    }
	    int readBytes;
	    byte[] buffer = new byte[4096];
	    while ((readBytes = stream.read(buffer)) > 0) {
		resStreamOut.write(buffer, 0, readBytes);
	    }
	} catch (Exception ex) {
	    throw new ReportException("exportResource", ex);
	}
	return jarFolder + resourceName;
    }

    public static String computeStatus(String vgDiffs) {
	int vgDiff = new Double(vgDiffs).intValue();
	if (vgDiff == 0) {
	    return "En Tiempo";
	} else if (vgDiff < 0) {
	    return "Atrasado";
	} else {
	    return "Adelantado";
	}
    }

    public static void saveJsonFormat(ReportContext context) {
	try {
	    Gson gson = new Gson();
	    String json = gson.toJson(context);
	    saveToDisk(new StringBuilder(json), "data.insight-report", context.getOutputFile());
	} catch (Exception e) {
	    LOGGER.error("JSON - ERROR: ", e);
	}
    }

    private static String locateJarFolder() throws ReportException {
	try {
	    return new File(ContextUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
		    .getParentFile().getPath().replace('\\', '/');
	} catch (URISyntaxException e) {
	    throw new ReportException("locateJarFolder", e);
	}
    }
}
