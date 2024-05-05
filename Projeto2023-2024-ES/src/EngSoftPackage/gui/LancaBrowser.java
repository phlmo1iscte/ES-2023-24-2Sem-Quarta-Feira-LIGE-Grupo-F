package EngSoftPackage.gui;

import EngSoftPackage.export.HorarioToCsv;
import EngSoftPackage.export.HorarioToJson;
import EngSoftPackage.html.CreateHTML;
import EngSoftPackage.html.Horario;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.JOptionPane;



public class LancaBrowser  { 
	
	private final submitPage submitFilePage;
	
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

			// filePath é o caminho/localização do ficheiro CSV de input
			String filePath = submitFilePage.getCsvFileLocationTextField().getText();
			// pathToExport é o caminho/localização que vamos utilizar para guardar o output
			//pode ser um ficheiro CSV ou Json (4ºRequesito)
			String pathToExport = submitFilePage.getCsvSaveFileLocation().getText();

			//confirma se foi passada uma localização
			if (filePath != null && !filePath.isEmpty()) {

				// Verifica se o input é um URL ou um caminho para um ficheiro
				if (filePath.matches("^https?://.*")) {
					submitFilePage.setRemoteFile(true);
					try {
						
						/*
						 * Vamos criar uma cópia deste ficheiro passado por URL
						 * Para a nossa pasta de assets e conseguimos ter acesso local 
						 * para manipulaçãodos dados.
						*/
						URL remoteFile = new URL(filePath);
						InputStream inputStream = remoteFile.openStream();
						String remoteFileLocalPath = "Projeto2023-2024-ES/assets/HorarioRemoto.csv";
						
						//fazemos num if a criação do ficheiro local para sabermos se tivemos sucesso ou não na criação do mesmo
						if (saveToLocalFile2(inputStream, remoteFileLocalPath)) {

							Horario horario = new Horario(remoteFileLocalPath);
							CreateHTML ch = new CreateHTML(horario, remoteFileLocalPath);
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
						} else {
							JOptionPane.showMessageDialog(submitFilePage, "Erro ao  Processar o ficheiro removo, tente novamente.",
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
					File file = new File(filePath);
					 if (file.exists()) {

						// Se existir usa o csv para gerar os dados para a pagina HTML e depois abre a
						// pagina
						Horario horario = new Horario(filePath);
						System.out.println(horario);

						/*Metodos de export abaixo, já funcionais mas comentados 
						para ver aonde devem ser implementados */
						//HorarioToJson hToJson = new HorarioToJson(horario,  pathToExport);
						//HorarioToCsv hToCsv = new HorarioToCsv(horario,  pathToExport);

						CreateHTML ch = new CreateHTML(horario, filePath);

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
						//provavelmente vamos precisar de implementar 2 botões aqui para exportar como Json e csv??
						submitFilePage.setVisible(false);
					
					} else {
						// No caso de não existir o ficheiro aparece uma mensagem de erro
							JOptionPane.showMessageDialog(submitFilePage, "Ficheiro não existe! " + file, "Erro",
							JOptionPane.ERROR_MESSAGE);
					}
				}

			} else {
				// No caso de não existir o ficheiro aparece uma mensagem de erro
				JOptionPane.showMessageDialog(submitFilePage, 
				"Localização inválida!", 
				"Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	/*
	 *Cria o ficheiro CSV local e devolve true se tiver sucesso
	 *
	 * Classe InputStream é uma classe abstrata que serve como a superclasse de todos os 
	 * tipos de classes de entrada de fluxo de bytes. Ele fornece uma interface comum 
	 * para ler bytes de diferentes fontes, como arquivos, arrays de bytes, buffers de memória, 
	 * conexões de rede, entre outros.
	 * 
	 * @param inputStream   	faz a leitura do ficheiro. 
	 * @param localFilePath 	localização local do ficheiro.
	 */
	private boolean saveToLocalFile(InputStream inputStream, String localFilePath) {
		try {
			File localFile = new File(localFilePath);

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

	public boolean saveToLocalFile2(InputStream inputStream, String localFilePath) {
        
        try {
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Criando um CSVParser para ler o arquivo CSV
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            // Criando um BufferedWriter para escrever os dados no novo arquivo CSV
            BufferedWriter writer = new BufferedWriter(new FileWriter(localFilePath));

            // Escrevendo os dados no novo arquivo CSV
            for (CSVRecord record : csvParser) {
                writer.write(String.join(",", record));
                writer.newLine();
            }

            // Fechando os recursos
            csvParser.close();
            writer.close();
			return true;

        } catch (IOException e) {
            e.printStackTrace();
			return false;
        }
    }
	
}  


