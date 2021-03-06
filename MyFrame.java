import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class MyFrame extends JFrame implements ActionListener {

	private boolean isUpdate = false;

	private JTable table;
	private JTextField tfId, tfName, tfPhone, tfDateOfBirth;
	private JButton btnOk;
	private JButton btnCancel;
	MyConnect myConnect = new MyConnect();
	
	private double phone;

	public MyFrame() {
		
		// thêm bảng điều khiển chính vào JFrame
		add(createMainPanel());
		setDisplayInput(false, false);
		
		// kết nối cơ sở dữ liệu
		myConnect.connect();
		
		// thiết lập thao tác đóng và cho phép cửa sổ hiển thị 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// tạo bảng điều khiển chính
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createTitlePanel(), BorderLayout.PAGE_START);
		panel.add(createTablePanel(), BorderLayout.CENTER);
		panel.add(createBottomPanel(), BorderLayout.PAGE_END);
		return panel;
	}
	
	// tạo bảng tiêu đề
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel lbTitle = new JLabel("Khách hàng");
		panel.add(lbTitle);
		return panel;
	}

	// tạo bảng điều khiển	
	private JPanel createTablePanel() {
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(table = createTabel()));
		return panel;
	}
	
	// tạo nút, thiết lập kích thước cho bảng điều khiển 
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(new EmptyBorder(5, 70, 10, 70));
		panel.add(createInputPanel(), BorderLayout.CENTER);
		panel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return panel;
	}

	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));

		// panel left
		JPanel panelLeft = new JPanel(new GridLayout(4, 1, 5, 5));
		panelLeft.add(new JLabel("Id"));
		panelLeft.add(new JLabel("Name"));
		panelLeft.add(new JLabel("Phone"));
		panelLeft.add(new JLabel("DateOfBirth"));
		

		// panel right
		JPanel panelRight = new JPanel(new GridLayout(4, 1, 5, 5));
		panelRight.add(tfId = new JTextField());
		panelRight.add(tfName = new JTextField());
		panelRight.add(tfPhone = new JTextField());
		panelRight.add(tfDateOfBirth = new JTextField());

		
		// panel ok & cancel
		JPanel panelOk = new JPanel(new GridLayout(1, 2, 5, 5));
		panelOk.setBorder(new EmptyBorder(0, 50, 0, 50));
		panelOk.add(btnOk = createButton("Ok"));
		panelOk.add(btnCancel = createButton("Cancel"));

		panel.add(panelLeft, BorderLayout.WEST);
		panel.add(panelRight, BorderLayout.CENTER);
		panel.add(panelOk, BorderLayout.PAGE_END);
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		panel.add(createButton("Add"));
		panel.add(createButton("Update"));
		panel.add(createButton("Delete"));
		
		return panel;
	}

	// tạo 1 table
	private JTable createTabel() {
		JTable table = new JTable();
		return table;
	}

	// tạo 1 button
	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		btn.addActionListener(this);
		return btn;
	}

	// xét hiển thị cho inputPanel
	private boolean setDisplayInput(boolean display, boolean update) {
		if (update && table.getSelectedRow() < 0) {
			return false;
		} else if (update) {
			int row = table.getSelectedRow();
			tfId.setText((String) table.getValueAt(row, 0));
			tfName.setText((String) table.getValueAt(row, 1));
			tfPhone.setText((String) table.getValueAt(row, 2));
			tfDateOfBirth.setText((String) table.getValueAt(row, 3));
		}
		tfId.setEnabled(display);
		tfName.setEnabled(display);
		tfPhone.setEnabled(display);
		tfDateOfBirth.setEnabled(display);
		btnOk.setEnabled(display);
		btnCancel.setEnabled(display);

		return true;

	}

	// load dữ liệu vào bảng
	private void loadData() {
		// tạo mô hình bảng	
		DefaultTableModel model = new DefaultTableModel();

		// lấy dữ liệu từ cơ sở dữ liệu
		ResultSet rs = myConnect.getData();
		try {
			// load tiêu đề các cột
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for (int i = 0; i < colNumber; i++) {
				arr[i] = rsMD.getColumnName(i + 1);
			}
			model.setColumnIdentifiers(arr);

			// load dữ liệu từ cơ sở dữ liệu vào bảng
			while (rs.next()) {
				for (int i = 0; i < colNumber; i++) {
					arr[i] = rs.getString(i + 1);
				}
				model.addRow(arr);
			}

		} catch (SQLException e) {
		}
		table.setModel(model);
	}
	
	//
	private void delete() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,
					"You must select a row in table", "Error delete",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int select = JOptionPane.showOptionDialog(null, "Are you want delete?",
				"Delete", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
		if (select == 0) {
			myConnect.deleteId((String) table.getValueAt(row, 0));
			loadData();
		}
	}

	private Khachhang getKhachhang() {
		String id = tfId.getText().trim().toUpperCase();
		String name = tfName.getText().trim();
		String dateOfBirth = tfDateOfBirth.getText().trim();

		if (id.equals("") || name.equals("")) {
			return null;
		}

		double phone;
		try {
			phone = Double.parseDouble(tfPhone.getText().trim());
		} catch (Exception e) {
			return null;
		}

		
		Khachhang st = new Khachhang(id, name, phone, dateOfBirth);
		return st;
	}

	private void update() {
		if (setDisplayInput(true, true)) {
			isUpdate = true;
		} else {
			JOptionPane.showMessageDialog(null, "Error update", "Error update",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void add() {
		setDisplayInput(true, false);
	}
	
	private void addOrUpdate() {
		Khachhang st = getKhachhang();
		if (st != null) {
			if (isUpdate) {
				myConnect.updateId(st.getId(), st);
				loadData();
				isUpdate = false;
			} else {
				myConnect.insert(st);
				loadData();
			}
			clear();
			setDisplayInput(false, false);
		} else {
			JOptionPane.showMessageDialog(null, "Infomation is error",
					"Error info", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void clear() {
		tfId.setText("");
		tfName.setText("");
		tfPhone.setText("");
		tfDateOfBirth.setText("");
	}

	private void cancel() {
		clear();
		setDisplayInput(false, false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Delete") {
			delete();
			return;
		}
		if (e.getActionCommand() == "Update") {
			update();
			return;
		}
		if (e.getActionCommand() == "Add") {
			add();
			return;
		}
		if (e.getSource() == btnOk) {
			addOrUpdate();
		}
		if (e.getSource() == btnCancel) {
			cancel();
		}
	}
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		myFrame.loadData();
	}
}