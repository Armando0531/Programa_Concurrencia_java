import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


class Concurrencia extends JFrame implements ActionListener{
	
	ArrayList<String> datos = new ArrayList<String>();
	JButton start;
	JTextArea indicesSi, indicesNo, numConteoSi, numConteoNo, numPorcentajeSi, numPorcentajeNo;
	JProgressBar pgsBar;

	public Concurrencia(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Concurrencia");
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);

		datos = new ArrayList<String>();
		for (int i = 0; i < 10000000; i++) {
			if ((int)(Math.round(Math.random()))==0) {
				datos.add("No");
			}else {
				datos.add("Si");
			}
			
		}


		JLabel resultadosSi = new JLabel("Resultados Si");
		resultadosSi.setBounds(42, 36, 170, 25);
		add(resultadosSi);

		JLabel resultadosNo = new JLabel("Resultados No");
		resultadosNo.setBounds(290, 36, 170, 25);
		add(resultadosNo);

		JLabel conteoSi = new JLabel("Conteo: ");
		conteoSi.setBounds(42, 75, 90, 25);
		add(conteoSi);

		JLabel conteoNo = new JLabel("Conteo: ");
		conteoNo.setBounds(290, 75, 90, 25);
		add(conteoNo);

		numConteoSi = new JTextArea();
		numConteoSi.setEditable(false);
		numConteoSi.setBounds(90,79,100,25);
		add(numConteoSi);

		numConteoNo = new JTextArea();
		numConteoNo.setEditable(false);
		numConteoNo.setBounds(340,79,100,25);
		add(numConteoNo);

		indicesSi = new JTextArea();
		indicesSi.setEditable(false);
		indicesSi.setLineWrap(true);
		indicesSi.setWrapStyleWord(true);
	    JScrollPane scrollSi = new JScrollPane(indicesSi);
	    scrollSi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollSi.setBounds(42,120,210,320);
	    add(scrollSi);

	    indicesNo = new JTextArea();
		indicesNo.setEditable(false);
		indicesNo.setLineWrap(true);
		indicesNo.setWrapStyleWord(true);
	    JScrollPane scrollNo = new JScrollPane(indicesNo);
	    scrollNo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollNo.setBounds(290,120,210,320);
	    add(scrollNo);
	    
	    pgsBar = new JProgressBar();
	    pgsBar.setBounds(520, 20, 245, 55);
	    add(pgsBar);
	    pgsBar.setValue(0);
	    pgsBar.setStringPainted(true);

	    JLabel porcentajeSi = new JLabel("Porcentaje Si:");
	    porcentajeSi.setBounds(530, 100, 150, 25);
	    add(porcentajeSi);

	    JLabel porcentajeNo = new JLabel("Porcentaje No:");
	    porcentajeNo.setBounds(530, 165, 150, 25);
	    add(porcentajeNo);

	    numPorcentajeSi = new JTextArea();
	    numPorcentajeSi.setEditable(false);
	    numPorcentajeSi.setBounds(640,100,100,25);
		add(numPorcentajeSi);

		numPorcentajeNo = new JTextArea();
	    numPorcentajeNo.setEditable(false);
	    numPorcentajeNo.setBounds(640,165,100,25);
		add(numPorcentajeNo);
		
		start = new JButton("Iniciar");
		start.setBounds(630,250,145,40);
		start.addActionListener(this);
		add(start);

	}

	class MostrarDatos extends Thread{

		public void run() {
			int sz = datos.size();
			int szc = sz/100;
			String x="",y="";
			for (int i = 0; i <sz; i++) {
				if (datos.get(i)=="Si") {
					x+=(i+"\n");
				}else {
					y+=(i+"\n");
				}
				if ((i+1)%szc==0) {
					indicesSi.append(x);
					indicesNo.append(y);
					x="";
					y="";

			}


		}

	}
		
	}
	
	class Histograma extends Thread{

		public void run() {
			int sz = datos.size();
			int szc = sz/100;
			int i = 0;
			while(i<100) {
				if ((indicesSi.getLineCount()+indicesNo.getLineCount())>=((i+1)*szc)) {
					i+=1;
					pgsBar.setValue(i);
				}
				try {
					currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==start) {
			MostrarDatos md = new MostrarDatos();
			md.start();
			Histograma hg = new Histograma();
			hg.start();
		}
		
	
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