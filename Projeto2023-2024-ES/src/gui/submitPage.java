package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class submitPage extends JFrame implements LayoutDefinable {
	
	private final JTextField csvFileLocation;
	private final JTextField saveFile;
	private final JButton continueButton;
    private boolean isRemoteFile = false;
	
	public submitPage(){
	
	    
	   setTitle("Main Page");

       GridBagConstraints gbc = setUpPageFrame(this, "Main Page");


       //Criação do painel central de topo
       JPanel topPanel = new JPanel(new GridBagLayout());
       GridBagConstraints gbcTop = new GridBagConstraints();

       //Posicionamento dos paineis
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 3; // 2 colunas
       gbc.fill = GridBagConstraints.BOTH;
       gbc.weightx = 1.0; // Take all horizontal space
       gbc.weighty = 0.5; // Cada painel adicionado ocupará metade do espaço vertical

       //Mesma lógica mas para o painel de topo
       gbcTop.gridx = 0;
       gbcTop.gridy = 0;
       gbcTop.fill = GridBagConstraints.CENTER;
       gbcTop.weighty = 0.5;

       //Titulo que é adicionado ao painel de topo
       JLabel headerLabel = new JLabel("CSV to WebBrowser Schedule ");
       headerLabel.setFont(new Font("Arial", Font.BOLD, 60));
       headerLabel.setForeground(new Color(44, 62, 80));
       topPanel.add(headerLabel, gbcTop);

       gbcTop.weighty = 0;
       //Caixa de texto para submeter o ficheiro e adição ao painel de topo
       JLabel csvFileLocationLabel = new JLabel("CSV File Location");
       csvFileLocationLabel.setFont(new Font("Arial", Font.BOLD, 20));
       csvFileLocationLabel.setForeground(new Color(44, 62, 80));
       gbcTop.gridy = 2;
       topPanel.add(csvFileLocationLabel, gbcTop);
       csvFileLocation = new JTextField("Please submit full .csv file location here", 90);
       gbcTop.gridy = 3;
       topPanel.add(csvFileLocation, gbcTop);
       
       gbcTop.weighty=0;
       JLabel saveFileLabel = new JLabel("Save Location");
       saveFileLabel.setFont(new Font("Arial", Font.BOLD, 20));
       saveFileLabel.setForeground(new Color(44, 62, 80));
       gbcTop.gridy = 4;
       topPanel.add(saveFileLabel, gbcTop);
       saveFile = new JTextField("Onde guardar", 90);
       gbcTop.gridy = 5;
       topPanel.add(saveFile, gbcTop);

       //Por o painel de topo na pagina
       add(topPanel, gbc);

       //Criação do painel de baixo
       JPanel bottomPanel = new JPanel(new GridBagLayout());
       GridBagConstraints gbcBottomPanel = new GridBagConstraints();
       gbcBottomPanel.gridx = 0;
       gbcBottomPanel.gridy = 0;
       gbcBottomPanel.weightx = 0.5;
       gbcBottomPanel.anchor = GridBagConstraints.CENTER;
	    
       continueButton = new JButton("Submit & Continue");
       continueButton.setBackground(Color.BLUE);
       continueButton.setForeground(Color.WHITE);
       continueButton.setPreferredSize(new Dimension(150, 50));
       bottomPanel.add(continueButton, gbcBottomPanel);
       gbcBottomPanel.gridx = 1;
       JButton reloadLastSessionButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE, "Reload Last Session", new Dimension(150, 50));
       gbc.gridx = 0;
       gbc.gridy = 1;
       gbc.gridwidth = 1; // Reset grid width to 1
       gbc.weighty = 1; // Leva o espaço vertical por completo

       gbc.gridx = 1;
       add(bottomPanel, gbc);

       //Faz com que a pagina ao ser iniciada apareça no centro do ecrã
       setLocationRelativeTo(null);
		
 
	} 
	    
	    
	  
	 private GridBagConstraints setUpPageFrame(JFrame frame, String title) {
	        frame.setTitle(title);
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        frame.setBackground(Color.darkGray);
	        frame.setLayout(new GridBagLayout());
	        return new GridBagConstraints();
	    }
	 
	 public JButton getContinueButton() { return continueButton;}
	 public JTextField getCsvFileLocationTextField() {
	        return csvFileLocation;
	    }
	 public JTextField getCsvSaveFileLocation(){return saveFile;}
	 public void setRemoteFile(boolean remoteFile) { isRemoteFile = remoteFile;}

}
