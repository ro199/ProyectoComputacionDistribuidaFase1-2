package guis.gridforce.ec;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import cliente.gridforce.ec.Cliente;
import common.gridforce.ec.DatosSensor;
import common.gridforce.ec.DatosTemperatura;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JWindow;

import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class ClientGUI {

	private JFrame frame;
	private Cliente cli;
	private JPanel panelRoomNumber;
	private JLabel lblRoomNumer;
	private JPanel panelEnterRoom;
	private JButton btnEnterRoom;
	private JPanel panelLeaveRoom;
	private JButton btnLeaveRoom;
	private JPanel panelData;
	private JPanel panelAirConditioning;
	private JLabel lblAirConditioning;
	private JRadioButton rdbtnAirConOn;
	private JRadioButton rdbtnAirConOff;
	private ButtonGroup groupAir;
	private JPanel panelSmokeDetector;
	private JLabel lblSmokeDetector;
	private JPanel panelTemperature;
	private JLabel lblTemperature;
	private JLabel lblTemperatureValue;
	private JPanel panelSmoke;
	private JLabel lblSmoke;
	private JLabel lblSmokeValue;
	private JPanel panelHour;
	private JLabel lblHour;
	private JLabel lblHourValue;
	private JButton btnSmoke;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/*public void run(Cliente cli) {
		try {
			ClientGUI window = new ClientGUI(cli);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the application.
	 */
	public ClientGUI(Cliente cli) {
		this.cli = cli;
		try {
			JWindow window = new JWindow();
			JLabel jLabelYourCompanyLogo = new JLabel();
			
			ImageIcon iconLogo = new ImageIcon("connectingToServer.gif");
			jLabelYourCompanyLogo.setIcon(iconLogo);
			window.getContentPane().add(jLabelYourCompanyLogo);
			window.setBounds(100,100, iconLogo.getIconWidth(),iconLogo.getIconHeight() );
			window.setVisible(true);
			try {
			    Thread.sleep(5000);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			window.setVisible(false);
			window.dispose();		
			
		initialize();
		this.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelRoomNumber = new JPanel();
		frame.getContentPane().add(panelRoomNumber, BorderLayout.NORTH);
		
		lblRoomNumer = new JLabel("Room number 1");
		lblRoomNumer.setVerticalAlignment(SwingConstants.TOP);
		lblRoomNumer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomNumer.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelRoomNumber.add(lblRoomNumer);
		
		panelEnterRoom = new JPanel();
		frame.getContentPane().add(panelEnterRoom, BorderLayout.WEST);
		
		btnEnterRoom = new JButton("Enter Room");
		
		btnEnterRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelEnterRoom.add(btnEnterRoom);
		
		panelLeaveRoom = new JPanel();
		frame.getContentPane().add(panelLeaveRoom, BorderLayout.EAST);
		
		btnLeaveRoom = new JButton("Leave Room");
		btnLeaveRoom.setEnabled(false);

		btnLeaveRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelLeaveRoom.add(btnLeaveRoom);
		
		panelData = new JPanel();
		frame.getContentPane().add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BoxLayout(panelData, BoxLayout.Y_AXIS));
		
		panelAirConditioning = new JPanel();
		panelData.add(panelAirConditioning);
		
		lblAirConditioning = new JLabel("Air Conditioning");
		lblAirConditioning.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAirConditioning.add(lblAirConditioning);
		
		rdbtnAirConOn = new JRadioButton("On");
		rdbtnAirConOn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAirConditioning.add(rdbtnAirConOn);
		
		rdbtnAirConOff = new JRadioButton("Off");
		rdbtnAirConOff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAirConditioning.add(rdbtnAirConOff);
		
		groupAir = new ButtonGroup();
		groupAir.add(rdbtnAirConOn);
		groupAir.add(rdbtnAirConOff);
		
		panelSmokeDetector = new JPanel();
		panelData.add(panelSmokeDetector);
		
		lblSmokeDetector = new JLabel("Smoke Detector");
		lblSmokeDetector.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSmokeDetector.add(lblSmokeDetector);
						
		btnSmoke = new JButton("Smoke");
		btnSmoke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cli.humo(true);
			}
		});
		btnSmoke.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSmokeDetector.add(btnSmoke);
		
		btnSmoke = new JButton("Stop smoke");
		btnSmoke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cli.humo(false);
			}
		});
		btnSmoke.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSmokeDetector.add(btnSmoke);
		
		panelTemperature = new JPanel();
		panelData.add(panelTemperature);
				
		lblTemperature = new JLabel("Temperature: ");
		lblTemperature.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTemperature.add(lblTemperature);
		
		lblTemperatureValue = new JLabel("0");
		lblTemperatureValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTemperature.add(lblTemperatureValue);
		
		panelSmoke = new JPanel();
		panelData.add(panelSmoke);
				
		lblSmoke = new JLabel("Smoke detection: ");
		lblSmoke.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSmoke.add(lblSmoke);
		
		lblSmokeValue = new JLabel("Everything is ok!");
		lblSmokeValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSmoke.add(lblSmokeValue);
		
		panelHour = new JPanel();
		panelData.add(panelHour);
		
		lblHour = new JLabel("Hour: ");
		lblHour.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelHour.add(lblHour);
		
		lblHourValue = new JLabel("4:20:00");
		lblHourValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelHour.add(lblHourValue);
						
		btnEnterRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEnterRoom.setEnabled(false);
				btnLeaveRoom.setEnabled(true);
				cli.iniciar();
				JOptionPane.showMessageDialog(null, "The guest has entered the room");
			}
		});
		
		btnLeaveRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnterRoom.setEnabled(true);
				btnLeaveRoom.setEnabled(false);
				cli.finalizar();
				JOptionPane.showMessageDialog(null, "The guest has left the room");
			}
		});
	}
	
	public void changeValues(DatosTemperatura dh, boolean airState) {
		Calendar now = Calendar.getInstance();
		if(airState) {
			rdbtnAirConOn.setSelected(true);
		}else {
			rdbtnAirConOff.setSelected(true);
		}
		lblRoomNumer.setText("Room number"+Integer.toString(dh.getNumHabitacion()));
		lblTemperatureValue.setText(Integer.toString(dh.getTemperatura()));
		lblHourValue.setText(now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND));
		
	}
	
	public void changeValues(DatosSensor dh) {
		if(dh.isEstadoHumo()) {
			rdbtnAirConOff.setSelected(true);
		}
		lblRoomNumer.setText("Room number"+Integer.toString(dh.getNumHabitacion()));
		lblSmokeValue.setText(dh.getMsgHumo());
		System.out.println(dh.getMsgHumo());
	}

}
