package lorena.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewCiudades extends AppCompatActivity {
    ListView listaciudades;
    ArrayList<String>ciudades1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_ciudades);
        listaciudades=(ListView)findViewById(R.id.lstcidades);
        Intent i=getIntent();
        ciudades1=Leerfichero.LeerCidades(i.getExtras().getString("Ruta"));
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(ListViewCiudades.this,android.R.layout.simple_list_item_1,ciudades1);
        listaciudades.setAdapter(adaptador);
        listaciudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Seleccionaches: " + ((TextView) view).getText() + " -  Posicion:" + (position+1), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
