package System.Courses;

import Tools.Enums.EvaluationTypes;

public class Evaluation {
	
	private EvaluationTypes tipo;
	private Classroom sala;
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	
	public EvaluationTypes getTipo() {
		return tipo;
	}

	public void setTipo(EvaluationTypes tipo) {
		this.tipo = tipo;
	}

	public Classroom getSala() {
		return sala;
	}

	public void setSala(Classroom sala) {
		this.sala = sala;
	}
	
	public String getFecha(){
		
		return (Integer.toString(this.dia) + "/" + Integer.toString(this.mes) + "/" + Integer.toString(this.ano));	
	
	}
	
	public void setFecha(int day, int month, int year){
		
		this.dia = day;
		this.mes = month;
		this.ano = year;
	}
	

	public int getHora() {

		return hora;
	}
	

	public void setHora(int hora) {
		this.hora = hora;
	}
}
