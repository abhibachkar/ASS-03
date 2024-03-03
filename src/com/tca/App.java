package com.tca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class App {
	public static void main(String[] args) throws IOException {
		Connection con = null;
		PreparedStatement ps = null;
		FileReader fr = null;
		BufferedReader br = null;

		String url = "jdbc:mysql://localhost:3306/hfbdb";
		String user = "root";
		String password = "Abhi@123";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);

			fr = new FileReader("Resources/Student.CSV");
			br = new BufferedReader(fr);

			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}

				String tok[] = line.split(",");
				// System.out.println(tok[0]+"---" + tok[1]+ "---" + tok[2]);

				int rno = 0;
				String name = null;
				double per = 0.0;

				try {
					rno = Integer.parseInt(tok[0]);
					name = tok[1];
					per = Double.parseDouble(tok[2]);

					ps = con.prepareStatement("insert into Student values(?,?,?)");
					ps.setInt(1, rno);
					ps.setString(2, name);
					ps.setDouble(3, per);

					int sval = ps.executeUpdate();
					if (sval == 1) {
						System.out.println("record is save for rollnumber " + rno);
					}

				}

				catch (Exception ae) {
					System.out.println(" Ignores : " + line);
				}

				// System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fr.close();
		}
	}
}
