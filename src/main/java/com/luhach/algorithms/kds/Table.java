package com.luhach.algorithms.kds;

import com.luhach.algorithms.commands.DatabaseCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class Table {

    private static final Logger LOG = LoggerFactory.getLogger(Table.class);
    class Node{
        HashMap columnMap;
        int rows;
        int columnCount;
        Node(HashMap columnMap,int rows,int columnCount){
            this.columnMap=columnMap;
            this.rows=rows;
            this.columnCount=columnCount;
        }
        Map<String, String[]> getColumnMap(){
            return columnMap;
        }
        int getRows(){
            return rows;
        }

        int getColumnCount(){
            return columnCount;
        }
        void setRows(int row){
            this.rows=row;
        }

    }
    HashMap<String,Node> table= new HashMap<>();
    //HashMap<String, String[]> columnsObj ;

    public void create(String name, String[] columns){
        if(table.get(name)!=null) {
            System.out.println("Table already exist");
            return;
        }
        if (columns.length>0){
            HashMap<String, String[]> tableColumnMap = new HashMap<String, String[]>();
            for (String eachColumn:columns) {
                String[] ColumnsArray = new String[10] ;
                LOG.info("Array:{}",ColumnsArray);
                tableColumnMap.put(eachColumn, ColumnsArray);
            }
            table.put(name,new Node(tableColumnMap,0,columns.length));
            LOG.info("Table Created:{}",tableColumnMap.toString());
        }

    }

    public void desc(String name){
        System.out.println(table.get(name).getColumnMap().toString());
    }

    public void insert(String name,String columns,String values){
        Node tableObject = table.get(name);
//        table.get(name).getColumnMap().get("name")[0]="Kushdev";

        int currentRowCursor= tableObject.getRows();
        LOG.info("Inserting Array:{} ",tableObject.getColumnMap().get("name").toString());
//        HashMap tabColumnMap =tableObject.getColumnMap().get("name");

        String[] columnsOfTab= columns.split(",");
        LOG.info(Arrays.toString(columnsOfTab));

        String[] valuesOfColumns= values.split(",");
        LOG.info(Arrays.toString(valuesOfColumns));

            for (int j=0;j<tableObject.columnCount;j++){ // insert for each column
                LOG.info("Inserting Row:{} into Column:{} Value :{} ",currentRowCursor++,columnsOfTab[j],valuesOfColumns[j]);
//                String[] ColumnArray =  (String[]) tabColumnMap.get(columnsOfTab[j]);
//                ColumnArray[currentRowCursor]=valuesOfColumns[j];
                currentRowCursor++;
            }
        tableObject.setRows(currentRowCursor);

    }

    public void select(String name){
        Node tableObject = table.get(name);
        int currentRowCursor= tableObject.getRows();
        Map<String, String[]> tableColumns= tableObject.getColumnMap();
        for (int j=1;j<currentRowCursor;j++){
            for (String eachColumn:tableColumns.keySet()) {
                System.out.println(tableColumns.get(eachColumn)[j]);
            }
        }

    }

}
