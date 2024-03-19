package gui;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.io.File;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import html.Horario;

import javax.swing.*;
import java.awt.*;
import html.CreateHTML;

public class LancaBrowser  { 
	
	private final submitPage submitFilePage;
//	private CreateHTML htmlCreator;
	
	public LancaBrowser() {
		submitFilePage = new submitPage();
		submitFilePage.setVisible(true);
		setUpContinueAndSubmitButton();
	}
	
	public static void main(String[] args) throws IOException {
	//	SwingUtilities.invokeLater(LancaBrowser::new);
		LancaBrowser lb = new LancaBrowser();
	}
	
	private void setUpContinueAndSubmitButton () {
		submitFilePage.getContinueButton().addActionListener(e -> {
			// Vai buscar o input do utilizador
			String input = submitFilePage.getCsvFileLocationTextField().getText();
			String str1 = submitFilePage.getCsvSaveFileLocation().getText();
			if (input != null && !input.isEmpty()) {

				// Verifica se o input é um URL ou um caminho para um ficheiro
				if (input.matches("^https?://.*")) {
					submitFilePage.setRemoteFile(true);
					try {
						URL remoteFile = new URL(input);
						if (saveToLocalFile(remoteFile.openStream(), "HorarioRemoto.csv")) {
							Horario horario = new Horario("HorarioRemoto.csv");
							CreateHTML ch = new CreateHTML(horario,str1);
					    	ch.generateHTMLPage();
					    	StringBuilder novaString = new StringBuilder();
					    	String str = ch.getPathHtml();
					    	for (int i = 0; i < str.length(); i++) {
					    	    char caractere = str.charAt(i);
					    	    if (caractere == '\\') {
					    	        novaString.append('/');
					    	    } else {
					    	        novaString.append(caractere);
					    	    }
					    	}
					    	Desktop desk = Desktop.getDesktop();
					    	desk.browse(new java.net.URI("file://" + novaString));
							submitFilePage.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(submitFilePage, "Error processing remote file, please try again",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// Neste caso o input é um caminho para um ficheiro local
				else {
					File file = new File(input);
					if (file.exists()) {
						// Se existir usa o csv para gerar os dados para a pagina HTML e depois abre a
						// pagina
						Horario horario = new Horario(input);
						CreateHTML ch = new CreateHTML(horario,str1);
				    	ch.generateHTMLPage();
				    	StringBuilder novaString = new StringBuilder();
				    	String str = ch.getPathHtml();
				    	for (int i = 0; i < str.length(); i++) {
				    	    char caractere = str.charAt(i);
				    	    if (caractere == '\\') {
				    	        novaString.append('/');
				    	    } else {
				    	        novaString.append(caractere);
				    	    }
				    	}
				    	Desktop desk = Desktop.getDesktop();
				    	try {
							desk.browse(new java.net.URI("file://" + novaString));
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						submitFilePage.setVisible(false);
					} else {
						// No caso de não existir o ficheiro aparece uma mensagem de erro
						JOptionPane.showMessageDialog(submitFilePage, "File does not exist: " + file, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				// No caso de não existir o ficheiro aparece uma mensagem de erro
				JOptionPane.showMessageDialog(submitFilePage, "Invalid file path.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private boolean saveToLocalFile(InputStream inputStream, String localFilePath) {
		try {
			File localFile = new File(localFilePath);
		//	localFile.createNewFile();

			FileOutputStream outputStream = new FileOutputStream(localFile);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}  
