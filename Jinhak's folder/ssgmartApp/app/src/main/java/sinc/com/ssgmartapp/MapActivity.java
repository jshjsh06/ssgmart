package sinc.com.ssgmartapp;

import android.Manifest;
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

/**
 * 이마트 24 검색 Activity
 * <p>
 * 명동 주변 이마트24 좌표 마커로 찍어야함.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions markerOptions;
    private List<String> emart24_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        emart24_location = new ArrayList<>();
        getLocationExcel();

    }

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

    @Override
    public void onMapReady(GoogleMap googleMap) {


        for (int i = 0; i < emart24_location.size(); i++) {
            String[] emartInfo_Token = emart24_location.get(i).split("#");
            String emartAdress = emartInfo_Token[0];
            double emartLat = Double.parseDouble(emartInfo_Token[1]);
            double emartLon = Double.parseDouble(emartInfo_Token[2]);

            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(emartLat, emartLon))
                    .title(emartAdress + " 점")
                    .snippet("emart");
            googleMap.addMarker(marker).showInfoWindow(); // 마커추가,화면에출력
        }



        mMap = googleMap;

        //신세계 아이앤씨 좌표
        LatLng s_inc = new LatLng(37.5597219, 126.9823744);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(s_inc));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);

        markerOptions = new MarkerOptions();

        markerOptions.position(s_inc).title("신세계_아이앤씨, 본사");

        mMap.addMarker(markerOptions).showInfoWindow();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(getApplicationContext(), marker.getTitle() + " 클릭했음", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    public void getLocationExcel() {

        Workbook workbook;
        Sheet sheet;
        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open("emart24_location01.xls");
            workbook = Workbook.getWorkbook(inputStream);
            sheet = workbook.getSheet(0);
            int MaxColumn = 2, RowStart = 3, RowEnd = sheet.getColumn(MaxColumn - 1).length - 1,
                    ColumnStart = 0,
                    ColumnStart1 = 2,
                    ColumnStart2 = 3;
            for (int row = RowStart; row <= RowEnd; row++) {
                String emartAdress = sheet.getCell(ColumnStart, row).getContents();
                String emartLat = sheet.getCell(ColumnStart1, row).getContents();
                String emartLon = sheet.getCell(ColumnStart2, row).getContents();
                emart24_location.add(emartAdress + "#" + emartLat + "#" + emartLon);
            }
            workbook.close();

        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

}