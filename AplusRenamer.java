import java.io.File;
import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class AplusRenamer
{
	public static String prefix = "Aplus00";
	public static final String[] killLines = { "//Name -", "//Date -", "//Class -", "//Lab -", "//Lab  -" };

	public static void main( String[] args )
	{
		prefix = JOptionPane.showInputDialog(null, "Prefix?", prefix);
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<File> files = new ArrayList<File>();

		File curDir = new File(".");
		for ( File f : curDir.listFiles() )
		{
			if ( f.getName().startsWith(prefix) || f.getName().equals("AplusRenamer.java") )
				continue;
			if ( f.isFile() && f.getName().endsWith(".java") )
			{
				files.add(f);
				classNames.add( chopExt(f.getName()) );
			}
		}

		if ( files.size() == 0 ) {
			System.out.println("Nothing to do.");
			System.exit(0);
		}

		// Sort longest to shortest (the fancy way)
		/* Collections.sort(classNames, new Comparator<String>() {
			public int compare( String a, String b ) {
				int diff = b.length() - a.length();
				return ( diff!=0 ) ? diff : a.compareTo(b);
			}
		});
		*/

		// Sort longest to shortest using exchange sort to avoid creating an inner class
		for ( int i=0; i<classNames.size(); i++ )
			for ( int j=i+1; j<classNames.size(); j++ )
				if ( classNames.get(i).length() < classNames.get(j).length() )
				{
					String temp = classNames.get(i);
					classNames.set(i, classNames.get(j));
					classNames.set(j, temp);
				}

		for ( File f : files )
		{
			System.out.printf("%30s --> %-40s\n", f.getName(), prefixed(f).getName() );
			if ( copyFile(f, prefixed(f), classNames) )
				f.deleteOnExit();
		}

	}

	public static boolean copyFile( File from, File to, ArrayList<String> classNames )
	{
		String b = "\\b";	// word boundary regex
		Scanner in = null;
		PrintWriter out = null;

		try {
			in = new Scanner(from);
			out = new PrintWriter(to);
		}
		catch ( java.io.IOException e )
		{
			System.err.print("Couldn't read from " + from.getName() );
			System.err.println("and/or write to " + to.getName() );
			System.err.println(e);
			return false;
		}

		lineiter:
		while ( in.hasNext() )
		{
			String line = in.nextLine();
			for ( String k : killLines )
				if ( line.startsWith(k) )
					continue lineiter;

			for ( String c : classNames )
				if ( line.contains(c) )
					line = line.replaceAll(b+c+b, prefix+c);

			out.println(line);
		}
		in.close();
		out.close();
		return true;
	}

	public static File prefixed( File f )
	{
		File ret = new File( f.getParent(), prefix+f.getName() );
		return ret;
	}

	public static String chopExt( String fname )
	{
		return fname.substring(0, fname.lastIndexOf('.'));
	}
}





















