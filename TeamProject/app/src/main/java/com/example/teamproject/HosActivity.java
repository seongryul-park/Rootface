package com.example.teamproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class HosActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private EditText editText;
    private Button button, back;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos);

        // 위치 서비스 초기화
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // 지도 객체 얻기
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        editText = findViewById(R.id.mapcheck);
        button = findViewById(R.id.buttoncheck);
        back = findViewById(R.id.buttonback);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mMap != null) {
            // 지도 관련 코드 실행

        mMap = googleMap;
        super.onCreate(savedInstanceState);

        // 위치 권한 확인 및 요청
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
            return;
        }

        // 현재 위치 가져오기
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Location location = task.getResult();
                LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());

                // 지도를 현재 위치로 이동
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f));

                // 현재 위치 표시 마커 추가
                Marker marker = mMap.addMarker(new MarkerOptions().position(myLocation).title("현재 위치"));
            }
        });

        }

        // 버튼 클릭 시 주소를 검색하여 해당 위치를 지도에 표시
        button.setOnClickListener(v -> {
            String address = editText.getText().toString();
            if (!address.isEmpty()) {
                int number = Integer.parseInt(address);
                int result = number % 2;
                if (result == 0){
                    editText.setText("해당자입니다.");
                }
                else {
                    editText.setText("해당되지 않습니다.");
                }
            }
        });

        back.setOnClickListener(v -> finish());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onMapReady(mMap); // 지도 준비 완료 시 호출되는 메서드 다시 실행
            } else {
                // 위치 권한 없이 실행할 수 없는 기능을 알림
            }
        }
    }

}