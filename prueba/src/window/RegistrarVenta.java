package window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import prendas.Prenda;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.xml.bind.ValidationException;

import com.mysql.jdbc.Statement;

import conexion.ConexionBd;
import estado.Estado;
import estado.Liquidacion;
import estado.Nueva;
import estado.Promocion;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class RegistrarVenta {
	
	ArrayList <Prenda> carrito = new ArrayList<Prenda>();
	DefaultListModel<String> modeloLista = new DefaultListModel();
	Estado estado;
	Statement st;
	JFrame frame;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
	LocalDateTime now = LocalDateTime.now();  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarVenta window = new RegistrarVenta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public RegistrarVenta() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//-----------
		
		ConexionBd conexion = new ConexionBd();
		conexion.conectar();
        st = (Statement) ConexionBd.conexion.createStatement();
      
		//-----------
		
		
		JTextPane txtpnRegistroDeVentas = new JTextPane();
		txtpnRegistroDeVentas.setText("Registro de Ventas");
		txtpnRegistroDeVentas.setBounds(227, 11, 97, 20);
		frame.getContentPane().add(txtpnRegistroDeVentas);
		
		
		
		JSpinner spinner_prenda = new JSpinner();
		spinner_prenda.setModel(new SpinnerListModel(new String[] {"saco", "pantalon", "camisa"}));
		spinner_prenda.setBounds(237, 42, 170, 27);
		
		frame.getContentPane().add(spinner_prenda);
		
		JSpinner spinner_estado = new JSpinner();
		spinner_estado.setModel(new SpinnerListModel(new String[] {"Nuevo", "Promocion", "Liquidacion"}));
		spinner_estado.setBounds(236, 80, 170, 27);
		frame.getContentPane().add(spinner_estado);
		
		JSpinner spinner_tipoDePago = new JSpinner();
		spinner_tipoDePago.setModel(new SpinnerListModel(new String[] {"Efectivo", "Tarjeta"}));
		spinner_tipoDePago.setBounds(237, 118, 170, 20);
		frame.getContentPane().add(spinner_tipoDePago);
		
		JEditorPane editTPrecio = new JEditorPane();
		editTPrecio.setContentType("int\r\n");
		editTPrecio.setBounds(301, 149, 106, 20);
		frame.getContentPane().add(editTPrecio);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(277, 171, 89, 23);
		frame.getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				if(spinner_estado.getValue().toString().equals("Nuevo")) {
					estado= new Nueva();
				}if(spinner_estado.getValue().toString().equals("Promocion")) {
					estado= new Promocion();
				}if(spinner_estado.getValue().toString().equals("Liquidacion")) {
					estado= new Liquidacion();
				}
				
				int precio = Integer.parseInt(editTPrecio.getText());
				String tipo = (String) spinner_prenda.getValue();
				Prenda prenda = new Prenda(estado, precio, tipo);
				
				agregarPrendaAlCarrito(prenda);
				actualizarListadoDePrendas(prenda);
			}
		});
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(332, 227, 92, 34);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					registrarVentaDePrendas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vaciarCarrito();
				RegistrarVenta.this.frame.dispose();
			}
			
		});
		
		
		JTextPane txtpnListaPrendas = new JTextPane();
		txtpnListaPrendas.setText("Lista Prendas");
		txtpnListaPrendas.setBounds(10, 11, 123, 20);
		frame.getContentPane().add(txtpnListaPrendas);
		
		
		
		JTextPane txtpnPrecio = new JTextPane();
		txtpnPrecio.setText("Precio");
		txtpnPrecio.setBounds(237, 149, 68, 20);
		frame.getContentPane().add(txtpnPrecio);
		
		JList<String> list = new JList<String>();
		list.setBounds(20, 47, 170, 188);
		frame.getContentPane().add(list);
		list.setModel(modeloLista);
		
		
		
		
	}
	
	public void vaciarCarrito() {
		carrito.removeAll(carrito);
	}
	
	public void registrarVentaDePrendas() throws SQLException {
		for(int i=0;i<carrito.size();i++) {
			String query = "INSERT INTO ventas (precioDePrenda, tipoDePrenda, fechaDeVenta) VALUES ("+carrito.get(i).getPrecio()+", '"+carrito.get(i).getTipo()+"', '"+now+"')";
	        st.executeUpdate(query);
		}
	}
	
	public void agregarPrendaAlCarrito (Prenda prenda) {
		carrito.add(prenda);
	}
	
	public void actualizarListadoDePrendas(Prenda prenda) {
		modeloLista.addElement(prenda.getTipo() +"; " + prenda.getEstado().estado() + "; "+ prenda.getPrecioFinal());
		
	}
}
