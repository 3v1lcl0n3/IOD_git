package paket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextArea in1;
	private JTextArea in2;
	private JButton btnPretrazi2;
	private JTextArea out;
	
	JFileChooser fc;
	File file1;
	File file2;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	private JLabel labelExport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		in1 = new JTextArea();
		in1.setEditable(false);
		in1.setBounds(40, 40, 270, 20);
		contentPane.add(in1);
		in1.setColumns(10);
		
		JButton btnPretrazi1 = new JButton("Pretra\u017Ei...");
		btnPretrazi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(GUI.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file1 = fc.getSelectedFile();
	                in1.setText(file1.getName());
	                
	            } else if(file1 == null){
	                in1.setText("Datoteka nije odabrana");
	            }
			}
		});
		btnPretrazi1.setBounds(320, 39, 89, 23);
		contentPane.add(btnPretrazi1);

		btnPretrazi2 = new JButton("Pretra\u017Ei...");
		btnPretrazi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(GUI.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file2 = fc.getSelectedFile();
	                in2.setText(file2.getName());
	                
	            } else if(file2 == null) {
	                in2.setText("Datoteka nije odabrana");
	            }
			}
		});
		btnPretrazi2.setBounds(320, 99, 89, 23);
		contentPane.add(btnPretrazi2);
		
		JButton btnProvjeri = new JButton("Provjeri");
		btnProvjeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.setText("");
				if(file1 != null) {
					out.append("Prvi: " + file1.getName() + "\r\n");
				}
				else {
					out.append("Prvi: nije odabran\r\n");
				}
				
				if(file2 != null) {
					out.append("Drugi: " + file2.getName() + "\r\n");
				}
				else {
					out.append("Drugi: nije odabran\r\n");
				}
			}
		});
		btnProvjeri.setBounds(40, 135, 89, 23);
		contentPane.add(btnProvjeri);
		
		out = new JTextArea();
		out.setEditable(false);
		out.setBounds(40, 170, 369, 285);
		contentPane.add(out);
		
		in2 = new JTextArea();
		in2.setEditable(false);
		in2.setBounds(40, 100, 270, 20);
		contentPane.add(in2);
		in2.setColumns(10);
		
		
		JLabel lblPrviDataset = new JLabel("Prvi dataset");
		lblPrviDataset.setBounds(40, 20, 100, 14);
		contentPane.add(lblPrviDataset);
		
		JLabel lblDrugiDataset = new JLabel("Drugi dataset");
		lblDrugiDataset.setBounds(40, 80, 100, 14);
		contentPane.add(lblDrugiDataset);
		
		JButton btnIzvezi = new JButton("Izvezi...");
		btnIzvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazivReport = new String("Izvjesce_");
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				nazivReport += sdf.format(timestamp);
				nazivReport += ".txt";
		        
				if(!out.getText().isEmpty()) {
					try {
						PrintWriter writer = new PrintWriter(nazivReport, "UTF-8");
						writer.println(out.getText());
						writer.close();
						labelExport.setText("Izvezeno u " + nazivReport);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						labelExport.setText("Greška kod izvoza");
					}
				}
				else {
					labelExport.setText("Provjeri prije izvoza");
				}
			}
		});
		btnIzvezi.setBounds(320, 466, 89, 23);
		contentPane.add(btnIzvezi);
		
		labelExport = new JLabel("");
		labelExport.setBounds(40, 466, 270, 20);
		contentPane.add(labelExport);
	}
}
