package guis.gridforce.ec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.gridforce.ec.ConexionMySQL;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class RegistroGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConexionMySQL conexion = new ConexionMySQL();
	Connection con = conexion.conexion();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroGUI frame = new RegistroGUI();
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
	public RegistroGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(178, 13, 56, 16);
		contentPane.add(lblRegistro);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Select();
			}
		});
		btnGuardar.setBounds(277, 203, 97, 25);
		contentPane.add(btnGuardar);
		
		textField = new JTextField();
		textField.setBounds(139, 92, 235, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(139, 54, 235, 25);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(139, 130, 235, 25);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(139, 168, 235, 25);
		contentPane.add(textField_3);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(35, 58, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(35, 96, 56, 16);
		contentPane.add(lblApellido);
		
		JLabel lblCorreoElectronico = new JLabel("Email:");
		lblCorreoElectronico.setBounds(35, 134, 56, 16);
		contentPane.add(lblCorreoElectronico);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(35, 172, 68, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblHabitacion = new JLabel("# Habitacion");
		lblHabitacion.setBounds(35, 207, 68, 16);
		contentPane.add(lblHabitacion);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(139, 204, 61, 25);
		contentPane.add(textField_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 128, 128));
		panel.setBounds(0, 0, 386, 315);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 28, 422, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"# Habitaci\u00F3n", "Nombre", "Apellido", "Email", "Tel\u00E9fono"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		
		
	}
	
	public void Select() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		String[] registro = new String[7];
		String sentencia = "select * from usuario";
		System.out.println("Entre");
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			while (rs.next()) {
				registro[0] = rs.getString("cedulaUsuario");
				registro[1] = rs.getString("nombre");
				registro[2] = rs.getString("apellido");
				registro[3] = rs.getString("correoU");
				registro[4] = rs.getString("telefonoU");
				registro[5] = rs.getString("contrasenaU");
				System.out.println(registro[0].toString());
				model.addRow(registro);
			}
			
			System.out.println("Fin");
					
			/*PreparedStatement sql = con.prepareStatement(sentencia);
			sql.execute();
			System.out.println(""+sql.toString());*/
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
