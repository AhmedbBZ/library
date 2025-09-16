package DAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner.ListEditor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entitees.Client;

public class RevenusDB {
	public static int ReadRevenu(String File){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(File)){
            Object obj = parser.parse(reader);
            JSONArray List = (JSONArray) obj;

            return (int) (parseint((JSONObject) List.get(0)));
        } catch (IOException | ParseException e) {
            try (FileWriter file = new FileWriter(File)) {
                file.write("[]");
                file.flush();
                return 0;
            } catch (IOException x) {
                x.printStackTrace();
            }
        }
        return 0;
    }
    public static void Write(String File,int x){
        try (FileWriter file = new FileWriter(File)) {
            file.write("[{\"Money\":{\"revenu\":"+x+"}}]");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
}
    
	public static long parseint(JSONObject a) {
		JSONObject MoneyObject = (JSONObject) a.get("Money");
		return (long) MoneyObject.get("revenu");
		
	}


}
