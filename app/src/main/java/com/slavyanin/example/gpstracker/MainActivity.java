package com.slavyanin.example.gpstracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Drawer drawerResult;
    private SupportMapFragment map;
    private TextView trackerLat, trackerLon, localLat, localLon, speed, tvDistance;
    private LocationManager locationManager;
    private GpsData gpsData = new GpsData();

    private double testLatTracker = gpsData.getLatitude(), testLonTracker = gpsData.getLongtitude();
    private double testLatLocal = 46.47747, testLonLocal = 30.73262;

    private final LatLng TRACKER = new LatLng(testLatTracker, testLonTracker);

    Button callButton;
    MySettings settings = new MySettings();
    GpsTracker tracker = new GpsTracker();
    Calculate calculate = new Calculate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToolbar();

        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        callButton();
        fillTextView();


    }

    private void fillTextView() {
        //TODO Hardcode!:
        trackerLat = (TextView) findViewById(R.id.tvLatitudeTracker);
        trackerLat.setText(calculate.getFormatGPS(testLatTracker, "latitude"));
        trackerLon = (TextView) findViewById(R.id.tvLongtitudeTracker);
        trackerLon.setText(calculate.getFormatGPS(testLonTracker, "longtitude"));

        localLat = (TextView) findViewById(R.id.tvLatitudeLocal);
        localLat.setText(calculate.getFormatGPS(testLatLocal, "latitude"));

        localLon = (TextView) findViewById(R.id.tvLongtitudeLocal);
        localLon.setText(calculate.getFormatGPS(testLonLocal, "longtitude"));

        speed = (TextView) findViewById(R.id.tvSpeedData);
        speed.setText(": " + String.format("%.3f", gpsData.getSpeed()) + " km/h");

        tvDistance = (TextView) findViewById(R.id.tvDistanceData);
        tvDistance.setText(": " + String.format("%.3f", calculate.getDistance(testLatTracker, testLonTracker, testLatLocal, testLonLocal)) + " km");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void showLocation(Location location) {
        if (location == null) {
            return;
        }
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            localLat.setText(myLatitudeFormat(location));
            localLon.setText(myLongtitudeFormat(location));
        } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            localLat.setText(myLatitudeFormat(location));
            localLon.setText(myLongtitudeFormat(location));
        }
    }

    private String myLatitudeFormat(Location location) {
        if (location == null) return "";
        return String.format("%1$.6f", location.getLatitude());
    }

    private String myLongtitudeFormat(Location location) {
        if (location == null) return "";
        return String.format("%1$.6f", location.getLongitude());
    }

    private void callButton() {
        callButton = (Button) findViewById(R.id.btnCall);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cellNumber = tracker.getCellNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", cellNumber, null));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private void addToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initializeNavigationDrawer(toolbar);

    }

    @Override
    public void onBackPressed() {
        if (drawerResult != null && drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initializeNavigationDrawer(Toolbar toolbar) {
        AccountHeader headerResult = createAccountHeader();

        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColor(getResources().getColor(R.color.md_blue_grey_100))
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        initializeDrawerItems()
                )
                .build();
    }


    @NonNull
    private IDrawerItem[] initializeDrawerItems() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(R.string.nav_menu_item_home)
                .withIdentifier(1)
                .withIcon(R.drawable.ic_home_black_18dp),
                new DividerDrawerItem(),
                new SecondaryDrawerItem()
                        .withName(R.string.nav_menu_item_history)
                        .withIcon(R.drawable.ic_change_history_black_18dp)
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(MainActivity.this, SmsListActivity.class);
                        startActivity(intent);
                        return false;
                    }
                })
                ,
                new SecondaryDrawerItem()
                        .withName(R.string.nav_menu_item_settings)
                        .withIcon(R.drawable.ic_settings_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.nav_menu_item_gprsSettings)
                        .withIcon(R.drawable.ic_settings_remote_black_18dp)
        };
    }

    private AccountHeader createAccountHeader() {
        IProfile profile = new ProfileDrawerItem()
                .withName("GPS Tracker - TK102")
                .withIcon(R.drawable.ic_verified_user_black_18dp);

        return new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.wall)
                .addProfiles(profile)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(TRACKER)
                .zoom(settings.getMapZoom())
                .bearing(settings.getMapBearing())
                .tilt(settings.getMapTilt())
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.setMapType(settings.getMapType());
        map.getUiSettings().setCompassEnabled(settings.isMapCompas());
        map.getUiSettings().setZoomControlsEnabled(settings.isMapZoomControl());
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red_18dp))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(testLatTracker, testLonTracker)));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(settings.isMyLocation());
    }
}
