package com.example.tokoro_10120175.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.tokoro_10120175.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
/*
NIM     : 10120175
Nama    : I Wayan Widi Pastika
Kelas   : IF-5
22 Juni 2023
*/
public class NearbyPlacesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat, lng;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nearby);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getCurrentLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            return;
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {

                if (locationResult == null) {
                    return;
                }

                for (Location location : locationResult.getLocations()) {
                    if (location != null) {

                        lat = location.getLatitude();
                        lng = location.getLongitude();

                        LatLng latLng = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                        StringBuilder stringBuilder = new StringBuilder("https://serpapi.com/search.json?" +
                                "engine=google_maps&"+
                                "q=restaurant&"+
                                "ll=@"+lat+","+lng+",19z&"+
                                "type=search"+
                                "&api_key=c5257ddcb5b3d6bbeb6b22fe85013dd26b59a55ecb7ae76c7ce9ba986470691e");

                        String url = stringBuilder.toString();
                        Object[] dataFetch = new Object[2];
                        dataFetch[0] = mMap;
                        dataFetch[1] = url;

                        FetchData fetchData = new FetchData();
                        fetchData.execute(dataFetch);
                        Toast.makeText(NearbyPlacesActivity.this, "searching..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (Request_code) {
            case Request_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
        }
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }
}