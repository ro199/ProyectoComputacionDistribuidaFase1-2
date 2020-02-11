package guis.gridforce.ec;

import cliente.gridforce.ec.Cliente;
import cliente.gridforce.ec.HiloTemperatura;
import servidor.gridforce.ec.Servidor;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;

public class ServidorGUI {

	private JFrame frmSystemMonitor;
	private JTable tableData;
	private DefaultTableModel modelTableData;
	private DefaultTableModel modelSmoke;
	private JPanel panelData;
	private JRadioButton rdbtn2secs;
	private JRadioButton rdbtn5secs;
	private JRadioButton rdbtn10secs;
	private JPanel panelSmoke;
	private JScrollPane scrollPane;
	private JTable tableSmoke;
	private JButton btnChange;
	private JButton btnClean;
	private JPanel panelTimers; 
	private ButtonGroup group;
	private JScrollPane scrollTable;
	int tiempo;
	private Servidor ser;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServidorGUI window = new ServidorGUI();
					window.frmSystemMonitor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/*public void run() {
		try {
			ServidorGUI window = new ServidorGUI();
			window.frmSystemMonitor.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the application.
	 */
	public ServidorGUI() {
		try {
			JWindow window = new JWindow();
			JLabel jLabelYourCompanyLogo = new JLabel();
			
			ImageIcon iconLogo = new ImageIcon("loading.gif");
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
			this.frmSystemMonitor.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemMonitor = new JFrame();
		frmSystemMonitor.setTitle("System monitor");
		frmSystemMonitor.setBounds(100, 100, 640, 400);
		frmSystemMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		panelTimers = new JPanel();
		frmSystemMonitor.getContentPane().add(panelTimers, BorderLayout.NORTH);
		
		rdbtn2secs = new JRadioButton("2 seconds");
		rdbtn2secs.setSelected(true);
		rdbtn2secs.setActionCommand("2");
		panelTimers.add(rdbtn2secs);
		
		rdbtn5secs = new JRadioButton("5 seconds");
		rdbtn5secs.setActionCommand("5");
		panelTimers.add(rdbtn5secs);
		
		rdbtn10secs = new JRadioButton("10 seconds");
		rdbtn10secs.setActionCommand("10");
		panelTimers.add(rdbtn10secs);
		
		group = new ButtonGroup();
	    group.add(this.rdbtn2secs);
	    group.add(this.rdbtn5secs);
	    group.add(this.rdbtn10secs);
	    
	    btnChange = new JButton("Change");
	    btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = group.getSelection().getActionCommand();
				tiempo = Integer.parseInt(s);
				try {
					ser = new Servidor();
					System.out.println("El tiempo es "+tiempo);
					ser.setTiempo(tiempo);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}
		});
	    panelTimers.add(btnChange);
	    
	    btnClean = new JButton("Clean");
	    panelTimers.add(btnClean);

		
		panelData = new JPanel(new BorderLayout());
		frmSystemMonitor.getContentPane().add(panelData, BorderLayout.CENTER);

		modelTableData = new DefaultTableModel();
		
		modelTableData.addColumn("Room");
		modelTableData.addColumn("Entry time");
		modelTableData.addColumn("Temperature");
		modelTableData.addColumn("Air conditioning state");
		
		tableData = new JTable(modelTableData);
		tableData.setPreferredScrollableViewportSize(tableData.getPreferredSize());
		tableData.setFillsViewportHeight(true);
					
	    scrollTable = new JScrollPane(tableData);
	    scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    panelData.add(scrollTable);
	    
	    panelSmoke = new JPanel(new BorderLayout());
	    frmSystemMonitor.getContentPane().add(panelSmoke, BorderLayout.WEST);
	    
	    modelSmoke = new DefaultTableModel();
	    
	    modelSmoke.addColumn("Hour");
	    modelSmoke.addColumn("Smoke detector state");
	    
	    tableSmoke = new JTable(modelSmoke);
	    tableSmoke.setPreferredScrollableViewportSize(tableSmoke.getPreferredSize());
	    tableSmoke.setFillsViewportHeight(true);
	    scrollPane = new JScrollPane(tableSmoke);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    panelSmoke.add(scrollPane);
	    
	    btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelTableData.setRowCount(0);
				modelSmoke.setRowCount(0);
			}
			
		});
	}
	
	public void addRowWithData(String[] row) {
		modelTableData.addRow(row);
	}
	
	public void addRowWithDataSmoke(String[] row) {
		modelSmoke.addRow(row);
		if(row[1].equals("Danger!")) {
			JOptionPane.showMessageDialog(frmSystemMonitor, "Danger! Smoke in the room");
		}
	}
}
