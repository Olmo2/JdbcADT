package com.olmo.main;
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT apellido,oficio,salario FROM empleados WHERE dept_no= 10";
			String sql2= "SELECT apellido,salario,dept_no FROM empleados WHERE (`salario` = (SELECT MAX(`salario`) FROM empleados))";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%s, %s, %d %n", resul.getString(1), resul.getString(2), resul.getInt(3));
			}
			
			 resul = sentencia.executeQuery(sql2);

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%s, %d, %s %n", resul.getString(1), resul.getInt(2), resul.getString(3));
			}

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
