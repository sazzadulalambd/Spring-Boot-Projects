package com.example.authorization.login.domain.repository.jdbc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;
// Point: RowCallbackHandler
public class UserRowCallbackHandler implements RowCallbackHandler {
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("sample.csv"))) {
                do {
                    String str = rs.getString("user_id") + ","
                            + rs.getString("password") + ","
                            + rs.getString("user_name") + ","
                            + rs.getDate("birthday") + ","
                            + rs.getInt("age") + ","
                            + rs.getBoolean("marriage") + ","
                            + rs.getString("role");
                    bw.write(str);
                    bw.newLine();
                } while (rs.next());
            } catch (IOException e) {
                e.printStackTrace();
                throw new SQLException(e);
            }
        }
    }
