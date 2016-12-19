import java.util.ArrayList;

public class Sudoku {

	public static void main(String[] args) {
			
		Matrix matrix = new Matrix("C:/Users/Francesco/Google Drive/Documenti/Università/Programmazione di sistemi multicore/Esercitazioni/Sudoku/stage.txt");
		matrix.showMetrix();
		
		System.out.println("");
		
		matrix.seekOnStage(4, 4).forEach((a)-> System.out.print(a + " "));

	}

}
