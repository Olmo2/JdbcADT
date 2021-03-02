package com.olmo.main;

import java.sql.*;
import java.util.Scanner;

public class Ejercicio10 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/libros", "root", "root");
			Statement sentencia = conexion.createStatement();
			
		  /*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
							"ejemplo", "ejemplo");
          */// recuperar argumentos de main
			
			
			
			Integer id = Integer.valueOf(args[0]); // num. departamento
			String apellido = args[1]; // nombre
			String oficio = args[2]; 
			Integer  dir = Integer.valueOf(args[3]);
			Float salario = Float.valueOf(args[4]);
			String comision  =args[5];
			Integer dept_no = Integer.valueOf(args[6]);
			Boolean dept = false, emp= false, sal = false, director = false;
			
			String sqlDept="Select dept_no from departamentos where dept_no =" + dept_no;
			ResultSet rs= sentencia.executeQuery(sqlDept);
			if(rs.next()) {
				System.out.println("El departamento " + dept_no +  " existe");
				dept=true;
			}else {
				dept=false;
			}
			
			
			String sqlemp="Select emp_no from empleados where emp_no =" + id;
			 rs= sentencia.executeQuery(sqlemp);
			if(rs.next()) {
				System.out.println("El empleado " + id +  " existe");
				emp=true;
			}else {
				emp=false;
			}
			
			if(salario>0) {
				sal=true;
			}else {
				sal=false;
			}
			
			String sqlDir="Select emp_no from empleados where emp_no =" + dir;
			 rs= sentencia.executeQuery(sqlDir);
			 if(rs.next()) {
				 director=true;
			 }else {
				 director=false;
			 }
			
			
			//construir orden INSERT	        
	        String sql = String.format("INSERT INTO empleados VALUES (%d, '%s', '%s', '%s', '%s', %d)",
	        		id,apellido,oficio,dir,comision,dept_no);
	        
	        System.out.println(sql);

	        
			//https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
	        
			System.out.println(sql);
			
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