package pack_login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginUI() {
		setTitle("이용자 상담 관리 프로그램");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 제목 라벨 생성 및 위치 설정
		JLabel titleLabel = new JLabel("이용자 상담 관리 프로그램");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		titleLabel.setBounds(200, 200, 700, 50);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel);

		
		// 로그인 버튼 생성 및 위치 설정
		JButton loginButton = new JButton("로그인");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// loginDialog : 로그인 팝업창 생성함. 로그인 버튼 클릭 시, 나타남.
				LoginDialog loginDialog = new LoginDialog();
				
				
				// 팝업 창이 정 가운데 위치하게 하기. 아래 2줄 삽입 시, 가능 // 이 기능은 나중에 다시 구현하기, 요소들 위치가 뒤죽박죽됨.
//				loginDialog.setSize(1000, 700);
//				loginDialog.setLocationRelativeTo(MiniUI.this);
				loginDialog.setModal(true);
				loginDialog.setVisible(true);
				
				// 로그인 성공 시, loginUI 창을 CurrentMainUi로 전환함. 
				if (loginDialog.isLoginSuccessful()) {
					setVisible(false);
				}
			}
		});
		loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		loginButton.setBounds(368, 495, 140, 50); // 위치와 크기 설정
		contentPane.add(loginButton);

		// 회원가입 버튼 생성 및 위치 설정
		JButton signUpButton = new JButton("회원가입");
		signUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 회원가입 JoinUP 다이얼로그 추가 
				JoinUpDialog dialog = new JoinUpDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		signUpButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		signUpButton.setBounds(596, 495, 140, 50); // 위치와 크기 설정
		contentPane.add(signUpButton);
	}
	
	
	
}
