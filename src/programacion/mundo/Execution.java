package programacion.mundo;

import java.util.ArrayList;

import programacion.controlador.Controlador;

public class Execution extends Thread
{
  private Controlador ctrl;
  private ArrayList lst;
  private ArrayList<Nodo> lista ;
  private ArrayList<String> listaN ;
  private World world;
  private String city[][] = new String[30][30]; ;
  private int campeon;
  private String brujula;
  public Execution(World world, Controlador ctrl)
  { 
	this.ctrl = ctrl;
    this.lst = new ArrayList();
    this.world = world;
  }
  
  public void run()
  { int make; boolean swmove = true, swgoal = false;
    //paintWorld();
    while (!swgoal)
    { while (swmove)
	  { try 
	    { if (lst.size() > 0)
	      {   make = Integer.parseInt((String)lst.get(0));	  
	          switch (make)
	          { case 0: swmove = ctrl.move(); break;
	            case 1: ctrl.leftKarel(); break;
	            case 2: ctrl.rightKarel(); break;
	          }
	          sleep(500);	          	          
	          if (swmove)
	        	  if (!ctrl.goal()) lst.remove(0);
	        	  else
	        	  {	 swmove = false;
	        	     swgoal = true;
	        	  }
	          else lst.clear();	          
	      }
	      else
	      { swmove = false;
	      }
	    } 
	    catch (InterruptedException e) 
	    {}
	  }  
      if (!swgoal)
      {   ctrl.setMessage( "Recalculating..." );
          findPath(); swmove = true;
      }
    }
    ctrl.setMessage( "Home..." ); 	
  }
  
  /*
   * Encontrar el camino a la meta, utilizando busqueda en anchura. 
   */
  public void findPath()
  { city = world.getCity();
    int i=0;
    campeon=0;
    lista = new ArrayList<Nodo>();
    listaN = new ArrayList<String>();
    Nodo nodo = new Nodo(world.getKarel().getAvenue(),world.getKarel().getStreet(),i,"inicio");
    lista.add(nodo);
    
    while(!comprobarListaH()) {
    	
    	int filas=lista.get(i).getFila();
    	int columnas = lista.get(i).getColumna();
    	city[filas][columnas]="R";
    	if(filas-1>=0 && city[filas-1][columnas]!="B" && city[filas-1][columnas]!="R" ) {
    		
    			if(comprobarLista(filas-1, columnas)) {
    				nodo=new Nodo(filas-1,columnas,i,"arriba");
    				lista.add(nodo);	
    			}
    		
    	}
    	if(columnas+1<=29 && city[filas][columnas+1]!="B" &&city[filas][columnas+1] !="R") {
    		
    			if(comprobarLista(filas, columnas+1)) {
    				nodo=new Nodo(filas,columnas+1,i,"derecha");
    				lista.add(nodo);
    			}
    		
    	}
    	if(filas+1<=29 && city[filas+1][columnas]!="B" && city[filas+1][columnas]!="R") {
    		
    			if(comprobarLista(filas+1, columnas)) {
    				nodo=new Nodo(filas+1,columnas,i,"abajo");
    				lista.add(nodo);
    			}
    		
    	}
    	if(columnas-1>=0 && city[filas][columnas-1]!="B" && city[filas][columnas-1]!="R") {
    		
    			if(comprobarLista(filas, columnas-1)) {
    				nodo=new Nodo(filas,columnas-1,i,"izquierda");
    				lista.add(nodo);	
    			}
    		
    	}
    	
    	i++;
    	
    }
	//System.out.println("Tenemos Camino "+campeon);
	// printLista();
	 listaCamino(lista.get(campeon));
	 System.out.println("-----------------------------------");
	 printListaN();
	 
	rode();
//    {lst.add("2"); // right
//	lst.add("0"); lst.add("0"); lst.add("0"); lst.add("0"); 
//    lst.add("0"); lst.add("0"); lst.add("0"); lst.add("0"); 
//    lst.add("2"); // right
//    lst.add("0"); lst.add("0"); lst.add("0"); lst.add("0"); 
//    lst.add("1"); // left
//    lst.add("0"); lst.add("0"); lst.add("0"); lst.add("0"); 
//    lst.add("2"); // right
//    lst.add("2"); // right  }
    
    i=0;
  }
  public void rode () {
System.out.println("-----------------------------------");
	  for (int i = 1; i < listaN.size(); i++) {
		 if(listaN.get(i).equals("arriba")) {
			if(world.getKarel().getCompass().equals("North")) {
				lst.add("0");
				System.out.println("avance");
			}else {
				arriba();
				System.out.println(world.getKarel().getCompass());
				lst.add("0");
				System.out.println("avance");
			}		
			
		}
		else if(listaN.get(i).equals("derecha")) {
			if(world.getKarel().getCompass().equals("East")) {
				lst.add("0");
				System.out.println("avance");
			}else {
				derecha();
				System.out.println(world.getKarel().getCompass());
				lst.add("0");
				System.out.println("avance");
			}
		}
		else if(listaN.get(i).equals("abajo")) {
			if(world.getKarel().getCompass().equals("South")) {
				lst.add("0");
				System.out.println("avance");
			}else {
				abajo();
				System.out.println(world.getKarel().getCompass());
				lst.add("0");
				System.out.println("avance");
			}
		}
		else if(listaN.get(i).equals("izquierda")) {
			if(world.getKarel().getCompass().equals("West")) {
				lst.add("0");
				System.out.println("avance");
			}else {
				izquierda();
				System.out.println(world.getKarel().getCompass());
				lst.add("0");
				System.out.println("avance");
			}
		}
	}
	  
  }
  public void arriba() {
	  if ( world.getKarel().getCompass().equals("South")) {
			 world.getKarel().turnLeft(); lst.add("1");
			 world.getKarel().turnLeft(); lst.add("1");
		 }
		 if ( world.getKarel().getCompass().equals("East")) {
			 world.getKarel().turnLeft(); lst.add("2");
		 }
		 if ( world.getKarel().getCompass().equals("West")) {
			 world.getKarel().turnRight(); lst.add("1");
		 }
  }
  public void derecha() {
	  if ( world.getKarel().getCompass().equals("South")) {
		  world.getKarel().turnLeft(); lst.add("1");
		 }
		 if ( world.getKarel().getCompass().equals("North")) {
			 world.getKarel().turnRight(); lst.add("2");
		 }
		 if ( world.getKarel().getCompass().equals("West")) {
			 world.getKarel().turnLeft(); lst.add("1");
			 world.getKarel().turnLeft(); lst.add("1"); 
		 }
  }
  public void abajo() {
	  if ( world.getKarel().getCompass().equals("North")) {
			 world.getKarel().turnLeft();lst.add("1");
			 world.getKarel().turnLeft();lst.add("1"); 
		 }
		 if ( world.getKarel().getCompass().equals("East")) {
			 world.getKarel().turnRight(); lst.add("2");
		 }
		 if ( world.getKarel().getCompass().equals("West")) {
			 world.getKarel().turnLeft(); lst.add("1");
		 }
  }
  public void izquierda() {
	  if ( world.getKarel().getCompass().equals("South")) {
		  world.getKarel().turnRight(); lst.add("2");
		 }
		 if ( world.getKarel().getCompass().equals("East")) {
			 world.getKarel().turnLeft(); lst.add("1");
			 world.getKarel().turnLeft(); lst.add("1");
		 }
		 if ( world.getKarel().getCompass().equals("North")) {
			 world.getKarel().turnLeft();lst.add("1"); 
		 }
  }
  
