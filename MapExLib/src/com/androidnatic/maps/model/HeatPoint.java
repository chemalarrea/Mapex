/*
 * Copyright (C) 2011 by Vinicius Carvalho (vinnie@androidnatic.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.androidnatic.maps.model;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * @author evincar
 *
 */
public class HeatPoint  implements KvmSerializable{
	public HeatPoint(double lat, double lon, int intensity) {
		this.intensity = intensity;
		this.lat = lat;
		this.lon = lon;
	}
	public int intensity;
	public double lat;
	public double lon;
	
	public HeatPoint(){
		this(0,0,0);
	}
	
	public HeatPoint(float lat, float lon){
		this(lat,lon,1);
	}

	@Override
	public Object getProperty(int arg0) {
	     switch(arg0)
	        {
		        case 0: return intensity;
		        case 1: return lat ;
		        case 2: return lon;
	        }
	        return null;		
	}

	@Override
	public int getPropertyCount() {
		return 3;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		  switch(arg0)
		    {
		    case 0:
		    	arg2.type = PropertyInfo.INTEGER_CLASS;
		        arg2.name = "intensity";
		        break;		        
		    case 1:
		    	arg2.type = Double.class;
		        arg2.name = "lat";
		        break;
		    case 2:
		        arg2.type = Double.class;
		        arg2.name = "lon";
		        break;
		    default:break;
		    }				
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch(arg0)
	    {
	    case 1:
	    	lat =  Double.parseDouble(arg1.toString());
	        break;
	    case 2:
	    	lon = Double.parseDouble(arg1.toString());
	        break;
	    case 0:
	    	intensity = Integer.parseInt(arg1.toString());
	        break;
	    default:
	        break;
	    }				
	}
	
}
