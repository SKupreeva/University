package database;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/course_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        Statement statement;
        Connection connection;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("\n\tСоединение с БД установлено!");

                int flag = 0;
                statement = connection.createStatement();

                System.out.println("\n\tПоиск всех фильмов(2019-2020 гг)...");

                String sqlRequest;
                sqlRequest = "SELECT * FROM lab_7.film where date(Realise_date) > '2018-12-31'";

                System.out.println("Результат:");
                ResultSet resultSetFilmRequest = statement.executeQuery(sqlRequest);
                System.out.println("================================");
                while (resultSetFilmRequest.next()) {
                    int id = resultSetFilmRequest.getInt("F_ID");
                    String name = resultSetFilmRequest.getString("Name");
                    String actorName = resultSetFilmRequest.getString("ActorName");
                    String realiseDate = resultSetFilmRequest.getString("Realise_date");
                    String country = resultSetFilmRequest.getString("Country");

                    System.out.println("Film id: " + id);
                    System.out.println("Film Name: " + name);
                    System.out.println("Actor Name: " + actorName);
                    System.out.println("Film Realise Date: " + realiseDate);
                    System.out.println("Film Realise Country: " + country);
                    System.out.println("================================");
                    flag++;
                }
                if(flag == 0) System.out.println("Таких фильмов нет в базе данных!\n");
                resultSetFilmRequest.close();


                System.out.println("\n\tПоиск информации об актерах, снимавшихся в фильме Devil All The Time...");
                flag = 0;

                sqlRequest = "select * from lab_7.actor  where Film_ID = (select F_ID from lab_7.film where name = 'Devil All the Time')";

                System.out.println("Результат:");
                ResultSet resultSetActorInfoFilm = statement.executeQuery(sqlRequest);
                System.out.println("================================");
                while (resultSetActorInfoFilm.next()) {
                    int id = resultSetActorInfoFilm.getInt("A_ID");
                    String actorName = resultSetActorInfoFilm.getString("FirstName");
                    String lastName = resultSetActorInfoFilm.getString("LastName");
                    String surname = resultSetActorInfoFilm.getString("Surname");
                    String birthDate = resultSetActorInfoFilm.getString("BirthDate");
                    int filmId = resultSetActorInfoFilm.getInt("Film_ID");

                    System.out.println("Actor id: " + id);
                    System.out.println("Actor First Name: " + actorName);
                    System.out.println("Actor Last Name: " + lastName);
                    System.out.println("Actor Surname: " + surname);
                    System.out.println("Actor Birth Date: " + birthDate);
                    System.out.println("Film ID: " + filmId);
                    System.out.println("================================");
                    flag++;
                }
                if(flag == 0) System.out.println("Таких актеров нет в базе данных!\n");
                resultSetActorInfoFilm.close();


                System.out.println("\n\tПоиск информации о режиссерах, снимавших хотя бы 2 фильма...");
                flag = 0;

                sqlRequest = "select * from lab_7.producer  where (select count(Film_ID)) >= 2";

                System.out.println("Результат:");
                ResultSet resultSetProducerInfoNFilms = statement.executeQuery(sqlRequest);
                System.out.println("================================");
                while (resultSetProducerInfoNFilms.next()) {
                    int id = resultSetProducerInfoNFilms.getInt("P_ID");
                    String producerName = resultSetProducerInfoNFilms.getString("FirstName");
                    String lastName = resultSetProducerInfoNFilms.getString("LastName");
                    String surname = resultSetProducerInfoNFilms.getString("Surname");
                    String birthDate = resultSetProducerInfoNFilms.getString("BirthDate");
                    int filmId = resultSetProducerInfoNFilms.getInt("Film_ID");

                    System.out.println("Producer id: " + id);
                    System.out.println("Producer First Name: " + producerName);
                    System.out.println("Producer Last Name: " + lastName);
                    System.out.println("Producer Surname: " + surname);
                    System.out.println("Producer Birth Date: " + birthDate);
                    System.out.println("Film ID: " + filmId);
                    System.out.println("================================");
                    flag++;
                }
                if(flag == 0) System.out.println("Таких режиссеров нет в базе данных!\n");
                resultSetProducerInfoNFilms.close();


                System.out.println("\n\tПоиск информации об актерах, снимавших хотя бы 1 фильм...");
                flag = 0;

                sqlRequest = "select a.A_ID, p.P_ID, a.FirstName , a.LastName, a.Surname, a.BirthDate, a.Film_ID, p.Film_ID from lab_7.actor a, lab_7.producer p where a.Surname = p.Surname";

                System.out.println("Результат:");
                ResultSet resultSetActorLikeProducer = statement.executeQuery(sqlRequest);
                System.out.println("================================");
                while (resultSetActorLikeProducer.next()) {
                    int actorId = resultSetActorLikeProducer.getInt("a.A_ID");
                    int producerId = resultSetActorLikeProducer.getInt("p.A_ID");
                    String producerName = resultSetActorLikeProducer.getString("a.FirstName");
                    String lastName = resultSetActorLikeProducer.getString("a.LastName");
                    String surname = resultSetActorLikeProducer.getString("a.Surname");
                    String birthDate = resultSetActorLikeProducer.getString("a.BirthDate");
                    int actorFilmId = resultSetActorLikeProducer.getInt("a.Film_ID");
                    int producerFilmId = resultSetActorLikeProducer.getInt("p.Film_ID");

                    System.out.println("Actor id: " + actorId);
                    System.out.println("Producer id: " + producerId);
                    System.out.println("First Name: " + producerName);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("Surname: " + surname);
                    System.out.println("Birth Date: " + birthDate);
                    System.out.println("Actor Film ID: " + actorFilmId);
                    System.out.println("Producer Film ID: " + producerFilmId);
                    System.out.println("================================");
                    flag++;
                }
                if(flag == 0) System.out.println("Таких актеров нет в базе данных!\n");
                resultSetActorLikeProducer.close();

                statement.close();
            }
            connection.close();
            System.out.println("\n\tСоединение с БД закрыто!");
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("\nСоединение с БД не установлено!");
        }
    }
}