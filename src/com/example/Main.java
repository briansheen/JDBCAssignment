package com.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = DatabaseUtils.getInstance().getConnection()) {


            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person p SET p.contacted = ? WHERE p.id = ?");

            LocalDate localDate = LocalDate.of(2017, 4, 21);

            long epochMillis = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
            int id = 3;

            preparedStatement.setDate(1, new java.sql.Date(epochMillis));
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

            localDate = LocalDate.of(2017, 1, 28);
            epochMillis = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
            id = 5;

            preparedStatement.setDate(1, new java.sql.Date(epochMillis));
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

            String name = "Sybil Six";

            LocalDate localDate2 = LocalDate.of(1998, 8, 8);
            long epochMillis2 = localDate2.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000; //DOB

            String gender = "F";

            localDate = LocalDate.now();
            epochMillis = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000; //Contacted

            preparedStatement = connection.prepareStatement("INSERT INTO person (name, dob, gender, contacted) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, new java.sql.Date(epochMillis));
            preparedStatement.setString(3, gender);
            preparedStatement.setDate(4, new java.sql.Date(epochMillis2));

            preparedStatement.executeUpdate();

            String email = "sybil@six.com";
            id = 6;

            preparedStatement = connection.prepareStatement("INSERT INTO email (email, person_id) VALUES (?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM email WHERE person_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            email = "sybil@sixers.com";
            preparedStatement = connection.prepareStatement("INSERT INTO email (email, person_id) VALUES (?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            email = "sybilsix@gmail.com";
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            String street1 = "70 Sixer Ave";
            String city = "Cheyenne";
            String state = "WY";
            String zip = "82001";

            preparedStatement = connection.prepareStatement("INSERT INTO address (street1, city, stateAbbr, zip, person_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, street1);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, state);
            preparedStatement.setString(4, zip);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT * FROM person p LEFT JOIN address a ON p.id = a.person_id LEFT JOIN email e ON p.id = e.person_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("id, name, dob, gender, contacted, id, street1, street2, city, stateAbbr, zip, person_id, id, email, person_id");
            while(resultSet.next()){
                StringBuilder sb =  new StringBuilder();
                sb.append(resultSet.getInt("id")+", ");
                sb.append(resultSet.getString("name")+", ");
                sb.append(resultSet.getDate("dob")+", ");
                sb.append(resultSet.getString("gender")+", ");
                sb.append(resultSet.getDate("contacted")+", ");
                sb.append(resultSet.getInt("id")+", ");
                sb.append(resultSet.getString("street1")+", ");
                sb.append(resultSet.getString("street2")+", ");
                sb.append(resultSet.getString("city")+", ");
                sb.append(resultSet.getString("stateAbbr")+", ");
                sb.append(resultSet.getString("zip")+", ");
                sb.append(resultSet.getInt("person_id")+", ");
                sb.append(resultSet.getInt("id")+", ");
                sb.append(resultSet.getString("email")+", ");
                sb.append(resultSet.getInt("person_id")+", ");
                System.out.println(sb.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
