
package com.pepkorit.assesment.kanban.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.pepkorit.assesment.kanban.KanbanBoard;
import com.pepkorit.assesment.kanban.XIllegalAction;
import com.pepkorit.assesment.kanban.dto.Status;
import com.pepkorit.assesment.kanban.dto.User;
import com.pepkorit.assesment.kanban.dto.WorkItem;

/**
 * A simple GUI to visualize the {@link KanbanBoard}
 * @author Natasha
 *
 */
public class MainWindow
{
	KanbanBoard        board;
	private JFrame     frmKanbanBoard;
	private JTextField textFieldAssignee;
	private JPanel     panelToDoItems;
	private JPanel     panelInProgressItems;
	private JPanel     panelDoneItems;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		KanbanBoard board = new KanbanBoard();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow(board);
					window.frmKanbanBoard.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 * 
	 * @param board
	 * @throws XIllegalAction
	 */
	public MainWindow(KanbanBoard board) throws XIllegalAction
	{
		this.board = board;
		insertDummyData();
		initialize();
	}
	
	public void insertDummyData() throws XIllegalAction
	{
		board.addWorkItem(new WorkItem("Task 1", "Setup-Users"));
		board.addWorkItem(new WorkItem("Task 2", "Remove-Users"));
		User a = new User("UserA", "uA@sample.com");
		User b = new User("UserB", "uA@sample.com");
		board.addWorkItem(new WorkItem(
				"Make Up Tasks",
				"Set up some dummy tasks",
				a,
				Status.IN_PROGRESS));
		
		board.addWorkItem(new WorkItem("Complete GUI", "Have some gui layout", b, Status.DONE));
		
		board.addWorkItem(new WorkItem("Complete Board", "Complete backend", b, Status.DONE));
		
		board.addWorkItem(new WorkItem(
				"Have fun",
				"Spend some time learning a new lesssson",
				a,
				Status.DONE));
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmKanbanBoard = new JFrame();
		frmKanbanBoard.setTitle("Kanban Board - Demo");
		frmKanbanBoard.setBounds(100, 100, 600, 600);
		frmKanbanBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKanbanBoard.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelMain = new JPanel();
		frmKanbanBoard.getContentPane().add(panelMain);
		panelMain.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelToDoMain = new JPanel();
		panelMain.add(panelToDoMain);
		panelToDoMain.setLayout(new BorderLayout(0, 0));
		
		JLabel lblToDo = new JLabel("To Do");
		lblToDo.setHorizontalAlignment(SwingConstants.LEFT);
		lblToDo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblToDo.setVerticalAlignment(SwingConstants.BOTTOM);
		panelToDoMain.add(lblToDo, BorderLayout.NORTH);
		
		JScrollPane scrollPaneToDOItems = new JScrollPane();
		panelToDoItems = new JPanel();
		panelToDoMain.add(panelToDoItems, BorderLayout.CENTER);
		panelToDoItems.setLayout(new BoxLayout(panelToDoItems, BoxLayout.Y_AXIS));
		scrollPaneToDOItems.setViewportView(panelToDoItems);
		panelToDoMain.add(scrollPaneToDOItems);
		
		JPanel panelInProgressMain = new JPanel();
		panelMain.add(panelInProgressMain);
		panelInProgressMain.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInProgress = new JLabel("In-Progress");
		lblInProgress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInProgress.setHorizontalAlignment(SwingConstants.LEFT);
		panelInProgressMain.add(lblInProgress, BorderLayout.NORTH);
		
		JScrollPane scrollInProgressItems = new JScrollPane();
		panelInProgressItems = new JPanel();
		panelInProgressMain.add(panelInProgressItems, BorderLayout.CENTER);
		panelInProgressItems.setLayout(new BoxLayout(panelInProgressItems, BoxLayout.Y_AXIS));
		scrollInProgressItems.setViewportView(panelInProgressItems);
		panelInProgressMain.add(scrollInProgressItems);
		
		JPanel panelDoneMain = new JPanel();
		panelMain.add(panelDoneMain);
		panelDoneMain.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDone = new JLabel("Done");
		lblDone.setHorizontalAlignment(SwingConstants.LEFT);
		lblDone.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelDoneMain.add(lblDone, BorderLayout.NORTH);
		
		JScrollPane scrollDoneItems = new JScrollPane();
		panelDoneItems = new JPanel();
		panelDoneMain.add(panelDoneItems, BorderLayout.CENTER);
		panelDoneItems.setLayout(new BoxLayout(panelDoneItems, BoxLayout.Y_AXIS));
		scrollDoneItems.setViewportView(panelDoneItems);
		panelDoneMain.add(scrollDoneItems);
		
		JMenuBar menuBar = new JMenuBar();
		frmKanbanBoard.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddUser = new JMenuItem("Add User");
		mntmAddUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new AddUser();
				
			}
		});
		mnFile.add(mntmAddUser);
		
		JMenuItem mntmNewWorkItem = new JMenuItem("Add New Work Item");
		
		mntmNewWorkItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new EditItem(null, null, frmKanbanBoard);
			}
		});
		frmKanbanBoard.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
			}
			
			@Override
			public void focusGained(FocusEvent e)
			{
				rebuildSwimLanes();
			}
		});
		mnFile.add(mntmNewWorkItem);
	}
	
	public void rebuildSwimLanes()
	{
		if (board != null)
		{
			HashMap<String, WorkItem> toDo = board.getWorkItemsByStatus(Status.TODO);
			panelToDoItems.removeAll();
			for (String key : toDo.keySet())
			{
				panelToDoItems.add(createWorkItemPanel(key, toDo.get(key)));
			}
			panelToDoItems.repaint();
			HashMap<String, WorkItem> ip = board.getWorkItemsByStatus(Status.IN_PROGRESS);
			panelInProgressItems.removeAll();
			for (String key : ip.keySet())
			{
				panelInProgressItems.add(createWorkItemPanel(key, ip.get(key)));
			}
			panelInProgressItems.repaint();
			HashMap<String, WorkItem> done = board.getWorkItemsByStatus(Status.DONE);
			panelDoneItems.removeAll();
			for (String key : done.keySet())
			{
				panelDoneItems.add(createWorkItemPanel(key, done.get(key)));
			}
			panelDoneItems.repaint();
			frmKanbanBoard.revalidate();
			frmKanbanBoard.getContentPane().repaint();
		}
	}
	
	public JPanel createWorkItemPanel(String id, WorkItem wi)
	{
		JPanel panelWI = new JPanel();
		panelWI.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0)),
				wi.getTitle(),
				TitledBorder.LEADING,
				TitledBorder.TOP,
				null,
				null));
		GridBagLayout gbl_panelWI = new GridBagLayout();
		gbl_panelWI.columnWidths = new int[] {56, 104, 0};
		gbl_panelWI.rowHeights = new int[] {22, 99, 25, 0};
		gbl_panelWI.columnWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
		gbl_panelWI.rowWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelWI.setLayout(gbl_panelWI);
		panelWI.setToolTipText(id);
		
		JLabel lblAssignee = new JLabel("Assignee");
		GridBagConstraints gbc_lblAssignee = new GridBagConstraints();
		gbc_lblAssignee.anchor = GridBagConstraints.WEST;
		gbc_lblAssignee.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssignee.gridx = 0;
		gbc_lblAssignee.gridy = 0;
		panelWI.add(lblAssignee, gbc_lblAssignee);
		
		textFieldAssignee = new JTextField();
		GridBagConstraints gbc_textFieldAssignee = new GridBagConstraints();
		gbc_textFieldAssignee.anchor = GridBagConstraints.NORTH;
		gbc_textFieldAssignee.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAssignee.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAssignee.gridx = 1;
		gbc_textFieldAssignee.gridy = 0;
		panelWI.add(textFieldAssignee, gbc_textFieldAssignee);
		textFieldAssignee.setText(wi.getUser().getName());
		textFieldAssignee.setEditable(false);
		textFieldAssignee.setColumns(10);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setEditable(false);
		textAreaDescription.setText(wi.getDescription());
		GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaDescription.gridwidth = 2;
		gbc_textAreaDescription.gridx = 0;
		gbc_textAreaDescription.gridy = 1;
		panelWI.add(textAreaDescription, gbc_textAreaDescription);
		
		JButton btnEdit = new JButton("Edit");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		gbc_btnEdit.gridx = 1;
		gbc_btnEdit.gridy = 2;
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new EditItem(wi, id, frmKanbanBoard);
			}
		});
		panelWI.add(btnEdit, gbc_btnEdit);
		
		return panelWI;
	}
	
	@SuppressWarnings("serial")
	class AddUser extends JDialog
	{
		
		private final JPanel    contentPanel = new JPanel();
		private JTextField      textFieldName;
		private JTextField      textFieldEmail;
		private JComboBox<User> users;
		
		/**
		 * Create the dialog.
		 */
		public AddUser()
		{
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
			setTitle("Add User");
			setResizable(false);
			setBounds(100, 100, 450, 140);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
			gbl_contentPanel.rowHeights = new int[] {0, 0, 0};
			gbl_contentPanel.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			
			JLabel lblNewLabel = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
			
			textFieldName = new JTextField();
			GridBagConstraints gbc_textFieldName = new GridBagConstraints();
			gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldName.fill = GridBagConstraints.BOTH;
			gbc_textFieldName.gridx = 1;
			gbc_textFieldName.gridy = 0;
			contentPanel.add(textFieldName, gbc_textFieldName);
			textFieldName.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("e-mail");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			textFieldEmail = new JTextField();
			GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
			gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
			gbc_textFieldEmail.gridx = 1;
			gbc_textFieldEmail.gridy = 1;
			contentPanel.add(textFieldEmail, gbc_textFieldEmail);
			textFieldEmail.setColumns(10);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(close());
			saveButton.addActionListener(addUser());
			saveButton.setActionCommand("OK");
			buttonPane.add(saveButton);
			getRootPane().setDefaultButton(saveButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(close());
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		
		/**
		 * @param comboBoxAssignee
		 */
		public AddUser(JComboBox<User> comboBoxAssignee)
		{
			this();
			this.users = comboBoxAssignee;
		}
		
		public ActionListener close()
		{
			return new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dispose();
					if (users != null)
					{
						users.removeAllItems();
						Set<User> usrs = board.getAllRegisteredUsers();
						for (User user : usrs)
						{
							users.addItem(user);
						}
						users.repaint();
						contentPanel.repaint();
					}
				}
			};
		}
		
		public ActionListener addUser()
		{
			return new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String email = textFieldEmail.getText();
					String name = textFieldName.getText();
					board.addUserIfMissing(new User(name, email));
					dispose();
				}
			};
		}
	}
	
	@SuppressWarnings("serial")
	class EditItem extends JDialog
	{
		private final JPanel      contentPanel = new JPanel();
		private JTextField        textFieldTitle;
		private JComboBox<Status> comboBoxStaus;
		private JComboBox<User>   comboBoxAssignee;
		private JTextArea         textAreaDescription;
		private WorkItem          wi;
		protected String          wiID;
		private JFrame            frmKanbanBoard;
		
		/**
		 * Create the dialog.
		 * 
		 * @param frmKanbanBoard
		 * 
		 * @param id
		 * 
		 * @param frmKanbanBoard
		 */
		public EditItem(WorkItem wi, String wiID, JFrame frmKanbanBoard)
		{
			if (wi != null)
			{
				this.wi = wi;
				this.wiID = wiID;
			}
			this.frmKanbanBoard = frmKanbanBoard;
			setTitle("Add/Edit Work Item");
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new FormLayout(
					new ColumnSpec[] {FormSpecs.DEFAULT_COLSPEC, ColumnSpec.decode("default:grow"),},
					new RowSpec[] {FormSpecs.DEFAULT_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("top:default:grow"),
							FormSpecs.DEFAULT_ROWSPEC,}));
			
			JLabel lblTitle = new JLabel("Title");
			contentPanel.add(lblTitle, "1, 1, left, default");
			
			textFieldTitle = new JTextField();
			if (wi != null)
			{
				textFieldTitle.setText(wi.getTitle());
			}
			contentPanel.add(textFieldTitle, "2, 1, fill, default");
			textFieldTitle.setColumns(10);
			
			JLabel lblAssignee = new JLabel("Assignee");
			contentPanel.add(lblAssignee, "1, 2, left, default");
			
			comboBoxAssignee = new JComboBox<User>();
			Set<User> users = board.getAllRegisteredUsers();
			for (User user : users)
			{
				comboBoxAssignee.addItem(user);
			}
			if (wi != null)
			{
				comboBoxAssignee.setSelectedItem(wi.getUser());
			}
			contentPanel.add(comboBoxAssignee, "2, 2, fill, default");
			
			JButton btnAddUser = new JButton("Add User");
			contentPanel.add(btnAddUser, "2, 3, right, default");
			btnAddUser.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					new AddUser(comboBoxAssignee);
				}
			});
			
			JLabel lblDescription = new JLabel("Description");
			contentPanel.add(lblDescription, "1, 4");
			
			textAreaDescription = new JTextArea();
			if (wi != null)
			{
				textAreaDescription.setText(wi.getTitle());
			}
			contentPanel.add(textAreaDescription, "2, 4, fill, fill");
			
			JLabel lblNewLabel = new JLabel("Status");
			contentPanel.add(lblNewLabel, "1, 5, right, default");
			
			comboBoxStaus = new JComboBox<Status>();
			comboBoxStaus.addItem(Status.TODO);
			comboBoxStaus.addItem(Status.IN_PROGRESS);
			comboBoxStaus.addItem(Status.DONE);
			
			if (wi != null)
			{
				comboBoxStaus.setSelectedItem(wi.getStatus());
			}
			contentPanel.add(comboBoxStaus, "2, 5, right, default");
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			okButton.addActionListener(editItem());
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(close());
			buttonPane.add(cancelButton);
		}
		
		public ActionListener close()
		{
			return new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			};
		}
		
		public ActionListener editItem()
		{
			return new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					User u = (User) comboBoxAssignee.getSelectedItem();
					if (u == null)
					{
						u = comboBoxAssignee.getItemAt(0);
					}
					Status s = (Status) comboBoxStaus.getSelectedItem();
					if (s == null)
					{
						s = comboBoxStaus.getItemAt(0);
					}
					String title = textFieldTitle.getText();
					String description = textAreaDescription.getText();
					
					try
					{
						if (wi == null)
						{
							WorkItem wi = new WorkItem(title, description, u, s);
							board.addWorkItem(wi);
						}
						else
						{
							wi = new WorkItem(title, description, u, s);
							board.saveWorkItem(wiID, wi);
						}
						dispose();
						frmKanbanBoard.requestFocus();
					}
					catch (XIllegalAction e1)
					{
						JOptionPane.showMessageDialog(null, e1.getErrorMessage(), "Error",
																JOptionPane.WARNING_MESSAGE);
						
					}
					
				}
			};
		}
	}
}
