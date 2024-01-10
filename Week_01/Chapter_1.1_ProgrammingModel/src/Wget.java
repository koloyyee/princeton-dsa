import edu.princeton.cs.algs4.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

public class Wget {
    public static void main(String[] args) {
        String url = args[0];
        try{
            read(url);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static void read(String url) throws URISyntaxException, MalformedURLException {
        In in = new In(url);
        Out out = new Out();
        while(!in.isEmpty()){
            StdOut.println(in.readString());
        }
    }
}