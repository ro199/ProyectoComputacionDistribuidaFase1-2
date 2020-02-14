package guis.gridforce.ec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.gridforce.ec.ConexionMySQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegistroGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConexionMySQL conexion = new ConexionMySQL();
	Connection con = conexion.conexion();

	private JPanel contentPane;
	private JTextField textFieldApellido;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldHabitacion;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RegistroGUI frame = new RegistroGUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */
	public void run() {
		try {
			RegistroGUI frame = new RegistroGUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegistro.setBounds(156, 13, 111, 28);
		contentPane.add(lblRegistro);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (textFieldApellido.getText().equalsIgnoreCase("") || textFieldEmail.getText().equalsIgnoreCase("")
						|| textFieldHabitacion.getText().equalsIgnoreCase("")
						|| textFieldNombre.getText().equalsIgnoreCase("")
						|| textFieldTelefono.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(contentPane, "Llene todos los campos");
				} else {
					insert();
				}
				select();

			}
		});
		btnGuardar.setBounds(277, 203, 97, 25);
		contentPane.add(btnGuardar);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(139, 92, 235, 25);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(139, 54, 235, 25);
		contentPane.add(textFieldNombre);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(139, 130, 235, 25);
		contentPane.add(textFieldEmail);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(139, 168, 235, 25);
		contentPane.add(textFieldTelefono);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(35, 58, 56, 16);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(35, 96, 56, 16);
		contentPane.add(lblApellido);

		JLabel lblCorreoElectronico = new JLabel("Email:");
		lblCorreoElectronico.setBounds(35, 134, 56, 16);
		contentPane.add(lblCorreoElectronico);

		JLabel lblDireccion = new JLabel("Telefono:");
		lblDireccion.setBounds(35, 172, 68, 16);
		contentPane.add(lblDireccion);

		JLabel lblHabitacion = new JLabel("# Habitacion");
		lblHabitacion.setBounds(35, 207, 79, 16);
		contentPane.add(lblHabitacion);

		textFieldHabitacion = new JTextField();
		textFieldHabitacion.setColumns(10);
		textFieldHabitacion.setBounds(139, 204, 61, 25);
		contentPane.add(textFieldHabitacion);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 128, 128));
		panel.setBounds(0, 0, 407, 315);
		contentPane.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 13, 475, 302);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "# Habitaci\u00F3n", "Nombre", "Apellido", "Email", "Tel\u00E9fono" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

	}

	public void insert() {

		try {
			String sentencia = "insert into Cliente value(?,?,?,?,?)";
			PreparedStatement sql = con.prepareStatement(sentencia);
			sql.setInt(1, Integer.parseInt(textFieldHabitacion.getText()));
			sql.setString(2, textFieldNombre.getText());
			sql.setString(3, textFieldApellido.getText());
			sql.setString(4, textFieldEmail.getText());
			sql.setString(5, textFieldTelefono.getText());
			sql.execute();
			System.out.println("" + sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		limpiar();
		

	}
	
	public void limpiar() {
		textFieldHabitacion.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldTelefono.setText("");
		textFieldEmail.setText("");
	}

	public void select() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String[] registro = new String[5];
		String sentencia = "select * from Cliente";
		System.out.println("Entre");

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			while (rs.next()) {
				registro[0] = rs.getString("numH");
				registro[1] = rs.getString("nombre");
				registro[2] = rs.getString("apellido");
				registro[3] = rs.getString("Email");
				registro[4] = rs.getString("telefono");
				System.out.println(registro[0].toString());
				model.addRow(registro);
			}

			System.out.println("Fin");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
