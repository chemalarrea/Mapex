package com.androidnatic.maps.demo.ws;

import java.io.IOException;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import com.androidnatic.maps.model.HeatPoint;


import android.util.Log;


@SuppressWarnings("deprecation")
public class WebServiceMap {
	
	
   	// Here package name inwebservice with reverse order.
	final String NAMESPACE = "http://server.ws.palyque.com"	;
	// you must use ipaddress here, don’t use Hostname or localhost
	final String URL = "http://palyque.com:8080/axis2/services/WS_test2?wsdl";//"http://palyque.com:8080/axis2/services/WS_test?wsdl"; //probar con http://www.palyque.com:8080
	//212.34.129.204


	/**
	 * Repasar porque a lo mejor no hay que incluirlo
	 * @param latitude
	 * @param longitude
	 * @param max
	 * @param level
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<HeatPoint> getHeatMap(int[][] bounds){
		String METHOD_NAME = "getHeatMap"; // our webservice method name
    	// here yourpackage name in webservice but in reverse order + method name
    	String SOAP_ACTION = METHOD_NAME;  //"http://www.palyque.com/" + METHOD_NAME; 
    	Vector<HeatPoint> result = new Vector<HeatPoint>();
    	Vector<?> v = null;

    	 try {
             //---doing some work---
         	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
         	
         	for(int i = 0; i< bounds[0].length;i++){
         		for(int j = 0; j<bounds[1].length;j++){
         			Log.i("", "bounds1"+bounds[i][j]+"-"+bounds[i][j]/1E6);
             		request.addProperty("bounds", ""+bounds[i][j]/1E6);
         		}         	
         	}
     		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
     		envelope.dotNet = true;
     		
     		envelope.setOutputSoapObject(request);

			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
     		androidHttpTransport.call(SOAP_ACTION,envelope);
		    Log.i("WebService",  "llamando a "+METHOD_NAME);
     		Object res =	envelope.getResponse();
		    if(res!=null){
			    Log.i("WebService",  "Result:"+res.toString());
			    if(res.getClass().toString().compareTo(Vector.class.toString())==0){
		    		v = (Vector<HeatPoint>) res ;
		    		Log.i("", "bounds1 v.size():"+v.size());
		    		for (int i=0;i<v.size();i++){
		     			SoapObject a = (SoapObject) v.get(i);
		     			HeatPoint mc = new HeatPoint();
		     			Log.i("", "num i:"+i);
		     			for(int j = 0; j<mc.getPropertyCount();j++){
		     				Log.i("", "num j:"+i);
		     				mc.setProperty(j, a.getProperty(j));
		     			}
		     			//for(int j=0;j<mc.intensity;j++){
		     				result.add(mc);
		     				//	}

		    		} 
     				Log.i("", "bounds1 result"+result.size());

			    }else{
			    	SoapObject a = (SoapObject) res;
			    	HeatPoint mc = new HeatPoint();
			    	for(int j = 0; j<mc.getPropertyCount();j++)
	     				mc.setProperty(j, a.getProperty(j));
			    	result.add( (HeatPoint) mc);
			    }
		    }
         } catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
