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
import javax.swing.JScrollBar;
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
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.ComponentOrientation;
import javax.swing.border.CompoundBorder;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.SystemColor;

public class GUI extends JFrame {
	
	private int lastRow = 0;
	private int rowX = 0;
	private int rowY = 0;
	private int buttonCounter = 0;
	private int nrButtons = 0;
	private int panelHeight = 0;

	private JPanel panel;
	private int i;
	private String chosenCategory;
	private GroupLayout gl_panel;
	private String titleText;
	private String monthNow;
	private String zoneNow;
	private Document doc;
	private Parse parse;
	
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


	private JScrollPane scrollPane;
	private JLabel lblKategorier;
	private JLabel titleTextTop;
	private JLabel lblVljVisningEnligt;
	private JButton btnFrukter;
	private JButton btnRotfrukter;
	private JButton btnKtt;
	private JButton btnBr;
	private JLabel lblBr;
	private JLabel lblRotfrukter;
	private JLabel lblKtt;
	private JLabel info;
	private JLabel start;
	private JButton closeButt;
	private JPanel rawInfoPanel;
	private JTextPane rawInfoText;
	private JLabel rawInfoTitle;
	private JTextPane txtpnSavojklOdlasP;
	private JLabel lblMiljpverkan;

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
		setTitle("S\u00E4songsmat 1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/icon.png")));

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
		
		
		final String[] patternExamples = { "Välj månad", "Januari", "Februari",
				"Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September",
				"Oktober", "November", "December", };
		
		
		// gets current month and converts to INT to get month string from patterExamples
		if (Integer.parseInt(parse.getCurrDate())<10){
			monthNow=patternExamples[Integer.parseInt(parse.getCurrDate().substring(1,2))];
		}else{
			monthNow=patternExamples[Integer.parseInt(parse.getCurrDate())];			
		}
		
		
		//Create combobox for months and add case selections
		monthComboBox = new JComboBox(patternExamples);
		monthComboBox.setBounds(20, 261, 220, 25);
		monthComboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				int index = monthComboBox.getSelectedIndex();
				
				if (chosenCategory == null) {
					chosenCategory = "Grönsaker";
				}

				switch (index) {
				
				case 0:
					parse.setDate(parse.getCurrDate());
					buttonLogic(chosenCategory);
					break;
					
				case 1:
					parse.setDate("01");
					monthNow="Januari";
					buttonLogic(chosenCategory);
					break;
					
				case 2:
					parse.setDate("02");
					monthNow="Februari";
					buttonLogic(chosenCategory);
					break;
					
				case 3:
					parse.setDate("03");
					monthNow="Mars";
					buttonLogic(chosenCategory);
					break;
					
				case 4:
					parse.setDate("04");
					monthNow="April";
					buttonLogic(chosenCategory);
					break;
					
				case 5:
					parse.setDate("05");
					monthNow="Maj";
					buttonLogic(chosenCategory);
					break;
					
				case 6:
					parse.setDate("06");
					monthNow="Juni";
					buttonLogic(chosenCategory);
					break;
					
				case 7:
					parse.setDate("07");
					monthNow="Juli";
					buttonLogic(chosenCategory);
					break;
					
				case 8:
					parse.setDate("08");
					monthNow="Augusti";
					buttonLogic(chosenCategory);
					break;
					
				case 9:
					parse.setDate("09");
					monthNow="September";
					buttonLogic(chosenCategory);
					break;
					
				case 10:
					parse.setDate("10");
					monthNow="Oktober";
					buttonLogic(chosenCategory);
					break;
					
				case 11:
					parse.setDate("11");
					monthNow="November";
					buttonLogic(chosenCategory);
					break;
					
				case 12:
					parse.setDate("12");
					monthNow="December";
					buttonLogic(chosenCategory);
					
				default:
					parse.setDate(parse.getCurrDate());
					break;
					
				}
				
			}
		});



		String[] zones = { "Välj zon", "Sydligaste Sverige", "Södra Sverige",
				"Norra Sverige" };

		zoneComboBox = new JComboBox(zones);
		zoneComboBox.setBounds(20, 224, 220, 24);
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int index = zoneComboBox.getSelectedIndex();

				if (chosenCategory == null) {
					chosenCategory = "Grönsaker";
				}

				switch (index) {
				case 0:
					parse.setZone("Z2");
					zoneNow = " i södra Sverige";
					buttonLogic(chosenCategory);
					break;
				case 1:
					parse.setZone("Z3");
					zoneNow = " i sydligaste Sverige";
					buttonLogic(chosenCategory);
					break;
				case 2:
					parse.setZone("Z2");
					zoneNow = " i södra Sverige";
					buttonLogic(chosenCategory);
					break;
				case 3:
					parse.setZone("Z1");
					zoneNow = " i norra Sverige";
					buttonLogic(chosenCategory);
					break;
				default:
					parse.setZone("Z2");
					zoneNow = " i södra Sverige";
					break;
				}
				
			}
		});

		
		getContentPane().setLayout(null);
		getContentPane().add(monthComboBox);
		
		rawInfoPanel = new JPanel();
		rawInfoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		rawInfoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		rawInfoPanel.addContainerListener(null);
		
		rawInfoPanel.setBackground(new Color(143, 188, 143));
		rawInfoPanel.setVisible(false);
		rawInfoPanel.setBounds(269, 224, 723, 479);
		getContentPane().add(rawInfoPanel);
		rawInfoPanel.setLayout(null);
		
		
		closeButt = new JButton("St\u00E4ng");
		closeButt.setBounds(638, 11, 75, 23);
		rawInfoPanel.add(closeButt);
		
		rawInfoTitle = new JLabel("SAVOJK\u00C5L");
		rawInfoTitle.setForeground(new Color(255, 255, 255));
		rawInfoTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		rawInfoTitle.setBounds(28, 32, 284, 23);
		rawInfoPanel.add(rawInfoTitle);
		
		lblMiljpverkan = new JLabel("Milj\u00F6p\u00E5verkan");
		lblMiljpverkan.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblMiljpverkan.setBounds(360, 51, 175, 23);
		rawInfoPanel.add(lblMiljpverkan);
		
		rawInfoText = new JTextPane();
		rawInfoText.setBackground(new Color(128, 128, 128));
		rawInfoText.setForeground(new Color(0, 0, 0));
		rawInfoText.setOpaque(false);
		rawInfoText.setBorder(null);
		rawInfoText.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		rawInfoText.setText("Savojk\u00E5l \u00E4r en k\u00E5lsort med krusiga, m\u00F6rkgr\u00F6na blad. Den finns skandinaviskt odlad under h\u00F6sten, och nordeuropeisk hela vintern. \u00D6vriga tider p\u00E5 \u00E5ret kommer den oftast fr\u00E5n Italien. Namnet kommer av ursprungsomr\u00E5det, det medeltida alp-grevskapet Savojen (numera delat mellan Frankrike och Italien). I Norden har den odlats sedan 1600-talet.\r\n\r\nSavojk\u00E5l g\u00E5r att \u00E4ta r\u00E5, d\u00E5 g\u00E4rna strimlad i sallader och liknande, eller tillagad.");
		rawInfoText.setBounds(28, 62, 322, 377);
		rawInfoPanel.add(rawInfoText);
		
		txtpnSavojklOdlasP = new JTextPane();
		txtpnSavojklOdlasP.setText("R\u00E5varan odlas p\u00E5 friland, vilket \u00E4r mindre energikr\u00E4vande \u00E4n v\u00E4xthusodling. S\u00E4rskilt under h\u00F6sten, n\u00E4r det finns lokalt odlad savojk\u00E5l i Skandinavien, \u00E4r den ett bra klimatval. Som m\u00E5nga k\u00E5lsorter kr\u00E4ver den dock mycket n\u00E4ring, och i konventionell odling anv\u00E4nds d\u00E4rf\u00F6r mycket g\u00F6dsel. Vid ekologisk odling anv\u00E4nds inget konstg\u00F6dsel.");
		txtpnSavojklOdlasP.setOpaque(false);
		txtpnSavojklOdlasP.setForeground(Color.BLACK);
		txtpnSavojklOdlasP.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		txtpnSavojklOdlasP.setBorder(null);
		txtpnSavojklOdlasP.setBackground(Color.GRAY);
		txtpnSavojklOdlasP.setBounds(360, 81, 322, 358);
		rawInfoPanel.add(txtpnSavojklOdlasP);
		
		closeButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rawInfoPanel.setVisible(false);
			}
		});
		getContentPane().add(zoneComboBox);

		
		JButton greenButton = new JButton("");
		greenButton.setIcon(new ImageIcon(GUI.class.getResource("/images/Gr\u00F6nsaker.jpg")));
		greenButton.setForeground(Color.WHITE);
		greenButton.setBounds(20, 321, 100, 100);
		greenButton.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(greenButton);
		
		JButton shellfishButton = new JButton("");
		shellfishButton.setIcon(new ImageIcon(GUI.class
				.getResource("/images/fisk och skaldjur.jpg")));
		shellfishButton.setForeground(Color.WHITE);
		shellfishButton.setBounds(140, 322, 100, 100);
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
		btnFrukter.setIcon(new ImageIcon(GUI.class
				.getResource("/images/Frukt.jpg")));
		btnFrukter.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFrukter.setForeground(Color.WHITE);
		btnFrukter.setBounds(20, 453, 100, 100);
		getContentPane().add(btnFrukter);

		btnRotfrukter = new JButton("");
		btnRotfrukter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Rotsaker");
				
			}
		});
		btnRotfrukter.setIcon(new ImageIcon(GUI.class
				.getResource("/images/Rotfrukter.jpg")));
		btnRotfrukter.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRotfrukter.setForeground(Color.WHITE);
		btnRotfrukter.setBounds(20, 585, 100, 100);
		getContentPane().add(btnRotfrukter);

		btnKtt = new JButton("");
		btnKtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Kött");
				
			}
		});
		btnKtt.setIcon(new ImageIcon(GUI.class
				.getResource("/images/K\u00F6tt.jpg")));
		btnKtt.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKtt.setForeground(Color.WHITE);
		btnKtt.setBounds(140, 585, 100, 100);
		getContentPane().add(btnKtt);

		btnBr = new JButton("");
		btnBr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLogic("Bär");
				
			}
		});
		btnBr.setIcon(new ImageIcon(GUI.class
				.getResource("/images/B\u00E4r.jpg")));
		btnBr.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBr.setForeground(Color.WHITE);
		btnBr.setBounds(140, 453, 100, 100);
		getContentPane().add(btnBr);

		lblKategorier = new JLabel("Kategorier");
		lblKategorier.setHorizontalAlignment(SwingConstants.CENTER);
		lblKategorier.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblKategorier.setBounds(20, 293, 220, 25);
		getContentPane().add(lblKategorier);

		titleTextTop = new JLabel("");
		titleTextTop.setHorizontalAlignment(SwingConstants.LEFT);
		titleTextTop.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		titleTextTop.setBounds(269, 182, 615, 31);
		getContentPane().add(titleTextTop);

		lblVljVisningEnligt = new JLabel("Visa r\u00E5varor f\u00F6r:");
		lblVljVisningEnligt.setFont(new Font("Franklin Gothic Medium",
				Font.BOLD, 20));
		lblVljVisningEnligt.setBounds(20, 182, 220, 31);
		getContentPane().add(lblVljVisningEnligt);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setOpaque(false);

		JLabel lblGrnsaker = new JLabel("Gr\u00F6nsaker");
		lblGrnsaker.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrnsaker.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblGrnsaker.setBounds(20, 424, 100, 16);
		getContentPane().add(lblGrnsaker);

		JLabel lblFiskSkaldjur = new JLabel("Fisk & Skaldjur");
		lblFiskSkaldjur.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiskSkaldjur.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblFiskSkaldjur.setBounds(140, 424, 100, 16);
		getContentPane().add(lblFiskSkaldjur);

		JLabel lblFrukter = new JLabel("Frukter");
		lblFrukter.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrukter.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblFrukter.setBounds(20, 556, 100, 16);
		getContentPane().add(lblFrukter);

		lblBr = new JLabel("B\u00E4r");
		lblBr.setHorizontalAlignment(SwingConstants.CENTER);
		lblBr.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblBr.setBounds(140, 556, 100, 16);
		getContentPane().add(lblBr);

		lblRotfrukter = new JLabel("Rotfrukter");
		lblRotfrukter.setHorizontalAlignment(SwingConstants.CENTER);
		lblRotfrukter.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblRotfrukter.setBounds(22, 687, 98, 16);
		getContentPane().add(lblRotfrukter);

		lblKtt = new JLabel("K\u00F6tt");
		lblKtt.setHorizontalAlignment(SwingConstants.CENTER);
		lblKtt.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblKtt.setBounds(140, 687, 100, 16);
		getContentPane().add(lblKtt);
		
		start = new JLabel("<html><center>Välkomen till Säsongsmat - Din guide till klimatsmartare mat! <br><br><br> Säsongsmat är en applikation som hjälper dig att göra<br> smarta val inför din matlagning med klimatet i fokus. <br><br>Du börjar med att välja bland kategorierna till vänster <br> och därefter klickar du på den råvara<br>du vill läsa mer om. <br><br> Du kan även välja säsong eller del av Sverige<br>för att specificera din sökning.</center></html>");
		start.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		start.setHorizontalAlignment(SwingConstants.CENTER);
		start.setBounds(270, 225, 722, 478);
		getContentPane().add(start);
		
		info = new JLabel("");
		info.setBackground(new Color(60, 179, 113));
		info.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(354, 422, 544, 45);
		getContentPane().add(info);

		MyLine myLine = new MyLine();
		myLine.setBounds(254, 224, 9, 479);
		getContentPane().add(myLine);
		scrollPane.setBounds(269, 224, 723, 479);
		getContentPane().add(scrollPane);
		panel = new JPanel();
		panel.setOpaque(false);
		scrollPane.setViewportView(panel);
		panel.setBorder(null);

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGap(0, 7, Short.MAX_VALUE));

		if (nrButtons == 0) {
			panelHeight = 1500;
			System.out.println("NOLLL");
		} else {
			panelHeight = nrButtons / 6 * 110;
			System.out.println("HÖJD: " + panelHeight);
		}
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGap(0, panelHeight, Short.MAX_VALUE));

		panel.setLayout(gl_panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1022, 718);
		lblNewLabel.setIcon(new ImageIcon(GUI.class.getResource("/images/Bakgrund-morotter_new2.jpg")));
		getContentPane().add(lblNewLabel);

		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonLogic("Grönsaker");
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 741);
		setResizable(false);
		setVisible(true);
	}

	public JComboBox getComboBox() {
		return monthComboBox;
	}

	// Creates buttons representing the search result depending on chosen category and month
	public void buttonLogic(String category) {
		rawInfoPanel.setVisible(false);
		scrollPane.setVisible(true);
		rowY = 0;
		lastRow = Constants.setButtonsPerRows;
		rowX = 30;
		buttonCounter = 0;
		chosenCategory = category;
		
		start.setText("");
		
		//Reset scrollbar to top position
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMinimum());

		if (buttons != null) {
			nrButtons = buttons.length;
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
		
		for (int i = 0; i < nameArray.size(); i++) {
			if (URLArray.get(i) == null) {
				URLArray.set(i, "http://beta.glorious.se/Ravaru_ikon_sasongsmat.png");
			}
		}
		
		if (nameArray.isEmpty()) {
			scrollPane.setVisible(false);
			info.setText("Hittade inget i säsong för den valda kategorien: "+chosenCategory);
			info.setOpaque(true);
		}else{
			
			info.setText("");
			info.setOpaque(false);
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

			labels[i] = new JLabel(nameArray.get(i).replace('_', ' '),
					JLabel.CENTER);
			labels[i].setForeground(Color.BLACK);

			if (i == lastRow) {
				rowY = rowY + Constants.buttonHeight + 20;
				lastRow = i + Constants.setButtonsPerRows;
				// System.out.println(rowY + " Y");

			} else {

			}

			// System.out.println(buttonCounter);
			if (buttonCounter == Constants.setButtonsPerRows) {
				buttonCounter = 0;

				buttons[i].setBounds((Constants.buttonWidth * buttonCounter), rowY,
						Constants.buttonWidth, Constants.buttonHeight);

				labels[i].setBounds((Constants.buttonWidth * buttonCounter), rowY + 120,
						Constants.labelWidth, Constants.labelHeight);

			} else {

				buttons[i].setBounds(
						(Constants.buttonWidth * buttonCounter + 10 * buttonCounter),
						rowY, Constants.buttonWidth, Constants.buttonHeight);

				labels[i].setBounds(
						(Constants.buttonWidth * buttonCounter + 10 * buttonCounter),
						rowY + 120, Constants.labelWidth, Constants.labelHeight);
			}

			buttonCounter++;
			// System.out.println(rowX);
			// buttons[i].setBounds((150*buttonCounter+10), rowY,
			// buttonWidth, buttonWidth);

			buttons[i].setActionCommand(nameArray.get(i));

			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String choice = e.getActionCommand();
					rawInfoPanel.setVisible(true);
					rawInfoTitle.setText(choice);
					rawInfoText.setText("Råvaran "+choice+" är en råvara med krusiga, mörkgröna blad. Den finns skandinaviskt odlad under hösten, och nordeuropeisk hela vintern. Övriga tider på året kommer den oftast från Italien. Namnet kommer av ursprungsområdet, det medeltida alp-grevskapet Savojen numera delat mellan Frankrike och Italien. I Norden har den odlats sedan 1600-talet.Savojkål går att äta rå, då gärna strimlad i sallader och liknande, eller tillagad.");
					//JOptionPane.showMessageDialog(null, "Råvaran "+ choice + " är i säsong\nI nästa uppdatering av Säsongsråvaror \nkommer det finnas mer information om råvaran.");
				}
			});

			panel.add(buttons[i]);
			panel.add(labels[i]);
			panel.revalidate();
			validate();
			panel.repaint();

		}
		//Set title on search area
		if(zoneNow == null)
		{
			zoneNow = "";
		}
		titleTextTop.setText(chosenCategory.replace("_", " ")+" i säsong under " + monthNow + "" + zoneNow);
		
		
	}

	public JPanel getRawInfoPanel() {
		return rawInfoPanel;
	}
	public JTextPane getRawInfoText() {
		return rawInfoText;
	}
	public JLabel getRawInfoTitle() {
		return rawInfoTitle;
	}
}