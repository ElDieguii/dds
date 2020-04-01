package window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import	conexion.ConexionBd;
import javax.swing.JTextArea;
import window.RegistrarVenta;


public class Prueba {

	private JFrame frame;
	RegistrarVenta registrarVenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
		
		
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba window = new Prueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prueba() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Consultar Ventas del dia");
		btnNewButton.setBounds(124, 104, 177, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConsultarVentasDelDia consultarVentasDelDia;
				try {
					consultarVentasDelDia = new ConsultarVentasDelDia();
					consultarVentasDelDia.frame.setVisible(true);
					Prueba.this.frame.dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Registrar Venta");
		btnNewButton_1.setBounds(124, 150, 177, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					registrarVenta = new RegistrarVenta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				registrarVenta.frame.setVisible(true);
				Prueba.this.frame.dispose();
				
			}
		});
		
		JTextArea txtrSistemaMacowins = new JTextArea();
		txtrSistemaMacowins.setText("Sistema MacOwins");
		txtrSistemaMacowins.setBounds(144, 11, 132, 29);
		frame.getContentPane().add(txtrSistemaMacowins);
	}
}
