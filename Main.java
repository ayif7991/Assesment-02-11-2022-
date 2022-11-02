import java.io.*;
import java.sql.Array;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {

        JSONParser parser = new JSONParser();

           //Retrieve data from the file

            Object obj = parser.parse(new FileReader("C:\\Users\\Alfiya\\IdeaProjects\\KotugTest\\src\\port_arthur.json"));
        JSONObject jsonObject = (JSONObject) obj;
            JSONArray data= (JSONArray) jsonObject.get("data");
            HashSet<Long> uniqueVessels = new HashSet<Long>();
            HashSet<Long> uniqueTugs = new HashSet<Long>();
            List<DataHandler> records =new ArrayList<DataHandler>();


        //Distinguish data based on mmsi and store it separately.

            for (Object record: data ) {
                JSONObject recordJson=(JSONObject) record;
                records.add(new DataHandler(recordJson));
                Long mmsi =(Long) ((JSONObject)recordJson.get("device")).get("mmsi");
                String type = (String) ((JSONObject)recordJson.get("vessel")).get("type");

                //Identify and label tug boats and big vessels based on type from the stored data

                if(type.equals("tug"))
                    uniqueTugs.add(mmsi);
                else
                    uniqueVessels.add(mmsi);
            }
            HashMap<Long,List<DataHandler>> tugBoatData = new HashMap<>();
            List<DataHandler> temp = new ArrayList<DataHandler>();

            //Get the collection of records of tugboat and store it
            System.out.println("MMSI OF TUGBOATS");
//            for (Long tug:uniqueTugs) {
//                for (int i=0;i<records.size();i++)
//                {
//                    if(tug.equals(records.get(i).mmsi))
//                        temp.add(records.get(i));
//                }
        //----------------------------------------------------------------//
        for (Long tug:uniqueTugs) {
                for (int i=0;i<records.size();i++)
                {
                    if(tug.equals(records.get(i).mmsi))
                        temp.add(records.get(i));
                }

            System.out.println(tug);
                /*----------------------------OUTPUT--------------------*/
//                MMSI OF TUGBOATS
//                367774360
//                367182980
//                367561460
//                367183020

                //Sort the tug data of each vessel based on the timestamp.

                  Collections.sort(temp, new Comparator<>() {
                    @Override
                    public int compare(DataHandler o1, DataHandler o2) {
                        return o2.date.compareTo(o1.date);
                    }
                  });

                tugBoatData.put(tug,temp);
                temp = new ArrayList<DataHandler>();

            }

            HashMap<Long,List<DataHandler>> vesselsData = new HashMap<>();
            temp = new ArrayList<DataHandler>();

        //Get the collection of records of vessels and store it
        System.out.println("MMSI OF VESSELS");
        for (Long vessel:uniqueVessels) {
                for (int i=0;i<records.size();i++)
                {
                    if(vessel.equals(records.get(i).mmsi))
                        temp.add(records.get(i));
                }


            System.out.println(vessel);
                /*----------------------------OUTPUT--------------------*/
//            MMSI OF VESSELS
//            224941000
//            351328000
//            232020054
//            235062848
//            310744000
//            338619000
//            357449000
//            563495000
//            311000646

            //Sort the vessel data of each vessel based on the timestamp.

            Collections.sort(temp, new Comparator<>() {
                    @Override
                    public int compare(DataHandler o1, DataHandler o2) {
                        return o2.date.compareTo(o1.date);
                    }
                });
                vesselsData.put(vessel,temp);
                temp = new ArrayList<DataHandler>();



           }
       }
}