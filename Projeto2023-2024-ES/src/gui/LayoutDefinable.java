package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A interface LayoutDefinable define métodos e constantes para o layout de
 * componentes de interface gráfica.
 */

public interface LayoutDefinable {

	/**
	 * Reinicia as configurações do GridBagConstraints para os valores padrão.
	 *
	 * @param gbc O GridBagConstraints a ser reiniciado.
	 * @return O GridBagConstraints reiniciado.
	 */

	default GridBagConstraints resetGBC(GridBagConstraints gbc) {
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 0, 0);
		return gbc;
	}

	/**
	 * Obtém uma instância de Color com base no nome da cor.
	 *
	 * @param color O nome da cor desejada.
	 * @return A instância de Color correspondente.
	 */

	public static Color getColor(String color) {
        switch (color) {
            case "white":
                return Color.white;
            case "black":
                return Color.black;
            case "red":
                return new Color(130, 0, 0);
            case "green":
                return new Color(0, 88, 0);
            case "blue":
                return new Color(0, 30, 180);
            case "yellow":
                return Color.yellow;
            case "cyan":
                return Color.cyan;
            case "magenta":
                return Color.magenta;
            case "orange":
                return Color.orange;
            case "pink":
                return Color.pink;
            case "gray":
                return new Color(65, 65, 65);
            default:
                return Color.darkGray;
        }
    }

	/**
	 * Configura o layout básico para um JFrame, incluindo o título, a cor de fundo
	 * e o comportamento de fechamento.
	 *
	 * @param title O título do JFrame.
	 * @param frame O JFrame a ser configurado.
	 * @param color A cor de fundo do JFrame.
	 */

	static void basicLayout(String title, JFrame frame, Color color) {
		frame.setTitle(title);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setLayout(new GridBagLayout());
		frame.setBackground(color);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
	}

	/**
	 * Define o layout para um JButton com cores, texto e dimensões específicos.
	 *
	 * @param backgroundColor A cor de fundo do JButton.
	 * @param foregroundColor A cor do texto do JButton.
	 * @param text            O texto do JButton.
	 * @param dimension       As dimensões preferenciais do JButton.
	 * @return O JButton configurado.
	 */

	static JButton defineButtonLayout(Color backgroundColor, Color foregroundColor, String text, Dimension dimension) {
		JButton button = new JButton(text);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.setPreferredSize(dimension);
		return button;
	}

	/**
	 * Define o layout para um JTextField com texto, fonte e cores específicos.
	 *
	 * @param text            O texto inicial do JTextField.
	 * @param fontName        O nome da fonte do JTextField.
	 * @param font            O estilo da fonte do JTextField.
	 * @param size            O tamanho da fonte do JTextField.
	 * @param backgroundColor A cor de fundo do JTextField.
	 * @param foregroundColor A cor do texto do JTextField.
	 * @return O JTextField configurado.
	 */

	static JTextField defineTextFieldLayout(String text, String fontName, int font, int size, Color backgroundColor,
			Color foregroundColor) {
		JTextField textField = new JTextField(text);
		textField.setFont(new Font(fontName, font, size));
		textField.setBackground(backgroundColor);
		textField.setForeground(foregroundColor);
		textField.setEditable(false);
		textField.setOpaque(false);
		return textField;
	}

	/**
	 * Define o layout para um JTextArea com texto, fonte e cores específicos.
	 *
	 * @param text            O texto inicial do JTextArea.
	 * @param fontName        O nome da fonte do JTextArea.
	 * @param font            O estilo da fonte do JTextArea.
	 * @param size            O tamanho da fonte do JTextArea.
	 * @param backgroundColor A cor de fundo do JTextArea.
	 * @param foregroundColor A cor do texto do JTextArea.
	 * @return O JTextArea configurado.
	 */

	static JTextArea defineTextAreaLayout(String text, String fontName, int font, int size, Color backgroundColor,
			Color foregroundColor) {
		JTextArea textArea = new JTextArea(text);
		textArea.setFont(new Font(fontName, font, size));
		textArea.setBackground(backgroundColor);
		textArea.setForeground(foregroundColor);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setLineWrap(false);
		return textArea;
	}

	/**
	 * Define a visibilidade de um JFrame.
	 *
	 * @param jframe    O JFrame cuja visibilidade será configurada.
	 * @param isVisible O valor de visibilidade desejado.
	 */

	static void setVisibility(JFrame jframe, boolean isVisible) {
		jframe.setVisible(isVisible);
	}
}