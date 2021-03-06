package com.finalP.Real_Estate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class map extends AppCompatActivity implements OnMapReadyCallback {


    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE=101;
    private double lat,lang;


    GoogleMap gMap;
    ArrayList<LatLng> arrayList=new ArrayList<LatLng>();
    LatLng manish=new LatLng(19.037414,73.013064);
    LatLng ritik=new LatLng(19.037525,73.020725);
    LatLng nachiket=new LatLng(19.036552,73.020047);
    LatLng prashant=new LatLng(19.049837,73.016915);
    LatLng aditya=new LatLng(19.042277,73.015183);
    LatLng bhagat=new LatLng(19.032885,73.011244);
    LatLng b=new LatLng(19.034635,73.061456);
    LatLng a=new LatLng(19.027819,73.057806);
    LatLng c=new LatLng(19.030821,73.066244);
    LatLng d=new LatLng(19.038367,73.067960);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);
        arrayList.add(manish);
        arrayList.add(ritik);
        arrayList.add(nachiket);
        arrayList.add(prashant);
        arrayList.add(aditya);
        arrayList.add(bhagat);
        arrayList.add(b);
        arrayList.add(a);
        arrayList.add(c);
        arrayList.add(d);
    }

    private void getLocation(final GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(map.this, Locale.getDefault());
                        //Initialize address list
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        lat = addresses.get(0).getLatitude();
                        lang = addresses.get(0).getLongitude();
                        Log.i("hello", String.valueOf(lat));
                        LatLng latLng=new LatLng(lat,lang);
                        MarkerOptions markerOptions=new MarkerOptions().position(latLng)
                                .title("you r here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                        googleMap.addMarker(markerOptions);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("hello", "unsucsessfull");
                    }
                }
            }
        });
    }










    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        List<String> list_users = Arrays.asList("2 BHk,1000 SQFT, Manish Jadhav", "3 BHK ,1500 SQFK , Ritik Karyat", "3 BHK ,1500 SQFK , Ritik Karyat","2 BHk,1000 SQFT, Prashant","3 BHK ,1500 SQFK , Aditya","3 BHK ,1500 SQFK , Omkar Bhagat","2 BHK ,1000 SQFK , Rohit","4 BHK ,2000 SQFK , Ameyash","2 BHK ,1000 SQFK , Anish ","3 BHK ,1500 SQFK , Mahendra Chaudhary");


        if (ActivityCompat.checkSelfPermission(map.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            getLocation(gMap);
        } else {
            //When permission denied
            ActivityCompat.requestPermissions(map.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }



        for (int i=0;i<arrayList.size();i++){
            gMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(list_users.get(i)));
            gMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }



//        gMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//
//                MarkerOptions markerOptions=new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title(latLng.latitude+":"+latLng.longitude);
//                gMap.clear();
//                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                gMap.addMarker(markerOptions);
//
//
//            }
//        });

    }
}
