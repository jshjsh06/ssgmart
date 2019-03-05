package sinc.com.ssgmartapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.dto.Item;
import sinc.com.ssgmartapp.dto.Location;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.remote.IMenuRequest;

/**
 * 이마트 24 검색 Activity
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MarkerOptions markerOptions;
    private List<Location> emart24_location;
    private final String URL_API = "https://ssg-mart-app.firebaseio.com/location.json";
    IMenuRequest mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mService = Common.getMenuRequest();
        emart24_location = new ArrayList<>();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        getLocationData(googleMap);

        mMap = googleMap;

        //신세계 아이앤씨 좌표
        LatLng s_inc = new LatLng(37.5597219, 126.9823744);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(s_inc));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);

        markerOptions = new MarkerOptions();

        markerOptions.position(s_inc).title("신세계_아이앤씨");
        markerOptions.snippet("본사");
        mMap.addMarker(markerOptions).showInfoWindow();


    }

    /**
     * 19/03/05 (위진학)
     * 이마트24 위치 정보 가져와서 리스트에 담기
     */
    private void getLocationData(final GoogleMap googleMap) {
        mService.getLocationList(URL_API)
                .enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        emart24_location.clear();
                        emart24_location.addAll(response.body());
                        drawEmartMarker(googleMap);
                    }

                    @Override
                    public void onFailure(Call<List<Location>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/05 (위진학)
     * 이마트24 위치정보 가져와서 지도에 그리기
     */
    private void drawEmartMarker(GoogleMap googleMap) {

        for (int i = 0; i < emart24_location.size(); i++) {
            String emartName = emart24_location.get(i).getName();
            String emartAdress = emart24_location.get(i).getAddress();
            double emartLat = Double.parseDouble(emart24_location.get(i).getLat());
            double emartLon = Double.parseDouble(emart24_location.get(i).getLon());

            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(emartLat, emartLon))
                    .title(emartName)
                    .snippet(emartAdress);
            googleMap.addMarker(marker).showInfoWindow(); // 마커추가,화면에출력
        }
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("marker_location", marker.getTitle());
                startActivity(intent);            }
        });

    }

    /**
     * 19/03/05 (위진학)
     * 지도검색
     */
    public void onMapSearch(View view) {
        EditText locationSearch = findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location.equals("")) {
            Toast.makeText(getApplicationContext(), "위치를 넣어주세요.", Toast.LENGTH_SHORT).show();
        } else {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            markerOptions = new MarkerOptions();
            markerOptions.position(latLng).title(location);
            mMap.addMarker(markerOptions).showInfoWindow();
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


        }
    }


}