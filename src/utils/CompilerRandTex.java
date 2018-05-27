package utils;

import gui.GUI;

import java.io.*;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class CompilerRandTex.
 */
public class CompilerRandTex {

    /** The instance. */
    private static CompilerRandTex instance;
    
    /**
     * Gets the single instance of CompilerRandTex.
     *
     * @return single instance of CompilerRandTex
     * @throws FileNotFoundException the file not found exception
     */
    public static CompilerRandTex getInstance() throws FileNotFoundException{
        if(instance==null)
            instance=new CompilerRandTex();
        return instance;
    }

    /** The rscript. */
    private String rscript;
    
    /** The miktex. */
    private String miktex;

    /**
     * Instantiates a new compiler rand tex.
     *
     * @throws FileNotFoundException the file not found exception
     */
    private CompilerRandTex() throws FileNotFoundException {
        ini_R();
        ini_Tex();
        if(rscript==null || miktex==null){
            throw new FileNotFoundException();
        }
    }

    /**
     * Ini R.
     */
    private void ini_R(){
        String rscript = null;
        try {
            File r_directory = new File("C:\\Program Files\\R");
            for (String s : Objects.requireNonNull(r_directory.list())){
                if(rscript==null)
                    rscript = s;
                else{
                    int v = versionCompare(rscript.replace("R-",""), s.replace("R-",""));
                    if(v<0)
                        rscript = s;
                }
            }
            this.rscript = "C:\\Program Files\\R\\"+rscript+"\\bin\\RScript.exe";

        } catch (NullPointerException e){
            GUI.getInstance().show_error("R script is not installed");
        }
    }

    /**
     * Ini tex.
     */
    private void ini_Tex(){
        String rscript = null;
        if(new File("C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64").isDirectory()){
            this.miktex = "C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64";
        }else{
            GUI.getInstance().show_error("MiKTeX 2.9 is not installed");
        }
    }

    /**
     * Compile R.
     *
     * @param experiment the experiment
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void compileR(String experiment) throws IOException {
        Process process = new ProcessBuilder(rscript, "HV.Boxplot.R").directory(new File("experimentBaseDirectory\\"+experiment+"\\R")).start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * Compile latex.
     *
     * @param experiment the experiment
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void compileLatex(String experiment) throws IOException {
        Process process = new ProcessBuilder(miktex +"\\pdflatex.exe",experiment+".tex").directory(new File("experimentBaseDirectory\\"+experiment+"\\latex\\")).start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * Compile EPS.
     *
     * @param experiment the experiment
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void compileEPS(String experiment) throws IOException{
        Process process = new ProcessBuilder(miktex +"\\epstopdf.exe","HV.Boxplot.eps").directory(new File("experimentBaseDirectory\\"+experiment+"\\R")).start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * Compares two version strings.
     * 
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     *         The result is a positive integer if str1 is _numerically_ greater than str2.
     *         The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    public static int versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }
}
