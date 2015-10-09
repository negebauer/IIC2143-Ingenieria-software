package System.Courses;

public class Evaluation {
	
	public enum CourseEvaluation {
		INTERROGATION ("I"),
		EXAM ("E"),
		CONTROL ("C");
		
		private final String evaluation;
		CourseEvaluation(String evaluation) {
	        this.evaluation = evaluation;
		}
		
		public String getEvaluation() {
			return this.evaluation;
		}
	}
	
	private CourseEvaluation tipo;
	private Classroom sala;
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	
	public CourseEvaluation getTipo() {
		return tipo;
	}

	public void setTipo(CourseEvaluation tipo) {
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
