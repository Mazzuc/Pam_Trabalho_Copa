package com.example.copa01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class mapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);




    }

    public void btnmapa (View view){
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mapa.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager LocManager = (LocationManager) getSystemService(mapa.this.LOCATION_SERVICE);
        LocationListener LocListener = new classLocalizacao();

        LocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, LocListener);

        if (LocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String texto = "Latitude.: " + classLocalizacao.latitude + "\n" +
                    "Longitude: " + classLocalizacao.longitude + "\n";
            Toast.makeText(mapa.this, texto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mapa.this, "Requerimento negado.", Toast.LENGTH_LONG).show();
        }

        this.mostrarLocalizacaoDados(classLocalizacao.latitude, classLocalizacao.longitude);

    }

    public void mostrarLocalizacaoDados ( double latitude, double longitude) {
        TextView textloc = findViewById(R.id.txtpermissao);


        textloc.setText("O estádio se localiza em:" + "\n" + latitude  + "\n" + longitude);

    }
}