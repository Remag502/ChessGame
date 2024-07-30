// Package files are corrupted and missing

//package chessCorrupted;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.util.Scanner;
//import java.awt.Color;
//import javax.swing.JLabel;
//
//public class JFrameMain extends JFrame {
//
//	public static boolean turn; // false is white
//	private JPanel contentPane;
//	private static DrawSquare[][] ds;
//	private static byte currentPosition = -1;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrameMain frame = new JFrameMain();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	
//	
//	public JFrameMain() {
//		setResizable(false);
//		setTitle("Chess");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-300, Toolkit.getDefaultToolkit().getScreenSize().height/2-300, 600, 600);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		
//		ds = new DrawSquare[8][8];
//		contentPane.setLayout(new GridLayout(8, 8, 0, 0));
//		boolean color = false;
//		for(int i = 0; i < 8; i++) {
//			color = !color;
//			for(int j = 0; j < 8; j++) {
//				color = !color;
//				ds[i][j] = new DrawSquare(color);
//				ds[i][j].setPosition((byte) (i * 8 + j));
//				contentPane.add(ds[i][j], i, j);
//			}
//		}
//		
//		ChessTools.setUpBoard(ds);
//		
////		ChessTools.movePiece(ds, (byte) 12, (byte) 28);
//			
//	}
//	
//	public static void setSquare(byte pos) {
//		
//		if(currentPosition != -1) {
//			ChessTools.getSquare(ds, currentPosition).setSelected(false);
//			ChessTools.movePiece(ds, currentPosition, pos);
//			System.out.println("Moving Pieces " + currentPosition + " " +  pos);
//			currentPosition = -1;
//		} else if(ChessTools.getSquare(ds, pos).getPiece() != "") {
//			if(!ChessTools.checkTurn(ds, pos))
//				return;
//			currentPosition = pos;
//			ChessTools.getSquare(ds, currentPosition).setSelected(true);
//		}
//			
//		
//	}
//		
//}
