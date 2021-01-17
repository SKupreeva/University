package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection
{
    private Connection myConnection;

    public DBConnection(){}

    public void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection=DriverManager.getConnection("jdbc:mysql://localhost/course_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT", "root", "12345678");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public Connection getMyConnection()
    {
        return myConnection;
    }

    public void close(ResultSet rs)
    {
        if(rs !=null)
        {
            try
            {
                rs.close();
            }
            catch(Exception e){System.out.println(e.getLocalizedMessage());}
        }
    }

    public void close(java.sql.Statement stmt)
    {
        if(stmt !=null)
        {
            try
            {
                stmt.close();
            }
            catch(Exception e){System.out.println(e.getLocalizedMessage());}
        }
    }

    public void destroy()
    {
        if(myConnection !=null)
        {
            try
            {
                myConnection.close();
            }
            catch(Exception e){System.out.println(e.getLocalizedMessage());}
        }
    }
}

