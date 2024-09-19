package pack_login;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack_counselor.CounselorDAO;
import pack_current.CR_InsertDialog;
import pack_current.CurrentMainUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JPasswordField passwordTextField;
	private boolean loginSuccessful = false;
	public static String loginID;
	// 메인메서드는 주석 처리 후, MiniUI에서 구현.
//	public static void main(String[] args) {
//		try {
//			LoginDialog dialog = new LoginDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
////

	public LoginDialog() {
		// 사이즈 조정을 위해 메인으로 옮김.
		setBounds(100, 100, 895, 613);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("로그인");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(362, 54, 122, 93);
			contentPanel.add(titleLabel);
		}
		{
			JButton loginButton = new JButton("로그인");
			loginButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CounselorDAO dao = new CounselorDAO();
					try {
						if (dao.SearchId(idTextField.getText(), passwordTextField.getText())){
							JOptionPane.showMessageDialog(LoginDialog.this, "로그인에 성공했습니다.");
							// 로그인 성공시 true로 설정.
							loginSuccessful = true;
							loginID = idTextField.getText();
							
							dispose();
							// 로그인한 계정 -> currentMainUI 오른쪽 상단에 표시해야함.
							CurrentMainUI mainUI = new CurrentMainUI();
//							mainUI.setLoginID(loginID);
							
							System.out.println(loginID);
							mainUI.setVisible(true);
							
						} else {
							JOptionPane.showMessageDialog(LoginDialog.this, "로그인에 실패했습니다. 아이디 혹은 비밀번호를 확인해주세요.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			loginButton.setBounds(281, 446, 113, 50);
			contentPanel.add(loginButton);
			loginButton.setActionCommand("OK");
			getRootPane().setDefaultButton(loginButton);
		}
		{
			JButton cancelButton = new JButton("취소");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			cancelButton.setBounds(477, 446, 98, 50);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		idTextField = new JTextField();
		idTextField.setBounds(362, 234, 182, 21);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel IdLabel = new JLabel("아이디");
		IdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		IdLabel.setBounds(290, 234, 57, 15);
		contentPanel.add(IdLabel);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		passwordLabel.setBounds(276, 301, 60, 15);
		contentPanel.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(362, 301, 182, 21);
		contentPanel.add(passwordTextField);
		
		
	}
	
	// 로그인 성공 시, loginUI 창을 CurrentMainUi로 전환함. 
	public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

//	// 로그인 시, 로그인한 아이디 가져오는 메서드 
//	public String getLoginID() {
//		return loginID;
//	}
	
}
