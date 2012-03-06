package com.androidnatic.maps.demo.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.util.Log;

import com.androidnatic.maps.HeatMapOverlay;
import com.androidnatic.maps.SimpleMapView;
import com.androidnatic.maps.demo.R;
import com.androidnatic.maps.demo.ws.WebServiceMap;
import com.androidnatic.maps.events.PanChangeListener;
import com.androidnatic.maps.model.HeatPoint;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

public class HeatMapActivity extends MapActivity {

	//private MapExDBHelper helper;
	private HeatMapOverlay overlay;
	private boolean calculated;
	private Vector<HeatPoint> heatpoints;
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		//this.helper = new MapExDBHelper(this);
		setContentView(R.layout.heat);
		final SimpleMapView mapview = (SimpleMapView)findViewById(R.id.mapview);
		this.overlay = new HeatMapOverlay(75, mapview);
		mapview.getOverlays().add(overlay);
		calculated = false;
		
		mapview.addPanChangeListener(new PanChangeListener() {
			
			@Override
			public void onPan(GeoPoint old, GeoPoint current) {
				if(!calculated){
					WebServiceMap ws = new WebServiceMap();
					heatpoints = ws.getHeatMap(mapview.getBounds());
					calculated =true;
				}
				List<HeatPoint> points = loadPoints(heatpoints);
				if(points.size() > 0){
					overlay.update(points);
				}
				
			}
		});
	}


public List<HeatPoint> loadPoints(Vector<HeatPoint> heatpoints){
	
	List<HeatPoint> points = new ArrayList<HeatPoint>(heatpoints.size());
	for(int i = 0; i<heatpoints.size();i++){
		HeatPoint point = new HeatPoint();
			point.lon = (heatpoints.get(i).lon);
			point.lat =  (heatpoints.get(i).lat);
			point.intensity = heatpoints.get(i).intensity;
			Log.i("", ""+point.intensity);
			points.add(point);
	
	}
	return points;
}
}