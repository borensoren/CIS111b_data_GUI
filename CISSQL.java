/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cissql;

import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * 
 * @author jmclaughlin
 */ 
public class CISSQL {
    
    protected String[] query;
    protected String schema;
    protected String tableName;
    
   /**
    * Creates a SQL query object.
    * @param str Array containing the search terms
    * @param s 
    * @param t 
    */
    
    public CISSQL(String[] str, String s, String t){
        query = str;
        schema = s;
        tableName = t;
    }
           
    
    public String getSQL(boolean check, String search,String table){
        
        Boolean contains = check;
        String start;
        String returner = "('";
        String column_name = search;
        String tableName = table;
        
        for(int i = 0; i < query.length; i++)
        {
            returner = returner+query[i]+"', '";            
        }
            
        returner = returner.substring(0, returner.length()-3) +");";
        
        if(contains){start = "in";}
        else{start = "not in";}
        
        returner = "select * from "+tableName+ " where "+column_name+" "+start+returner;
        
        return returner;
    }
    
    
    public String getSQL(String search,String table){
        
        String start;
        String returner = "('";
        String column_name = search;
        String tableName = table;
        
        for(int i = 0; i < query.length; i++)
        {
            returner = returner+query[i]+"', '";            
        }
            
        returner = returner.substring(0, returner.length()-3) +");";

        returner = "select * from "+tableName+ " where "+column_name+" in"+returner;
        
        return returner;
    }
    
    
    
    public String getSQL(String search){
        
        String returner = "('";
        String column_name = search;

        for(int i = 0; i < query.length; i++)
        {
            returner = returner+query[i]+"', '";            
        }
            
        returner = returner.substring(0, returner.length()-3) +");";
        
        
        returner = "select * from "+tableName+ " where "+column_name+" in"+returner;
        
        return returner;
    }
    
    
    /**
     * 
     * @param newTable Changes the table the CISSQL object references.
     */
    
    public void changeTable(String newTable){    
        tableName = newTable;   
    }
    
    /**
     * 
     * @param sch Changes Schema of the CISSQL object. 
     */
    public void changeSchema(String sch){        
        schema = sch;
    }
     
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String[] str = {"PA","NJ","DE"};
        String table = "STATES";
        String schema = "DBUSER";
        
        System.out.println(Arrays.toString(str));
        
        CISSQL test1 = new CISSQL(str, schema, table);
        
        System.out.println(test1.getSQL(true, "state","states"));        
        System.out.println(test1.getSQL(false, "state","states"));
        System.out.println(test1.getSQL("state","states"));
        System.out.println(test1.getSQL("states"));
    }
}
