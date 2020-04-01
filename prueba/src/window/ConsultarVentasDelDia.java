package window;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mysql.jdbc.Statement;

import conexion.ConexionBd;

import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;


public class ConsultarVentasDelDia {

	JFrame frame;
	Statement st;
	private SpringLayout springLayout;
	DefaultListModel<String> modeloLista = new DefaultListModel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarVentasDelDia window = new ConsultarVentasDelDia();
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
	public ConsultarVentasDelDia() throws SQLException {
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
		
		//-------------------
		Properties properties = new Properties();
		properties.put("text.today", "Dia");
		properties.put("text.month", "Mes");
		properties.put("text.year", "Año");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		frame.getContentPane().setLayout(null);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(10, 48, 202, 23);
		frame.getContentPane().add(datePicker);
		//----------------
		
		
		
		//-----------
		ConexionBd conexion = new ConexionBd();
		conexion.conectar();
        st = (Statement) ConexionBd.conexion.createStatement();
		//----------
        
		JTextPane txtpnGananciasDelDia = new JTextPane();
		txtpnGananciasDelDia.setBackground(Color.LIGHT_GRAY);
		txtpnGananciasDelDia.setBounds(233, 11, 191, 20);
		txtpnGananciasDelDia.setText("Ganancias del dia");
		frame.getContentPane().add(txtpnGananciasDelDia);
		
		JList list = new JList();
		list.setBackground(Color.ORANGE);
		list.setBounds(233, 34, 191, 190);
		frame.getContentPane().add(list);
		list.setModel(modeloLista);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.setBounds(129, 201, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextPane textTotalGanancias = new JTextPane();
		textTotalGanancias.setBounds(243, 230, 181, 20);
		frame.getContentPane().add(textTotalGanancias);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				int gananciaTotal=0;
				String query = "SELECT * FROM ventas WHERE fechaDeVenta = "+datePicker.getJFormattedTextField().getText()+"";
		        try {
					rs= st.executeQuery(query);
					while(rs.next()){
				            gananciaTotal += Integer.parseInt(rs.getString("precioDePrenda"));
				            //modeloLista.addElement(gananciaTotal);
				            try {
				                  Thread.sleep(3000);
				            } catch(Exception e) {}
				        }
					
					textTotalGanancias.setText(""+gananciaTotal);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		       
				
				
			}
			
		});
		
		
	}
	
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyyMMdd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
}
