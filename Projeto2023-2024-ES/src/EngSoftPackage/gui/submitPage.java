package EngSoftPackage.gui;

import java.awt.*;
import javax.swing.*;


/**
 * Esta classe é responsável por fornecer uma interface gráfica para submissão de dados pelo usuário.
 * 
 * Implementa a interface LayoutDefinable
 *
 * Ela define a estrutura e comportamento de uma página de submissão, incluindo os campos necessários
 * e ações de interação, adequando-se às definições de layout especificadas pela interface
 * 
 * Implementa metodos do tipo get (getContinueButton, getCsvFileLocationTextField, getCsvSaveFileLocation) e set(setUpPageFrame, setRemoteFile)
 */

public class submitPage extends JFrame implements LayoutDefinable {
	
	private final JTextField csvFileLocation;
	private final JTextField saveFile;
	private final JButton continueButton;
    private boolean isRemoteFile = false;
	

    /**
     * Construtor para a classe  submitPage
     * Este construtor inicializa a janela principal da app, configura o layout e adiciona os componentes essenciais.
     * 
     * A janela é configurada com um título "Página Inicial". Ele também define e adiciona dois painéis principais:
     * um painel superior que inclui rótulos e campos de texto para entrada de localização de arquivos, e um painel inferior
     * que contém botões para ações de usuário, como submeter dados e recarregar a última sessão.
     * <
     * Os componentes são configurados com tamanhos, cores e fontes específicas para garantir uma interface clara e acessível ao usuário.
     * O layout é gerenciado com  o {@link GridBagLayout}
     * 
     * A janela é centrada na tela ao final da inicialização, pronta para a interação do usuário.
     *
     */
	public submitPage(){
	
	   setTitle("Página Inicial");

       GridBagConstraints gbc = setUpPageFrame(this, "Página Inicial");

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
       JLabel headerLabel = new JLabel("Software para gestão de horários");
       headerLabel.setFont(new Font("Arial", Font.BOLD, 60));
       headerLabel.setForeground(new Color(44, 62, 80));
       topPanel.add(headerLabel, gbcTop);

       gbcTop.weighty = 0;
       //Caixa de texto para submeter o ficheiro e adição ao painel de topo
       JLabel csvFileLocationLabel = new JLabel("Localização do ficheiro CSV");
       csvFileLocationLabel.setFont(new Font("Arial", Font.BOLD, 20));
       csvFileLocationLabel.setForeground(new Color(44, 62, 80));
       gbcTop.gridy = 2;
       topPanel.add(csvFileLocationLabel, gbcTop);
       csvFileLocation = new JTextField("Por favor, submeta aqui a localização do ficheiro .csv.", 90);
       gbcTop.gridy = 3;
       topPanel.add(csvFileLocation, gbcTop);
       
       gbcTop.weighty=0;
       JLabel saveFileLabel = new JLabel("Localização do ficheiro a exportar");
       saveFileLabel.setFont(new Font("Arial", Font.BOLD, 20));
       saveFileLabel.setForeground(new Color(44, 62, 80));
       gbcTop.gridy = 4;
       topPanel.add(saveFileLabel, gbcTop);
       saveFile = new JTextField("Por favor, informe a localização onde guardar o ficheiro.", 90);
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
	    
       continueButton = new JButton("Submeter e continuar");
       continueButton.setBackground(Color.WHITE);
       continueButton.setForeground(new Color(44, 62, 80));
       continueButton.setPreferredSize(new Dimension(150, 50));
       bottomPanel.add(continueButton, gbcBottomPanel);
       gbcBottomPanel.gridx = 1;
       JButton reloadLastSessionButton = LayoutDefinable.defineButtonLayout(Color.WHITE, new Color(44, 62, 80), "Recarregar a última sessão", new Dimension(150, 50));
       gbc.gridx = 0;
       gbc.gridy = 1;
       gbc.gridwidth = 1; // Reset grid width to 1
       gbc.weighty = 1; // Leva o espaço vertical por completo

       gbc.gridx = 1;
       add(bottomPanel, gbc);

       //Faz com que a pagina ao ser iniciada apareça no centro do ecrã
       setLocationRelativeTo(null);
 
	} 
	    
    /**
     * Metodo set
     * Configura o layout e as propriedades básicas do frame fornecido.
     * Este método define o título do frame, maximiza seu estado, define a operação de fechamento padrão,
     * ajusta a cor de fundo e especifica o tipo de layout.
     * 
     * O método é projetado para ser utilizado durante a inicialização de frames que requerem
     * um layout baseado no {@link GridBagLayout}, oferecendo um ponto de partida consistente para a configuração de UI.
     * 
     * @param frame O frame ({@link JFrame}) a ser configurado.
     * @param title O título a ser definido para o frame.
     * 
     * @return Uma nova instância de {@link GridBagConstraints} que pode ser usada para posicionar componentes dentro do frame.
     */

	 private GridBagConstraints setUpPageFrame(JFrame frame, String title) {
	        frame.setTitle(title);
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        frame.setBackground(Color.darkGray);
	        frame.setLayout(new GridBagLayout());
	        return new GridBagConstraints();
	    }
	 
    /**
     * Método get
     * Retorna o botão de continuação utilizado na interface.
     * @return continueButton 
     */
	 public JButton getContinueButton() { 
        return continueButton;
    }

    /**
     * Método get
     * Retorna o campo de texto para entrada da localização do arquivo CSV
     * @return csvFileLocation 
     */
	 public JTextField getCsvFileLocationTextField() {
	        return csvFileLocation;
	}

    /**
     * Método get
     * Retorna o campo de texto para a localização do arquivo onde os dados CSV serão salvos.
     * @return saveFile 
     */
	 public JTextField getCsvSaveFileLocation(){
        return saveFile;
    }

    /**
     * Metodo set
     * Define se o arquivo a ser processado é remoto.
     * @param remoteFile
     */
	 public void setRemoteFile(boolean remoteFile) { 
        isRemoteFile = remoteFile;
    }

}
