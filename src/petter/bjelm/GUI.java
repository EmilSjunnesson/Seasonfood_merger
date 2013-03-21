package petter.bjelm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.w3c.dom.Document;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {

	private JPanel panel;
	private int i;
	private int lastRow = 0;
	private int rowX = 0;
	private int rowY = 0;
	private int setButtonsPerRows = 6;
	private int buttonCounter = 0;
	private int nrButtons=0;
	private GroupLayout gl_panel;
	Document doc;
	private Parse parse;
	private int panelHeight=0;
	private ArrayList<String> nameArray;
	private ArrayList<JButton> buttonArray;
	private ArrayList<String> URLArray;
	private GetCategory data;
	private ImageParsing imageParse;
	private JButton[] buttons;
	private JComboBox monthComboBox;
	private JComboBox zoneComboBox;
	private GetImgFromURL URLImage;

	private int buttonWidth = 120;
	private int buttonHeight = 120;
	private JScrollPane scrollPane;
	private JLabel lblKategorier;
	private JLabel lblRvarorISsong;
	private JLabel currentMonth;
	private JLabel lblVljVisningEnligt;

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

	public GUI() {
		super("Add component on JFrame at runtime");

		nameArray = new ArrayList<String>();
		buttonArray = new ArrayList<JButton>();
		URLArray = new ArrayList<String>();
		URLImage = new GetImgFromURL();
		imageParse = new ImageParsing();
		data = new GetCategory();
		parse = new Parse();
		parse.setDate(parse.getCurrDate());
		parse.setZone("Z2");

		String[] patternExamples = { "Välj månad", "Januari", "Februari",
				"Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September",
				"Oktober", "November", "December", };

		monthComboBox = new JComboBox(patternExamples);
		monthComboBox.setBounds(1131, 303, 196, 25);
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int index = monthComboBox.getSelectedIndex();

				switch (index) {
				case 0:
					parse.setDate(parse.getCurrDate());
					break;
				case 1:
					parse.setDate("01");
					break;
				case 2:
					parse.setDate("02");
					break;
				case 3:
					parse.setDate("03");
					break;
				case 4:
					parse.setDate("04");
					break;
				case 5:
					parse.setDate("05");
					break;
				case 6:
					parse.setDate("06");
					break;
				case 7:
					parse.setDate("07");
					break;
				case 8:
					parse.setDate("08");
					break;
				case 9:
					parse.setDate("09");
					break;
				case 10:
					parse.setDate("10");
					break;
				case 11:
					parse.setDate("11");
					break;
				case 12:
					parse.setDate("12");
				default:
					parse.setDate(parse.getCurrDate());
					break;
				}
			}
		});

		JButton greenButton = new JButton("Grönsaker");
		greenButton.setBounds(30, 266, 100, 100);
		greenButton.setHorizontalTextPosition(SwingConstants.CENTER);

		String[] zones = { "Välj zon", "Sydligaste Sverige", "Södra Sverige",
				"Norra Sverige" };

		zoneComboBox = new JComboBox(zones);
		zoneComboBox.setBounds(1131, 266, 196, 24);
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = zoneComboBox.getSelectedIndex();

				switch (index) {
				case 0:
					parse.setZone("Z2");
					break;
				case 1:
					parse.setZone("Z3");
					break;
				case 2:
					parse.setZone("Z2");
					break;
				case 3:
					parse.setZone("Z1");
					break;
				default:
					parse.setZone("Z2");
					break;
				}
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(greenButton);
		getContentPane().add(monthComboBox);
		getContentPane().add(zoneComboBox);
		
				JButton shellfishButton = new JButton("Skaldjur");
				shellfishButton.setBounds(160, 266, 100, 100);
				getContentPane().add(shellfishButton);
				shellfishButton.setHorizontalTextPosition(SwingConstants.CENTER);
				shellfishButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg1) {
						buttonLogic("Skaldjur");
					}

				});
		
		lblKategorier = new JLabel("Kategorier");
		lblKategorier.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		lblKategorier.setBounds(30, 224, 230, 31);
		getContentPane().add(lblKategorier);
		
		lblRvarorISsong = new JLabel("R\u00E5varor i s\u00E4song:");
		lblRvarorISsong.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		lblRvarorISsong.setBounds(298, 224, 174, 31);
		getContentPane().add(lblRvarorISsong);
		
		currentMonth = new JLabel("Mars");
		currentMonth.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		currentMonth.setBounds(471, 224, 118, 31);
		getContentPane().add(currentMonth);
		
		lblVljVisningEnligt = new JLabel("Visa r\u00E5varor f\u00F6r:");
		lblVljVisningEnligt.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		lblVljVisningEnligt.setBounds(1131, 224, 196, 31);
		getContentPane().add(lblVljVisningEnligt);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(298, 266, 780, 466);
		getContentPane().add(scrollPane);
		panel = new JPanel();
		panel.setOpaque(false);
		scrollPane.setViewportView(panel);
		panel.setBorder(null);
		
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 840, Short.MAX_VALUE)
		);
		
		if(nrButtons==0){
			panelHeight=600;
			System.out.println("NOLLL");
		}else{
			panelHeight=nrButtons/6*110;
			System.out.println("HÖJD: "+panelHeight);
		}
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0,panelHeight, Short.MAX_VALUE)
		);
		

		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(GUI.class.getResource("/images/Bakgrund morotter.jpg")));
		getContentPane().add(lblNewLabel);

		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonLogic("Grönsaker");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setResizable(false);
		setVisible(true);
	}

	public JComboBox getComboBox() {
		return monthComboBox;
	}

	public void buttonLogic(String category) {
		rowY = 0;
		lastRow = setButtonsPerRows;
		rowX = 30;
		buttonCounter = 0;
		
		
		if (buttons != null) {
			nrButtons=buttons.length;
			for (i = 0; i < buttons.length; i++) {

				panel.remove(buttons[i]);
				panel.revalidate();
				panel.repaint();

			}
		}

		try {
			doc = parse.ParseDoc(category);
		} catch (Exception e9) {
			e9.printStackTrace();
		}

		try {
			nameArray = data.getArray(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			URLArray = imageParse.getURLArray(doc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (nameArray.size() != URLArray.size()) {
			nameArray.remove(nameArray.indexOf("Svedjerova"));
		}

		for (int i = 0; i < URLArray.size(); i++) {
			System.out.print(nameArray.get(i) + "\t");
			System.out.println(URLArray.get(i));
		}

		buttons = new JButton[nameArray.size()];
		
		for (i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(nameArray.get(i).replace('_', ' '),
					new ImageIcon(URLImage.getURLImage(URLArray.get(i))));
			buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
			buttons[i].setForeground(Color.WHITE);

			if (i == lastRow) {
				rowY = rowY + buttonHeight;
				lastRow = i + setButtonsPerRows;
				// System.out.println(rowY + " Y");

			} else {

			}

			// System.out.println(buttonCounter);
			if (buttonCounter == setButtonsPerRows) {
				buttonCounter = 0;

				buttons[i].setBounds((buttonWidth * buttonCounter), rowY,
						buttonWidth, buttonHeight);
			} else {

				buttons[i].setBounds(
						(buttonWidth * buttonCounter + 10 * buttonCounter),
						rowY, buttonWidth, buttonHeight);
			}

			buttonCounter++;
			// System.out.println(rowX);
			// buttons[i].setBounds((150*buttonCounter+10), rowY,
			// buttonWidth, buttonWidth);

			buttons[i].setActionCommand(nameArray.get(i));

			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String choice = e.getActionCommand();
					JOptionPane.showMessageDialog(null, "You have clicked: "
							+ choice);
				}
			});

			panel.add(buttons[i]);
			panel.revalidate();
			validate();
			panel.repaint();
	
		}
	}
	public JPanel getPanel() {
		return panel;
	}
}