import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ClientJFrame extends JFrame {
	
	private JTextField textField;
	
	public ClientJFrame() {
		
		super("Client");
		
		textField = new JTextField("Enter file name");
		add(textField);
		
		textField.addActionListener(
			new ActionListener() {
				// get the file name specified by user
				public void actionPerformed(ActionEvent event) {
					Scanner input = getFile(event.getActionCommand());
					int rows = input.nextInt();
					int cols = input.nextInt();
					
					int[][] matrix1 = matrixFromFile(rows, cols, input);
					int[][] matrix2 = matrixFromFile(rows, cols, input);
				}
			}
		); 
		
		setSize(400, 300);
		setVisible(true);
	}
	
	// copied this from readwritedata.java
	private Scanner getFile(String fileName) {
		File file = new File(fileName);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.printf("%nError on file: %s (either enpty or wrong file format)%n%n", file); 
			e.printStackTrace();
			System.exit(1);
		}
		
		return input;
	}
	
	// copied this from my matrix addition
	public static int[][] matrixFromFile(int rows, int cols, Scanner fileReader) {
		int[][] matrix = new int[rows][cols];
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				// read in the values and put in the proper places in the array
				matrix[r][c] = fileReader.nextInt();
			}
		}
		
		return matrix;
	}
}