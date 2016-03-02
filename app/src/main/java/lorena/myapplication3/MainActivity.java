package lorena.myapplication3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textofolder;
    Button btnañadir;
    CheckBox cbsobreescribir;
    EditText textocity;
    Button leer;
    File rutacompleta;
     File dirFichero;
    TextView tv;
    private static final int dialogo_lista = 1;
    AlertDialog.Builder venta;
    Button btnsegundaactividad;
    Intent i;
    static String nombrefichero = "cities.txt";
    RadioButton corazul;
    RadioButton cornegro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textofolder = (EditText) findViewById(R.id.textofolder);
        btnañadir = (Button) findViewById(R.id.btnadd);
        cbsobreescribir = (CheckBox) findViewById(R.id.sobreescribir);
        tv = (TextView) findViewById(R.id.mostrartexto);
        textocity = (EditText) findViewById(R.id.textociudades);
        leer = (Button) findViewById(R.id.btnread);
        corazul=(RadioButton)findViewById(R.id.corazul);
        cornegro=(RadioButton)findViewById(R.id.cornegro);
        btnsegundaactividad = (Button) findViewById(R.id.btnactividad);
        btnsegundaactividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog(1);

            }
        });

        btnañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sobreescribir = false;

                dirFichero = new File(getFilesDir(), textofolder.getText().toString());
                if (!dirFichero.exists()) {
                    dirFichero.mkdir();
                }
                rutacompleta = new File(dirFichero.getAbsolutePath(), nombrefichero);

                try {
                    if (cbsobreescribir.isChecked()) {

                        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(rutacompleta, sobreescribir));
                        fout.write(textocity.getText() + "\n");
                        fout.close();
                        Log.i("Ruta completa do ficheiro", "" + rutacompleta.toString() + "\n");
                    } else {

                        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(rutacompleta, true));
                        osw.write(textocity.getText() + "\n");
                        osw.close();
                        Log.i("Ruta completa do ficheiro", "" + rutacompleta.toString() + "\n");


                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }


        });
        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linea = "";
                tv.setText(linea);
                rutacompleta = new File(dirFichero.getAbsolutePath(), nombrefichero);


                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutacompleta)));
                    while ((linea = br.readLine()) != null)
                        tv.append(linea + "\n");
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e("Interna", "Erro lendo o ficheiro");
                    Toast.makeText(MainActivity.this, "Problemas lendo o ficheiro", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Interna", "Erro lendo o ficheiro");
                    Toast.makeText(MainActivity.this, "Problemas lendo o ficheiro", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case dialogo_lista:
                venta = new AlertDialog.Builder(this);
                venta.setTitle("Escolle opcion");

                venta.setIcon(android.R.drawable.ic_dialog_info);
                venta.setItems(R.array.opcions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            i = new Intent(MainActivity.this, ListViewCiudades .class);


                        } else if (which==1){
                            i = new Intent(MainActivity.this, SpinnerCiudades.class);

                        }
                        dirFichero = new File(getFilesDir(), textofolder.getText().toString());
                        if (!dirFichero.exists()) {
                            dirFichero.mkdir();
                        }
                        rutacompleta = new File(dirFichero.getAbsolutePath(), nombrefichero);
                        i.putExtra("Ruta", rutacompleta.toString());
                        startActivity(i);
                    }
                });


        }
        return venta.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.escogercolor:
                if (corazul.isChecked())
                    textocity.setTextColor(getResources().getColor(R.color.azul));
                if (cornegro.isChecked())
                    textocity.setTextColor(getResources().getColor(R.color.negro));
                return true;

            case R.id.salir:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

