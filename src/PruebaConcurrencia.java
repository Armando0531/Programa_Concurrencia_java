import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.*;

class Dato{
	String ans;

	public Dato(String valor) {
		this.ans = valor;
	}
}


class Concurrencia extends JFrame{

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public Concurrencia(){
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Concurrencia");
		gbc.fill=GridBagConstraints.BOTH;
		setVisible(true);

		Dato datos[] = new Dato[10000000];
		for (int i = 0; i < 10000000; i++) {
			int x = (int)(Math.round(Math.random()));
			if (x==1) {
				datos[i]=new Dato("Si");
			}else {
				datos[i]=new Dato("No");
			}
			System.out.println(datos[i].ans);
		}


		JTextArea display = new JTextArea();
	    display.setEditable(false);
	    display.setLineWrap(true);
	    display.setWrapStyleWord(true);
	    JScrollPane scroll = new JScrollPane(display);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(277, 323, 463, 150);

		add(scroll);

		pack();
	}

}

public class PruebaConcurrencia {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Concurrencia();
			}
		});

	}

}