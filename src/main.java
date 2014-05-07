import ij.ImagePlus;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.border.LineBorder;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import transformacion.TUmbralado;
import engine.Imagen;
import engine.Objeto;

import java.awt.Toolkit;


public class main {

	public static JFrame frmMeatAnalyzer;

	private ImagePlus im;
	private ImagePlus imTrabajo;

	private JLabel imSalida;

	private JSpinner spUmbral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frmMeatAnalyzer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMeatAnalyzer = new JFrame();
		frmMeatAnalyzer.setIconImage(Toolkit.getDefaultToolkit().getImage("icono.png"));
		frmMeatAnalyzer.setFont(new Font("Verdana", Font.PLAIN, 14));
		frmMeatAnalyzer.setResizable(false);
		frmMeatAnalyzer.setTitle("Meat Analyzer - CAIMMI - ROCHA.");
		int ancho = frmMeatAnalyzer.getToolkit().getScreenSize().width;
		int alto = frmMeatAnalyzer.getToolkit().getScreenSize().height;
		frmMeatAnalyzer.setBounds(0,0,ancho - 100, alto - 100);
		frmMeatAnalyzer.setLocationRelativeTo(null);
		frmMeatAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMeatAnalyzer.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmCargarImgen = new JMenuItem("Cargar Im\u00E1gen");
		mntmCargarImgen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmCargarImgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			cargarImagen();
			}
		});
		mnArchivo.add(mntmCargarImgen);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAcercaDe();
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JPanel panDer = new JPanel();
		panDer.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout fl_panDer = (FlowLayout) panDer.getLayout();
		fl_panDer.setAlignment(FlowLayout.LEADING);
		fl_panDer.setAlignOnBaseline(true);
		frmMeatAnalyzer.getContentPane().add(panDer, BorderLayout.EAST);
		
		spUmbral = new JSpinner();
		spUmbral.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		panDer.add(spUmbral);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			umbralar();
			}
		});
		btnProcesar.setFont(new Font("Tahoma", Font.BOLD, 16));
		panDer.add(btnProcesar);
		
		JButton btnNewButton = new JButton("Resetear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetear();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		panDer.add(btnNewButton);
		
		JPanel panTrabajo = new JPanel();
		panTrabajo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panTrabajo.setBackground(Color.WHITE);
		frmMeatAnalyzer.getContentPane().add(panTrabajo, BorderLayout.CENTER);
		
		imSalida = new JLabel("");
		imSalida.setHorizontalAlignment(SwingConstants.CENTER);
		panTrabajo.add(imSalida);
		

	}

	protected void mostrarAcercaDe() {
		dialogAcercaDe about = new dialogAcercaDe(this);
		about.setVisible(true);
		about.setAlwaysOnTop(true);
		about.setModal(true);
		about.setModalityType(ModalityType.APPLICATION_MODAL);	
	}

	protected void resetear() {
		imTrabajo = im;
		imSalida.setIcon(new ImageIcon(im.getImage()));
	}

	//Método que carga la imagenes a usar.
	protected void cargarImagen() {

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Abrir archivo .bmp");

		//Agregar en un futuro un filtro.
		//fc.setFileFilter(new FiltroBmp());

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//Atenti al file
			File file = fc.getSelectedFile();

			//Seteo la imagen en pantalla
			im = new ImagePlus(file.getAbsolutePath());
			imTrabajo = new ImagePlus(file.getAbsolutePath());
			imSalida.setIcon(new ImageIcon(im.getImage()));

		}
	}
	
	protected void umbralar() {
		Integer valor = (Integer) spUmbral.getValue();
		imTrabajo = Imagen.aplicarTransformacion(im, new TUmbralado(0,valor, 255));
		imSalida.setIcon(new ImageIcon(imTrabajo.getImage()));
		
		Objeto prueba = new Objeto(Imagen.segmentar(imTrabajo));
		
	}
	
}
