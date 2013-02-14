package project.code;

import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {
	// it will filter the files and show only the types that return ture
	public boolean accept(File f) {
		// show if it is a directory
		if (f.isDirectory()) {
			return true;
		}
		// show if it is a file with PNG extension
		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals("png"))
				return true;
			else
				return false;
		}
		// otherwise don't show
		return false;
	}

	public String getDescription() {
		// the description of the PNG extension
		return "Portable Network Graphics (PNG)";
	}

	private String getExtension(File f) {
		// this method returns the extension of the given file name
		String e = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			e = s.substring(i + 1).toLowerCase();
		}
		return e;
	}
}