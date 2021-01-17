package com.company;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentModel extends AbstractTableModel {

    private int colnum=4;
    private int rownum;

    private String[] colNames =
            {
                    "StudentID", "StudentName","StudentSurname","StudentGroupNumber"
            };

    private ArrayList<String[]> ResultSets;

    public StudentModel(ResultSet rs)
    {
        ResultSets=new ArrayList<>();
        try
        {
            while(rs.next())
            {
                String[] row =
                        {
                                rs.getString("F_ID"),
                                rs.getString("SName"),
                                rs.getString("SurName"),
                                rs.getString("GroupNumber")
                        };
                ResultSets.add(row);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Object getValueAt(int rowindex, int columnindex)
    {
        System.out.println(rowindex+" + "+columnindex);
        String[] row = ResultSets.get(rowindex);
        return row[columnindex];
    }

    @Override
    public int getRowCount()
    {
        return ResultSets.size();
    }

    @Override
    public int getColumnCount()
    {
        return colnum;
    }

    @Override
    public String getColumnName(int param)
    {
        return colNames[param];
    }


}
