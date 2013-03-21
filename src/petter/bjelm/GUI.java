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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class GUI extends JFrame {

	private JPanel panel;
	private int i;
	private int lastRow = 0;
	private int rowX = 0;
	private int rowY = 0;
	private int setButtonsPerRows = 9;
	private int buttonCounter = 0;

	Document doc;
	private Parse parse;

	private ArrayList<String> nameArray;
	private ArrayList<JButton> buttonArray;
	private ArrayList<String> URLArray;
	private GetCategory data;
	private ImageParsing imageParse;
	private JButton[] buttons;
	private JComboBox monthComboBox;
	private GetImgFromURL URLImage;

	private int buttonWidth = 100;
	private int buttonHeight = 100;

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

		JButton button = new JButton("Skaldjur");
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				rowY = 0;
				lastRow = setButtonsPerRows;
				rowX = 30;
				buttonCounter = 0;

				if (buttons != null) {
					for (i = 0; i < buttons.length; i++) {

						panel.remove(buttons[i]);
						panel.revalidate();
						panel.repaint();

					}
				}

				try {
					doc = parse.ParseDoc("Skaldjur");
				} catch (Exception e8) {
					e8.printStackTrace();
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

				for (int i = 0; i < URLArray.size(); i++) {
					System.out.print(nameArray.get(i) + "\t");
					System.out.println(URLArray.get(i));
				}

				buttons = new JButton[nameArray.size()];

				for (i = 0; i < buttons.length; i++) {

					buttons[i] = new JButton(
							nameArray.get(i).replace('_', ' '), new ImageIcon(
									URLImage.getURLImage(URLArray.get(i))));
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

						buttons[i].setBounds((buttonWidth * buttonCounter),
								rowY, buttonWidth, buttonHeight);
					} else {

						buttons[i].setBounds(
								(buttonWidth * buttonCounter + 10 * i), rowY,
								buttonWidth, buttonHeight);
					}

					buttonCounter++;
					// System.out.println(rowX);
					// buttons[i].setBounds((150*buttonCounter+10), rowY,
					// buttonWidth, buttonWidth);

					buttons[i].setActionCommand(nameArray.get(i));

					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String choice = e.getActionCommand();
							JOptionPane.showMessageDialog(null,
									"You have clicked: " + choice);
						}
					});

					panel.add(buttons[i]);
					panel.revalidate();
					validate();
					panel.repaint();
				}

			}

		});

		String[] patternExamples = { "Välj månad", "Januari", "Februari",
				"Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September",
				"Oktober", "November", "December", };

		monthComboBox = new JComboBox(patternExamples);
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
					parse.setDate("00");
					break;
				}
			}
		});

		JButton buttonTwo = new JButton("Grönsaker");
		buttonTwo.setHorizontalTextPosition(SwingConstants.CENTER);
		panel = new JPanel();
		panel.setBorder(null);

		String[] zones = { "Välj zon", "Sydligaste Sverige", "Södra Sverige",
				"Norra Sverige" };

		JComboBox zoneComboBox = new JComboBox(zones);
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = monthComboBox.getSelectedIndex();

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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																934,
																Short.MAX_VALUE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				buttonTwo,
																				GroupLayout.DEFAULT_SIZE,
																				458,
																				Short.MAX_VALUE)
																		.addGap(18)
																		.addComponent(
																				button,
																				GroupLayout.DEFAULT_SIZE,
																				458,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				monthComboBox,
																				GroupLayout.PREFERRED_SIZE,
																				716,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				zoneComboBox,
																				0,
																				200,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																button,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonTwo,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																monthComboBox,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																zoneComboBox,
																GroupLayout.PREFERRED_SIZE,
																24,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												642, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setAutoCreateGaps(true);
		gl_panel.setAutoCreateContainerGaps(true);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGap(0, 736, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGap(0, 539, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rowY = 0;
				lastRow = setButtonsPerRows;
				rowX = 30;
				buttonCounter = 0;

				if (buttons != null) {
					for (i = 0; i < buttons.length; i++) {

						panel.remove(buttons[i]);
						panel.revalidate();
						panel.repaint();

					}
				}

				try {
					doc = parse.ParseDoc("Grönsaker");
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

				for (int i = 0; i < URLArray.size(); i++) {
					System.out.print(nameArray.get(i) + "\t");
					System.out.println(URLArray.get(i));
				}

				buttons = new JButton[nameArray.size()];

				for (i = 0; i < buttons.length; i++) {

					buttons[i] = new JButton(
							nameArray.get(i).replace('_', ' '), new ImageIcon(
									URLImage.getURLImage(URLArray.get(i))));
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

						buttons[i].setBounds((buttonWidth * buttonCounter),
								rowY, buttonWidth, buttonHeight);
					} else {

						buttons[i]
								.setBounds(
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
							JOptionPane.showMessageDialog(null,
									"You have clicked: " + choice);
						}
					});

					panel.add(buttons[i]);
					panel.revalidate();
					validate();
					panel.repaint();
				}

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 768);
		setResizable(false);
		setVisible(true);
	}

	public JComboBox getComboBox() {
		return monthComboBox;
	}
}