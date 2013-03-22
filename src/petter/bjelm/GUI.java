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
	private String chosenCategory;
	private GroupLayout gl_panel;
	Document doc;
	private Parse parse;
	private int panelHeight=0;
	private ArrayList<String> nameArray;
	private ArrayList<JButton> buttonArray;
	private ArrayList<JLabel> labelArray;
	private ArrayList<String> URLArray;
	private GetCategory data;
	private ImageParsing imageParse;
	private JButton[] buttons;
	private JLabel[] labels;
	private JComboBox monthComboBox;
	private JComboBox zoneComboBox;
	private GetImgFromURL URLImage;

	private int buttonWidth = 120;
	private int buttonHeight = 120;
	private int labelWidth = 120;
	private int labelHeight = 20;
	private JScrollPane scrollPane;
	private JLabel lblKategorier;
	private JLabel lblRvarorISsong;
	private JLabel currentMonth;
	private JLabel lblVljVisningEnligt;
	private JButton btnFrukter;
	private JButton btnRotfrukter;
	private JButton btnKtt;
	private JButton btnBr;
	private JLabel lblBr;
	private JLabel lblRotfrukter;
	private JLabel lblKtt;

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
		labelArray = new ArrayList<JLabel>();
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
				
				if(chosenCategory == null){
					chosenCategory = "Grönsaker";
				}

				switch (index) {
				case 0:
					parse.setDate(parse.getCurrDate());
					buttonLogic(chosenCategory);
					break;
				case 1:
					parse.setDate("01");
					buttonLogic(chosenCategory);
					currentMonth.setText("Januari");
					break;
				case 2:
					parse.setDate("02");
					buttonLogic(chosenCategory);
					currentMonth.setText("Februari");
					break;
				case 3:
					parse.setDate("03");
					buttonLogic(chosenCategory);
					currentMonth.setText("Mars");
					break;
				case 4:
					parse.setDate("04");
					buttonLogic(chosenCategory);
					currentMonth.setText("April");
					break;
				case 5:
					parse.setDate("05");
					buttonLogic(chosenCategory);
					currentMonth.setText("Maj");
					break;
				case 6:
					parse.setDate("06");
					buttonLogic(chosenCategory);
					currentMonth.setText("Juni");
					break;
				case 7:
					parse.setDate("07");
					buttonLogic(chosenCategory);
					currentMonth.setText("Juli");
					break;
				case 8:
					parse.setDate("08");
					buttonLogic(chosenCategory);
					currentMonth.setText("Augusti");
					break;
				case 9:
					parse.setDate("09");
					buttonLogic(chosenCategory);
					currentMonth.setText("September");
					break;
				case 10:
					parse.setDate("10");
					buttonLogic(chosenCategory);
					currentMonth.setText("Oktober");
					break;
				case 11:
					parse.setDate("11");
					buttonLogic(chosenCategory);
					currentMonth.setText("November");
					break;
				case 12:
					parse.setDate("12");
					buttonLogic(chosenCategory);
					currentMonth.setText("December");
				default:
					parse.setDate(parse.getCurrDate());
					break;
				}
			}
		});

		JButton greenButton = new JButton("");
		greenButton.setIcon(new ImageIcon(GUI.class.getResource("/images/Gr\u00F6nsaker.jpg")));
		greenButton.setForeground(Color.WHITE);
		greenButton.setBounds(30, 265, 100, 100);
		greenButton.setHorizontalTextPosition(SwingConstants.CENTER);

		String[] zones = { "Välj zon", "Sydligaste Sverige", "Södra Sverige",
				"Norra Sverige" };

		zoneComboBox = new JComboBox(zones);
		zoneComboBox.setBounds(1131, 266, 196, 24);
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = zoneComboBox.getSelectedIndex();
				
				if(chosenCategory == null){
					chosenCategory = "Grönsaker";
				}

				switch (index) {
				case 0:
					parse.setZone("Z2");
					buttonLogic(chosenCategory);
					break;
				case 1:
					parse.setZone("Z3");
					buttonLogic(chosenCategory);
					break;
				case 2:
					parse.setZone("Z2");
					buttonLogic(chosenCategory);
					break;
				case 3:
					parse.setZone("Z1");
					buttonLogic(chosenCategory);
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
		
				JButton shellfishButton = new JButton("");
				shellfishButton.setIcon(new ImageIcon(GUI.class.getResource("/images/fisk och skaldjur.jpg")));
				shellfishButton.setForeground(Color.WHITE);
				shellfishButton.setBounds(160, 266, 100, 100);
				getContentPane().add(shellfishButton);
				shellfishButton.setHorizontalTextPosition(SwingConstants.CENTER);
				shellfishButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg1) {
						buttonLogic("Fisk_och_skaldjur");
					}

				});
		
		btnFrukter = new JButton("");
		btnFrukter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonLogic("Frukt");
			}
		});
		btnFrukter.setIcon(new ImageIcon(GUI.class.getResource("/images/Frukt.jpg")));
		btnFrukter.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFrukter.setForeground(Color.WHITE);
		btnFrukter.setBounds(30, 397, 100, 100);
		getContentPane().add(btnFrukter);
		
		btnRotfrukter = new JButton("");
		btnRotfrukter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Rotsaker");
			}
		});
		btnRotfrukter.setIcon(new ImageIcon(GUI.class.getResource("/images/Rotfrukter.jpg")));
		btnRotfrukter.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRotfrukter.setForeground(Color.WHITE);
		btnRotfrukter.setBounds(30, 529, 100, 100);
		getContentPane().add(btnRotfrukter);
		
		btnKtt = new JButton("");
		btnKtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Kött");
			}
		});
		btnKtt.setIcon(new ImageIcon(GUI.class.getResource("/images/K\u00F6tt.jpg")));
		btnKtt.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKtt.setForeground(Color.WHITE);
		btnKtt.setBounds(160, 529, 100, 100);
		getContentPane().add(btnKtt);
		
		btnBr = new JButton("");
		btnBr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Bär");
			}
		});
		btnBr.setIcon(new ImageIcon(GUI.class.getResource("/images/B\u00E4r.jpg")));
		btnBr.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBr.setForeground(Color.WHITE);
		btnBr.setBounds(160, 397, 100, 100);
		getContentPane().add(btnBr);
		
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
		
		JLabel lblGrnsaker = new JLabel("Gr\u00F6nsaker");
		lblGrnsaker.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblGrnsaker.setBounds(53, 368, 58, 16);
		getContentPane().add(lblGrnsaker);
		
		JLabel lblFiskSkaldjur = new JLabel("Fisk & Skaldjur");
		lblFiskSkaldjur.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblFiskSkaldjur.setBounds(170, 368, 86, 16);
		getContentPane().add(lblFiskSkaldjur);
		
		JLabel lblFrukter = new JLabel("Frukter");
		lblFrukter.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblFrukter.setBounds(60, 500, 40, 16);
		getContentPane().add(lblFrukter);
		
		lblBr = new JLabel("B\u00E4r");
		lblBr.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblBr.setBounds(200, 500, 19, 16);
		getContentPane().add(lblBr);
		
		lblRotfrukter = new JLabel("Rotfrukter");
		lblRotfrukter.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblRotfrukter.setBounds(55, 631, 56, 16);
		getContentPane().add(lblRotfrukter);
		
		lblKtt = new JLabel("K\u00F6tt");
		lblKtt.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblKtt.setBounds(200, 631, 23, 16);
		getContentPane().add(lblKtt);
		scrollPane.setBounds(298, 266, 790, 466);
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
			panelHeight=1500;
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
		setResizable(true);
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
		chosenCategory = category;
		
		
		if (buttons != null) {
			nrButtons=buttons.length;
			for (i = 0; i < buttons.length; i++) {

				panel.remove(buttons[i]);
				panel.remove(labels[i]);
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
		labels = new JLabel[nameArray.size()];
		
		for (i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(nameArray.get(i).replace('_', ' '),
					new ImageIcon(URLImage.getURLImage(URLArray.get(i))));
			
			labels[i] = new JLabel(nameArray.get(i).replace('_', ' '), JLabel.CENTER);
			labels[i].setForeground(Color.BLACK);

			if (i == lastRow) {
				rowY = rowY + buttonHeight+20;
				lastRow = i + setButtonsPerRows;
				// System.out.println(rowY + " Y");

			} else {

			}

			// System.out.println(buttonCounter);
			if (buttonCounter == setButtonsPerRows) {
				buttonCounter = 0;

				buttons[i].setBounds((buttonWidth * buttonCounter), rowY,
						buttonWidth, buttonHeight);
				
				labels[i].setBounds((buttonWidth * buttonCounter), rowY+120,
						labelWidth, labelHeight);
				
			} else {

				buttons[i].setBounds(
						(buttonWidth * buttonCounter + 10 * buttonCounter),
						rowY, buttonWidth, buttonHeight);
				
				labels[i].setBounds(
						(buttonWidth * buttonCounter + 10 * buttonCounter),
						rowY+120, labelWidth, labelHeight);
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
			panel.add(labels[i]);
			panel.revalidate();
			validate();
			panel.repaint();
	
		}
	}
	public JPanel getPanel() {
		return panel;
	}
}