package DAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entitees.Client;


public class ClientDB {
	public static Client[] ReadClient(String File){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(File)){
            Object obj = parser.parse(reader);
            JSONArray List = (JSONArray) obj;
            Client[] Result = new Client[List.size()];
            for(int i = 0;i<List.size();i++){
                Result[i] = parseClient((JSONObject)List.get(i));
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

    public static void Write(String File,Client[] x){
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
    private static Client parseClient(JSONObject Client)
    {
        JSONObject CO = (JSONObject) Client.get("Client");

        long cin = (long) CO.get("cin");
        String nom = (String) CO.get("nom");
        String prenom = (String) CO.get("prenom");
        String date_naissance = (String) CO.get("date_naissance");
        String adress = (String) CO.get("adress");
        long num_tel = (long) CO.get("num_tel");
        boolean premium = (boolean ) CO.get("premium");
        boolean  suspendu = (boolean ) CO.get("suspendu");
        String livres = (String) CO.get("livres");
        String Abannement = (String) CO.get("Abannement");

 return new Client(nom, prenom, cin, date_naissance, adress, num_tel, premium, suspendu,livres,Abannement);
}}