import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
	
	private Cell[][] stage = null;
	
	private int ROW = 9, COULMN = 9;
	
	
	
	public Matrix(String path) {
		
		stage = new Cell[ROW][COULMN];
		
		try {

			BufferedReader bf = new BufferedReader( new FileReader(path));

			String CurrentLine;
			Integer r = 0, b = 0, cc = 0, cr = 0, cb = 0, tmpB = 0;
			char c = ".".charAt(0);
			while ((CurrentLine = bf.readLine()) != null) {
				for (int i = 0; i < CurrentLine.length(); i++){					
					if ( cr == 1){ b = tmpB; cr = 0;} // controllo sulle righe		
					stage[r][i] = new Cell( CurrentLine.charAt(i) );
					stage[r][i].setBlock(b); // set current block;		
					cc++; 		
					if ( cc >= 3 ){ b++; cc = 0; } // controllo sulle colonne	
				}
				r++;
				cr++;
				cb++;
				if (cb >= 3 ){ tmpB = b; cb = 0;} // controllo sul blocco
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Ritorna il valore della cella selezionata, se fuori dallo
	 * stage ritorna -1.
	 * 
	 * @param Row<int>
	 * @param Columns<int>
	 * @return integer
	 */
	public Integer getCellValue(int box, int rowIndex, int colIndex){
		if (checkPosition(box,rowIndex, colIndex))
			return stage[rowIndex][colIndex].getValue();
		else return -1;
	}
	
	/**
	 *  Controlla se la cella selezionata è all' interno dello stage, se 
	 *  il valore si trova all' interno dello stage ritorna "true" altrimenti
	 *  ritorna "false".
	 *  
	 * @param rIndex
	 * @param cIndex
	 * @return
	 */
	private boolean checkPosition(int bIndex, int rIndex, int cIndex){
		if ( ( rIndex  <= stage.length - 1 && rIndex  >= 0 ) && ( cIndex <= stage[0].length -1 && cIndex >= 0 ) )
			return true;
		return false;
	}
	
	/**
	 *  Stampa una rappresentazione grafica dello stato attuale del gioco.
	 */
	public void showMetrix(){
		System.out.println("###############################################");
		System.out.println("#                                               SUDOKU                                                         # ");
		System.out.println("###############################################");
		System.out.println("");
		String cell = "                           | %1$-2s| %2$-2s| %3$-2s|      | %4$-2s| %5$-2s| %6$-2s|       | %7$-2s| %8$-2s| %9$-2s|\n";
		int b  = 0; // conta le righe per poi calcolare quando stampare lo spazio tra una box è l' altra
		for ( Cell[] row : stage){
			b++;
			System.out.format(cell, 
					row[0].getValueString(),
					row[1].getValueString(),
					row[2].getValueString(), 
					row[3].getValueString(), 
					row[4].getValueString(),
					row[5].getValueString(),
					row[6].getValueString(), 
					row[7].getValueString(),
					row[8].getValueString()
				);		
			if ( b >= 3){
				b = 0;
				System.out.println("");
			}
		}
	
	}
	
	/**
	 * Sfoglia tutte le celle della riga della cella attuale
	 * 
	 * @param index row<Integer>
	 * @param index column<Integer>
	 * @return list<ArrayList>
	 */
	public ArrayList seekOnRow(Integer ir, Integer ic){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int c = ic-1 ; c >= 0 ; c--){
			if ( stage[ir][c].getValue() != 0) list.add( stage[ir][c].getValue() );
		}
		for (int c = ic+1 ; c < stage[0].length ; c++){
			if ( stage[ir][c].getValue() != 0) list.add( stage[ir][c].getValue() );
		}
	
		return list;
	}
	
	/**
	 * Sfoglia tutte le celle della collonna della cella attuale
	 * 
	 * @param index row<Integer>
	 * @param index column<Integer>
	 * @return list<ArrayList>
	 */
	public ArrayList seekOnColumn(Integer ir, Integer ic){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int r = ir-1 ; r >= 0 ; r--){
			if ( stage[r][ic].getValue() != 0) list.add( stage[r][ic].getValue() );
		}
		for (int r = ir+1 ; r < stage.length ; r++){
			if ( stage[r][ic].getValue() != 0) list.add( stage[r][ic].getValue() );
		}
	
		return list;
	}
	
	/**
	 *  Ritorna una lista di tutti i valori del blocco della 
	 *  cella passata tramite cordinate
	 *  
	 *  @param row<Integer>
	 *  @param culomn<Integer>
	 *  @return ArrayList
	 */
	
	public ArrayList seekOnBlock(Integer ir, Integer ic){
		ArrayList<Integer> list = new ArrayList<Integer>();
		int currentBlock = stage[ir][ic].getBlock();
		for ( int r=0; r<stage.length;r++){
			for( int c=0; c<stage[0].length;c++){
				if ( stage[r][c].getBlock() == currentBlock && stage[r][c].getValue() != 0){
					if (!( ir == r && ic == c)) // escludo se stessp
						list.add(stage[r][c].getValue());
				}
					
			}
		}
		return list;
	}
	
	
	/**
	 * Ritorna gli elementi delle righe, delle colonne  e
	 * del blocco del sudoku
	 * 
	 * @param index row <Integer>
	 * @param index column <Integer>
	 * @return ArrayList
	 * 
	 */
	public ArrayList seekOnStage(Integer ir, Integer ic){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(seekOnRow(ir,ic));
		list.addAll(seekOnColumn(ir,ic));
		list.addAll(seekOnBlock(ir,ic));
		return list;
	}


}
