package com.example.talma;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

public class TablaDinamica {

    private TableLayout tableLayout;
    private Context context;
    private String [] header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multicolor = false;
    int firstColor, secondColor, textcolor;

    public TablaDinamica(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader (String [] header){
        this.header = header;
        createHeader();
    }

    public void addData (ArrayList<String[]> data){
        this.data = data;
        createDataTable();
    }

    private void newRow (){
        tableRow = new TableRow(context);
    }

    private void newCell (){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(18);
    }

    private void createHeader(){
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(){
        String info;
        for(indexR = 1; indexR <= header.length; indexR++){
            newRow();
            for(indexC = 0; indexC <= header.length; indexC++){
                newCell();
                String [] row = data.get(indexR - 1);
                info = (indexC < row.length)? row[indexC] : "";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    public void addItem(String [] item){
        String info;
        data.add(item);
        indexC = 0;
        newRow();

        while (indexC < header.length){
            newCell();
            info = (indexC < item.length)? item[indexC++] : "";
            txtCell.setText(info);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size()-1);
        reColoring();

    }

    public void backgroundHeader(int color){
        indexC = 0;
        while (indexC < header.length){
            txtCell = getCell(0, indexC++);
            txtCell.setBackgroundColor(color);
        }
    }

    public void backgroundData(int firstColor, int secondColor){
        for(indexR = 1; indexR <= header.length; indexR++){
           multicolor = !multicolor;
            for(indexC = 0; indexC <= header.length; indexC++){
                txtCell = getCell(indexR, indexC);
                txtCell.setBackgroundColor((multicolor)? firstColor: secondColor);
            }
        }
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public void lineColor (int color){
        indexR = 0;
        while (indexR < data.size()){
            getRow(indexR++).setBackgroundColor(color);
        }
    }

    public void textColorData (int color){
        for(indexR = 1; indexR <= header.length; indexR++){
            for(indexC = 0; indexC <= header.length; indexC++)
                 getCell(indexR, indexC).setTextColor(color);

            this.textcolor = color;
        }
    }

    public void textColorHeader (int color){
        indexC = 0;
        while (indexC < header.length)
            getCell(0, indexC++).setTextColor(color);
    }

    public void reColoring(){
        indexC = 0;
        multicolor = !multicolor;
        while (indexC < header.length){
            txtCell = getCell(data.size() - 1, indexC++);
            txtCell.setBackgroundColor((multicolor)? firstColor: secondColor);
            txtCell.setTextColor(textcolor);
        }
    }

    private TableRow getRow(int index){
        return (TableRow) tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex, int columIndex){
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columIndex);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }
}
