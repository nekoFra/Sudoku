import java.util.ArrayList;
import java.util.Arrays;

public class Cell {

	private Integer value = 0 ;
	private ArrayList<Integer> solutions = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9));
	private Integer block;
	
	public Cell(char value) {
		char v = ( value == ".".charAt(0) )? "0".charAt(0) : value; 
		this.value =  Integer.parseInt(String.valueOf(v));
	}
	
	/**
	 * Inserisce  un valore
	 * 
	 * @param value <integer>
	 */
	
	public void set(Integer value){
		this.value = value;
	}
	
	/**
	 * Restituisce un valore
	 * @return string
	 */
	public String getValueString(){
		return (value == 0)? "   ": value.toString();
	}
	
	/**
	 * Restituisce un valore
	 * @return integer
	 */
	public Integer getValue(){
		return value;
	}

	/**
	 * Rimuove le possibili soluzioni della cella
	 * 
	 * @param n<Integer>
	 * @return void
	 */
	 public void removeSolution(Integer n){
		 solutions.remove(solutions.indexOf(n));
	 }
	 
	 public void setBlock(Integer block){
		 this.block = block;
	 }
	 public Integer getBlock(){
		 return block;
	 }
}
