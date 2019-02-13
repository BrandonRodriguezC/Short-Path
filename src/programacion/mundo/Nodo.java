package programacion.mundo;

public class Nodo {
int indice;
int fila,columna;
String direccion;
	public Nodo(int fila, int columna,int indice ,String direccion) {
	this.indice=indice;
	this.fila=fila;
	this.columna=columna;
	this.direccion=direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	
}
