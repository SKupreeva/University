
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection(boolean flag)
            throws ClassNotFoundException, SQLException, FileNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName +"?useUnicode=true&serverTimezone=UTC&allowMultiQueries=true";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        if(flag) {
            System.out.println("Генерация скрипта базы данных:");
            ScriptRunner runner = new ScriptRunner(dbConnection);
            runner.runScript(new BufferedReader(new FileReader("course.sql")));
        }
        return dbConnection;
    }

    public String getNumOfCompanies(){
        ResultSet rs = null;
        int count =0;
        try {
            String select = "SELECT COUNT(1) FROM "+ Const.COMPANY_TABLE;

            PreparedStatement prep = getDbConnection(false).prepareStatement(select);
            rs = prep.executeQuery();
            while (rs.next()) {
                count = rs.getInt( 1 );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    public String getCompanies(String userId){

        Company company1;
        JSONObject companyJson;
        JSONArray companies = new JSONArray(  );
        try {

            String selectFirst = "SELECT "+ Const.USERCOMPANY_COMPANY +" FROM "+Const.USERCOMPANY_TABLE +" WHERE "+ Const.USERCOMPANY_USER+
                    " = "+userId;


            PreparedStatement prep1 = getDbConnection(false).prepareStatement( selectFirst );
            ResultSet rs1 = prep1.executeQuery();
            while (rs1.next()){
                int id = rs1.getInt( "IDCompany" );
                String select = "SELECT * FROM course_schema.company WHERE IDCompany=" + id;
                PreparedStatement prep = getDbConnection(false).prepareStatement( select);
                ResultSet rs = prep.executeQuery();
                while (rs.next()) {
                    company1 = new Company();
                    company1.setID( rs.getInt( "IDCompany" ) );
                    company1.setName( rs.getString( "Name" ) );
                    company1.setAddress( rs.getString( "Address" ) );

                    companyJson = new JSONObject();
                    companyJson.put( "id", company1.getID() );
                    companyJson.put( "name", company1.getName() );
                    companyJson.put( "address", company1.getAddress() );

                    companies.put( companyJson );
                }
            }


        } catch (SQLException | ClassNotFoundException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return companies.toString();
    }

    public void addReport(Report report, int IDUser) throws ClassNotFoundException, SQLException, JSONException, FileNotFoundException {
        addUSD("{eur:2.1,byn:2.3,rub:2.4}");
        String insert = "INSERT INTO "+ Const.REPORT_TABLE +"("+
                Const.REPORTS_ID+","+ Const.REPORTS_DATE +"," +
                Const.REPORTS_IDUSD+","+ Const.X1+","+
                Const.X2+","+Const.X3+","+Const.X4+","+Const.X5+","+
                Const.X6+","+ Const.X7+","+Const.X8+","+Const.X9+","+
                Const.REPORTS_RESULT +"," + Const.REPORTS_IDCOMPANY +")"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection(false).prepareStatement(insert);
            prSt.setInt(1, report.getID());
            prSt.setString(2, report.getDate());
            prSt.setInt(3, 8);
            prSt.setDouble(4, report.getX1());
            prSt.setDouble(5, report.getX2());
            prSt.setDouble(6, report.getX3());
            prSt.setDouble(7, report.getX4());
            prSt.setDouble(8, report.getX5());
            prSt.setDouble(9, report.getX6());
            prSt.setDouble(10, report.getX7());
            prSt.setDouble(11, report.getX8());
            prSt.setDouble(12, report.getX9());
            prSt.setString(13, report.getResult());
            prSt.setInt(14, report.getIDCompany());
            prSt.executeUpdate();

            String insert2 = "INSERT INTO "+ Const.USERREPORT_TABLE+"("+
                    Const.USERREPORT_IDUSER+","+Const.USERREPORT_IDREPORT+")"+
                    "VALUES(?,?)";
            PreparedStatement prep = getDbConnection(false).prepareStatement( insert2 );
            prep.setInt( 1, IDUser );
            prep.setInt( 2, report.getID() );
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getLastRate(String type, int id){
        String name = null;
        switch (type){
            case "byn": {name = "BYNUSD"; break;}
            case "ryb": {name = "RUBUSD"; break;}
            case "eur": {name = "EURUSD"; break;}
        }
        double rate = 0;
        String select = "SELECT "+name +" FROM " + Const.USD_TABLE+ " WHERE "+ Const.USD_ID+" = " +id;
        try {
            PreparedStatement prep = getDbConnection(false).prepareStatement(select);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                rate = rs.getDouble( 1 );
            }



        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return rate;

    }

    public String signInUser(String login, String password) throws SQLException {
        JSONObject userJson = new JSONObject();
        User user = new User();
        String result =null;
        try {
            // Statement stmt = getDbConnection().createStatement();
            String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "+ Const.USERS_LOGIN +
                    "= ?";
            PreparedStatement prep = getDbConnection(false).prepareStatement(select);
            prep.setString(1, login);
            //prep.setString(2, password);
            ResultSet rs = prep.executeQuery();
            if (rs.next() == false) {
                result = "nobody";
            } else
            { do {
                user.setID(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setSurname(rs.getString(5));
                user.setName(rs.getString(4));
                user.setEmail(rs.getString(6));
                user.setPhone(rs.getString(7));

                userJson.put("id", user.getID());
                userJson.put("surname", user.getSurname());
                userJson.put("name", user.getName());
                userJson.put("login", user.getLogin());
                userJson.put("password", user.getPassword());
                userJson.put("email", user.getEmail());
                userJson.put("phone", user.getPhone());
            } while (rs.next());
                result = userJson.toString();
            }

        } catch (ClassNotFoundException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }
        //if(user.getName().equals("null")) result = user.getName();

        return result;
    }

    public String signUpUser(int id, String surname, String name, String phone, String email,
                             String login, String password) throws SQLException, ClassNotFoundException, FileNotFoundException {
        String insert = "INSERT INTO "+ Const.USER_TABLE +"("+
                Const.USERS_ID+","+ Const.USERS_SURNAME +"," +
                Const.USERS_NAME+","+ Const.USERS_EMAIL+","+
                Const.USERS_PHONE +"," + Const.USERS_LOGIN+ ","+
                Const.USERS_PASSWORD +")"+
                "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prSt = getDbConnection(false).prepareStatement(insert);
        prSt.setInt(1, id);
        prSt.setString(2, surname);
        prSt.setString(3, name);
        prSt.setString(4, email);
        prSt.setString(5, phone);
        prSt.setString(6, login);
        prSt.setString(7, password);
        prSt.executeUpdate();

        JSONObject userJson = new JSONObject();
        try {
            userJson.put("id", id);
            userJson.put("surname", surname);
            userJson.put("name", name);
            userJson.put("login", login);
            userJson.put("password", password);
            userJson.put("email", email);
            userJson.put("phone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJson.toString();
    }

    public String getReportsCompany(String companyId) {
        JSONArray array = new JSONArray(  );
        JSONObject reportJson;
        String result = "none";
        String select = "SELECT * FROM "+ Const.REPORT_TABLE + " WHERE "+Const.REPORTS_IDCOMPANY+" = " + companyId;
        PreparedStatement prep = null;
        try {
            prep = getDbConnection(false).prepareStatement(select);

            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                reportJson = new JSONObject(  );
                reportJson.put( "id", rs.getInt( "IDReport" ) );
                reportJson.put( "date", rs.getString( "Date" ) );
                reportJson.put( "result", rs.getDouble( "H" ));
                reportJson.put( "x1", rs.getDouble( "X1" ) );
                reportJson.put( "x2", rs.getDouble( "X2" ) );
                reportJson.put( "x3", rs.getDouble( "X3" ) );
                reportJson.put( "x4", rs.getDouble( "X4" ) );
                reportJson.put( "x5", rs.getDouble( "X5" ) );
                reportJson.put( "x6", rs.getDouble( "X6" ) );
                reportJson.put( "x7", rs.getDouble( "X7" ) );
                reportJson.put( "x8", rs.getDouble( "X8" ) );
                reportJson.put( "x9", rs.getDouble( "X9" ) );
                array.put( reportJson );
                result = array.toString();

            }
        } catch (SQLException | ClassNotFoundException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addCompany(String company, int IDUser) {
        try {
            JSONObject object = new JSONObject( company );
            String name = object.getString( "name" );
            String address = object.getString( "address" );
            Company company1 = new Company( name, address );

            String insert = "INSERT INTO "+ Const.COMPANY_TABLE +"("+
                    Const.COMPANY_ID+","+ Const.COMPANY_NAME +"," +
                    Const.COMPANY_ADDRESS+")"+
                    "VALUES(?,?,?)";
            PreparedStatement prSt = getDbConnection(false).prepareStatement(insert);
            prSt.setInt(1, company1.getID());
            prSt.setString(2, company1.getName());
            prSt.setString(3, company1.getAddress());
            prSt.executeUpdate();

            String insert2 = "INSERT INTO "+ Const.USERCOMPANY_TABLE+"("+
                    Const.USERCOMPANY_USER +","+Const.USERCOMPANY_COMPANY+")"+
                    "VALUES(?,?)";
            PreparedStatement prep = getDbConnection(false).prepareStatement( insert2 );
            prep.setInt( 1, IDUser );
            prep.setInt( 2, company1.getID() );
            prep.executeUpdate();
        } catch (JSONException | ClassNotFoundException | SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getUsers() {
        User user;
        JSONObject userJson;
        JSONArray users = new JSONArray(  );
        try {

            String select = "SELECT * FROM "+Const.USER_TABLE;
            PreparedStatement prep1 = getDbConnection(false).prepareStatement( select );
            ResultSet rs = prep1.executeQuery();
            while (rs.next()){

                user = new User(  );
                user.setID(rs.getInt( 1 ));
                user.setName( rs.getString( 2 ) );
                user.setSurname( rs.getString( 3 ) );
                user.setPhone( rs.getString( 5 ) );
                user.setEmail( rs.getString( 4 ) );
                user.setLogin( rs.getString( 6 ) );
                user.setPassword( rs.getString( 7 ) );

                userJson = new JSONObject(  );
                userJson.put( "id", user.getID() );
                userJson.put( "login", user.getLogin() );
                userJson.put( "name", user.getName() );
                userJson.put( "surname", user.getSurname() );
                userJson.put( "phone", user.getPhone() );
                userJson.put( "email", user.getEmail() );

                users.put( userJson );
            }


        } catch (SQLException | ClassNotFoundException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return users.toString();
    }

    public void addUSD(String newRate) throws JSONException, SQLException, ClassNotFoundException, FileNotFoundException {
        JSONObject rate = new JSONObject( newRate );
        double eur = rate.getDouble( "eur");
        double byn = rate.getDouble( "byn" );
        double rub = rate.getDouble( "rub" );
        USD usd = new USD(byn, eur, rub);
        String insert = "INSERT INTO "+ Const.USD_TABLE +"("+
                Const.USD_BYN+","+ Const.USD_EUR +"," +
                Const.USD_RUB+","+ Const.USD_ID+","+ Const.USD_DATE+")"+
                "VALUES(?,?,?,?,?)";
        PreparedStatement prSt = getDbConnection(false).prepareStatement(insert);
        prSt.setDouble(1, 2.1);
        prSt.setDouble(2, 2.3);
        prSt.setDouble(3, 2.4);
        prSt.setInt( 4, IdGenerator.getInstance( "usd" ).getNextId()+1 );
        prSt.setString( 5,usd.getDate() );
        prSt.executeUpdate();
    }

    public String getReportsUser(String userId) {
        Report report;
        JSONObject reportJson;
        JSONArray reports = new JSONArray(  );
        try {

            String selectFirst = "SELECT "+ Const.USERREPORT_IDREPORT +" FROM "+Const.USERREPORT_TABLE +" WHERE "+ Const.USERREPORT_IDUSER+
                    " = "+userId;
            PreparedStatement prep1 = getDbConnection(false).prepareStatement( selectFirst );
            ResultSet rs1 = prep1.executeQuery();
            while (rs1.next()){
                int id = rs1.getInt( "IDReport" );
                String select = "SELECT * FROM "+ Const.REPORT_TABLE + " WHERE " + Const.REPORTS_ID+" = " + id;
                PreparedStatement prep = getDbConnection(false).prepareStatement( select );
                ResultSet rs = prep.executeQuery();
                while (rs.next()) {

                    reportJson = new JSONObject();
                    reportJson.put( "id", rs.getInt( "IDReport" ) );
                    reportJson.put( "date", rs.getString( "Date" ) );
                    reportJson.put( "result", rs.getDouble( "H" ));
                    reportJson.put( "x1", rs.getDouble( "X1" ) );
                    reportJson.put( "x2", rs.getDouble( "X2" ) );
                    reportJson.put( "x3", rs.getDouble( "X3" ) );
                    reportJson.put( "x4", rs.getDouble( "X4" ) );
                    reportJson.put( "x5", rs.getDouble( "X5" ) );
                    reportJson.put( "x6", rs.getDouble( "X6" ) );
                    reportJson.put( "x7", rs.getDouble( "X7" ) );
                    reportJson.put( "x8", rs.getDouble( "X8" ) );
                    reportJson.put( "x9", rs.getDouble( "X9" ) );

                    reports.put( reportJson );
                }
            }


        } catch (SQLException | ClassNotFoundException | JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return reports.toString();

    }

    public void deleteUser(String userId) throws SQLException, ClassNotFoundException, FileNotFoundException {
        String deletion = "DELETE FROM course_schema.user WHERE "+ Const.USERS_ID +"="+ userId;
        PreparedStatement prep = null;
        prep = getDbConnection(false).prepareStatement(deletion);
        prep.executeUpdate();
    }

    public Report getLastReport() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String select = "SELECT * FROM "+ Const.REPORT_TABLE+" WHERE "+Const.REPORTS_ID+ "= (SELECT MAX("+Const.REPORTS_ID+") FROM "+Const.REPORT_TABLE+")";
        PreparedStatement prep1 = getDbConnection(false).prepareStatement( select );
        ResultSet rs = prep1.executeQuery();
        Report report = new Report(  );
        while (rs.next()){
            report.setID(rs.getInt( "IDReport" ) );
            report.setDate( rs.getString( "Date" ) );
            report.setResult( rs.getDouble( "H" ));
            report.setX1( rs.getDouble( "X1" ) );
            report.setX2( rs.getDouble( "X2" ) );
            report.setX3( rs.getDouble( "X3" ) );
            report.setX4( rs.getDouble( "X4" ) );
            report.setX5( rs.getDouble( "X5" ) );
            report.setX6( rs.getDouble( "X6" ) );
            report.setX7( rs.getDouble( "X7" ) );
            report.setX8( rs.getDouble( "X8" ) );
            report.setX9( rs.getDouble( "X9" ) );
        }
        return report;
    }

    public void saveStatistics() {
        java.util.Date dateNow = new Date(  );
        SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yyyy" );
        String date = format.format( dateNow );
        File file = new File("Отчет по статистике "+ date+".txt");
        String numOfUsers = null, numOfCompanies = null, numOfReports = null;
        numOfCompanies = getNumOfCompanies();
        numOfReports = getNumOfReports();
        numOfUsers = getNumOfUsers();
        try {
            if (!file.exists()) {
                file.createNewFile();
                FileWriter add = new FileWriter(file, false);
                add.write( "Кол-во пользователей: "+ numOfUsers+'\n');
                add.write( "Кол-во компаний: "+ numOfCompanies+'\n');
                add.write( "Кол-во отчетов: "+ numOfReports+'\n');
                add.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNumOfUsers() {
        ResultSet rs = null;
        int count = 0;
        try {
            String select = "SELECT COUNT(1) FROM "+ Const.USER_TABLE;
            PreparedStatement prep = getDbConnection(false).prepareStatement( select );
            rs = prep.executeQuery();
            while (rs.next()) {
                count = rs.getInt( 1 );
            }
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf( count );
    }

    private String getNumOfReports() {
        ResultSet rs = null;
        int count = 0;
        try {
            String select = "SELECT COUNT(1) FROM "+ Const.REPORT_TABLE;

            PreparedStatement prep = getDbConnection(false).prepareStatement(select);
            rs = prep.executeQuery();
            while (rs.next()) {
                count = rs.getInt( 1 );
            }

        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf( count );
    }
}
