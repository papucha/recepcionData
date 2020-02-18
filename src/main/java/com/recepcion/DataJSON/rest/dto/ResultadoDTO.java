package com.recepcion.DataJSON.rest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect
@JsonSerialize

public class ResultadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	    private float num1;
	   
	    private float num2;
	    
	    private char operador;
	    
	    private float resultado;

		public float getNum1() {
			return num1;
		}

		public void setNum1(float num1) {
			this.num1 = num1;
		}

		public float getNum2() {
			return num2;
		}

		public void setNum2(float num2) {
			this.num2 = num2;
		}

		public char getOperador() {
			return operador;
		}

		public void setOperador(char operador) {
			this.operador = operador;
		}

		public float getResultado() {
			return resultado;
		}

		public void setResultado(float resultado) {
			this.resultado = resultado;
		}
	    
	       
	    
	    

}
