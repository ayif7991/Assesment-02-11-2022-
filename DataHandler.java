
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DataHandler {
    double course,draught, speed,lattitude,longitude = 0.0;
    int imo,to_bow,to_stern,to_port,to_starboard =0;
    Long mmsi= Long.valueOf(0);

    String status,name,subType,callSign,type,time="";
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
    public DataHandler(JSONObject record) throws ParseException {
        JSONObject navigation =(JSONObject) record.get("navigation");
        this.course= (double) navigation.get("course");
        this.draught=(double) navigation.get("draught");
        this.speed = (double) navigation.get("speed");
        this.status = (String) navigation.get("status");
      //  this.lattitude = (double) ((JSONObject)navigation.get("location")).get("lat");
      //  this.longitude = (double) ((JSONObject)navigation.get("location")).get("long");
        this.time = (String) navigation.get("time") ;
        JSONObject vessel =(JSONObject) record.get("vessel");
        this.type =(String) vessel.get("type");
        this.name =(String) vessel.get("name");
        this.callSign =(String) vessel.get("callsign");
        JSONObject device =(JSONObject) record.get("device");
        this.mmsi = (Long) device.get("mmsi");
        //Parsing the given String to Date object
        date = formatter.parse(time);
    }
}