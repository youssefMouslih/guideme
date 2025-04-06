/**
 * Experience Map Fragment
 * Handles location services and map display functionality
 * Uses OSMDroid for map rendering and location tracking
 */
package com.ysf.mslh.guideme.fragmentsExperience;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.ysf.mslh.guideme.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ExperienceMap extends Fragment {

    private MapView mapView;
    private FusedLocationProviderClient fusedLocationClient;
    private GeoPoint userLocation;
    private final GeoPoint destination = new GeoPoint(31.6300, -8.0089); // Marrakech
    private static final String OSRM_API_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_map, container, false);

        // Initialize the map
        mapView = view.findViewById(R.id.osm_map);
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Initialize location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        // Check for location permissions
        requestLocationPermission();

        return view;
    }

    // Check and request location permissions
    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
        } else {
            getUserLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                getUserLocation();
            } else {
                Log.e("Location", "Permission denied!");
            }
        }
    }

    // Check if GPS is enabled
    private boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    // Get the last known location
    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        if (!isGPSEnabled()) {
            Toast.makeText(requireContext(), "Please enable GPS to get your location", Toast.LENGTH_LONG).show();
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                userLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
                Log.d("Location", "User location: " + userLocation.getLatitude() + ", " + userLocation.getLongitude());

                // Fetch the route from the user location to the destination
                fetchRoute(userLocation, destination);

                // Update the map with the user's location
                requireActivity().runOnUiThread(() -> {
                    mapView.getController().setZoom(10.0);
                    mapView.getController().setCenter(userLocation);

                    // Add user marker
                    addMarker(userLocation, "You are here");

                    // Add destination marker
                    addMarker(destination, "Destination");
                });
            } else {
                Log.e("Location", "Unable to retrieve location!");
                requestRealTimeLocation();
            }
        }).addOnFailureListener(e -> {
            Log.e("Location", "Error fetching location: " + e.getMessage());
            requestRealTimeLocation();
        });
    }

    // Request real-time location updates
    @SuppressLint("MissingPermission")
    private void requestRealTimeLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000); // Update every 5 seconds

        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) return;
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        userLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
                        addMarker(userLocation, "You are here");
                        fetchRoute(userLocation, destination);
                        mapView.getController().setCenter(userLocation);

                        fusedLocationClient.removeLocationUpdates(this); // Stop after getting the first location
                    }
                }
            }
        }, Looper.getMainLooper());
    }

    // Add marker to the map
    private void addMarker(GeoPoint point, String title) {
        requireActivity().runOnUiThread(() -> {
            Marker marker = new Marker(mapView);
            marker.setPosition(point);
            marker.setTitle(title);
            mapView.getOverlays().add(marker);
            mapView.invalidate();
        });
    }

    // Fetch route from OSRM
    private void fetchRoute(GeoPoint start, GeoPoint end) {
        String url = OSRM_API_URL + start.getLongitude() + "," + start.getLatitude() + ";" + end.getLongitude() + "," + end.getLatitude() + "?overview=full&geometries=polyline";

        // Async request to OSRM API
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                String jsonResponse = response.body().string();

                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray routes = jsonObject.getJSONArray("routes");
                if (routes.length() > 0) {
                    JSONObject route = routes.getJSONObject(0);
                    JSONArray geometry = route.getJSONObject("geometry").getJSONArray("coordinates");

                    // Create list of geo points for the polyline
                    List<GeoPoint> path = new ArrayList<>();
                    for (int i = 0; i < geometry.length(); i++) {
                        JSONArray point = geometry.getJSONArray(i);
                        path.add(new GeoPoint(point.getDouble(1), point.getDouble(0)));
                    }

                    // Draw the route on the map
                    requireActivity().runOnUiThread(() -> {
                        Polyline polyline = new Polyline();
                        polyline.setPoints(path);
                        polyline.setColor(0x800000FF); // Semi-transparent blue
                        polyline.setWidth(8.0f);
                        mapView.getOverlays().add(polyline);
                        mapView.invalidate();
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDetach();
    }
}
