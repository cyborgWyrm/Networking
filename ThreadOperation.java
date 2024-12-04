

public class ThreadOperation extends Thread {
	
	// instance variables
	private String quadrant;
	private int[][] matrix1;
	private int[][] matrix2;
	private int[][] resultMatrix;
	
	// constructor
	public ThreadOperation(int[][] matrix1, int[][] matrix2, String quadrant, int[][] resultMatrix) {
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.quadrant = quadrant;
		this.resultMatrix = resultMatrix;
	}
	
	// run
	public void run() {
		// get indexes
		int[][] indexes = getQuadrantIndexes(quadrant);
		
		// run through the given x and y values in the arrays
		for (int row = indexes[0][0]; row < indexes[0][1]; row++) {
			for (int col = indexes[1][0]; col < indexes[1][1]; col++) {
				// add the value in matrix1 to the value in matrix2 and put the result
				// in result matrix
				resultMatrix[row][col] = matrix1[row][col] + matrix2[row][col];
			}
		}
		
	}
	
	private int[][] getQuadrantIndexes(String quadrant) {
		// automatically assumes the values for quadrant AA
		int xstart = 0;
		int ystart = 0;
		int xend = matrix1[0].length/2;
		int yend = matrix1.length/2;
		
		// if the quadrant is in the bottom half of the matrix
		if (quadrant.charAt(0) == 'B') {
			// change the values to fit
			ystart = matrix1.length/2;
			yend = matrix1.length;
		}
		// if the quadrant is in the right half of the matrix
		if (quadrant.charAt(1) == 'B') {
			// change the values to fit
			xstart = matrix1[0].length/2;
			xend = matrix1[0].length;
		}
		
		// prepare values to return
		int[][] returnValues = {
			{ystart, yend},
			{xstart, xend}
		};
		
		return returnValues;
	}
	
	
	
	
}