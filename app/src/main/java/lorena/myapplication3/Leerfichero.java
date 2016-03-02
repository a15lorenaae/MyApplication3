package lorena.myapplication3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Lorena on 12/12/2015.
 */
public class Leerfichero {
    public static ArrayList<String> LeerCidades(String ruta) {
        String linea = "";
        ArrayList<String> cidades = new ArrayList<String>();

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
            while ((linea = br.readLine()) != null)
                cidades.add(linea);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("Interna", "Erro lendo o ficheiro");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Interna", "Erro lendo o ficheiro");


        }


        return cidades;

    }

}
