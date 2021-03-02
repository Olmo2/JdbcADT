package com.olmo.main;

import java.sql.*;

public class InsertarDep {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/libros", "root", "root");
			
			
		  /*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
							"ejemplo", "ejemplo");
          */ 
			// recuperar argumentos de main
			Integer id = 9; // num. departamento
			String nombre = "Cervantes"; // nombre
			String pais = "ESP"; // localidad
			
			//construir orden INSERT	        
	        String sql = String.format("INSERT INTO autores VALUES (%d, '%s', '%s')",
	        		id,nombre,pais);
	        
	        System.out.println(sql);

	        
			//https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
	        
			System.out.println(sql);
			Statement sentencia = conexion.createStatement();
			int filas=0;
			try {
			  filas = sentencia.executeUpdate(sql.toString());
			  System.out.println("Filas afectadas: " + filas);
			} catch (SQLException e) {
				//e.printStackTrace();
				   System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n"); 
				   System.out.printf("Mensaje   : %s %n", e.getMessage()); 
				   System.out.printf("SQL estado: %s %n", e.getSQLState()); 
				   System.out.printf("Cód error : %s %n", e.getErrorCode());	    	
			}
			
			

			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
