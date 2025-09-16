package DAO;
import entitees.*;

import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class LibraryDB {
	public static ArmoireObject[] ReadBooks(String File){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(File)){
            Object obj = parser.parse(reader);
            JSONArray List = (JSONArray) obj;
            ArmoireObject[] Result = new ArmoireObject[List.size()];
            for(int i = 0;i<List.size();i++){
                Result[i] = parseBookObject((JSONObject)List.get(i));
            }
            return Result;
        } catch (IOException | ParseException e) {
            try (FileWriter file = new FileWriter(File)) {
                file.write("[]");
                file.flush();
                return null;
            } catch (IOException x) {
                x.printStackTrace();
            }
        }
        return null;
    }

    public static void Write(String File,ArmoireObject[] x){
        if(x!=null){
            String ch = "";
            for(int i = 0;i<x.length;i++){
                if(x[i]!=null)ch+=x[i].toString();
                if(i!= x.length-1) ch+=",";
            }
            try (FileWriter file = new FileWriter(File)) {
                file.write("["+ch+"]");
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try (FileWriter file = new FileWriter(File)) {
                file.write("[]");
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static ArmoireObject parseBookObject(JSONObject Book)
    {
        JSONObject BookObject = (JSONObject) Book.get("Entite");

        String Type = (String) BookObject.get("type");
        String Name = (String) BookObject.get("titre");
        String Description = (String) BookObject.get("description");
        String type = (String) BookObject.get("genre");
        String Editeur = (String) BookObject.get("editeur");
        long Year = (long) BookObject.get("anne");
        long ISBN = (long) BookObject.get("ISBN");
        long NbrCopie = (long) BookObject.get("nbr_copie");
        long NbrPages = (long) BookObject.get("nbr");
        String Authors = (String) BookObject.get("auteur");

        if(Objects.equals(Type, "DVD")){
            return new DVD (Name,Authors,(int)Year,(int)ISBN,Description,type,(int)NbrCopie,(int)NbrPages,Editeur);
        }
        if (Objects.equals(Type, "Livre")){
        	return new Livre(Name,Authors,(int)Year,(int)ISBN,Description,type,(int)NbrCopie,(int)NbrPages,Editeur);
        }
        if (Objects.equals(Type, "Magazine")) {
        	return new Magazine(Name,Authors,(int)Year,(int)ISBN,Description,type,(int)NbrCopie,(int)NbrPages,Editeur);
        }
        else {
        	return new Filme(Name,Authors,(int)Year,(int)ISBN,Description,type,(int)NbrCopie,(int)NbrPages,Editeur);
		}
    }
}
