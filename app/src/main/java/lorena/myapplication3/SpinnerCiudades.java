package lorena.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpinnerCiudades extends AppCompatActivity {
ArrayList<String >ciudades1=new ArrayList<String>();
    Spinner spinciudades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_ciudades);
        spinciudades=(Spinner)findViewById(R.id.spinnerlista);
        Intent i=getIntent();
        ciudades1=Leerfichero.LeerCidades(i.getExtras().getString("Ruta"));
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(SpinnerCiudades.this,android.R.layout.simple_spinner_dropdown_item,ciudades1);
        spinciudades.setAdapter(adaptador);
        spinciudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