  public void listaCamino( Nodo e) {
	  if (e==lista.get(0)) {
		  listaN.add(e.getDireccion());
	  }else {
		  listaCamino(lista.get(e.getIndice()));
		  listaN.add(e.getDireccion());
	  }
	 
  }
  
  public void printListaN() {
	  for (int i = 0; i < listaN.size(); i++) {
		System.out.println((i)+listaN.get(i));
	}
  }
  
  public void printLista() {
	  for (int j = 0; j < lista.size(); j++) {
			System.out.println( "("+j+") "+lista.get(j).getFila()+","+ lista.get(j).getColumna()+";"+lista.get(j).getIndice()+"-"+lista.get(j).getDireccion());	
		
			}
  }
  public boolean comprobarLista(int fila, int columna) {
	  for (int i = 0; i < lista.size(); i++) {
		if (lista.get(i).getFila()==fila && lista.get(i).getColumna()==columna) {
			return false;
		}
	}
	  return true;
  }
  
  public boolean comprobarListaH() {
	  for (int i = 0; i < lista.size(); i++) {
		  if(city[lista.get(i).getFila()][lista.get(i).getColumna()].equals("H")) {
			  campeon=i;
				return true;
			}
	}
	  return false;
  }
  
  public void paintWorld()
  { String city[][] = new String[30][30]; 
    city = world.getCity();
	
    for (int i=0; i<30; i++){
    	for (int j=0; j<30; j++) {
    	 if (city[i][j].equals(""))	{
    		 System.out.print(".");
    	 }
         else {
        	 System.out.print(city[i][j]);
         } 
       
    	}  
    	 System.out.println();
    }	  
  }
  
}
