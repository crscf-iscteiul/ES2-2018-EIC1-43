package utils;

import gui.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CompilerRandTex {

    private static CompilerRandTex instance;
    public static CompilerRandTex getInstance() {
        if(instance==null)
            instance=new CompilerRandTex();
        return instance;
    }

    private String rscript;
    private String pdflatex;

    private CompilerRandTex(){
        ini_R();
        ini_Tex();
    }

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
            GUI.getInstance().show_error("R script is no installed");
        }
    }

    private void ini_Tex(){
        String rscript = null;
        if(new File("C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64\\pdflatex.exe").isFile()){
            this.pdflatex = "C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64\\pdflatex.exe";
        }else{
            GUI.getInstance().show_error("MiKTeX 2.9 is no installed");
        }
    }

    public void compileR(String experiment) throws IOException {
        Process process = new ProcessBuilder(rscript, "HV.Boxplot.R").directory(new File("experimentBaseDirectory\\"+experiment+"\\R")).start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }


    public void compileLatex(String experiment) throws IOException {
        Process process = new ProcessBuilder(pdflatex,experiment+".tex").directory(new File("experimentBaseDirectory\\"+experiment+"\\latex\\")).start();

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
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     *         The result is a positive integer if str1 is _numerically_ greater than str2.
     *         The result is zero if the strings are _numerically_ equal.
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
